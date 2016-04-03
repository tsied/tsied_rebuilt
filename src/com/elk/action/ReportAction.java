package com.elk.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elk.cache.RedisService;
import com.elk.entity.ChartType;
import com.elk.entity.ESTemplate;
import com.elk.entity.Script;
import com.elk.es.ElasticClient;
import com.elk.service.IScriptService;
import com.elk.utils.StringUtil;
import com.elk.utils.TemplateUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * Author     Version       Date        Changes
 * rock      1.0           2016年3月1日     Created
 * </pre>
 */

@Controller
@RequestMapping("/front")
public class ReportAction {

	@Resource
	private IScriptService scriptService;

	@Autowired
	private ElasticClient client;

	@Resource
	private RedisService redisService;


	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 根据时间段，模板名称从缓存中获取数据，缓存拿不到数据时从ElasticSearch拉取
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @param templateName
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/report", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody JsonNode report(HttpServletRequest request,HttpServletResponse response,@RequestParam("templateName") String templateName,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) throws JsonProcessingException, IOException {
		JsonNode node = null;
		String data = null;
			request.setAttribute("startDate", request.getParameter("startDate"));
			request.setAttribute("endDate", request.getParameter("endDate"));

			/**
			 * 缓存数据为空时，从ES抓取并存入到缓存中
			 */
			// if(StringUtil.isBlank(redisService.getDataByTemplateName(indexName,
			// templateName))){
			String templatePath = Thread.currentThread().getContextClassLoader().getResource("resource/template/es").getPath()+ templateName;
			String content = client.readFile(templatePath);
			/*Script script = new Script();
			script.setIndexName("informatiom_stat");
			script.setIndexType("ad_stat");
			script.setStartDate("2016-03-01 00:00:00.000");
			script.setEndDate("2016-03-31 00:00:00.000");*/
		//	Map<String, String> resultMap = client.execQuery(content,request.getParameter("esIndex"), request.getParameter("esType"),startDate, endDate);
			//data = new TemplateUtil().formatData(resultMap);
			// redisService.putESData(indexName,templateName,data);
			// }
			/*
			 * else{
			 * 
			 * data =
			 * redisService.getDataByTemplateName(indexName,templateName); }
			 */
			node = mapper.readTree(data);
			Map<String, Object> covertMap = mapper.readValue(node.toString(),Map.class);
			covertMap.put("divId", request.getParameter("divId"));
			covertMap.put("chartName",new String(request.getParameter("chartName").getBytes("ISO-8859-1"), "UTF-8"));
			covertMap.put("chartType", request.getParameter("chartType"));
			node = mapper.readTree(mapper.writeValueAsString(covertMap));
		return node;
	}

	/**
	 * 根据时间段搜索数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping(value="/indexReport")
	public  String  queryDataByDate(HttpServletRequest request,HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException{
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		request.setAttribute("access", "notFirst");
		
		
		if (StringUtil.isBlank(request.getParameter("chartName"))
				&& StringUtil.isBlank(request.getParameter("esIndex"))
				&& StringUtil.isBlank(request.getParameter("eSType"))
				&& StringUtil.isBlank(request.getParameter("scriptName"))
				&& StringUtil.isBlank(request.getParameter("chartType"))
				&& StringUtil.isBlank(request.getParameter("divId"))) {
			List<Script> scriptList = scriptService.getScriptList();
			request.setAttribute("scriptList", scriptList);
		}
		else{
			Script script = new Script();
			if(!StringUtil.isBlank(request.getParameter("chartName"))){
				script.setChartName(new String(request.getParameter("chartName").getBytes("ISO-8859-1"), "UTF-8"));
			}
			script.setEsIndex(request.getParameter("esIndex"));
			script.seteSType(request.getParameter("eSType"));
			script.setScriptName(request.getParameter("scriptName"));
			script.setChartType(request.getParameter("chartType"));
			script.setDivId(request.getParameter("divId"));
			
			List<Script> scriptList = new ArrayList<Script>();
			Script returnScript = scriptService.getScriptByScript(script);
			scriptList.add(returnScript);
			request.setAttribute("scriptList", scriptList);
		}
		return "indexReport";
	}

	/**
	 * 脚本配置初始化
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/initScriptConfig")
	public String initScriptConfig(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		List<ESTemplate> templateList = new ArrayList<ESTemplate>();
		List<ChartType> chartTypeList = new ArrayList<ChartType>();

		Map<Object, String> templateMap = redisService.getESTemplateName();
		Map<Object, String> chartTypeMap = redisService.getReportTemplateName();

		for (Object key : templateMap.keySet()) {
			ESTemplate template = new ESTemplate();
			template.setTemplateName(key.toString());
			template.setTemplateSource(templateMap.get(key));
			templateList.add(template);
		}

		for (Object key : chartTypeMap.keySet()) {
			ChartType chartType = new ChartType();
			chartType.setChartJsp(key.toString());
			chartType.setChatType(chartTypeMap.get(key));
			chartTypeList.add(chartType);
		}
		request.setAttribute("templateList", templateList);
		request.setAttribute("chartTypeList", chartTypeList);
		return "scriptConfig";
	}

	/**
	 * 脚本配置
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/scriptConfig", method = RequestMethod.POST)
	public String scriptConfig(HttpServletRequest request)
			throws JsonProcessingException {
		Script script = new Script();
		script.setEsIndex(request.getParameter("esIndex"));
		script.seteSType(request.getParameter("eSType"));
		script.setScriptName(request.getParameter("scriptName"));
		script.setChartType(request.getParameter("chartType"));
		script.setDivId(StringUtil.getRandomString(5));// DIV
		script.setChartName(request.getParameter("chartName"));
		scriptService.saveScript(script);
		// redisService.setScript(mapper.writeValueAsString(script));

		List<Script> scriptList = scriptService.getScriptList();
		request.setAttribute("scriptList", scriptList);
		return "indexReport";
	}
}
