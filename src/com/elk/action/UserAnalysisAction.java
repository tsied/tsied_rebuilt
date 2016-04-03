package com.elk.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elk.entity.Advert;
import com.elk.entity.CruxIndex;
import com.elk.entity.MonthIndex;
import com.elk.entity.WeekIndex;
import com.elk.es.ElasticClient;
import com.elk.service.IIndexService;
import com.elk.utils.StringUtils;

/**
 * @date 2016-03-23
 * @author rock
 * @desc 用户分析
 */
@Controller
@RequestMapping("/user")
public class UserAnalysisAction extends BaseAction{
	@Resource
	private IIndexService indexService;
	
	@Autowired
	private ElasticClient client;
	
	@RequestMapping(value="/user-analysis")
	public String  init(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		List<CruxIndex> cruxIndexList = new ArrayList<CruxIndex>();//关健指标合计数值
		CruxIndex cruxIndex1 = new CruxIndex();
		cruxIndex1.setRegisterUser(2210);
		cruxIndex1.setAdProject("杏娱项目");
		cruxIndex1.setLoginUserNum(2003);
		cruxIndex1.setConsumeUserNum(215);
		cruxIndex1.setConsumeUserCount(310);
		cruxIndex1.setNextDayRetention(173);
		cruxIndex1.setNextDayRetention(22);
		cruxIndex1.setThrDaysRetention(97);
		cruxIndex1.setSevenDaysRetention(67);
		cruxIndexList.add(cruxIndex1);
		
		CruxIndex cruxIndex = new CruxIndex();
		int count = 0;
		//count = advertService.getAdvertCount(cruxIndex);
		cruxIndex.setPageSize(10);
		cruxIndex.setTotal(count);
		cruxIndex.setCurrentTotal(count);
		List<CruxIndex> cruxIndexAssortList = new ArrayList<CruxIndex>();//关健指标分类数值   //advertService.getAdvertList(cruxIndex);
		
		CruxIndex cruxIndex3 = new CruxIndex();
		cruxIndex3.setAdName("测试广告1");
		cruxIndex3.setAdStartDate(new Date());
		cruxIndex3.setAdProject("杏娱项目");
		cruxIndex3.setRegisterUser(19911);
		cruxIndex3.setLoginUserNum(21);
		cruxIndex3.setConsumeUserNum(77);
		cruxIndex3.setConsumeUserCount(66);
		cruxIndex3.setConsumeUserCount(33);
		cruxIndex3.setNextDayRetention(83);
		cruxIndex3.setThrDaysRetention(55);
		cruxIndex3.setSevenDaysRetention(21);
		
		
		CruxIndex cruxIndex2 = new CruxIndex();
		cruxIndex2.setAdName("测试广告2");
		cruxIndex2.setAdStartDate(new Date());
		cruxIndex2.setAdProject("杏娱项目2");
		cruxIndex2.setRegisterUser(19911);
		cruxIndex2.setLoginUserNum(21);
		cruxIndex2.setConsumeUserNum(77);
		cruxIndex2.setConsumeUserCount(66);
		cruxIndex2.setConsumeUserCount(33);
		cruxIndex2.setNextDayRetention(83);
		cruxIndex2.setThrDaysRetention(55);
		cruxIndex2.setSevenDaysRetention(21);
		
		cruxIndexAssortList.add(cruxIndex3);
		cruxIndexAssortList.add(cruxIndex3);
		
		
		request.setAttribute("cruxIndexList", cruxIndexList);
		request.setAttribute("cruxIndexAssortList", cruxIndexAssortList);
		request.setAttribute("page", cruxIndex);//分页信息
		
		List<WeekIndex> weekIndexList = new ArrayList<WeekIndex>();//周指标合计数值
		List<WeekIndex> weekIndexAssortList = new ArrayList<WeekIndex>();//周指标分类数值
		request.setAttribute("weekIndexList", weekIndexList);
		request.setAttribute("weekIndexAssortList", weekIndexAssortList);
		
		
		List<MonthIndex> monthIndexList = new ArrayList<MonthIndex>();//月指标合计数值
		List<MonthIndex> monthIndexAssortList = new ArrayList<MonthIndex>();//月指标分类数值
		request.setAttribute("monthIndexList", monthIndexList);
		request.setAttribute("monthIndexAssortList", monthIndexAssortList);
		
		return "user-analysis";
	}
	
	/**
	 * 查找关健指标
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/findCruxIndex")
	public String findCruxIndex(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		CruxIndex cruxIndex = new CruxIndex();
		cruxIndex.setAdName(request.getParameter("cruxAdName")!= null?request.getParameter("cruxAdName"):"");
		cruxIndex.setAdProject(request.getParameter("cruxAdProject"));
		cruxIndex.setAdType(request.getParameter("cruxAdType"));
		cruxIndex.setAdStatus(request.getParameter("cruxAdSatus"));
		if(!StringUtils.isBlank(request.getParameter("cruxStartDay"))){
			cruxIndex.setStartDay(Integer.parseInt(request.getParameter("cruxStartDay")));
		}
		if(!StringUtils.isBlank(request.getParameter("cruxEndDay"))){
			cruxIndex.setEndDay(Integer.parseInt(request.getParameter("cruxEndDay")));
		}
		cruxIndex.setStartDate(request.getParameter("cruxStartDate"));
		cruxIndex.setEndDate(request.getParameter("cruxEndDate"));
		
		/**
		 * query.........
		 */
		
		
		request.setAttribute("cruxAdName",request.getParameter("cruxAdName"));
		request.setAttribute("cruxAdProject", request.getParameter("cruxAdProject"));
		request.setAttribute("cruxAdType", request.getParameter("cruxAdType"));
		request.setAttribute("cruxAdSatus", request.getParameter("cruxAdSatus"));
		request.setAttribute("cruxStartDay", request.getParameter("cruxStartDay"));
		request.setAttribute("cruxEndDay", request.getParameter("cruxEndDay"));
		request.setAttribute("cruxStartDate", request.getParameter("cruxStartDate"));
		request.setAttribute("cruxEndDate", request.getParameter("cruxEndDate"));
		
		
		int count = 0;
		//count = advertService.getAdvertCount(cruxIndex);
		cruxIndex.setPageSize(10);
		cruxIndex.setTotal(count);
		cruxIndex.setCurrentTotal(count);
		
		List<CruxIndex> cruxIndexList = new ArrayList<CruxIndex>();//关健指标合计数值
		List<CruxIndex> cruxIndexAssortList = new ArrayList<CruxIndex>();//关健指标分类数值//advertService.getAdvertList(cruxIndex);
		request.setAttribute("cruxIndexList", cruxIndexList);
		request.setAttribute("cruxIndexAssortList", cruxIndexAssortList);
		
		List<WeekIndex> weekIndexList = new ArrayList<WeekIndex>();//周指标合计数值
		List<WeekIndex> weekIndexAssortList = new ArrayList<WeekIndex>();//周指标分类数值
		request.setAttribute("weekIndexList", weekIndexList);
		request.setAttribute("weekIndexAssortList", weekIndexAssortList);
		
		
		List<MonthIndex> monthIndexList = new ArrayList<MonthIndex>();//月指标合计数值
		List<MonthIndex> monthIndexAssortList = new ArrayList<MonthIndex>();//月指标分类数值
		request.setAttribute("monthIndexList", monthIndexList);
		request.setAttribute("monthIndexAssortList", monthIndexAssortList);
		
		return "user-analysis";
	}
	
	/**
	 * 查找周指标
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/findWeekIndex")
	public String findWeekIndex(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		WeekIndex weekIndex = new WeekIndex();
		weekIndex.setAdName(request.getParameter("weekAdName")!= null?request.getParameter("weekAdName"):"");
		weekIndex.setAdProject(request.getParameter("weekAdProject"));
		weekIndex.setAdType(request.getParameter("weekAdType"));
		weekIndex.setAdStatus(request.getParameter("weekAdSatus"));
		
		if(!StringUtils.isBlank(request.getParameter("weekStartDay"))){
			weekIndex.setStartDay(Integer.parseInt(request.getParameter("weekStartDay")));
		}
		if(!StringUtils.isBlank(request.getParameter("weekEndDay"))){
			weekIndex.setEndDay(Integer.parseInt(request.getParameter("weekEndDay")));
		}
		weekIndex.setStartDate(request.getParameter("weekStartDate"));
		weekIndex.setEndDate(request.getParameter("weekEndDate"));
		
		
		request.setAttribute("weekAdName", request.getParameter("weekAdName"));
		request.setAttribute("weekAdProject", request.getParameter("weekAdProject"));
		request.setAttribute("weekAdType", request.getParameter("weekAdType"));
		request.setAttribute("weekAdSatus", request.getParameter("weekAdSatus"));
		request.setAttribute("weekStartDay", request.getParameter("weekStartDay"));
		request.setAttribute("weekEndDay", request.getParameter("weekEndDay"));
		request.setAttribute("weekStartDate", request.getParameter("weekStartDate"));
		request.setAttribute("weekEndDate", request.getParameter("weekEndDate"));
		
		int count = 0;
		//count = advertService.getAdvertCount(weekIndex);
		weekIndex.setPageSize(10);
		weekIndex.setTotal(count);
		weekIndex.setCurrentTotal(count);
		
		
		List<CruxIndex> cruxIndexList = new ArrayList<CruxIndex>();//关健指标合计数值
		List<CruxIndex> cruxIndexAssortList = new ArrayList<CruxIndex>();//关健指标分类数值
		request.setAttribute("cruxIndexList", cruxIndexList);
		request.setAttribute("cruxIndexAssortList", cruxIndexAssortList);
		
		List<WeekIndex> weekIndexList = new ArrayList<WeekIndex>();//周指标合计数值
		List<WeekIndex> weekIndexAssortList = new ArrayList<WeekIndex>();//周指标分类数值//advertService.getAdvertList(weekIndex);
		request.setAttribute("weekIndexList", weekIndexList);
		request.setAttribute("weekIndexAssortList", weekIndexAssortList);
		
		
		List<MonthIndex> monthIndexList = new ArrayList<MonthIndex>();//月指标合计数值
		List<MonthIndex> monthIndexAssortList = new ArrayList<MonthIndex>();//月指标分类数值
		request.setAttribute("monthIndexList", monthIndexList);
		request.setAttribute("monthIndexAssortList", monthIndexAssortList);
		
		return "user-analysis";
	}
	
	/**
	 * 查找月指标
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/findMonthIndex")
	public String findMonthIndex(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		MonthIndex monthIndex = new MonthIndex();
		monthIndex.setAdName(request.getParameter("monthAdName")!= null?request.getParameter("monthAdName"):"");
		monthIndex.setAdProject(request.getParameter("monthAdProject"));
		monthIndex.setAdType(request.getParameter("monthAdType"));
		monthIndex.setAdStatus(request.getParameter("monthAdSatus"));
		if(!StringUtils.isBlank(request.getParameter("weekStartDay"))){
			monthIndex.setStartDay(Integer.parseInt(request.getParameter("weekStartDay")));
		}
		if(!StringUtils.isBlank(request.getParameter("weekEndDay"))){
			monthIndex.setEndDay(Integer.parseInt(request.getParameter("weekEndDay")));
		}
		monthIndex.setStartDate(request.getParameter("monthStartDate"));
		monthIndex.setEndDate(request.getParameter("monthEndDate"));
		
		int count = 0;
		//count = advertService.getAdvertCount(weekIndex);
		monthIndex.setPageSize(10);
		monthIndex.setTotal(count);
		monthIndex.setCurrentTotal(count);
		
		request.setAttribute("monthAdName", request.getParameter("monthAdName"));
		request.setAttribute("monthAdProject", request.getParameter("monthAdProject"));
		request.setAttribute("monthAdType", request.getParameter("monthAdType"));
		request.setAttribute("monthAdSatus", request.getParameter("monthAdSatus"));
		request.setAttribute("monthStartDay", request.getParameter("monthStartDay"));
		request.setAttribute("monthEndDay", request.getParameter("monthEndDay"));
		request.setAttribute("monthStartDate", request.getParameter("monthStartDate"));
		request.setAttribute("monthEndDate", request.getParameter("monthEndDate"));
		
		
		List<CruxIndex> cruxIndexList = new ArrayList<CruxIndex>();//关健指标合计数值
		List<CruxIndex> cruxIndexAssortList = new ArrayList<CruxIndex>();//关健指标分类数值
		request.setAttribute("cruxIndexList", cruxIndexList);
		request.setAttribute("cruxIndexAssortList", cruxIndexAssortList);
		
		List<WeekIndex> weekIndexList = new ArrayList<WeekIndex>();//周指标合计数值
		List<WeekIndex> weekIndexAssortList = new ArrayList<WeekIndex>();//周指标分类数值
		request.setAttribute("weekIndexList", weekIndexList);
		request.setAttribute("weekIndexAssortList", weekIndexAssortList);
		
		
		List<MonthIndex> monthIndexList = new ArrayList<MonthIndex>();//月指标合计数值
		List<MonthIndex> monthIndexAssortList = new ArrayList<MonthIndex>();//月指标分类数值//advertService.getAdvertList(weekIndex);
		request.setAttribute("monthIndexList", monthIndexList);
		request.setAttribute("monthIndexAssortList", monthIndexAssortList);
		
		return "user-analysis";
	}
	
}
