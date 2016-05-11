package com.elk.es;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.elk.utils.JsonUtil;
import com.elk.utils.StringUtil;
import com.elk.utils.TemplateUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Rock
 *
 */
@Component
public class ElasticClient {

	private static Logger log = LoggerFactory.getLogger(ElasticClient.class);

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	/**
	 * exec template(search,delete,insert....)
	 * 
	 * @param templatePath
	 * @param searchParam
	 */
	public String readFile(String templatePath) {
		StringBuilder strBuffer = new StringBuilder();
		try {
			BufferedReader bodyReader = new BufferedReader(new InputStreamReader(new FileInputStream(templatePath),
					"utf8"));
			String line = null;
			while ((line = bodyReader.readLine()) != null) {
				strBuffer.append(line);
			}
			bodyReader.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return strBuffer.toString();
	}

	/**
	 * search execQuery
	 * 
	 * @param source
	 * @param indexName
	 * @return
	 */
	public Map<String, String> execQuery(String source, Script script) {
		Map<String, String> resultMap = new HashMap<String, String>();
		SearchResponse sResponse = null;
		StringBuffer queryBody = new StringBuffer();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(source);

			JSONObject jsonObject = JSONObject.parseObject(jsonNode.get("query").toString());

			JSONArray mustNodeArray = jsonObject.getJSONObject("filtered").getJSONObject("filter")
					.getJSONObject("bool").getJSONArray("must");
			for (Iterator<Object> iterator = mustNodeArray.iterator(); iterator.hasNext();) {
				JSONObject jSONObject = (JSONObject) iterator.next();
				if (jSONObject.getJSONObject("range") != null) {
					@SuppressWarnings("unchecked")
					Map<String, String> timestampMap = mapper.readValue(jSONObject.getJSONObject("range")
							.toJSONString(), Map.class);
					for (String dateRange : timestampMap.keySet()) {
						script.setDateRange(dateRange);
					}
				}
			}
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("templateName", "script-body.ftl");
			paramMap.put("data", mapper.writeValueAsString(script));
			String scriptBody = new TemplateUtil().formatData(paramMap);
			queryBody.append(scriptBody);

			if (!StringUtil.isBlank(jsonNode.get("size").toString())) {
				queryBody.append(",\"size\":").append(jsonNode.get("size").toString());
			}
			SearchRequestBuilder srb = elasticsearchTemplate.getClient().prepareSearch(script.getIndexName())
					.setQuery(queryBody.toString()).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

			AbstractAggregationBuilder aggsBuilder = JsonUtil.parseJson("aggs", jsonNode.get("aggs"),
					script.getStartTime(), script.getEndTime());

			srb.addAggregation(aggsBuilder);
			log.debug(srb.toString());
			sResponse = srb.execute().actionGet();
			log.debug(sResponse.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		resultMap.put("data", sResponse.toString());
		return resultMap;
	}

}