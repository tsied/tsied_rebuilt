package com.elk.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elk.entity.Advert;
import com.elk.entity.Domain;
import com.elk.entity.HouseholdData;
import com.elk.entity.ReportIndex;
import com.elk.es.Script;
import com.elk.utils.DateUtils;
import com.elk.utils.TemplateUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;

/**
 * @date 2016-03-23
 * @author rock
 * @desc 项目数据统计
 */
@Controller
@RequestMapping("/project")
public class ProjectsDataAction extends BaseAction{
	
	private static Logger log = LoggerFactory.getLogger(ProjectsDataAction.class);
	
	/**
	 * 初始化项目数据统计
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/projects-data")
	public String  initProjectDataStats(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		return "projects-data";
	}
	
	/**
	 * 初始化内部指标报表
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/project-data")
	public String  innerIndexReport(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		return "project-data";
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
	
	/**
	 * 项目数据统计
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/find-projects-data", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode projectDataStats(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		System.out.println(request.getParameter("adProject"));
		System.out.println(request.getParameter("projectType"));
		Advert advert = new Advert();
		advert.setAdProject(request.getParameter("adProject"));
		advert.setAdType(request.getParameter("projectType"));
		advert.setStartDate(request.getParameter("startDate"));
		advert.setEndDate(request.getParameter("endDate"));		
		request.setAttribute("adProject", request.getParameter("adProject"));
		request.setAttribute("projectType", request.getParameter("projectType"));
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		
		Domain domains = findDomainTerms(request);
		List<Domain> domainAssortList = domainService.getDomainList(domains);// 分类数值
		
		List<String> domainQueryList = new ArrayList<String>();
		for (Domain domain : domainAssortList) {
			if (1 > domainAssortList.size() ) {
				String domainQuery = "*";
				domainQueryList.add(domain.getDomainName());
			} else if (1 == domainAssortList.size()) {
				String domainQuery = domain.getDomainName();
				domainQueryList.add(domain.getDomainName());
			} else {
				domainQueryList.add("{\"term\" : {\"source_url.raw\" : \"" + domain.getDomainName() + "\"}}");
//				String adQuery = ad.getAdAddr() + 'and' ;
			}			 					
		}		
		String addomainQuery = StringUtils.join(domainQueryList, ",");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long startTime = format.parse(request.getParameter("startDate")).getTime();
		long endTime = format.parse(request.getParameter("endDate")).getTime();
		
		List<ReportIndex> reportIndexList = new ArrayList<ReportIndex>();
		
		List<HouseholdData> householdList = new ArrayList<HouseholdData>();// 门户咨询数据
		
		String householdTemplatePath = Thread.currentThread().getContextClassLoader()
				.getResource("resource/template/es").getPath()
				+ "/household-data.customcache";
		String userContent = client.readFile(householdTemplatePath);
		
		Map<String, String> usrMap =null;
		
		if (Integer.valueOf(request.getParameter("adProject")) == 5){
			usrMap = client.execQuery(userContent, new Script("all_game_stats", "game", startTime, endTime));
		}else if (Integer.valueOf(request.getParameter("adProject")) == 7){
			usrMap = client.execQuery(userContent, new Script("all_stats", "ap_main_site", startTime, endTime));
		}else {
			usrMap = client.execQuery(userContent, new Script("all_news_stats", "ap_news_site", startTime, endTime));
		}		
				
		usrMap.put("templateName", "household-data.ftl");
		String data = new TemplateUtil().formatData(usrMap);
//		Map<String, List<Map<String, Object>>> statsMap = mapper.readValue(data, Map.class);
		
		
		
		
		// 新增注册用户数统计
		String registerNewUserTemplatePath = Thread.currentThread()
				.getContextClassLoader().getResource("resource/template/es")
				.getPath()
				+ "/register-loginuser-user.customcache";
		String newUserContent = client.readFile(registerNewUserTemplatePath);
		Map<String, String> newRegUsrMap = null;
		if (Integer.valueOf(request.getParameter("adProject")) == 5){
			newRegUsrMap = client.execQuery(newUserContent, new Script("pay_game_stats", "	pay_game_stats", startTime, endTime));
		}else if (Integer.valueOf(request.getParameter("adProject")) == 7){
			newRegUsrMap = client.execQuery(newUserContent, new Script("pay_stats", "pay_stats", startTime, endTime));
		}else {
			newRegUsrMap = client.execQuery(newUserContent, new Script("pay_news_stats", "pay_news_stats", startTime, endTime));
		}	

		newRegUsrMap.put("templateName", "register-loginuser-user.ftl");
		String newRegUserData = new TemplateUtil().formatData(newRegUsrMap);
		List<Map<String, Object>> newRegUseList = mapper.readValue(mapper.readTree(newRegUserData).get("series").toString(), List.class);

		List<Map<String, Object>> seriesList = mapper.readValue(mapper.readTree(data).get("series").toString(), List.class);
		
		for (Map<String, Object> map : newRegUseList) {// 将注册用户数模板返回的数据与门户咨询获取的会话数、PV,UV组合在一起
			seriesList.add(map);
		}
		
		int ipViewNum = 0;
		int uvNum = 0;
		int pvNum = 0;
		int sessionNum = 0;
		int bounceNum = 0;
		int avgSessionTime = 0;
		int reqPageNum = 0;
		int newRegisterUserNum = 0;
		int registerUserNum = 0;
		int loginUserNum = 0;
		
		for (int i = 0; i < seriesList.size(); i++) {
			Map<String, Object> map = seriesList.get(i);
			ReportIndex index = new ReportIndex();
			for (String key : map.keySet()) {
				switch (map.get(key).toString()) {
				case "pv":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> pvStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : pvStats) {
						pvNum += integer;
					}

					break;
				case "uv":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> uvStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : uvStats) {
						uvNum += integer;
					}
					break;
				case "独立IP访问量":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> ipStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : ipStats) {
						ipViewNum += integer;
					}
					break;
				case "会话数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> sessionStats = mapper.readValue(mapper.writeValueAsString(map.get("data")),
							List.class);
					for (Integer integer : sessionStats) {
						sessionNum += integer;
					}
					break;
				case "跳出率":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> bounceStats = mapper
							.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : bounceStats) {
						bounceNum += integer;
					}
					break;
				case "平均会话时长":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> sessionTime = mapper
							.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : sessionTime) {
						avgSessionTime += integer;
					}
					break;
				case "平均每次会话浏览页数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> avgReqPage = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : avgReqPage) {
						reqPageNum += integer;
					}
					break;
				case "结束日期注册用户总数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> newRegisterUserList = mapper.readValue(mapper.writeValueAsString(map.get("data")),
							List.class);
					registerUserNum = newRegisterUserList.get(newRegisterUserList.size() - 1);
					break;
				case "登录用户数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> loginUser = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : loginUser) {
						loginUserNum += integer;
					}
					break;
				case "新增注册用户数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> newRegUser = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : newRegUser) {
						newRegisterUserNum += integer;
					}
					break;
				}
				
			}
		}
		
		HouseholdData household = new HouseholdData();
		household.setIpViewNum(ipViewNum);
		household.setNewRegisterUserNum(newRegisterUserNum);
		household.setPageViewNum(pvNum);
		household.setRegisterUserNum(registerUserNum);
		household.setSessionNum(sessionNum);
		household.setUserViewNum(uvNum);
		household.setBounceNum(bounceNum);
		household.setAvgSessionTime(avgSessionTime);
		household.setReqPageNum(reqPageNum);
		household.setLoginUserNum(loginUserNum);
//		householdList.add(household);
		
		
		String templatePath = Thread.currentThread().getContextClassLoader().getResource("resource/template/es")
				.getPath()
				+ "/household-data.customcache";
		String content = client.readFile(templatePath);

		Script script = null;
		if (Integer.valueOf(request.getParameter("adProject")) == 5){
//			usrMap = client.execQuery(userContent, new Script("all_game_stats", "game", startTime, endTime));
			script = new Script("all_game_stats", "game", startTime, endTime, addomainQuery);
		}else if (Integer.valueOf(request.getParameter("adProject")) == 7){
			script = new Script("all_stats", "ap_main_site", startTime, endTime, addomainQuery);
		}else {
			script = new Script("all_news_stats", "ap_news_site", startTime, endTime, addomainQuery);
		}	
		
		Map<String, String> resultMap = null;
		try {
			resultMap = client.execQuery(content, script);
		} catch (Exception e) {
			log.error("ad [" + addomainQuery + "] hava no data", e);

		}
		
		resultMap.put("templateName", "household-data.ftl");
		String userData = new TemplateUtil().formatData(resultMap);
		Map<String, List<Map<String, Object>>> userStatsMap = mapper.readValue(userData, Map.class);
		
		
		
		// 新增注册用户数统计
		String userRegisterNewUserTemplatePath = Thread.currentThread()
				.getContextClassLoader().getResource("resource/template/es")
				.getPath()
				+ "/register-loginuser-user.customcache";
		String adUserContent = client.readFile(userRegisterNewUserTemplatePath);
		Map<String, String> adRegUsrMap = null;
		
		Script adscript = null;
		if (Integer.valueOf(request.getParameter("adProject")) == 5){
//			usrMap = client.execQuery(userContent, new Script("all_game_stats", "game", startTime, endTime));
			adscript = new Script("pay_game_stats", "pay_game_stats", startTime, endTime, addomainQuery);
		}else if (Integer.valueOf(request.getParameter("adProject")) == 7){
			adscript = new Script("pay_stats", "pay_stats", startTime, endTime, addomainQuery);
		}else {
			adscript = new Script("pay_news_stats", "pay_news_stats", startTime, endTime, addomainQuery);
		}	
		
		Map<String, String> adResultMap = null;
		try {
			adResultMap = client.execQuery(adUserContent, adscript);
		} catch (Exception e) {
			log.error("ad [" + addomainQuery + "] hava no data", e);

		}
		
	

		adResultMap.put("templateName", "register-loginuser-user.ftl");
		String adRegUserData = new TemplateUtil().formatData(adResultMap);
		List<Map<String, Object>> adRegUseList = mapper.readValue(mapper.readTree(adRegUserData).get("series").toString(), List.class);
		
		List<Map<String, Object>> userSeriesList = mapper.readValue(mapper.readTree(userData).get("series").toString(),
				List.class);
		

		
		for (Map<String, Object> map : adRegUseList) {// 将注册用户数模板返回的数据与门户咨询获取的会话数、PV,UV组合在一起
			userSeriesList.add(map);
		}
		
		int adipViewNum = 0;
		int aduvNum = 0;
		int adpvNum = 0;
		int adsessionNum = 0;
		int adbounceNum = 0;
		int adavgSessionTime = 0;
		int adreqPageNum = 0;
		int adnewRegisterUserNum = 0;
		int adregisterUserNum = 0;
		int adloginUserNum = 0;
		
		for (int i = 0; i < userSeriesList.size(); i++) {
			Map<String, Object> map = userSeriesList.get(i);
			ReportIndex index = new ReportIndex();
			for (String key : map.keySet()) {
				switch (map.get(key).toString()) {
				case "pv":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> pvStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : pvStats) {
						adpvNum += integer;
					}

					break;
				case "uv":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> uvStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : uvStats) {
						aduvNum += integer;
					}
					break;
				case "独立IP访问量":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> ipStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : ipStats) {
						adipViewNum += integer;
					}
					break;
				case "会话数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> sessionStats = mapper.readValue(mapper.writeValueAsString(map.get("data")),
							List.class);
					for (Integer integer : sessionStats) {
						adsessionNum += integer;
					}
					break;
				case "跳出率":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> bounceStats = mapper
							.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : bounceStats) {
						adbounceNum += integer;
					}
					break;
				case "平均会话时长":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> sessionTime = mapper
							.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : sessionTime) {
						adavgSessionTime += integer;
					}
					break;
				case "平均每次会话浏览页数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> avgReqPage = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : avgReqPage) {
						adreqPageNum += integer;
					}
					break;
				case "结束日期注册用户总数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> newRegisterUserList = mapper.readValue(mapper.writeValueAsString(map.get("data")),
							List.class);
					adregisterUserNum = newRegisterUserList.get(newRegisterUserList.size() - 1);
					break;
				case "登录用户数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> loginUser = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : loginUser) {
						adloginUserNum += integer;
					}
					break;
				case "新增注册用户数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> newRegUser = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : newRegUser) {
						adnewRegisterUserNum += integer;
					}
					break;
				}
				
			}
		}
		
		HouseholdData userHousehold = new HouseholdData();
		userHousehold.setIpViewNum(adipViewNum);
		userHousehold.setNewRegisterUserNum(adnewRegisterUserNum);
		userHousehold.setPageViewNum(adpvNum);
		userHousehold.setRegisterUserNum(adregisterUserNum);
		userHousehold.setSessionNum(adsessionNum);
		userHousehold.setUserViewNum(aduvNum);
		userHousehold.setBounceNum(adbounceNum);
		userHousehold.setAvgSessionTime(adavgSessionTime);
		userHousehold.setReqPageNum(adreqPageNum);
		userHousehold.setLoginUserNum(adloginUserNum);
//		householdList.add(userHousehold);
		
		
		String d = "";
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.0000");
//		d = df.format(userHousehold.getIpViewNum()/(household.getIpViewNum() * 1.0));
//		a = Double.parseDouble(df.format(userHousehold.getIpViewNum()/(household.getIpViewNum() * 1.0))) * 100
		List<String> categories = new ArrayList<String>();
		categories.add("页面浏览量PV");
		categories.add("独立IP访问量");
		categories.add("独立用户数UV");
		categories.add("新增注册用户数");
		categories.add("登录用户数");
		List<Map>  datas = new ArrayList<Map>();
		 
//		 datas.add(Doubles.asList(1,20,3));
//		 datas.add(Doubles.asList(1,21,3));
//		 datas.add(Doubles.asList(1,232,3));
//		 datas.add(Doubles.asList(1,24,3));
//		 datas.add(Doubles.asList(1,25,3));
//		datas.add(Double.parseDouble(df.format(userHousehold.getPageViewNum()/(household.getPageViewNum() * 1.0))) * 100);
//		datas.add(Double.parseDouble(df.format(userHousehold.getIpViewNum()/(household.getIpViewNum() * 1.0))) * 100);
//		datas.add(Double.parseDouble(df.format(userHousehold.getUserViewNum()/(household.getUserViewNum() * 1.0))) * 100);
//		datas.add(Double.parseDouble(df.format(userHousehold.getNewRegisterUserNum()/(household.getNewRegisterUserNum() * 1.0))) * 100);
//		datas.add(Double.parseDouble(df.format(userHousehold.getLoginUserNum()/(household.getLoginUserNum() * 1.0))) * 100);
//
//		List<Double>  datasum = new ArrayList<Double>();
//		datasum.add(Double.parseDouble(df.format(household.getPageViewNum())));
//		datasum.add(Double.parseDouble(df.format(household.getIpViewNum())));
//		datasum.add(Double.parseDouble(df.format(household.getUserViewNum())));
//		datasum.add(Double.parseDouble(df.format(household.getNewRegisterUserNum())));
//		datasum.add(Double.parseDouble(df.format(household.getLoginUserNum())));
//		
//		List<Double>  datausersum = new ArrayList<Double>();
//		datausersum.add(Double.parseDouble(df.format(userHousehold.getPageViewNum())));
//		datausersum.add(Double.parseDouble(df.format(userHousehold.getIpViewNum())));
//		datausersum.add(Double.parseDouble(df.format(userHousehold.getUserViewNum())));
//		datausersum.add(Double.parseDouble(df.format(userHousehold.getNewRegisterUserNum())));
//		datausersum.add(Double.parseDouble(df.format(userHousehold.getLoginUserNum())));
		Map<String,Double>  pvMap = new HashMap<String,Double>();
		pvMap.put("sum", Double.parseDouble(df.format(household.getPageViewNum())));
		pvMap.put("usersum", Double.parseDouble(df.format(userHousehold.getPageViewNum())));
		pvMap.put("y", Double.parseDouble(df.format(userHousehold.getPageViewNum()/(household.getPageViewNum() * 1.0))) * 100);
		datas.add(pvMap);
		
		Map<String,Double>  ipMap = new HashMap<String,Double>();
		ipMap.put("sum", Double.parseDouble(df.format(household.getIpViewNum())));
		ipMap.put("usersum", Double.parseDouble(df.format(userHousehold.getIpViewNum())));
		ipMap.put("y", Double.parseDouble(df.format(userHousehold.getIpViewNum()/(household.getIpViewNum() * 1.0))) * 100);
		datas.add(ipMap);
		
		Map<String,Double>  uvMap = new HashMap<String,Double>();
		uvMap.put("sum", Double.parseDouble(df.format(household.getUserViewNum())));
		uvMap.put("usersum", Double.parseDouble(df.format(userHousehold.getUserViewNum())));
		uvMap.put("y", Double.parseDouble(df.format(userHousehold.getUserViewNum()/(household.getUserViewNum() * 1.0))) * 100);
		datas.add(uvMap);
		
		Map<String,Double>  nrlMap = new HashMap<String,Double>();
		nrlMap.put("sum", Double.parseDouble(df.format(household.getNewRegisterUserNum())));
		nrlMap.put("usersum", Double.parseDouble(df.format(userHousehold.getNewRegisterUserNum())));
//		nrlMap.put("y", Double.parseDouble(df.format(userHousehold.getNewRegisterUserNum()/(household.getNewRegisterUserNum() * 1.0))) * 100);
		if (Double.parseDouble(df.format(household.getNewRegisterUserNum())) != 0){
			nrlMap.put("y", Double.parseDouble(df.format(userHousehold.getNewRegisterUserNum()/(household.getNewRegisterUserNum() * 1.0))) * 100);
		}else{
			nrlMap.put("y", Double.parseDouble(df.format(household.getNewRegisterUserNum())));
		}
		
		datas.add(nrlMap);
		
		Map<String,Double>  luMap = new HashMap<String,Double>();
		luMap.put("sum", Double.parseDouble(df.format(household.getLoginUserNum())));
		luMap.put("usersum", Double.parseDouble(df.format(userHousehold.getLoginUserNum())));
		luMap.put("y", Double.parseDouble(df.format(userHousehold.getLoginUserNum()/(household.getLoginUserNum() * 1.0))) * 100);
		datas.add(luMap);
		
		Map<String,Object>  projectDataMap = new HashMap<String,Object>();
		projectDataMap.put("categories",categories);
		projectDataMap.put("data",datas);
//		projectDataMap.put("datasum",datasum);
//		projectDataMap.put("datausersum",datausersum);
		return mapper.readTree(mapper.writeValueAsString(projectDataMap));
	}
	
	private Domain findDomainTerms(HttpServletRequest request) {
		Domain domain = new Domain();
		domain.setDomainProject(Integer.valueOf(request.getParameter("projectType")));

		/*
		 * if(!StringUtils.isBlank(request.getParameter("startDay").toString())){
		 * advert
		 * .setStartDay(Integer.parseInt(request.getParameter("startDay"))); }
		 * if(!StringUtils.isBlank(request.getParameter("endDay").toString())){
		 * advert.setEndDay(Integer.parseInt(request.getParameter("endDay"))); }
		 */
//		advert.setAdStartTime(DateUtils.parseDate(request.getParameter("adStartTime")));
//		advert.setAdEndTime(DateUtils.parseDate(request.getParameter("adEndTime")));

		request.setAttribute("projectType", request.getParameter("projectType"));
		request.setAttribute("adProject", request.getParameter("adProject"));
//		request.setAttribute("adType", request.getParameter("adType"));
//		request.setAttribute("adSatus", request.getParameter("adSatus"));
		 request.setAttribute("startDay", request.getParameter("startDay"));
		 request.setAttribute("endDay", request.getParameter("endDay"));
		request.setAttribute("adStartTime", request.getParameter("adStartTime"));
		request.setAttribute("adEndTime", request.getParameter("adEndTime"));
		return domain;
	}
	
	
	/**
	 * 内部指标报表数据
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/find-project-data", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode innerIndexReportData(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		Advert advert = new Advert();
		System.out.println(request.getParameter("adProject"));
		System.out.println(request.getParameter("indexType"));
		System.out.println(request.getParameter("startDate"));
		System.out.println(request.getParameter("endDate"));
		
		advert.setAdProject(request.getParameter("adProject"));
		advert.setAdType(request.getParameter("indexType"));
		advert.setStartDate(request.getParameter("startDate"));
		advert.setEndDate(request.getParameter("endDate"));
		initPage(request,response);
		request.setAttribute("adProject", request.getParameter("adProject"));
		request.setAttribute("indexType", request.getParameter("indexType"));
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long startTime = format.parse(request.getParameter("startDate")).getTime();
		long endTime = format.parse(request.getParameter("endDate")).getTime();
		
		List<ReportIndex> reportIndexList = new ArrayList<ReportIndex>();
		
		List<HouseholdData> householdList = new ArrayList<HouseholdData>();// 门户咨询数据
		
		String householdTemplatePath = Thread.currentThread().getContextClassLoader()
				.getResource("resource/template/es").getPath()
				+ "/household-data.customcache";
		String userContent = client.readFile(householdTemplatePath);
		
		Map<String, String> usrMap =null;
		
		if (Integer.valueOf(request.getParameter("adProject")) == 5){
			usrMap = client.execQuery(userContent, new Script("all_game_stats", "game", startTime, endTime));
		}else if (Integer.valueOf(request.getParameter("adProject")) == 7){
			usrMap = client.execQuery(userContent, new Script("all_stats", "ap_main_site", startTime, endTime));
		}else {
			usrMap = client.execQuery(userContent, new Script("all_news_stats", "ap_news_site", startTime, endTime));
		}		
				
		usrMap.put("templateName", "household-data.ftl");
		String data = new TemplateUtil().formatData(usrMap);
//		Map<String, List<Map<String, Object>>> statsMap = mapper.readValue(data, Map.class);
		
		
		
		
		// 新增注册用户数统计
		String registerNewUserTemplatePath = Thread.currentThread()
				.getContextClassLoader().getResource("resource/template/es")
				.getPath()
				+ "/register-loginuser-user.customcache";
		String newUserContent = client.readFile(registerNewUserTemplatePath);
		Map<String, String> newRegUsrMap = null;
		if (Integer.valueOf(request.getParameter("adProject")) == 5){
			newRegUsrMap = client.execQuery(newUserContent, new Script("pay_game_stats", "	pay_game_stats", startTime, endTime));
		}else if (Integer.valueOf(request.getParameter("adProject")) == 7){
			newRegUsrMap = client.execQuery(newUserContent, new Script("pay_stats", "pay_stats", startTime, endTime));
		}else {
			newRegUsrMap = client.execQuery(newUserContent, new Script("pay_news_stats", "pay_news_stats", startTime, endTime));
		}	

		newRegUsrMap.put("templateName", "register-loginuser-user.ftl");
		String newRegUserData = new TemplateUtil().formatData(newRegUsrMap);
		List<Map<String, Object>> newRegUseList = mapper.readValue(mapper.readTree(newRegUserData).get("series").toString(), List.class);

		List<Map<String, Object>> seriesList = mapper.readValue(mapper.readTree(data).get("series").toString(), List.class);
		
		for (Map<String, Object> map : newRegUseList) {// 将注册用户数模板返回的数据与门户咨询获取的会话数、PV,UV组合在一起
			seriesList.add(map);
		}
		
		int ipViewNum = 0;
		int uvNum = 0;
		int pvNum = 0;
		int sessionNum = 0;
		int bounceNum = 0;
		int avgSessionTime = 0;
		int reqPageNum = 0;
		int newRegisterUserNum = 0;
		int registerUserNum = 0;
		int loginUserNum = 0;
		
		for (int i = 0; i < seriesList.size(); i++) {
			Map<String, Object> map = seriesList.get(i);
			ReportIndex index = new ReportIndex();
			for (String key : map.keySet()) {
				switch (map.get(key).toString()) {
				case "pv":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> pvStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : pvStats) {
						pvNum += integer;
					}

					break;
				case "uv":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> uvStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : uvStats) {
						uvNum += integer;
					}
					break;
				case "独立IP访问量":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> ipStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : ipStats) {
						ipViewNum += integer;
					}
					break;
				case "会话数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> sessionStats = mapper.readValue(mapper.writeValueAsString(map.get("data")),
							List.class);
					for (Integer integer : sessionStats) {
						sessionNum += integer;
					}
					break;
				case "跳出率":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> bounceStats = mapper
							.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : bounceStats) {
						bounceNum += integer;
					}
					break;
				case "平均会话时长":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> sessionTime = mapper
							.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : sessionTime) {
						avgSessionTime += integer;
					}
					break;
				case "平均每次会话浏览页数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> avgReqPage = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : avgReqPage) {
						reqPageNum += integer;
					}
					break;
				case "结束日期注册用户总数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> newRegisterUserList = mapper.readValue(mapper.writeValueAsString(map.get("data")),
							List.class);
					registerUserNum = newRegisterUserList.get(newRegisterUserList.size() - 1);
					break;
				case "登录用户数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> loginUser = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : loginUser) {
						loginUserNum += integer;
					}
					break;
				case "新增注册用户数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> newRegUser = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : newRegUser) {
						newRegisterUserNum += integer;
					}
					break;
				}
				
			}
		}
		
		HouseholdData household = new HouseholdData();
		household.setIpViewNum(ipViewNum);
		household.setNewRegisterUserNum(newRegisterUserNum);
		household.setPageViewNum(pvNum);
		household.setRegisterUserNum(registerUserNum);
		household.setSessionNum(sessionNum);
		household.setUserViewNum(uvNum);
		household.setBounceNum(bounceNum);
		household.setAvgSessionTime(avgSessionTime);
		household.setReqPageNum(reqPageNum);
		household.setLoginUserNum(loginUserNum);
//		householdList.add(household);
		
		
		
		//获得user值
		Advert adverts = findAdvertTerms(request);
		List<Advert> advertAssortList = advertService.getAdvertList(adverts);// 分类数值
		
		List<String> adQueryList = new ArrayList<String>();
		for (Advert ad : advertAssortList) {
			if (1 > advertAssortList.size() ) {
				String adQuery = "*";
				adQueryList.add(ad.getAdAddr());
			} else if (1 == advertAssortList.size()) {
				String adQuery = ad.getAdAddr();
				adQueryList.add(ad.getAdAddr());
			} else {
//				adQueryList.add("source_url.raw:" + ad.getAdAddr());
				adQueryList.add("{\"term\" : {\"source_url.raw\" : \"" + ad.getAdAddr() + "\"}}");
//				String adQuery = ad.getAdAddr() + 'and' ;
			}			 					
		}		
		String adQuery = StringUtils.join(adQueryList, ",");
		
		String templatePath = Thread.currentThread().getContextClassLoader().getResource("resource/template/es")
				.getPath()
				+ "/household-data.customcache";
		String content = client.readFile(templatePath);

		Script script = null;
		if (Integer.valueOf(request.getParameter("adProject")) == 5){
//			usrMap = client.execQuery(userContent, new Script("all_game_stats", "game", startTime, endTime));
			script = new Script("all_game_stats", "game", startTime, endTime, adQuery);
		}else if (Integer.valueOf(request.getParameter("adProject")) == 7){
			script = new Script("all_stats", "ap_main_site", startTime, endTime, adQuery);
		}else {
			script = new Script("all_news_stats", "ap_news_site", startTime, endTime, adQuery);
		}	
		
		Map<String, String> resultMap = null;
		try {
			resultMap = client.execQuery(content, script);
		} catch (Exception e) {
			log.error("ad [" + adQuery + "] hava no data", e);

		}
		
		resultMap.put("templateName", "household-data.ftl");
		String userData = new TemplateUtil().formatData(resultMap);
		Map<String, List<Map<String, Object>>> userStatsMap = mapper.readValue(userData, Map.class);
		
		
		
		// 新增注册用户数统计
		String userRegisterNewUserTemplatePath = Thread.currentThread()
				.getContextClassLoader().getResource("resource/template/es")
				.getPath()
				+ "/register-loginuser-user.customcache";
		String adUserContent = client.readFile(userRegisterNewUserTemplatePath);
		Map<String, String> adRegUsrMap = null;
		
		Script adscript = null;
		if (Integer.valueOf(request.getParameter("adProject")) == 5){
//			usrMap = client.execQuery(userContent, new Script("all_game_stats", "game", startTime, endTime));
			adscript = new Script("pay_game_stats", "pay_game_stats", startTime, endTime, adQuery);
		}else if (Integer.valueOf(request.getParameter("adProject")) == 7){
			adscript = new Script("pay_stats", "pay_stats", startTime, endTime, adQuery);
		}else {
			adscript = new Script("pay_news_stats", "pay_news_stats", startTime, endTime, adQuery);
		}	
		
		Map<String, String> adResultMap = null;
		try {
			adResultMap = client.execQuery(adUserContent, adscript);
		} catch (Exception e) {
			log.error("ad [" + adQuery + "] hava no data", e);

		}
		
	

		adResultMap.put("templateName", "register-loginuser-user.ftl");
		String adRegUserData = new TemplateUtil().formatData(adResultMap);
		List<Map<String, Object>> adRegUseList = mapper.readValue(mapper.readTree(adRegUserData).get("series").toString(), List.class);
		
		List<Map<String, Object>> userSeriesList = mapper.readValue(mapper.readTree(userData).get("series").toString(),
				List.class);
		

		
		for (Map<String, Object> map : adRegUseList) {// 将注册用户数模板返回的数据与门户咨询获取的会话数、PV,UV组合在一起
			userSeriesList.add(map);
		}
		
		int adipViewNum = 0;
		int aduvNum = 0;
		int adpvNum = 0;
		int adsessionNum = 0;
		int adbounceNum = 0;
		int adavgSessionTime = 0;
		int adreqPageNum = 0;
		int adnewRegisterUserNum = 0;
		int adregisterUserNum = 0;
		int adloginUserNum = 0;
		
		for (int i = 0; i < userSeriesList.size(); i++) {
			Map<String, Object> map = userSeriesList.get(i);
			ReportIndex index = new ReportIndex();
			for (String key : map.keySet()) {
				switch (map.get(key).toString()) {
				case "pv":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> pvStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : pvStats) {
						adpvNum += integer;
					}

					break;
				case "uv":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> uvStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : uvStats) {
						aduvNum += integer;
					}
					break;
				case "独立IP访问量":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> ipStats = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : ipStats) {
						adipViewNum += integer;
					}
					break;
				case "会话数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> sessionStats = mapper.readValue(mapper.writeValueAsString(map.get("data")),
							List.class);
					for (Integer integer : sessionStats) {
						adsessionNum += integer;
					}
					break;
				case "跳出率":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> bounceStats = mapper
							.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : bounceStats) {
						adbounceNum += integer;
					}
					break;
				case "平均会话时长":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> sessionTime = mapper
							.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : sessionTime) {
						adavgSessionTime += integer;
					}
					break;
				case "平均每次会话浏览页数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> avgReqPage = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : avgReqPage) {
						adreqPageNum += integer;
					}
					break;
				case "结束日期注册用户总数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> newRegisterUserList = mapper.readValue(mapper.writeValueAsString(map.get("data")),
							List.class);
					adregisterUserNum = newRegisterUserList.get(newRegisterUserList.size() - 1);
					break;
				case "登录用户数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> loginUser = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : loginUser) {
						adloginUserNum += integer;
					}
					break;
				case "新增注册用户数":
					index.setIndexName(map.get("name").toString());
					index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
					List<Integer> newRegUser = mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class);
					for (Integer integer : newRegUser) {
						adnewRegisterUserNum += integer;
					}
					break;
				}
				
			}
		}
		
		HouseholdData userHousehold = new HouseholdData();
		userHousehold.setIpViewNum(adipViewNum);
		userHousehold.setNewRegisterUserNum(adnewRegisterUserNum);
		userHousehold.setPageViewNum(adpvNum);
		userHousehold.setRegisterUserNum(adregisterUserNum);
		userHousehold.setSessionNum(adsessionNum);
		userHousehold.setUserViewNum(aduvNum);
		userHousehold.setBounceNum(adbounceNum);
		userHousehold.setAvgSessionTime(adavgSessionTime);
		userHousehold.setReqPageNum(adreqPageNum);
		userHousehold.setLoginUserNum(adloginUserNum);
//		householdList.add(userHousehold);
		
		
		
		List<Map<String, Object>> series = new ArrayList<Map<String, Object>>();
		Map<String, Object> pro1 = new HashMap<String, Object>();
		Map<String, Object> pro2 = new HashMap<String, Object>();
		

			
			String d = "";
			java.text.DecimalFormat df = new java.text.DecimalFormat("0.0000");
			if (Integer.parseInt(request.getParameter("indexType")) == 22) {
				d = df.format(userHousehold.getIpViewNum()/(household.getIpViewNum() * 1.0));
			}else if (Integer.parseInt(request.getParameter("indexType")) == 23){
				d = df.format(userHousehold.getSessionNum()/(household.getSessionNum() * 1.0));
			}else if (Integer.parseInt(request.getParameter("indexType")) == 19){
				d = df.format(userHousehold.getNewRegisterUserNum()/(household.getNewRegisterUserNum() * 1.0));
			}else {
				d = df.format(userHousehold.getLoginUserNum()/(household.getLoginUserNum() * 1.0));
			}
			pro1.put("name", "非广告访问");
			pro1.put("y", (1 - Double.parseDouble(d)) * 100);
			pro2.put("name", "广告带来");
			pro2.put("y", Double.parseDouble(d) * 100);
		
		series.add(pro1);
		series.add(pro2);
		return mapper.readTree(mapper.writeValueAsString(series));
	}
	
	/**
	 * 详细广告数据
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/advert-detail")
	public String advertDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Advert advert = new Advert();
		advert.setAdProject(request.getParameter("adProject"));
		advert.setAdType(request.getParameter("indexType"));
		advert.setStartDate(request.getParameter("startDate"));
		advert.setEndDate(request.getParameter("endDate"));
		initPage(request,response);
		request.setAttribute("adProject", request.getParameter("adProject"));
		request.setAttribute("indexType", request.getParameter("indexType"));
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		return "ad-detail";
	}
	
	/**
	 * 查询详细广告数据
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/find-advert-detail", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode findAdvertDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Advert advert = new Advert();
		advert.setAdProject(request.getParameter("adProject"));
		advert.setAdType(request.getParameter("indexType"));
		advert.setStartDate(request.getParameter("startDate"));
		advert.setEndDate(request.getParameter("endDate"));
		initPage(request,response);
		request.setAttribute("adProject", request.getParameter("adProject"));
		request.setAttribute("indexType", request.getParameter("indexType"));
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		List<String> categories = new ArrayList<String>();
		categories.add("内部广告");
		categories.add("外部推广");
		categories.add("社交推广");
		categories.add("其它类型");
		List<Integer>  data = new ArrayList<Integer>();
		 data.add(104);
		 data.add(75);
		 data.add(23);
		 data.add(3);
		Map<String,Object>  projectDataMap = new HashMap<String,Object>();
		projectDataMap.put("categories",categories);
		projectDataMap.put("data",data);
		return mapper.readTree(mapper.writeValueAsString(projectDataMap));
		
		
	}
	
	
	
}
