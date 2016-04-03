
		
package com.elk.es;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
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
public class Bak {

	private static TransportClient client = null;

	private static Logger log = LoggerFactory.getLogger(Bak.class);
	/**
	 * init client instance
	 * @return
	 */
	public  Bak() {
		try {
			InputStream in = new BufferedInputStream (new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/resource/esConfig.properties"));
			Properties properties = new Properties();
			properties.load(in);
			Settings settings = Settings.settingsBuilder().put("cluster.name", properties.get("clusterName")).build();
			 client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(properties.get("esip").toString()), Integer.parseInt(properties.get("esport").toString())));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * close client
	 */
	public void closed() {
		if (null != client) {
			client.close();
			client = null;
		}
	}

	/**
	 * exec template(search,delete,insert....)
	 * @param templatePath
	 * @param searchParam
	 */
	public String readFile(String templatePath){
		StringBuilder strBuffer = new StringBuilder();
			try{ 
				try (BufferedReader bodyReader = new BufferedReader(new InputStreamReader(new FileInputStream(templatePath), "utf8"))) {
					String line = null;
					while ((line = bodyReader.readLine()) != null) {
					strBuffer.append(line);
					}
				}
		} catch (Exception e) {
				log.error(e.getMessage());
		}
			
		return strBuffer.toString();
	}
	
	
	/**
	 * search execQuery
	 * @param source
	 * @param indexName
	 * @return
	 */
	public Map<String,String>  execQuery(String source,String indexName,String esType,String startDate,String endDate){
		Map<String,String> resultMap = new HashMap<String, String>();
		SearchResponse sResponse = null;
		StringBuffer queryBody = new StringBuffer();
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(source);
			if(startDate.equals("undefined") || endDate.equals("undefined") || StringUtil.isBlank(startDate) || StringUtil.isBlank(endDate)){
				queryBody.append(jsonNode.get("query").toString());
			}
			else{
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				long startTime = sdf.parse(sdf.format(format.parse(startDate))).getTime() / 1;
				long endTime   = sdf.parse(sdf.format(format.parse(endDate))).getTime() / 1;
				queryBody.append(setQueryTime(jsonNode.get("query").toString(),startTime,endTime));
			}
			if(!StringUtil.isBlank(jsonNode.get("size").toString())){
				queryBody.append(",\"size\":").append(jsonNode.get("size").toString());
			}
			
		    SearchRequestBuilder srb = client.prepareSearch(indexName).setTypes(esType).setQuery(queryBody.toString()).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		  
		  //  AbstractAggregationBuilder aggsBuilder = JsonUtil.parseJson("aggs", jsonNode.get("aggs"));
		    
			//srb.addAggregation(aggsBuilder);
		//	sResponse = srb.execute().actionGet();
			System.out.println(sResponse.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		resultMap.put("data", sResponse.toString());
		return resultMap;
	}
	
	
	/**
	 * set query scriptBody time
	 * @param queryJson
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String setQueryTime(String queryJson,long startDate,long endDate){
		JSONObject jsonObject = new JSONObject();
		jsonObject = jsonObject.parseObject(queryJson);
		
		JSONArray mustNodeArray = jsonObject.getJSONObject("filtered").getJSONObject("filter").getJSONObject("bool").getJSONArray("must");
		Map<String, Object> timestampMap = JSON.parseObject(mustNodeArray.getJSONObject(0).getJSONObject("range").getJSONObject("@timestamp").toJSONString());
		timestampMap.put("gte", startDate);
		timestampMap.put("lte", endDate);
		
		
		
		Map<String, Object> rangeMap =JSON.parseObject(mustNodeArray.getJSONObject(0).getJSONObject("range").toJSONString());
		rangeMap.put("@timestamp", timestampMap);
		
		
		Map<String, Object> jsonArrayMap = JSON.parseObject(mustNodeArray.getJSONObject(0).toJSONString());
		jsonArrayMap.put("range", rangeMap);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(JSON.toJSON(jsonArrayMap));
		
		Map<String, Object> boolNodeMap = jsonObject.getJSONObject("filtered").getJSONObject("filter").getJSONObject("bool");
		boolNodeMap.put("must", JSON.toJSON(jsonArray));
		
		Map<String, Object> filterMap = jsonObject.getJSONObject("filtered").getJSONObject("filter");
		filterMap.put("bool", JSON.toJSON(boolNodeMap));
		
		Map<String, Object> filteredMap = jsonObject.getJSONObject("filtered");
		filteredMap.put("filter", JSON.toJSON(filterMap));
		
		
		Map<String, Object> jsonMap = jsonObject;
		jsonMap.put("filtered", JSON.toJSON(filteredMap));
		
		return JSON.toJSON(jsonMap).toString();
	}
	public static void main(String[] args) throws ParseException {
		Bak esClient = new Bak();
		String templatePath = Thread.currentThread().getContextClassLoader().getResource("resource/template/es").getPath()+"/flow-analysis.customcache";
		String content = esClient.readFile(templatePath);
		//Map<String, String> resultMap = esClient.execQuery(content,"informatiom_stat", "ad_stat","2016-03-01 00:00:00.000", "2016-03-31 00:00:00.000");
		//String data = new TemplateUtil().formatData(resultMap);
		//System.out.println(data);
	}
}
		
