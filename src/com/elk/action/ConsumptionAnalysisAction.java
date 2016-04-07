package com.elk.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elk.entity.Advert;
import com.elk.es.ElasticClient;
import com.elk.es.Script;
import com.elk.service.IIndexService;
import com.elk.utils.DateUtils;
import com.elk.utils.StringUtil;
import com.elk.utils.StringUtils;
import com.elk.utils.TemplateUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @desc消费分析
 * @author rock
 *
 */
@Controller
@RequestMapping("/consumption")
public class ConsumptionAnalysisAction extends BaseAction{
	
	/**
	 * 初始化
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/consumption-analysis")
	public String  init(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		Advert advert = new Advert();
		advert.setAdStartTime(DateUtils.parseDate(DateUtils.formatDate(DateUtils.lastYear())));
		advert.setAdEndTime(DateUtils.parseDate(DateUtils.formatDate(DateUtils.getCurrent())));
		assembleAdvert(request, advert);
		request.setAttribute("adStartTime", DateUtils.formatDate(DateUtils.lastYear()));
		request.setAttribute("adEndTime",DateUtils.formatDate(DateUtils.getCurrent()));
		return "consumption-analysis";
	}
	
	/**
	 * 查找消费分析
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/findAdvert")
	public String findConsumeAnalysis(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		Advert advert = advertTerms(request);
		assembleAdvert(request, advert);
		return "consumption-analysis";
	}
	
	@RequestMapping(value="/pageForm")
	public String  page(HttpServletRequest request,HttpServletResponse response) throws IOException{
			initPage(request,response);
			Advert advert = advertTerms(request);
			if(!StringUtil.isBlank(request.getParameter("page"))){
				advert.setOffset(Integer.parseInt(request.getParameter("offset")));
			}
			assembleAdvert(request, advert);
		return "ad";
	}	
	
	private Advert advertTerms(HttpServletRequest request) {
		Advert advert = new Advert();
		advert.setAdName(request.getParameter("adName")!= null?request.getParameter("adName"):"");
		advert.setAdProject(request.getParameter("adProject"));
		advert.setAdType(request.getParameter("adType"));
		advert.setAdStatus(request.getParameter("adSatus"));
		if(!StringUtils.isBlank(request.getParameter("startDay"))){
			advert.setStartDay(Integer.parseInt(request.getParameter("startDay")));
		}
		if(!StringUtils.isBlank(request.getParameter("endDay"))){
			advert.setEndDay(Integer.parseInt(request.getParameter("endDay")));
		}
		advert.setAdStartTime(DateUtils.parseDate(request.getParameter("adStartTime")));
		advert.setAdEndTime(DateUtils.parseDate(request.getParameter("adEndTime")));
		
		request.setAttribute("adName", request.getParameter("adName"));
		request.setAttribute("adProject", request.getParameter("adProject"));
		request.setAttribute("adType", request.getParameter("adType"));
		request.setAttribute("adSatus", request.getParameter("adSatus"));
		request.setAttribute("startDay", request.getParameter("startDay"));
		request.setAttribute("endDay", request.getParameter("endDay"));
		request.setAttribute("adStartTime", request.getParameter("adStartTime"));
		request.setAttribute("adEndTime", request.getParameter("adEndTime"));
		return advert;
	}
	
	private void assembleAdvert(HttpServletRequest request, Advert advert)
			throws IOException, JsonParseException, JsonMappingException,
			JsonProcessingException {
		int count = 0;
		count = advertService.getAdvertCount(advert);
		advert.setPageSize(10);
		advert.setTotal(count);
		advert.setCurrentTotal(count);
		List<Advert> advertAssortList = advertService.getAdvertList(advert);
		List<Advert> advertStatsList = new ArrayList<Advert>();//分类数值 
		List<Advert> advertNumList = new ArrayList<Advert>();//合计数值
		double registerUsrConsumeAmount = 0;
		double loginUsrConsumeAmount = 0;
		double consumeUsrAmount = 0;
		double consumeUsrDayAmount = 0;
		double consumeUsrAvgAmount = 0;
		double longinUsrAvgAmount = 0;
		String indexType = "";
		if(advert.getAdProject()!=null && !advert.getAdProject().equals("0")){
			indexType = indexService.getIndexTypeByValue(advert.getAdProject());
		}
		for (Advert ad : advertAssortList) {
			String templatePath = Thread.currentThread().getContextClassLoader().getResource("resource/template/es").getPath()+"/consumption-analysis.customcache";
			String content = client.readFile(templatePath);
			Script script = new Script("pay_stats",indexType,ad.getAdStartTime().getTime(),ad.getAdEndTime().getTime(),ad.getAdAddr());
			Map<String, String> resultMap = client.execQuery(content,script);
			resultMap.put("templateName", "consumption-analysis.ftl");
			String data = new TemplateUtil().formatData(resultMap);
			@SuppressWarnings("unchecked")
			Map<String,List<Map<String,Object>>> statsMap = mapper.readValue(data, Map.class);
			for (String key : statsMap.keySet()) {
				for(Map<String, Object> map:statsMap.get(key)){
					JsonNode node = mapper.readTree(mapper.writeValueAsString(map));
					String str = node.get("name").textValue();
					String dataValue = node.get("data").toString().substring(1, node.get("data").toString().length()-1);
					switch(str){
					case "reguserpaycnt":ad.setRegisterUsrConsumeAmount("".equals(dataValue)?0:Double.valueOf(dataValue));//注册用户消费金额
					registerUsrConsumeAmount += "".equals(dataValue)?0:Integer.valueOf(dataValue);
					break;
					case "reguserpayargvcnt":ad.setRegisterUsrDayConsumeAmount("".equals(dataValue)?0:Double.valueOf(dataValue));//注册用户日均消费金额
					break;
					case "loginuserpaycnt":ad.setLoginUsrConsumeAmount("".equals(dataValue)?0:Double.valueOf(dataValue));//登录用户消费金额
					loginUsrConsumeAmount += "".equals(dataValue)?0:Integer.valueOf(dataValue);
					break;
					case "loginuserpayargvcnt":ad.setLoginUsrDayConsumeAmount("".equals(dataValue)?0:Double.valueOf(dataValue));//登录用户日均消费金额
					break;
					case "userpaycnt":ad.setConsumeUsrAmount("".equals(dataValue)?0:Double.valueOf(dataValue));//付费用户消费合计//   注册用户消费金额+登录用户消费金额
					consumeUsrAmount += "".equals(dataValue)?0:Double.valueOf(dataValue);
					break;
					case "userpayargvcnt":ad.setConsumeUsrDayAmount("".equals(dataValue)?0:Double.valueOf(dataValue));//付费用户消费日均消费//  
					consumeUsrDayAmount += "".equals(dataValue)?0:Integer.valueOf(dataValue);
					break;
					case "usepayrarpu":ad.setConsumeUsrAvgAmount("".equals(dataValue)?0:Double.valueOf(dataValue));//付费用户平均付费金额//   注册用户消费金额+登录用户消费金额/payusercnt
					consumeUsrAvgAmount += "".equals(dataValue)?0:Integer.valueOf(dataValue);
					break;
					case "userarpu":ad.setLonginUsrAvgAmount("".equals(dataValue)?0:Double.valueOf(dataValue));//登录用户平均付费金额(ARPU)//
					longinUsrAvgAmount += "".equals(dataValue)?0:Integer.valueOf(dataValue);
					break;
					}
				}
			}
			advertStatsList.add(ad);
		}
		Advert advertStats = new Advert();
		advertStats.setRegisterUsrConsumeAmount(registerUsrConsumeAmount);
		advertStats.setLoginUsrConsumeAmount(loginUsrConsumeAmount);
		advertStats.setConsumeUsrAmount(consumeUsrAmount);
		advertStats.setConsumeUsrDayAmount(consumeUsrDayAmount);
		advertStats.setConsumeUsrAvgAmount(consumeUsrAvgAmount);
		advertStats.setLonginUsrAvgAmount(longinUsrAvgAmount);
		advertNumList.add(advertStats);
		
		request.setAttribute("advertNumList", advertNumList);
		request.setAttribute("advertStatsList", advertStatsList);
		request.setAttribute("page", advert);//分页信息
	}
}
