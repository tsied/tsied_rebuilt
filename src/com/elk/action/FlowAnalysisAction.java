package com.elk.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elk.entity.Advert;
import com.elk.es.Script;
import com.elk.utils.DateUtils;
import com.elk.utils.StringUtil;
import com.elk.utils.TemplateUtil;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @desc 流量分析
 * @author rock
 *
 */
@Controller
@RequestMapping("/flow")
public class FlowAnalysisAction extends BaseAction {

	private static Logger log = LoggerFactory.getLogger(FlowAnalysisAction.class);

	@RequestMapping(value = "/flow-analysis")
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initPage(request, response);
		int count = 0;
		Date initStartDate = DateUtils.daysDiff(-90);
		Date initEndDate = DateUtils.daysDiff(90);

		Advert advert = new Advert();
		advert.setAdStartTime(initStartDate);
		advert.setAdEndTime(initEndDate);
		count = advertService.getAdvertCount(advert);
		advert.setTotal(count);
		advert.setCurrentTotal(count);
		if (!StringUtil.isBlank(request.getParameter("page"))) {
			advert.setOffset(Integer.parseInt(request.getParameter("offset")));
		}
		List<Advert> advertNumList = new ArrayList<Advert>();// 合计及平均数值
		List<Advert> advertAssortList = advertService.getAdvertList(advert);// 获取广告信息
		List<Advert> advertStatsList = new ArrayList<Advert>();// 分类数值
		assembleAdvert(request, request.getParameter("adProject"), advert, advertNumList, advertAssortList, advertStatsList);
		request.setAttribute("adStartTime", DateUtils.formatDate(initStartDate));
		request.setAttribute("adEndTime", DateUtils.formatDate(initEndDate));
		return "flow-analysis";
	}

	@RequestMapping(value = "/findAdvert")
	public String findAdvert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initPage(request, response);
		Advert advert = findAdvertTerms(request);
		int count = 0;
		count = advertService.getAdvertCount(advert);
		advert.setTotal(count);
		advert.setCurrentTotal(count);
		if (!StringUtil.isBlank(request.getParameter("page"))) {
			advert.setOffset(Integer.parseInt(request.getParameter("offset")));
		}
		List<Advert> advertNumList = new ArrayList<Advert>();// 合计及平均数值
		List<Advert> advertAssortList = advertService.getAdvertList(advert);// 获取广告信息
		List<Advert> advertStatsList = new ArrayList<Advert>();
		assembleAdvert(request, request.getParameter("adProject"), advert, advertNumList, advertAssortList, advertStatsList);
		return "flow-analysis";
	}

	private Advert findAdvertTerms(HttpServletRequest request) {
		Advert advert = new Advert();
		advert.setAdName(request.getParameter("adName") != null ? request.getParameter("adName") : "");
		advert.setAdProject(request.getParameter("adProject"));
		advert.setAdType(request.getParameter("adType"));
		advert.setAdStatus(request.getParameter("adSatus"));
		/*
		 * if(!StringUtils.isBlank(request.getParameter("startDay").toString())){
		 * advert
		 * .setStartDay(Integer.parseInt(request.getParameter("startDay"))); }
		 * if(!StringUtils.isBlank(request.getParameter("endDay").toString())){
		 * advert.setEndDay(Integer.parseInt(request.getParameter("endDay"))); }
		 */
//		advert.setAdStartTime(DateUtils.parseDate(request.getParameter("adStartTime")));
//		advert.setAdEndTime(DateUtils.parseDate(request.getParameter("adEndTime")));

		request.setAttribute("adName", request.getParameter("adName"));
		request.setAttribute("adProject", request.getParameter("adProject"));
		request.setAttribute("adType", request.getParameter("adType"));
		request.setAttribute("adSatus", request.getParameter("adSatus"));
		// request.setAttribute("startDay", request.getParameter("startDay"));
		// request.setAttribute("endDay", request.getParameter("endDay"));
		request.setAttribute("adStartTime", request.getParameter("adStartTime"));
		request.setAttribute("adEndTime", request.getParameter("adEndTime"));
		return advert;
	}

	@RequestMapping(value = "/pageForm")
	public String page(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initPage(request, response);
		int count = 0;
		Advert advert = findAdvertTerms(request);
		count = advertService.getAdvertCount(advert);
		if (!StringUtil.isBlank(request.getParameter("page"))) {
			advert.setOffset(Integer.parseInt(request.getParameter("offset")));
		}
		advert.setTotal(count);
		advert.setCurrentTotal(count);
		List<Advert> advertNumList = new ArrayList<Advert>();// 合计及平均数值
		List<Advert> advertAssortList = advertService.getAdvertList(advert);// 分类数值
		List<Advert> advertStatsList = new ArrayList<Advert>();// 分类数值
		assembleAdvert(request, request.getParameter("adProject"), advert, advertNumList, advertAssortList, advertStatsList);
		return "flow-analysis";
	}

	private void assembleAdvert(HttpServletRequest request, String project, Advert advert, List<Advert> advertNumList,
			List<Advert> advertAssortList, List<Advert> advertStatsList) throws IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		String adAddr = "";
		int uvNum = 0;
		int ipNum = 0;
		int sessionNum = 0;
		int avgViewNum = 0;
		int sessionTime = 0;
		double bounceRate = 0.00;
		String indexName = "";

//		for (Advert ad : advertAssortList) {
//			String templatePath = Thread.currentThread().getContextClassLoader().getResource("resource/template/es")
//					.getPath()
//					+ "/flow-analysis.customcache";
//			String content = client.readFile(templatePath);
//			indexName = indexService.getIndexTypeByValue(ad.getAdProject());
//			Script script = new Script(indexName, ad.getAdStartTime().getTime(), ad.getAdEndTime().getTime(),
//					ad.getAdAddr());
//			Map<String, String> resultMap = null;
//			try {
//				resultMap = client.execQuery(content, script);
//			} catch (Exception e) {
//				log.error("ad [" + ad.getAdAddr() + "] hava no data", e);
//				continue;
//			}
//
//			resultMap.put("templateName", "flow-analysis.ftl");
//			String data = new TemplateUtil().formatData(resultMap);
//			@SuppressWarnings("unchecked")
//			Map<String, List<Map<String, Object>>> statsMap = mapper.readValue(data, Map.class);
//			for (String key : statsMap.keySet()) {
//				for (Map<String, Object> map : statsMap.get(key)) {
//					JsonNode node = mapper.readTree(mapper.writeValueAsString(map));
//					String str = node.get("name").textValue();
//					String dataValue = node.get("data").toString()
//							.substring(1, node.get("data").toString().length() - 1);
//					switch (str) {
//					case "uv":
//						ad.setUserViewNum("".equals(dataValue) ? 0 : Integer.valueOf(dataValue));
//						uvNum += "".equals(dataValue) ? 0 : Integer.valueOf(dataValue);
//						break;
//					case "ipStats":
//						ad.setIpViewNum("".equals(dataValue) ? 0 : Integer.valueOf(dataValue));
//						ipNum += "".equals(dataValue) ? 0 : Integer.valueOf(dataValue);
//						break;
//					case "sessionStat":
//						ad.setSessionNum("".equals(dataValue) ? 0 : Integer.valueOf(dataValue));
//						sessionNum += "".equals(dataValue) ? 0 : Integer.valueOf(dataValue);
//						break;
//					case "reqPages":
//						ad.setAvgSessionViewNum("".equals(dataValue) ? 0 : Integer.valueOf(dataValue));
//						avgViewNum += "".equals(dataValue) ? 0 : Integer.valueOf(dataValue);
//						break;
//					case "sessionTime":
//						ad.setAvgSessionDuration("".equals(dataValue) ? "0"
//								: String.valueOf(Integer.valueOf(dataValue) / 1000));
//						sessionTime += "".equals(dataValue) ? 0 : Integer.valueOf(dataValue) / 1000;
//						break;
//					case "bounceSessionCount":
//						ad.setBounceRate("".equals(dataValue) ? "0" : dataValue);
//						bounceRate += "".equals(dataValue) ? 0 : Double.valueOf(dataValue);
//						break;
//					}
//				}
//			}
//			advertStatsList.add(ad);
//		}
		
		List<String> adQueryList = new ArrayList<String>();
		String delimiter = "and";
		for (Advert ad : advertAssortList) {
			if (1 > advertAssortList.size() ) {
				String adQuery = "*";
				adQueryList.add(ad.getAdAddr());
			} else if (1 == advertAssortList.size()) {
				String adQuery = ad.getAdAddr();
				adQueryList.add(ad.getAdAddr());
			} else {
				adQueryList.add("source_url.raw:" + ad.getAdAddr());
//				String adQuery = ad.getAdAddr() + 'and' ;
			}			 					
		}		
		String adQuery = StringUtils.join(adQueryList, " and ");
		
		
		
		String templatePath = Thread.currentThread().getContextClassLoader().getResource("resource/template/es")
				.getPath()
				+ "/flow-analysis.customcache";
		String content = client.readFile(templatePath);
		indexName = indexService.getIndexByValue(Integer.parseInt(project));
		Script script = new Script(indexName, DateUtils.parseDate(request.getParameter("adStartTime")).getTime(), DateUtils.parseDate(request.getParameter("adEndTime")).getTime(),
				adQuery);
		Map<String, String> resultMap = null;
		try {
			resultMap = client.execQuery(content, script);
		} catch (Exception e) {
			log.error("ad [" + adQuery + "] hava no data", e);

		}

		resultMap.put("templateName", "flow-analysis.ftl");
		String data = new TemplateUtil().formatData(resultMap);
		@SuppressWarnings("unchecked")
		Map<String, List<Map<String, Object>>> statsMap = mapper.readValue(data, Map.class);
		/***------------------------------------------------*/
		List<Advert> strlist = new ArrayList<Advert>();
		for (String key : statsMap.keySet()) {
			Advert ad = new Advert();
			for (Map<String, Object> map : statsMap.get(key)) {
				JsonNode node = mapper.readTree(mapper.writeValueAsString(map));
				String str = node.get("name").textValue();
				String dataValue = node.get("data").toString().substring(1, node.get("data").toString().length() - 1);
				String[] stra = dataValue.split(",");
				for(int i=0;i<stra.length;i++){
					Advert adtmp = new Advert();
					strlist.add(adtmp);
				}
			}
		}
		
		/***------------------------------------------------*/
		
		for (String key : statsMap.keySet()) {
			Advert ad = new Advert();
			for (Map<String, Object> map : statsMap.get(key)) {
				JsonNode node = mapper.readTree(mapper.writeValueAsString(map));
				String str = node.get("name").textValue();
				String dataValue = node.get("data").toString()
						.substring(1, node.get("data").toString().length() - 1);
				
				switch (str) {
				case "addomain":
//					ad.setAdAddr(dataValue);
										
					String[] stra = dataValue.split(",");
					for(int i=0;i<stra.length;i++){
						strlist.get(i).setAdAddr(stra[i]);
					}
										
					
//					adAddr = dataValue;
					break;
				case "uv":
					
					String[] strauv = dataValue.split(",");
					for(int i=0;i<strauv.length;i++){
						strlist.get(i).setUserViewNum("".equals(strauv[i]) ? 0 : Integer.valueOf(strauv[i]));
					}
					
//					ad.setUserViewNum("".equals(dataValue) ? 0 : Integer.valueOf(dataValue));
//					uvNum += "".equals(dataValue) ? 0 : Integer.valueOf(dataValue);
					break;
				case "ipStats":
					String[] straip = dataValue.split(",");
					for(int i=0;i<straip.length;i++){
						strlist.get(i).setIpViewNum("".equals(straip[i]) ? 0 : Integer.valueOf(straip[i]));
					}
//					ad.setIpViewNum("".equals(dataValue) ? 0 : Integer.valueOf(dataValue));
//					ipNum += "".equals(dataValue) ? 0 : Integer.valueOf(dataValue);
					break;
				case "sessionStat":
					String[] strasession = dataValue.split(",");
					for(int i=0;i<strasession.length;i++){
						strlist.get(i).setSessionNum("".equals(strasession[i]) ? 0 : Integer.valueOf(strasession[i]));
					}
//					ad.setSessionNum("".equals(dataValue) ? 0 : Integer.valueOf(dataValue));
//					sessionNum += "".equals(dataValue) ? 0 : Integer.valueOf(dataValue);
					break;
				case "reqPages":
					String[] strapages = dataValue.split(",");
					for(int i=0;i<strapages.length;i++){
						strlist.get(i).setAvgSessionViewNum("".equals(strapages[i]) ? 0 : Integer.valueOf(strapages[i]));
					}
//					ad.setAvgSessionViewNum("".equals(dataValue) ? 0 : Integer.valueOf(dataValue));
//					avgViewNum += "".equals(dataValue) ? 0 : Integer.valueOf(dataValue);
					break;
				case "sessionTime":
					String[] strasessiontime = dataValue.split(",");
					for(int i=0;i<strasessiontime.length;i++){
						strlist.get(i).setAvgSessionDuration("".equals(strasessiontime[i]) ? "0" : String.valueOf(Integer.valueOf(strasessiontime[i])  / 1000));
					}
//					ad.setAvgSessionDuration("".equals(dataValue) ? "0"
//							: String.valueOf(Integer.valueOf(dataValue) / 1000));
//					sessionTime += "".equals(dataValue) ? 0 : Integer.valueOf(dataValue) / 1000;
					break;
				case "bounceSessionCount":
					String[] strasessioncount = dataValue.split(",");
					for(int i=0;i<strasessioncount.length;i++){
						strlist.get(i).setBounceRate("".equals(strasessioncount[i]) ? "0" : strasessioncount[i]);
					}
//					ad.setBounceRate("".equals(dataValue) ? "0" : dataValue);
//					bounceRate += "".equals(dataValue) ? 0 : Double.valueOf(dataValue);
					break;
				}
			}
//			advertStatsList.add(ad);
		}
		
		
		Advert advertStats = new Advert();
		advertStats.setUserViewNum(uvNum);
		advertStats.setIpViewNum(ipNum);
		advertStats.setSessionNum(sessionNum);
		advertStats.setAvgSessionViewNum(avgViewNum);
		advertStats.setAvgSessionDuration(sessionTime == 0 ? "0" : String.valueOf(sessionTime));
		advertStats.setBounceRate(String.valueOf(bounceRate));
		advertNumList.add(advertStats);
		request.setAttribute("advertNumList", advertNumList);
		request.setAttribute("advertStatsList", strlist);
		request.setAttribute("page", advert);// 分页信息
	}
}
