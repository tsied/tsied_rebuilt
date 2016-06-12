package com.elk.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/ad")
public class AdvertAction extends BaseAction{
	
	@Resource
	private IIndexService indexService;
	
	@Resource
	private IAdvertService advertService;
	
	@Autowired
	private ElasticClient client;
	
	Integer successStats = 1;
	Integer failStats = 0;
	
	
	@RequestMapping(value="/index")
	public String  index(HttpServletRequest request,HttpServletResponse response) throws IOException{
			Map<String,Object> pageMap = initPage(request,response);
			response.addHeader("Access-Control-Allow-Origin","*");
//			Advert advert = advertTerms(request);
//			int count = 0;
//			count = advertService.getAdvertCount(advert);
//			advert.setTotal(count);
//			advert.setCurrentTotal(count);
//			List<Advert> advertList = advertService.getAdvertList(advert);
//			List<Map<String,Object>> advertList = advertService.getAdvertMap(advert);
//			ObjectMapper mapper = new ObjectMapper();
//			String result = mapper.writeValueAsString(advertList);
//			System.out.println(result);
//			request.setAttribute("advertList", advertList);//广告信息
//			request.setAttribute("page", advert);//分页信息
//			
//			pageMap.put("advertlist", advertList);
//			String result = mapper.writeValueAsString(pageMap);
//			System.out.println(mapper.readTree(result));
		return "ad";
	}
	
	@RequestMapping(value="/getdata")
	public @ResponseBody JsonNode  getdata(HttpServletRequest request,HttpServletResponse response) throws IOException{
			Map<String, Object> pageData = new HashMap<String, Object>();		
			
			response.addHeader("Access-Control-Allow-Origin","*");
			Map<String,Object> pageMap = initPage(request,response);
			Advert advert = advertTerms(request);
			int count = 0;
			count = advertService.getAdvertCount(advert);
			advert.setTotal(count);
			advert.setCurrentTotal(count);
//			List<Advert> advertList = advertService.getAdvertList(advert);
			
//			Integer adId = null;
//			if(request.getParameter("adId")!=null){
//				adId = Integer.parseInt(request.getParameter("adId"));
//				advert.setAdId(adId);
//				pageData.put("status", 1);
//				pageData.put("message", "修改成功");
//			}
			
			List<Map<String,Object>> advertList = advertService.getAdvertMap(advert);
			ObjectMapper mapper = new ObjectMapper();
//			String result = mapper.writeValueAsString(advertList);
//			System.out.println(result);
//			request.setAttribute("advertList", advertList);//广告信息
//			request.setAttribute("page", advert);//分页信息
												
			pageMap.put("advertlist", advertList);			
			pageData.put("status", successStats);
			pageData.put("message", " ");
			pageData.put("data", pageMap);
			String result = mapper.writeValueAsString(pageData);
			System.out.println(mapper.readTree(result));
			
		return mapper.readTree(result);
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

	
	@RequestMapping(value="/addormodify", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode  addAdvert(HttpServletRequest request,HttpServletResponse response) throws IOException{
			Advert advert = new Advert();
			Map<String, Object> pageData = new HashMap<String, Object>();
			String result;
			List<String> errorMsg = new ArrayList<String>();
			response.addHeader("Access-Control-Allow-Origin","*");
			Integer adId = null;
			String adName = "";
			String adDomain = "";
			String adProject= "";
			String adType = "";
			String adSocialSoftware = "";
			String adCostBudget = "";
			String adStatus = "";
			String adAddr = "";
			String adStartTime = "";
			String adEndTime = "";
						
			if(request.getParameter("adName")!=""){
				adName = request.getParameter("adName");
			}else{
				errorMsg.add("广告名称不能为空");
			} 
			
			if(request.getParameter("adDomain")!=""){
				adDomain = request.getParameter("adDomain");
			}else{
				errorMsg.add("投放域名不能为空");
			} 
			
			if(request.getParameter("adProject")!=""){
				adProject = request.getParameter("adProject");
			}else{
				errorMsg.add("所属项目不能为空");
			} 
			
			if(request.getParameter("adType")!=""){
				adType = request.getParameter("adType");
			}else{
				errorMsg.add("广告类型不能为空");
			}
			
			if(request.getParameter("adSocialSoftware")!=""){
				adSocialSoftware = request.getParameter("adSocialSoftware");
			}else{
				errorMsg.add("社交软件不能为空");
			}
			
			if(request.getParameter("adCostBudget")!=""){
				adCostBudget = request.getParameter("adCostBudget");
			}else{
				errorMsg.add("成本预算不能为空");
			}
			
			if(request.getParameter("adStatus")!=""){
				adStatus = request.getParameter("adStatus");
			}else{
				errorMsg.add("广告状态不能为空");
			}
			
			if(request.getParameter("adAddr")!=""){
				adAddr = request.getParameter("adAddr");
			}else{
				errorMsg.add("广告地址不能为空");
			}
			
			if(request.getParameter("adStartTime")!=""){
				adStartTime = request.getParameter("adStartTime");
			}else{
				errorMsg.add("广告开始时间不能为空");
			}
			
			if(request.getParameter("adEndTime")!=""){
				adEndTime = request.getParameter("adEndTime");
			}else{
				errorMsg.add("广告结束时间不能为空");
			}
						
			advert.setAdName(adName);
			advert.setAdDomain(adDomain);
			advert.setAdProject(adProject);
			advert.setAdType(adType);
			advert.setAdSocialSoftware(adSocialSoftware);
			advert.setAdCostBudget(adCostBudget);
			advert.setAdStatus(adStatus);
			advert.setAdAddr(adAddr);
			advert.setAdStartTime(DateUtils.parseDate(adStartTime));
			advert.setAdEndTime(DateUtils.parseDate(adEndTime));
			
			if(request.getParameter("adId")!=null){
				adId = Integer.parseInt(request.getParameter("adId"));
				advert.setAdId(adId);
				advertService.modifyAdvert(advert);
				pageData.put("status", successStats);
				pageData.put("message", "修改成功");
			}else{			
				if (errorMsg.size() > 0){
					pageData.put("status", failStats);
					String adErrorMsgQuery = StringUtils.join(errorMsg, ",");
					pageData.put("message", adErrorMsgQuery);
	//				result = mapper.writeValueAsString(pageData);
				}else{
								
					List<Advert> adList = advertService.getAdvertList(advert);
					if (adList.size() > 0){
						pageData.put("status", failStats);
						pageData.put("message", "添加的广告已存在");
					}else{
						advertService.saveAdvert(advert);
						pageData.put("status", successStats);
						pageData.put("message", "添加成功");
					}
					
													
				}
			}
						
			result = mapper.writeValueAsString(pageData);
			
			return mapper.readTree(result);
//			setPage(request, response);
		
	}
	
	
	@RequestMapping(value="/del", produces = "application/json;charset=UTF-8")
	public @ResponseBody JsonNode delAdvert(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Advert advert = new Advert();
		Map<String, Object> pageData = new HashMap<String, Object>();
		String result;
		response.addHeader("Access-Control-Allow-Origin","*");
		advert.setAdId(Integer.parseInt(request.getParameter("adId")));
		advertService.delAdvert(advert);
		pageData.put("status", successStats);
		pageData.put("message", "删除成功");
		result = mapper.writeValueAsString(pageData);
//		initPage(request,response);
//		setPage(request, response);
		return mapper.readTree(result);
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
			advert.setAdStartTime(request.getParameter("startTime")!=null?DateUtils.parseDate(request.getParameter("startTime")):null);
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
