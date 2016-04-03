package com.elk.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elk.entity.Advert;
import com.elk.utils.StringUtil;
import com.elk.utils.StringUtils;
/**
 * @desc 流量分析
 * @author rock
 *
 */
@Controller
@RequestMapping("/flow")
public class FlowAnalysisAction extends BaseAction{
	
	
	@RequestMapping(value="/flow-analysis")
	public String  index(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		int count = 0;
		Advert advert = new Advert();
		count = advertService.getAdvertCount(null);
		advert.setPageSize(10);
		advert.setTotal(count);
		advert.setCurrentTotal(count);
		List<Advert> advertNumList = new ArrayList<Advert>();//合计及平均数值
		
		List<Advert> advertAssortList = advertService.getAdvertList(advert);//分类数值 //advertService.getAdvertList(advert);
		
		
		Advert advert2 = new Advert();
		advert2.setAdName("泰皇登基充值2折");
		advert2.setAdStartTime(new Date());
		advert2.setClickNum(5079);
		advert2.setUserViewNum(10023);
		advert2.setIpViewNum(12203);
		advert2.setTargetPageNum(3402);
		advert2.setSessionNum(13472);
		advert2.setAvgSessionViewNum(19403);
		advert2.setAvgSessionDuration("742159");
		advert2.setBounceRate("23.5");
		
		advertAssortList.add(advert2);
		
		request.setAttribute("advertNumList", advertNumList);
		request.setAttribute("advertAssortList", advertAssortList);
		request.setAttribute("page", advert);//分页信息
		return "flow-analysis";
	}
	
	@RequestMapping(value="/findAdvert")
	public String findAdvert(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		Advert advert = findAdvertTerms(request);
		int count = 0;
		//count = advertService.getAdvertCount(advert);
		advert.setPageSize(10);
		advert.setTotal(count);
		advert.setCurrentTotal(count);
		List<Advert> advertNumList = new ArrayList<Advert>();//合计及平均数值
		List<Advert> advertAssortList = new ArrayList<Advert>();//分类数值 advertService.getAdvertList(advert);
		request.setAttribute("advertNumList", advertNumList);
		request.setAttribute("advertAssortList", advertAssortList);
		request.setAttribute("page", advert);//分页信息
		return "flow-analysis";
	}

	private Advert findAdvertTerms(HttpServletRequest request) {
		Advert advert = new Advert();
		advert.setAdName(request.getParameter("adName")!= null?request.getParameter("adName"):"");
		advert.setAdProject(request.getParameter("adProject"));
		advert.setAdType(request.getParameter("adType"));
		advert.setAdStatus(request.getParameter("adSatus"));
		if(!StringUtils.isBlank(request.getParameter("startDay"))){
			advert.setStartDay(Integer.parseInt(request.getParameter("startDay")));
		}
		if(StringUtils.isBlank(request.getParameter("endDay"))){
			advert.setEndDay(Integer.parseInt(request.getParameter("endDay")));
		}
		advert.setStartDate(request.getParameter("startDate"));
		advert.setEndDate(request.getParameter("endDate"));
		
		request.setAttribute("adName", request.getParameter("adName"));
		request.setAttribute("adProject", request.getParameter("adProject"));
		request.setAttribute("adType", request.getParameter("adType"));
		request.setAttribute("adSatus", request.getParameter("adSatus"));
		request.setAttribute("startDay", request.getParameter("startDay"));
		request.setAttribute("endDay", request.getParameter("endDay"));
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		return advert;
	}
	
	@RequestMapping(value="/pageForm")
	public String  page(HttpServletRequest request,HttpServletResponse response) throws IOException{
			initPage(request,response);
			int count = 0;
			Advert advert = findAdvertTerms(request);
			advert.setPageSize(10);
			//count = advertService.getAdvertCount(advert);
			if(!StringUtil.isBlank(request.getParameter("page"))){
				advert.setOffset(Integer.parseInt(request.getParameter("offset")));
			}
			advert.setTotal(count);
			advert.setCurrentTotal(count);			
			List<Advert> advertNumList = new ArrayList<Advert>();//合计及平均数值
			List<Advert> advertAssortList = new ArrayList<Advert>();//分类数值   //advertService.getAdvertList(advert);
			Advert advert2 = new Advert();
			advert2.setAdName("泰皇登基充值2折");
			advert2.setAdStartTime(new Date());
			advert2.setClickNum(5079);
			advert2.setUserViewNum(10023);
			advert2.setIpViewNum(12203);
			advert2.setTargetPageNum(3402);
			advert2.setSessionNum(13472);
			advert2.setAvgSessionViewNum(19403);
			advert2.setAvgSessionDuration("742159");
			advert2.setBounceRate("23.5");
			
			
			Advert advert1 = new Advert();
			advert1.setAdName("泼水节充值8折");
			advert1.setAdStartTime(new Date());
			advert1.setClickNum(100);
			advert1.setUserViewNum(102);
			advert1.setIpViewNum(203);
			advert1.setTargetPageNum(402);
			advert1.setSessionNum(172);
			advert1.setAvgSessionViewNum(1903);
			advert1.setAvgSessionDuration("759");
			advert1.setBounceRate("88.5");
			advertAssortList.add(advert2);
			advertAssortList.add(advert1);
			
			request.setAttribute("advertNumList", advertNumList);
			request.setAttribute("advertAssortList", advertAssortList);
			request.setAttribute("page", advert);//分页信息
		return "ad";
	}	
}
