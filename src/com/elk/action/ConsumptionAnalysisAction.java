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
import com.elk.es.ElasticClient;
import com.elk.service.IIndexService;
import com.elk.utils.StringUtil;
import com.elk.utils.StringUtils;

/**
 * @desc消费分析
 * @author rock
 *
 */
@Controller
@RequestMapping("/consumption")
public class ConsumptionAnalysisAction extends BaseAction{
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
	@RequestMapping(value="/consumption-analysis")
	public String  init(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initPage(request,response);
		List<Advert> advertNumList = new ArrayList<Advert>();//合计数值
		
		
		Advert advertStats = new Advert();
		
		advertStats.setRegisterUsrConsumeAmount(22.1);
		advertStats.setLoginUsrConsumeAmount(17.3);
		advertStats.setLoginUsrConsumeAmount(55.2);
		advertStats.setConsumeUsrAmount(22.3);
		advertStats.setConsumeUsrAmount(11.1);
		advertStats.setConsumeUsrDayAmount(77.3);
		advertStats.setConsumeUsrAvgAmount(22.43);
		advertStats.setLonginUsrAvgAmount(7.3);
		advertNumList.add(advertStats);
		
		int count = 0;
		Advert advert = new Advert();
		//count = advertService.getAdvertCount(advert);
		advert.setPageSize(10);
		advert.setTotal(count);
		advert.setCurrentTotal(count);
		List<Advert> advertAssortList = new ArrayList<Advert>();//分类数值 //advertService.getAdvertList(advert);
		Advert advert2 = new Advert();
		advert2.setAdName("测试广告1");
		advert2.setRegisterUsrConsumeAmount(11.5);
		advert2.setRegisterUsrDayConsumeAmount(22.7);
		advert2.setLoginUsrConsumeAmount(33.2);
		advert2.setLoginUsrDayConsumeAmount(26.3);
		advert2.setConsumeUsrAmount(66.7);
		advert2.setConsumeUsrDayAmount(88.6);
		advert2.setConsumeUsrAvgAmount(17.2);
		advert2.setLonginUsrAvgAmount(53.4);
		
		Advert advert1 = new Advert();
		advert1.setAdName("测试广告2");
		advert1.setRegisterUsrConsumeAmount(15.5);
		advert1.setRegisterUsrDayConsumeAmount(32.7);
		advert1.setLoginUsrConsumeAmount(53.2);
		advert1.setLoginUsrDayConsumeAmount(16.3);
		advert1.setConsumeUsrAmount(36.7);
		advert1.setConsumeUsrDayAmount(78.6);
		advert1.setConsumeUsrAvgAmount(37.2);
		advert1.setLonginUsrAvgAmount(23.4);
		
		advertAssortList.add(advert2);
		advertAssortList.add(advert1);
		
		request.setAttribute("advertNumList", advertNumList);
		request.setAttribute("advertAssortList", advertAssortList);
		request.setAttribute("page", advert);//分页信息
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
		
		int count = 0;
		//count = advertService.getAdvertCount(advert);
		advert.setPageSize(10);
		advert.setTotal(count);
		advert.setCurrentTotal(count);
		
		List<Advert> advertNumList = new ArrayList<Advert>();//合计数值
		List<Advert> advertAssortList = new ArrayList<Advert>();//分类数值 //advertService.getAdvertList(advert);
		request.setAttribute("advertNumList", advertNumList);
		request.setAttribute("advertAssortList", advertAssortList);
		request.setAttribute("page", advert);//分页信息
		return "consumption-analysis";
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
			Advert advert = advertTerms(request);
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
