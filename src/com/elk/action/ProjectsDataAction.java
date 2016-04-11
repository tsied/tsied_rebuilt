package com.elk.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elk.entity.Advert;
import com.fasterxml.jackson.databind.JsonNode;

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
	
	/**
	 * 项目数据统计
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/find-projects-data", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode projectDataStats(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Advert advert = new Advert();
		advert.setAdProject(request.getParameter("adProject"));
		advert.setAdType(request.getParameter("projectType"));
		advert.setStartDate(request.getParameter("startDate"));
		advert.setEndDate(request.getParameter("endDate"));
		request.setAttribute("adProject", request.getParameter("adProject"));
		request.setAttribute("projectType", request.getParameter("projectType"));
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		List<String> categories = new ArrayList<String>();
		categories.add("注册用户1");
		categories.add("登录用户1");
		categories.add("消费用户1");
		categories.add("用户消费金额1");
		categories.add("浏览量1");
		categories.add("独立用户访客1");
		categories.add("监测页面点击量1");
		List<Integer>  data = new ArrayList<Integer>();
		 data.add(35);
		 data.add(80);
		 data.add(68);
		 data.add(24);
		 data.add(71);
		 data.add(33);
		 data.add(18);
		Map<String,Object>  projectDataMap = new HashMap<String,Object>();
		projectDataMap.put("categories",categories);
		projectDataMap.put("data",data);
		return mapper.readTree(mapper.writeValueAsString(projectDataMap));
	}
	
	
	/**
	 * 内部指标报表数据
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/find-project-data", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode innerIndexReportData(HttpServletRequest request,HttpServletResponse response) throws IOException{
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
		List<Map<String,Object>> series = new ArrayList<Map<String,Object>>();
		Map<String,Object> pro1 = new HashMap<String,Object>();
		pro1.put("name", "直接访问1");
		pro1.put("y", 58.5);
		Map<String,Object> pro2 = new HashMap<String,Object>();
		pro2.put("name", "广告带来1");
		pro2.put("y", 13.3);
		Map<String,Object> pro3 = new HashMap<String,Object>();
		pro3.put("name", "其他1");
		pro3.put("y", 28.1);
		series.add(pro1);
		series.add(pro2);
		series.add(pro3);
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
