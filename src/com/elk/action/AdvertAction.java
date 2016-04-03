package com.elk.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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

import com.elk.entity.Advert;
import com.elk.entity.Dic;
import com.elk.es.ElasticClient;
import com.elk.service.IAdvertService;
import com.elk.service.IIndexService;
import com.elk.utils.DateUtils;
import com.elk.utils.StringUtil;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
@RequestMapping("/ad")
public class AdvertAction extends BaseAction{
	
	@Resource
	private IIndexService indexService;
	
	@Resource
	private IAdvertService advertService;
	
	@Autowired
	private ElasticClient client;
	
	
	@RequestMapping(value="/index")
	public String  index(HttpServletRequest request,HttpServletResponse response) throws IOException{
			initPage(request,response);
			Advert advert = advertTerms(request);
			int count = 0;
			count = advertService.getAdvertCount(advert);
			advert.setPageSize(10);
			advert.setTotal(count);
			advert.setCurrentTotal(count);
			List<Advert> advertList = advertService.getAdvertList(advert);
			request.setAttribute("advertList", advertList);//广告信息
			request.setAttribute("page", advert);//分页信息
		return "ad";
	}
	
	/**
	 * 分页
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/pageForm")
	public String  page(HttpServletRequest request,HttpServletResponse response) throws IOException{
			initPage(request,response);
			int count = 0;
			Advert advert = advertTerms(request);
			advert.setPageSize(10);
			count = advertService.getAdvertCount(advert);
			if(!StringUtil.isBlank(request.getParameter("page"))){
				advert.setOffset(Integer.parseInt(request.getParameter("offset")));
			}
			List<Advert> advertList = advertService.getAdvertList(advert);
			advert.setTotal(count);
			advert.setCurrentTotal(count);			
			request.setAttribute("advertList", advertList);//广告信息
			request.setAttribute("page", advert);//分页信息
		return "ad";
	}	

	
	@RequestMapping(value="/add", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode  addAdvert(HttpServletRequest request,HttpServletResponse response) throws IOException{
			Advert advert = new Advert();
			advert.setAdName(request.getParameter("adName"));
			advert.setAdDomain(request.getParameter("adDomain")!= null ?request.getParameter("adDomain"):"");
			advert.setAdProject(request.getParameter("adProject")!=null?request.getParameter("adProject"):"");
			advert.setAdType(request.getParameter("adType")!=null?request.getParameter("adType"):"");
			advert.setAdSocialSoftware(request.getParameter("socialSoft")!=null?request.getParameter("socialSoft"):"");
			advert.setAdCostBudget(request.getParameter("adCostBudget")!=null?request.getParameter("adCostBudget"):"");
			advert.setAdStatus(request.getParameter("adStatus") != null?request.getParameter("adStatus"):"");
			advert.setAdAddr(request.getParameter("adAddr") != null?request.getParameter("adAddr"):"");
			advert.setAdStartTime(DateUtils.parseDate(request.getParameter("adStartTime")));
			advert.setAdEndTime(DateUtils.parseDate(request.getParameter("adEndTime")));
			advertService.saveAdvert(advert);
			setPage(request, response);
		return mapper.readTree("{\"success\":\"success\"}");
	}
	
	@RequestMapping(value="/modify", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode  modifyAdvert(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Advert advert = new Advert();
		advert.setAdId(request.getParameter("adId")!=null?Integer.parseInt(request.getParameter("adId")):0);
		advert.setAdName(request.getParameter("adName"));
		advert.setAdProject(request.getParameter("adProject")!=null?request.getParameter("adProject"):"");
		advert.setAdType(request.getParameter("adType")!=null?request.getParameter("adType"):"");
		advert.setAdSocialSoftware(request.getParameter("socialSoft")!=null?request.getParameter("socialSoft"):"");
		advert.setAdCostBudget(request.getParameter("adCostBudget")!=null?request.getParameter("adCostBudget"):"");
		advert.setAdStatus(request.getParameter("adStatus") != null?request.getParameter("adStatus"):"");
		advert.setAdAddr(request.getParameter("adAddr") != null?request.getParameter("adAddr"):"");
		advert.setAdDomain(request.getParameter("adDomain")!= null ?request.getParameter("adDomain"):"");
		advert.setAdStartTime(request.getParameter("adStartTime")!=null?DateUtils.parseDate(request.getParameter("adStartTime")):new Date());
		advert.setAdEndTime(request.getParameter("adEndTime")!=null?DateUtils.parseDate(request.getParameter("adEndTime")):new Date());
		advertService.modifyAdvert(advert);
		setPage(request, response);
		return mapper.readTree("{\"success\":\"success\"}");
	}
	
	
	@RequestMapping(value="/del", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode delAdvert(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Advert advert = new Advert();
		advert.setAdId(Integer.parseInt(request.getParameter("adId")));
		advertService.delAdvert(advert);
		initPage(request,response);
		setPage(request, response);
		return mapper.readTree("{\"success\":\"success\"}");
	}
	
	/**
	 * 分页
	 * @param request
	 * @param response
	 */
	public void setPage(HttpServletRequest request,HttpServletResponse response){
		int count = 0;
		Advert advertPage = new Advert();
		count = advertService.getAdvertCount(advertPage);
		advertPage.setPageSize(10);
		advertPage.setTotal(count);
		advertPage.setCurrentTotal(count);
		List<Advert> advertList = advertService.getAdvertList(advertPage);
		request.setAttribute("advertList", advertList);//广告信息
	}
	
	
	private Advert advertTerms(HttpServletRequest request)throws UnsupportedEncodingException {
		Advert advert = new Advert();
		if(!StringUtil.isBlank(request.getParameter("adName"))){
			request.setAttribute("adName",request.getParameter("adName"));
			advert.setAdName(request.getParameter("adName"));
		}
		if(!StringUtil.isBlank(request.getParameter("adSatus"))){
			request.setAttribute("adSatus", request.getParameter("adSatus"));
			advert.setAdStatus(request.getParameter("adSatus"));
		}
		if(!StringUtil.isBlank(request.getParameter("adProject"))){
			request.setAttribute("adProject", request.getParameter("adProject"));
			advert.setAdProject(request.getParameter("adProject"));
		}
		if(!StringUtil.isBlank(request.getParameter("adType"))){
			request.setAttribute("adType", request.getParameter("adType"));
			advert.setAdType(request.getParameter("adType"));
		}
		if(!StringUtil.isBlank(request.getParameter("startTime"))){
			request.setAttribute("startTime", request.getParameter("startTime"));
			advert.setAdStartEnd(request.getParameter("startTime")!=null?DateUtils.parseDate(request.getParameter("startTime")):null);
		}
		if(!StringUtil.isBlank(request.getParameter("startEnd"))){
			request.setAttribute("startEnd", request.getParameter("startEnd"));
			advert.setAdStartEnd(request.getParameter("startEnd")!=null?DateUtils.parseDate(request.getParameter("startEnd")):null);
		}
		if(!StringUtil.isBlank(request.getParameter("endTime"))){
			request.setAttribute("endTime", request.getParameter("endTime"));
			advert.setAdEndTime(request.getParameter("endTime")!=null?DateUtils.parseDate(request.getParameter("endTime")):null);
		}
		if(!StringUtil.isBlank(request.getParameter("endEnd"))){
			request.setAttribute("endEnd", request.getParameter("endEnd"));
			advert.setAdEndEnd(request.getParameter("endEnd")!=null?DateUtils.parseDate(request.getParameter("endEnd")):null);
		}
		return advert;
	} 
	
	@RequestMapping(value = "/find", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode findAdvert(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Advert advert = new Advert();
		advert.setAdId(Integer.parseInt(request.getParameter("adId")));
		Advert rsPonseAdvert = advertService.getAdvert(advert);
		rsPonseAdvert.setStartDate(DateUtils.formatDate(rsPonseAdvert.getAdStartTime()));
		rsPonseAdvert.setEndDate(DateUtils.formatDate(rsPonseAdvert.getAdEndTime()));
		Map<String,Object> advertMap = new HashMap<String,Object>();
		advertMap.put("advert", rsPonseAdvert);
		List<Dic> adStatusList = indexService.getDicList("adStatus");
		advertMap.put("adStatus", adStatusList);
		List<Dic> proList = indexService.getDicList("adProject");
		advertMap.put("adProject", proList);
		List<Dic> adTypeList = indexService.getDicList("adType");
		advertMap.put("adType", adTypeList);
		List<Dic> socialSoftList = indexService.getDicList("socialSoft");
		advertMap.put("socialSoft", socialSoftList);
		return mapper.readTree(mapper.writeValueAsString(advertMap));
	}
	
}
