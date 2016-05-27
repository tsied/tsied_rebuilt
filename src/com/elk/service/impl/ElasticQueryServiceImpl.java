package com.elk.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.avg.AvgBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.elk.entity.Advert;
import com.elk.utils.DateUtils;

/**
 * Elasticsearch 查询逻辑层
 * 
 * @author kerl
 *
 */
@Service("elasticQueryService")
public class ElasticQueryServiceImpl {

	private static Logger log = LoggerFactory.getLogger(ElasticQueryServiceImpl.class);

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	/**
	 * 流量数据查询
	 * 
	 * @param esIndexName
	 * @param adStartTime
	 * @param adEndTime
	 * @param advertAssortList
	 * @return
	 */
	public Map<String, Object> flowRateQuery(String esIndexName, Date adStartTime, Date adEndTime,
			List<Advert> advertAssortList) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<Advert> hitResult = new ArrayList<Advert>();
		String sourceFiled = "source_url.raw";
		String delimiter = " and ";
		String adQuery = "";

		Map<String, Advert> adAdressMapping = new HashMap<String, Advert>();
		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
		for (int i = 0; i < advertAssortList.size(); i++) {
			adQuery += i == 0 ? sourceFiled + ":" + advertAssortList.get(i).getAdAddr() : delimiter
					+ advertAssortList.get(i).getAdAddr();
			adAdressMapping.put(advertAssortList.get(i).getAdAddr(), advertAssortList.get(i));

			boolQueryBuilder.should(termQuery(sourceFiled, advertAssortList.get(i).getAdAddr()));
		}
		// QueryBuilder qb = queryStringQuery(StringUtils.isBlank(adQuery) ? "*"
		// : adQuery);

		// es search query
		SearchRequestBuilder request = elasticsearchTemplate
				.getClient()
				.prepareSearch(esIndexName)
				.setQuery(
						rangeQuery("statsDate").from(DateUtils.formatEsTime(adStartTime.getTime())).to(
								DateUtils.formatEsTime(adEndTime.getTime())))
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		request.setQuery(boolQueryBuilder);

		// SouceTerm Aggs
		String souceTermAggs = "sourceUrlAggs";
		TermsBuilder souceTermAggsBuilder = AggregationBuilders.terms(souceTermAggs).field(sourceFiled).size(0);
		request.addAggregation(souceTermAggsBuilder);

		// PV
		String pvAggs = "pvAggs";
		String pvField = "pv";
		SumBuilder pvAggsBuild = AggregationBuilders.sum(pvAggs).field(pvField);
		souceTermAggsBuilder.subAggregation(pvAggsBuild);

		// UV
		String uvAggs = "uvAggs";
		String uvField = "uv";
		SumBuilder uvAggsBuild = AggregationBuilders.sum(uvAggs).field(uvField);
		souceTermAggsBuilder.subAggregation(uvAggsBuild);

		// ipStats
		String ipStatsAggs = "ipStatsAggs";
		String ipStatsField = "ipStats";
		SumBuilder ipStatsAggsBuild = AggregationBuilders.sum(ipStatsAggs).field(ipStatsField);
		souceTermAggsBuilder.subAggregation(ipStatsAggsBuild);

		// sessionStat
		String sessionStatAggs = "sessionStatAggs";
		String sessionStatField = "sessionStat";
		SumBuilder sessionStatAggsBuild = AggregationBuilders.sum(sessionStatAggs).field(sessionStatField);
		souceTermAggsBuilder.subAggregation(sessionStatAggsBuild);

		// sessionTime
		String sessionTimeAggs = "sessionTimeAggs";
		String sessionTimeField = "sessionTime";
		AvgBuilder sessionTimeAggsBuild = AggregationBuilders.avg(sessionTimeAggs).field(sessionTimeField);
		souceTermAggsBuilder.subAggregation(sessionTimeAggsBuild);

		// reqPages
		String reqPagesAggs = "reqPagesAggs";
		String reqPagesField = "reqPages";
		AvgBuilder reqPagesAggsBuild = AggregationBuilders.avg(reqPagesAggs).field(reqPagesField);
		souceTermAggsBuilder.subAggregation(reqPagesAggsBuild);

		// exitSessionCount
		String exitSessionCountAggs = "exitSessionCountAggs";
		String exitSessionCountField = "exitSessionCount";
		SumBuilder exitSessionCountAggsBuild = AggregationBuilders.sum(exitSessionCountAggs).field(
				exitSessionCountField);
		souceTermAggsBuilder.subAggregation(exitSessionCountAggsBuild);

		// bounceSessionCount
		String bounceSessionCountAggs = "bounceSessionCountAggs";
		String bounceSessionCountField = "bounceSessionCount";
		SumBuilder bounceSessionCountAggsBuild = AggregationBuilders.sum(bounceSessionCountAggs).field(
				bounceSessionCountField);
		souceTermAggsBuilder.subAggregation(bounceSessionCountAggsBuild);

		log.debug(request.toString());

		SearchResponse response = request.execute().actionGet();

		log.debug(response.toString());

		// result handle
		Double sumPv = 0.0;
		Double sumUv = 0.0;
		Double sumIpStats = 0.0;
		Double sumSessionStat = 0.0;
		Double sumSessionTime = 0.0;
		Double sumReqPages = 0.0;
		Double sumExitSessionCount = 0.0;
		Double sumBounceSessionCount = 0.0;

		Terms souceTerms = response.getAggregations().get(souceTermAggs);
		for (Terms.Bucket souceTerm : souceTerms.getBuckets()) {

			String adAddr = (String) souceTerm.getKey();
			Advert ad = adAdressMapping.get(adAddr);
			if (ad == null) {
				continue;
			}
			Sum sumPvAggs = souceTerm.getAggregations().get(pvAggs);
			Sum sumUvAggs = souceTerm.getAggregations().get(uvAggs);
			Sum sumIpStatsAggs = souceTerm.getAggregations().get(ipStatsAggs);
			Sum sumSessionStatAggs = souceTerm.getAggregations().get(sessionStatAggs);
			Avg avgSessionTimeAggs = souceTerm.getAggregations().get(sessionTimeAggs);
			Avg avgReqPagesAggs = souceTerm.getAggregations().get(reqPagesAggs);
			Sum sumExitSessionCountAggs = souceTerm.getAggregations().get(exitSessionCountAggs);
			Sum sumBounceSessionCountAggs = souceTerm.getAggregations().get(bounceSessionCountAggs);

			ad.setClickNum(Double.valueOf(sumPvAggs.value()).intValue());
			ad.setUserViewNum(Double.valueOf(sumUvAggs.value()).intValue());
			ad.setIpViewNum(Double.valueOf(sumIpStatsAggs.value()).intValue());

			Integer sessionNum = Double.valueOf(sumSessionStatAggs.getValue()).intValue();
			ad.setSessionNum(sessionNum);

			Double reqPages = avgReqPagesAggs.getValue();
			ad.setAvgSessionViewNum(reqPages == null || reqPages.equals(Double.NaN) ? 0 : reqPages.intValue());

			Double sessiontime = avgSessionTimeAggs.getValue();
			ad.setAvgSessionDuration(sessiontime == null || sessiontime.equals(Double.NaN) ? "0.0" : String.format(
					"%.0f", sessiontime / 1000));

			Double bounceSessionCount = sumBounceSessionCountAggs.getValue();
			ad.setBounceRate(sessionNum == 0 ? "0" : String.format("%.4f", (bounceSessionCount / sessionNum)));
			hitResult.add(ad);

			sumPv += sumPvAggs.value();
			sumUv += sumUvAggs.value();
			sumIpStats += sumIpStatsAggs.value();
			sumSessionStat += sumSessionStatAggs.value();
			sumSessionTime += (sessiontime == null || sessiontime.equals(Double.NaN)) ? 0.0 : sessiontime;
			sumReqPages += (reqPages == null || reqPages.equals(Double.NaN)) ? 0.0 : reqPages;
			sumExitSessionCount += sumExitSessionCountAggs.value();
			sumBounceSessionCount += sumBounceSessionCountAggs.value();
		}

		result.put("hitResult", hitResult);
		result.put("sumPv", sumPv);
		result.put("sumUv", sumUv);
		result.put("sumIpStats", sumIpStats);
		result.put("sumSessionStat", sumSessionStat);
		result.put("avgSessionTime", String.format("%.0f", sumSessionTime / (souceTerms.getBuckets().size() * 1000)));
		result.put("avgReqPages", String.format("%.2f", sumReqPages / souceTerms.getBuckets().size()));
		result.put("sumExitSessionCount", sumExitSessionCount);
		result.put("sumBounceSessionCount", sumBounceSessionCount);

		result.put("bounceRate",
				String.format("%.4f", sumSessionStat == 0 ? 0.0 : sumBounceSessionCount / sumSessionStat));

		return result;
	}
}
