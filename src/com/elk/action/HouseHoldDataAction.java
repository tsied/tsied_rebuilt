package com.elk.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elk.entity.HouseholdData;
import com.elk.entity.ReportIndex;
import com.elk.es.ElasticClient;
import com.elk.es.Script;
import com.elk.service.IIndexService;
import com.elk.utils.TemplateUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @date 2016-03-23
 * @author rock
 * @desc 门户咨询数据
 */
@Controller
@RequestMapping("/household")
public class HouseHoldDataAction extends BaseAction{
	
	@Resource
	private IIndexService indexService;
	
	@Autowired
	private ElasticClient client;
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@RequestMapping(value="/household-data")
	public String  init(HttpServletRequest request,HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException, ParseException{
		initPage(request,response);
		List<HouseholdData> householdList = new ArrayList<HouseholdData>();//门户咨询数据
		HouseholdData household = new HouseholdData();
		household.setIpViewNum(2000);
		household.setNewRegisterUserNum(2658);
		household.setPageViewNum(9981);
		household.setRegisterUserNum(118);
		household.setReviewRate(88.8);
		household.setReviewUserNum(7532);
		household.setSessionNum(10265);
		household.setUserNum(200533);
		householdList.add(household);
		List<ReportIndex> reportIndexList = new ArrayList<ReportIndex>();//报表指标选项
		String templatePath = Thread.currentThread().getContextClassLoader().getResource("resource/template/es").getPath()+"/pv.customcache";
		String content = client.readFile(templatePath);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long startTime = format.parse("2016-03-01").getTime() / 1;
		long endTime   = format.parse("2016-03-31").getTime() / 1;
		Script script = new Script("informatiom_stat","ad_stat",startTime,endTime);
		
		Map<String, String> resultMap = client.execQuery(content,script);
		resultMap.put("templateName", "house-hold.ftl");
		String data = new TemplateUtil().formatData(resultMap);
		List<Long> dateList = new ArrayList<Long>();//报表日期
		List<String> convertDateList = mapper.readValue(mapper.readTree(data).get("date").toString(),List.class) ;
		for (Iterator iterator = convertDateList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			long date = format.parse(string).getTime() / 1000;
			dateList.add(date);
		}
		
		List<Map<String,Object>> seriesList = mapper.readValue(mapper.readTree(data).get("series").toString(),List.class); 
		for (Iterator iterator = seriesList.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			ReportIndex index = new ReportIndex();
			for(String key:map.keySet()){
				index.setIndexName(map.get("name").toString());
				index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")), List.class));
				index.setIndexDate(dateList);
			}
			reportIndexList.add(index);
		}
		
		request.setAttribute("indexDate", dateList);
		request.setAttribute("householdList", householdList);
		request.setAttribute("reportIndexList", reportIndexList);
		return "household-data";
	}
	
	
	@RequestMapping(value="/find-household-data")
	public String findHouseholdData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		request.getParameter("startDate");
		request.getParameter("endDate");
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		List<HouseholdData> householdList = new ArrayList<HouseholdData>();//门户咨询数据
		HouseholdData household = new HouseholdData();
		household.setIpViewNum(2000);
		household.setNewRegisterUserNum(2658);
		household.setPageViewNum(9981);
		household.setRegisterUserNum(118);
		household.setReviewRate(88.8);
		household.setReviewUserNum(7532);
		household.setSessionNum(10265);
		household.setUserNum(200533);
		householdList.add(household);
		List<ReportIndex> reportIndexList = new ArrayList<ReportIndex>();//报表指标选项
		ReportIndex index = new ReportIndex();
		index.setIndexName("独立用户数UV");
		List<Integer> valueList = new ArrayList<Integer>();
		valueList.add(100);
		valueList.add(800);
		valueList.add(300);
		valueList.add(120);
		valueList.add(740);
		valueList.add(301);
		valueList.add(23);
		index.setIndexValue(valueList);
		reportIndexList.add(index);
		
		
		ReportIndex ipIndex = new ReportIndex();
		ipIndex.setIndexName("独立IP访问量");
		List<Integer> valueList1 = new ArrayList<Integer>();
		valueList1.add(300);
		valueList1.add(600);
		valueList1.add(670);
		valueList1.add(1200);
		valueList1.add(140);
		valueList1.add(501);
		valueList1.add(280);
		ipIndex.setIndexValue(valueList1);
		reportIndexList.add(ipIndex);
		
		
		ReportIndex pageIndex = new ReportIndex();
		pageIndex.setIndexName("页面浏览量PV");
		List<Integer> valueList2 = new ArrayList<Integer>();
		valueList2.add(330);
		valueList2.add(350);
		valueList2.add(650);
		valueList2.add(298);
		valueList2.add(754);
		valueList2.add(821);
		valueList2.add(920);
		pageIndex.setIndexValue(valueList2);
		reportIndexList.add(pageIndex);
		
		
		ReportIndex sessionIndex = new ReportIndex();
		sessionIndex.setIndexName("会话数");
		List<Integer> valueList3 = new ArrayList<Integer>();
		valueList3.add(500);
		valueList3.add(320);
		valueList3.add(510);
		valueList3.add(821);
		valueList3.add(932);
		valueList3.add(549);
		valueList3.add(832);
		sessionIndex.setIndexValue(valueList3);
		reportIndexList.add(sessionIndex);
		
		
		ReportIndex reviewIndex = new ReportIndex();
		reviewIndex.setIndexName("回访用户数");
		List<Integer> valueList4 = new ArrayList<Integer>();
		valueList4.add(630);
		valueList4.add(200);
		valueList4.add(280);
		valueList4.add(420);
		valueList4.add(350);
		valueList4.add(371);
		valueList4.add(89);
		reviewIndex.setIndexValue(valueList4);
		reportIndexList.add(reviewIndex);
		
		ReportIndex reviewRate = new ReportIndex();
		reviewRate.setIndexName("回访率");
		List<Integer> valueList5 = new ArrayList<Integer>();
		valueList5.add(901);
		valueList5.add(630);
		valueList5.add(43);
		valueList5.add(96);
		valueList5.add(643);
		valueList5.add(333);
		valueList5.add(56);
		reviewRate.setIndexValue(valueList5);
		reportIndexList.add(reviewRate);
		
		ReportIndex registerUserNum = new ReportIndex();
		registerUserNum.setIndexName("结束日期注册用户总数");
		List<Integer> valueList6 = new ArrayList<Integer>();
		valueList6.add(82);
		valueList6.add(130);
		valueList6.add(430);
		valueList6.add(50);
		valueList6.add(140);
		valueList6.add(331);
		valueList6.add(267);
		registerUserNum.setIndexValue(valueList6);
		reportIndexList.add(registerUserNum);
		
		ReportIndex newRegisterUser = new ReportIndex();
		newRegisterUser.setIndexName("新增注册用户数");
		List<Integer> valueList7 = new ArrayList<Integer>();
		valueList7.add(12);
		valueList7.add(23);
		valueList7.add(434);
		valueList7.add(65);
		valueList7.add(678);
		valueList7.add(980);
		valueList7.add(21);
		newRegisterUser.setIndexValue(valueList7);
		reportIndexList.add(newRegisterUser);
		
		request.setAttribute("householdList", householdList);
		request.setAttribute("reportIndexList", reportIndexList);
		return "household-data";
	}
	
}
