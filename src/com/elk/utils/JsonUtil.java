package com.elk.utils;

import java.util.Map;

import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService.ScriptType;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * <pre>
 * Author     Version       Date        Changes
 * rock      1.0           2016年3月1日     Created
 * </pre>
 */

public class JsonUtil {
	
	private static Logger log = LoggerFactory.getLogger(JsonUtil.class);
	
	public static AbstractAggregationBuilder parseJson(String parentKey, JsonNode jsonNode,long startDate,long endDate) {
		AbstractAggregationBuilder aggsBuilder = null;

		try {

			Map<String, Object> node = JSON.parseObject(jsonNode.toString(), new TypeReference<Map<String, Object>>() {});
			for (String str : node.keySet()) {

				switch (str) {
				case "aggs":
					break;
				case "terms":
					TermsBuilder termsBuilder = AggregationBuilders.terms(parentKey);
					if (jsonNode.get("terms").get("field") != null) {
						termsBuilder.field(jsonNode.get("terms").get("field").textValue());
					}
					if (jsonNode.get("terms").get("size") != null) {
						termsBuilder.size(Integer.parseInt(jsonNode.get("terms").get("size").toString()));
					}
					if (jsonNode.get("terms").get("min_doc_count") != null) {
						termsBuilder
								.minDocCount(Long.parseLong(jsonNode.get("terms").get("min_doc_count").textValue()));
					}
					
					aggsBuilder = termsBuilder;
					if (jsonNode.get("aggs") != null) {
						for (String str2 : JSON.parseObject(jsonNode.get("aggs").toString(),
								new TypeReference<Map<String, Object>>() {
								}).keySet()) {
							termsBuilder.subAggregation(parseJson(str2, jsonNode.get("aggs").get(str2),startDate,endDate));
						}

					}
					break;
				case "date_histogram":
					DateHistogramBuilder dateTermsBuilder = null;
					String interval = jsonNode.get("date_histogram").get("interval").textValue();
					if (interval.equals("1d")) {
						dateTermsBuilder = AggregationBuilders.dateHistogram(parentKey).interval(
								DateHistogramInterval.DAY);
					} else if (interval.equals("1M")) {
						dateTermsBuilder = AggregationBuilders.dateHistogram(parentKey).interval(
								DateHistogramInterval.MONTH);
					}

					if (jsonNode.get("date_histogram").get("field") != null) {
						dateTermsBuilder.field(jsonNode.get("date_histogram").get("field").textValue());
					}
					if (jsonNode.get("date_histogram").get("min_doc_count") != null) {
						dateTermsBuilder.minDocCount(Integer.parseInt(jsonNode.get("date_histogram")
								.get("min_doc_count").toString()));
					}
					if (jsonNode.get("date_histogram").get("extended_bounds") != null) {
						if (jsonNode.get("date_histogram").get("extended_bounds").get("min") != null
								&& jsonNode.get("date_histogram").get("extended_bounds").get("max") != null) {
							dateTermsBuilder.extendedBounds(startDate,endDate);
						}
					}

					if (jsonNode.get("date_histogram").get("time_zone") != null) {
						dateTermsBuilder.timeZone(jsonNode.get("date_histogram").get("time_zone").textValue());
					}
					aggsBuilder = dateTermsBuilder;
					if (jsonNode.get("aggs") != null) {
						for (String str2 : JSON.parseObject(jsonNode.get("aggs").toString(),
								new TypeReference<Map<String, Object>>() {
								}).keySet()) {
							dateTermsBuilder.subAggregation(parseJson(str2, jsonNode.get("aggs").get(str2),startDate,endDate));
						}
					}

					break;
				case "sum":
					SumBuilder sumBuilder = AggregationBuilders.sum(parentKey);
					if (jsonNode.get("sum").get("script") != null) {
						String scripts = jsonNode.get("sum").get("script").textValue();

						sumBuilder.script(new Script(scripts, ScriptType.INLINE, "expression", null));
					}
					if (jsonNode.get("sum").get("field") != null) {
						sumBuilder.field(jsonNode.get("sum").get("field").textValue());
					}
					aggsBuilder = sumBuilder;
					break;
				case "min":
					aggsBuilder = AggregationBuilders.min(parentKey)
							.field(jsonNode.get("min").get("field").textValue());
					break;
				case "max":
					aggsBuilder = AggregationBuilders.max(parentKey)
							.field(jsonNode.get("max").get("field").textValue());
					break;
				case "avg":
					AvgBuilder avgBuilder = AggregationBuilders.avg(parentKey);
					if (jsonNode.get("avg").get("script") != null) {
						String scripts = jsonNode.get("avg").get("script").textValue();
						avgBuilder.script(new Script(scripts, ScriptType.INLINE, "expression", null));
					}
					if (jsonNode.get("avg").get("field") != null) {
						avgBuilder.field(jsonNode.get("avg").get("field").textValue());
					}
					aggsBuilder = avgBuilder;
					break;
				case "cardinality":
					aggsBuilder = AggregationBuilders.cardinality(parentKey).field(
							jsonNode.get("cardinality").get("field").textValue());
					break;
				case "percentiles":
					String[] percentilesStr = jsonNode.get("percentiles").get("percents").textValue().split(",");
					double[] percentiles = new double[] {};
					for (int i = 0; i < percentilesStr.length; i++) {
						percentiles[i] = Double.parseDouble(percentilesStr[i]);
					}
					aggsBuilder = AggregationBuilders.percentiles(parentKey)
							.field(jsonNode.get("percentiles").get("field").textValue()).percentiles(percentiles);
					break;
				default:
					aggsBuilder = parseJson(str, jsonNode.get(str),startDate,endDate);
					break;
				}
			}
		} catch (Exception e) {
			log.error("Construcate aggregation failed", e);

		}

		return aggsBuilder;
	}
	
}
