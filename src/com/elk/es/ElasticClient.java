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
import java.util.Iterator;
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
import com.elk.utils.DateUtils;
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

	private static TransportClient client = null;

	private static Logger log = LoggerFactory.getLogger(ElasticClient.class);
	
	
	/**
	 * init client instance
	 * @return
	 */
	public  ElasticClient() {
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
			BufferedReader bodyReader = new BufferedReader(new InputStreamReader(new FileInputStream(templatePath), "utf8"));
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
	 * @param source
	 * @param indexName
	 * @return
	 */
	public Map<String,String>  execQuery(String source,Script script){
		Map<String,String> resultMap = new HashMap<String, String>();
		SearchResponse sResponse = null;
		StringBuffer queryBody = new StringBuffer();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(source);
			
			JSONObject jsonObject = JSONObject.parseObject(jsonNode.get("query").toString());

			JSONArray mustNodeArray = jsonObject.getJSONObject("filtered").getJSONObject("filter").getJSONObject("bool").getJSONArray("must");
			for (Iterator<Object> iterator = mustNodeArray.iterator(); iterator.hasNext();) {
				JSONObject jSONObject = (JSONObject) iterator.next();
				if (jSONObject.getJSONObject("range") != null) {
					@SuppressWarnings("unchecked")
					Map<String,String>   timestampMap = mapper.readValue(jSONObject.getJSONObject("range").toJSONString(), Map.class);
					for (String object : timestampMap.keySet()) {
						script.setDateRang(object);
					}
				}

			}
			
			HashMap<String, String> paramMap = new HashMap<String,String>();
			paramMap.put("templateName", "script-body.ftl");
			paramMap.put("data", mapper.writeValueAsString(script));
			String scriptBody = new TemplateUtil().formatData(paramMap);
			queryBody.append(scriptBody);
			
			if(!StringUtil.isBlank(jsonNode.get("size").toString())){
				queryBody.append(",\"size\":").append(jsonNode.get("size").toString());
			}
		    SearchRequestBuilder srb = client.prepareSearch(script.getIndexName()).setTypes(script.getIndexType()).setQuery(queryBody.toString()).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		  
		    AbstractAggregationBuilder aggsBuilder = JsonUtil.parseJson("aggs", jsonNode.get("aggs"),script.getStartTime(),script.getEndTime());
		    
			srb.addAggregation(aggsBuilder);
			sResponse = srb.execute().actionGet();
			System.out.println(sResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		resultMap.put("data", sResponse.toString());
		return resultMap;
	}
	
	
	public static void main(String[] args) throws ParseException {
		ElasticClient esClient = new ElasticClient();
		String templatePath = Thread.currentThread().getContextClassLoader().getResource("resource/template/es").getPath()+"/flow-analysis.customcache";
		String content = esClient.readFile(templatePath);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Script script = new Script("all_stats","ap_main_site",Long.parseLong("1451577600000"),Long.parseLong("1483199999999"),"zqzm.sinyugame.com");
		script.setDateRang("@timestamp");
		long startTime = format.parse("2016-03-01").getTime() / 1;
		//long endTime   = format.parse("2016-03-31").getTime() / 1;
		System.out.println(startTime +"---------------------------"+DateUtils.getTime(format.parse("2016-03-01")));
		Map<String, String> resultMap = esClient.execQuery(content,script);
		resultMap.put("templateName", "flow-analysis.ftl");
		String data = new TemplateUtil().formatData(resultMap);
		System.out.println(data);
	}
}