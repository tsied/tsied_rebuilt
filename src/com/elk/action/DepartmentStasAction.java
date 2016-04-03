package com.elk.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elk.entity.DepartmentStas;
import com.elk.es.ElasticClient;
import com.elk.service.IIndexService;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @desc 部门总量报表
 * @author rock
 *
 */
@Controller
@RequestMapping("/department")
public class DepartmentStasAction extends BaseAction{
	@Resource
	private IIndexService indexService;
	
	@Autowired
	private ElasticClient client;
	
	/**
	 * 初始化
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/department-stats")
	public String  init(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		List<DepartmentStas> departStasList = new ArrayList<DepartmentStas>();//IE事业群各项目合计数值
		List<DepartmentStas> departList = new ArrayList<DepartmentStas>();//IE事业群各项目数值
		request.setAttribute("departStasList", departStasList);
		request.setAttribute("departList", departList);
		return "department-stats";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/find-department-stas")
	public String findDepartment(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		request.getParameter("startDate");
		request.getParameter("endDate");
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		List<DepartmentStas> departStasList = new ArrayList<DepartmentStas>();//IE事业群各项目合计数值
		List<DepartmentStas> departList = new ArrayList<DepartmentStas>();//IE事业群各项目数值
		request.setAttribute("departStasList", departStasList);
		request.setAttribute("departList", departList);
		return "department-stats";
	}
	
	
	@RequestMapping(value = "/find-department-chart-stats", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode departmentChartData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		request.getParameter("startDate");
		request.getParameter("endDate");
		List<Map<String,Object>> firChartList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> senChartList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> thrChartList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> fourChartList = new ArrayList<Map<String,Object>>();
		Map<String,Object> firstChart1 = new HashMap<String,Object>();
		firstChart1.put("name", "杏娱咨询");
		firstChart1.put("y", 13);
		Map<String,Object> firstChart2 = new HashMap<String,Object>();
		firstChart2.put("name", "杏娱游戏");
		firstChart2.put("y", 54);
		Map<String,Object> firstChart3 = new HashMap<String,Object>();
		firstChart3.put("name", "杏娱女神");
		firstChart3.put("y", 21);
		Map<String,Object> firstChart4 = new HashMap<String,Object>();
		firstChart4.put("name", "杏娱视频");
		firstChart4.put("y", 12);
		
		firChartList.add(firstChart1);
		firChartList.add(firstChart2);
		firChartList.add(firstChart3);
		firChartList.add(firstChart4);
		
		
		Map<String,Object> secondChart1 = new HashMap<String,Object>();
		secondChart1.put("name", "杏娱咨询");
		secondChart1.put("y", 13);
		Map<String,Object> secondChart2 = new HashMap<String,Object>();
		secondChart2.put("name", "杏娱游戏");
		secondChart2.put("y", 54);
		Map<String,Object> secondChart3 = new HashMap<String,Object>();
		secondChart3.put("name", "杏娱女神");
		secondChart3.put("y", 21);
		Map<String,Object> secondChart4 = new HashMap<String,Object>();
		secondChart4.put("name", "杏娱视频");
		secondChart4.put("y", 12);
		
		
		senChartList.add(secondChart1);
		senChartList.add(secondChart2);
		senChartList.add(secondChart3);
		senChartList.add(secondChart4);
		
		
		Map<String,Object> thirdChart1 = new HashMap<String,Object>();
		thirdChart1.put("name", "杏娱咨询");
		thirdChart1.put("y", 14);
		Map<String,Object> thirdChart2 = new HashMap<String,Object>();
		thirdChart2.put("name", "杏娱游戏");
		thirdChart2.put("y", 51);
		Map<String,Object> thirdChart3 = new HashMap<String,Object>();
		thirdChart3.put("name", "杏娱女神");
		thirdChart3.put("y", 18);
		Map<String,Object> thirdChart4 = new HashMap<String,Object>();
		thirdChart4.put("name", "杏娱视频");
		thirdChart4.put("y", 17);
		
		
		thrChartList.add(thirdChart1);
		thrChartList.add(thirdChart2);
		thrChartList.add(thirdChart3);
		thrChartList.add(thirdChart4);
		
		Map<String,Object> fourChart1 = new HashMap<String,Object>();
		fourChart1.put("name", "杏娱咨询");
		fourChart1.put("y", 26);
		Map<String,Object> fourChart2 = new HashMap<String,Object>();
		fourChart2.put("name", "杏娱游戏");
		fourChart2.put("y", 46);
		Map<String,Object> fourChart3 = new HashMap<String,Object>();
		fourChart3.put("name", "杏娱女神");
		fourChart3.put("y", 20);
		Map<String,Object> fourChart4 = new HashMap<String,Object>();
		fourChart4.put("name", "杏娱视频");
		fourChart4.put("y", 8);
		fourChartList.add(fourChart1);
		fourChartList.add(fourChart2);
		fourChartList.add(fourChart3);
		fourChartList.add(fourChart4);
		
		Map<String,List<Map<String,Object>>> chartMap = new HashMap<String, List<Map<String,Object>>>();
		chartMap.put("incomeStas", firChartList);
		chartMap.put("pvStas", senChartList);
		chartMap.put("viewStas", thrChartList);
		chartMap.put("registerStas", fourChartList);
		
		return mapper.readTree(mapper.writeValueAsString(chartMap));
	}
	
}
