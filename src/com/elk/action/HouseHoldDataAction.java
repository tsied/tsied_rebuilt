package com.elk.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elk.entity.HouseholdData;
import com.elk.entity.ReportIndex;
import com.elk.es.Script;
import com.elk.utils.DateUtils;
import com.elk.utils.TemplateUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @date 2016-03-23
 * @author rock
 * @desc 门户咨询数据
 */
@Controller
@RequestMapping("/household")
public class HouseHoldDataAction extends BaseAction {

	@RequestMapping(value = "/household-data")
	public String init(HttpServletRequest request, HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException, ParseException {
		initPage(request, response);
		getHouseholdData(request, DateUtils.formatDate(DateUtils.daysDiff(-90)),
				DateUtils.formatDate(DateUtils.daysDiff(-1)));
		return "household-data";
	}

	@SuppressWarnings("unchecked")
	private void getHouseholdData(HttpServletRequest request, String startDate, String endDate) throws IOException,
			JsonParseException, JsonMappingException, JsonProcessingException, ParseException {
		List<HouseholdData> householdList = new ArrayList<HouseholdData>();// 门户咨询数据
		List<ReportIndex> reportIndexList = new ArrayList<ReportIndex>();// 报表指标选项
		String templatePath = Thread.currentThread().getContextClassLoader().getResource("resource/template/es")
				.getPath()
				+ "/household-data.customcache";
		String content = client.readFile(templatePath);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long startTime = format.parse(startDate).getTime();
		long endTime = format.parse(endDate).getTime();
		Map<String, String> resultMap = client.execQuery(content, new Script("all_stats", "ap_main_site", startTime,
				endTime));
		resultMap.put("templateName", "household-data.ftl");
		String data = new TemplateUtil().formatData(resultMap);
		String registerUserTemplatePath = Thread.currentThread().getContextClassLoader()
				.getResource("resource/template/es").getPath()
				+ "/register-user.customcache";
		String userContent = client.readFile(registerUserTemplatePath);
		Map<String, String> usrMap = client.execQuery(userContent, new Script("pay_s", "pay_game", startTime, endTime));
		usrMap.put("templateName", "register-user.ftl");
		String userData = new TemplateUtil().formatData(usrMap);

		GetResponse response = client.getESClient().prepareGet("pay_s", "pay_game", endDate + "-local-2")
				.setOperationThreaded(false).get();
		Integer sumRegUserCnt = Integer.parseInt(response.getSource().get("sumregusercnt").toString());

		String registerNewUserTemplatePath = Thread.currentThread().getContextClassLoader()
				.getResource("resource/template/es").getPath()
				+ "/register-newuser.customcache";
		String newUserContent = client.readFile(registerNewUserTemplatePath);
		SearchResponse myRsp = client.execQueryRaw(newUserContent, new Script("tmp_stats", "www_stats", startTime,
				endTime));
		Sum newRegisterUserSum = myRsp.getAggregations().get("1");

		List<Map<String, Object>> userList = mapper.readValue(mapper.readTree(userData).get("series").toString(),
				List.class);// 用户数据
		List<Long> dateList = new ArrayList<Long>();// 报表日期
		List<String> convertDateList = mapper.readValue(mapper.readTree(data).get("date").toString(), List.class);
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = convertDateList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			long date = format.parse(string).getTime() / 1000;
			dateList.add(date);
		}
		int ipViewNum = 0;
		int uvNum = 0;
		int pvNum = 0;
		int sessionNum = 0;
		int bounceNum = 0;
		int avgSessionTime = 0;
		int reqPageNum = 0;
		int newRegisterUserNum = Double.valueOf(newRegisterUserSum.getValue()).intValue();
		int registerUserNum = 0;
		List<Map<String, Object>> seriesList = mapper.readValue(mapper.readTree(data).get("series").toString(),
				List.class);
		for (Map<String, Object> map : userList) {// 将注册用户数模板返回的数据与门户咨询获取的会话数、PV,UV组合在一起
			seriesList.add(map);
		}
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
					// index.setIndexName(map.get("name").toString());
					// index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")),
					// List.class));
					// List<Integer> newRegisterUserList =
					// mapper.readValue(mapper.writeValueAsString(map.get("data")),
					// List.class);
					registerUserNum = sumRegUserCnt;
					break;
				case "新增注册用户数":
					// index.setIndexName(map.get("name").toString());
					// index.setIndexValue(mapper.readValue(mapper.writeValueAsString(map.get("data")),
					// List.class));
					// List<Integer> registerUserList =
					// mapper.readValue(mapper.writeValueAsString(map.get("data")),
					// List.class);
					// for (Integer integer : registerUserList) {
					// newRegisterUserNum += integer;
					// }
					break;
				}
				index.setIndexDate(dateList);
			}

			reportIndexList.add(index);
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
		householdList.add(household);
		request.setAttribute("indexDate", dateList);
		request.setAttribute("householdList", householdList);
		request.setAttribute("reportIndexList", reportIndexList);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
	}

	@RequestMapping(value = "/find-household-data")
	public String findHouseholdData(HttpServletRequest request, HttpServletResponse response) throws IOException,
			ParseException {
		initPage(request, response);
		String startDate = request.getParameter("startDate") == null ? DateUtils.formatDate(DateUtils.lastYear())
				: request.getParameter("startDate");
		String endDate = request.getParameter("endDate") == null ? DateUtils.formatDate(DateUtils.getCurrent())
				: request.getParameter("endDate");
		getHouseholdData(request, startDate, endDate);
		return "household-data";
	}

	public Integer getMaxValueFromList(List<Integer> list) {
		int max = 0;
		for (int i = 0; i <= list.size() - 1; i++) {
			if (max <= list.get(i)) {
				max = list.get(i);
			}
		}
		return max;
	}
}
