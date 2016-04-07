package com.elk.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.elk.es.Script;
import com.elk.service.IIndexService;
import com.elk.utils.DateUtils;
import com.elk.utils.StringUtils;
import com.elk.utils.TemplateUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

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
		
		Advert cruxIndex = new Advert();
		getCruxIndexData(request, cruxIndex);
		
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

	private void getCruxIndexData(HttpServletRequest request, Advert cruxIndex)
			throws IOException, JsonParseException, JsonMappingException, JsonProcessingException {
		int count = 0;
		cruxIndex.setAdStartTime(DateUtils.parseDate(DateUtils.formatDate(DateUtils.lastYear())));
		cruxIndex.setAdEndTime(DateUtils.parseDate(DateUtils.formatDate(DateUtils.getCurrent())));
		count = advertService.getAdvertCount(cruxIndex);
		cruxIndex.setPageSize(10);
		cruxIndex.setTotal(count);
		cruxIndex.setCurrentTotal(count);
		List<Advert> advertStatsList = advertService.getAdvertList(cruxIndex);//关健指标分类数值
		List<Advert> cruxIndexList = new ArrayList<Advert>();
		List<Advert> cruxIndexStatsList = new ArrayList<Advert>();//分类数值 
		int registerUsrNum = 0;
		int loginUsrNum = 0;
		int consumeUserNum = 0;
		int consumeCount = 0;
		double nextDayRetention = 0.00;
		double thrdDaysRetention = 0.00;
		double SevenDaysRetention = 0.00;
		String indexType = "";
		if(cruxIndex.getAdProject()!=null && !cruxIndex.getAdProject().equals("0")){
			indexType = indexService.getIndexTypeByValue(cruxIndex.getAdProject());
		}
		for (Advert ad : advertStatsList) {
			String templatePath = Thread.currentThread().getContextClassLoader().getResource("resource/template/es").getPath()+"/user-analysis.customcache";
			String content = client.readFile(templatePath);
			Script script = new Script("pay_stats",indexType,ad.getAdStartTime().getTime(),ad.getAdEndTime().getTime(),ad.getAdAddr());
			Map<String, String> resultMap = client.execQuery(content,script);
			resultMap.put("templateName", "user-analysis.ftl");
			String data = new TemplateUtil().formatData(resultMap);
			@SuppressWarnings("unchecked")
			Map<String,List<Map<String,Object>>> statsMap = mapper.readValue(data, Map.class);
			for (String key : statsMap.keySet()) {
				for(Map<String, Object> map:statsMap.get(key)){
					JsonNode node = mapper.readTree(mapper.writeValueAsString(map));
					String str = node.get("name").textValue();
					String dataValue = node.get("data").toString().substring(1, node.get("data").toString().length()-1);
					switch(str){
						case "regusercnt":ad.setRegisterUser("".equals(dataValue)?0:Integer.valueOf(dataValue));//注册用户数
						registerUsrNum += "".equals(dataValue)?0:Integer.valueOf(dataValue);
						break;
						case "loginusercnt":ad.setLoginUserNum("".equals(dataValue)?0:Integer.valueOf(dataValue));//登录用户数
						loginUsrNum += "".equals(dataValue)?0:Integer.valueOf(dataValue);
						break;
						case "payusercnt":ad.setConsumeUserNum("".equals(dataValue)?0:Integer.valueOf(dataValue));//消费用户数
						consumeUserNum += "".equals(dataValue)?0:Integer.valueOf(dataValue);
						break;
						case "paycnt":ad.setConsumeUserCount("".equals(dataValue)?0:Integer.valueOf(dataValue));//消费次数
						consumeCount += "".equals(dataValue)?0:Integer.valueOf(dataValue);
						break;
						case "ydrate":ad.setNextDayRetention("".equals(dataValue)?0:Double.valueOf(dataValue));//次日留存率
						nextDayRetention += "".equals(dataValue)?0:Double.valueOf(dataValue);
						break;
						case "tdrate":ad.setThrDaysRetention("".equals(dataValue)?0:Double.valueOf(dataValue));//三日留存率
						thrdDaysRetention += "".equals(dataValue)?0:Double.valueOf(dataValue);
						break;
						case "sdrate":ad.setSevenDaysRetention("".equals(dataValue)?0:Double.valueOf(dataValue));//七日留存率
						SevenDaysRetention += "".equals(dataValue)?0:Double.valueOf(dataValue);
						break;
					}
				}
			}
			cruxIndexList.add(ad);
		}
		
		Advert advertStats = new Advert();
		advertStats.setRegisterUser(registerUsrNum);
		advertStats.setLoginUserNum(loginUsrNum);
		advertStats.setConsumeUserNum(consumeUserNum);
		advertStats.setConsumeUserCount(consumeCount);
		advertStats.setNextDayRetention(nextDayRetention);
		advertStats.setThrDaysRetention(thrdDaysRetention);
		advertStats.setSevenDaysRetention(SevenDaysRetention);
		cruxIndexStatsList.add(advertStats);
		request.setAttribute("cruxIndexList", cruxIndexList);
		request.setAttribute("cruxIndexStatsList", cruxIndexStatsList);
		request.setAttribute("page", cruxIndex);//分页信息
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
		Advert cruxIndex = new Advert();
		cruxIndex.setAdName(request.getParameter("cruxAdName")!= null?request.getParameter("cruxAdName"):"");
		cruxIndex.setAdProject(request.getParameter("cruxAdProject"));
		cruxIndex.setAdType(request.getParameter("cruxAdType"));
		cruxIndex.setAdStatus(request.getParameter("cruxAdSatus"));
		/*if(!StringUtils.isBlank(request.getParameter("cruxStartDay"))){
			cruxIndex.setStartDay(Integer.parseInt(request.getParameter("cruxStartDay")));
		}
		if(!StringUtils.isBlank(request.getParameter("cruxEndDay"))){
			cruxIndex.setEndDay(Integer.parseInt(request.getParameter("cruxEndDay")));
		}*/
		cruxIndex.setAdStartTime(DateUtils.parseDate(request.getParameter("cruxStartDate")));
		cruxIndex.setAdEndTime(DateUtils.parseDate(request.getParameter("cruxEndDate")));
		getCruxIndexData(request, cruxIndex);
		request.setAttribute("cruxAdName",request.getParameter("cruxAdName"));
		request.setAttribute("cruxAdProject", request.getParameter("cruxAdProject"));
		request.setAttribute("cruxAdType", request.getParameter("cruxAdType"));
		request.setAttribute("cruxAdSatus", request.getParameter("cruxAdSatus"));
		request.setAttribute("cruxStartDay", request.getParameter("cruxStartDay"));
		request.setAttribute("cruxEndDay", request.getParameter("cruxEndDay"));
		request.setAttribute("cruxStartDate", request.getParameter("cruxStartDate"));
		request.setAttribute("cruxEndDate", request.getParameter("cruxEndDate"));
		
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
		
		/*if(!StringUtils.isBlank(request.getParameter("weekStartDay"))){
			weekIndex.setStartDay(Integer.parseInt(request.getParameter("weekStartDay")));
		}
		if(!StringUtils.isBlank(request.getParameter("weekEndDay"))){
			weekIndex.setEndDay(Integer.parseInt(request.getParameter("weekEndDay")));
		}*/
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
		
	/*	int count = 0;
		//count = advertService.getAdvertCount(weekIndex);
		weekIndex.setPageSize(10);
		weekIndex.setTotal(count);
		weekIndex.setCurrentTotal(count);
		*/
		
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
		
	/*	int count = 0;
		//count = advertService.getAdvertCount(weekIndex);
		monthIndex.setPageSize(10);
		monthIndex.setTotal(count);
		monthIndex.setCurrentTotal(count);*/
		
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

