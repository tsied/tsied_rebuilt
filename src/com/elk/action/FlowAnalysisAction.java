package com.elk.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.elk.entity.Advert;
import com.elk.service.impl.ElasticQueryServiceImpl;
import com.elk.utils.DateUtils;

/**
 * @desc 流量分析
 * @author rock
 *
 */
@Controller
@RequestMapping("/flow")
public class FlowAnalysisAction extends BaseAction {

	private static Logger log = LoggerFactory.getLogger(FlowAnalysisAction.class);

	@Autowired
	private ElasticQueryServiceImpl elasticQueryService;

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping()
	public ModelAndView defaultHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initPage(request, response);
		ModelAndView mav = new ModelAndView();

		// 获取所需的请求参数
		String paramAdName = request.getParameter("adName");// 广告类型
		String paramAdProject = request.getParameter("adProject");// 广告所属项目
		String paramAdType = request.getParameter("adType");// 广告类型
		String paramAdSatus = request.getParameter("adSatus");// 广告状态
		String paramAdStartTime = request.getParameter("adStartTime");// 广告投放开始时间
		String paramAdEndTime = request.getParameter("adEndTime");// 广告投放结束时间时间

		// 获取对应的广告信息
		Advert queryAdvert = new Advert();
		queryAdvert.setAdName(StringUtils.isNotBlank(paramAdName) ? paramAdName : null);
		queryAdvert.setAdProject(StringUtils.isNotBlank(paramAdProject) && !"0".equals(paramAdProject) ? paramAdProject
				: null);
		queryAdvert.setAdType(StringUtils.isNotBlank(paramAdType) && !"0".equals(paramAdType) ? paramAdType : null);
		queryAdvert
				.setAdStatus(StringUtils.isNotBlank(paramAdSatus) && !"0".equals(paramAdSatus) ? paramAdSatus : null);
		queryAdvert.setPageSize(100000);
		List<Advert> advertAssortList = advertService.getAdvertList(queryAdvert);

		// ES 查询
		String esIndexName = StringUtils.isBlank(paramAdProject) || "0".equals(paramAdProject) ? "all_stats"
				: indexService.getIndexByValue(Integer.parseInt(paramAdProject));// 匹配项目和ES
																					// indexName
		Date adStartTime = DateUtils.daysDiff(-90);// ES日期查询区间
		Date adEndTime = DateUtils.daysDiff(90);// ES日期查询区间
		try {
			adStartTime = StringUtils.isNotBlank(paramAdStartTime) ? DateUtils.parseDate(paramAdStartTime)
					: adStartTime;
			adEndTime = StringUtils.isNotBlank(paramAdEndTime) ? DateUtils.parseDate(paramAdEndTime) : adEndTime;
		} catch (Exception e) {
			log.error("[paramAdStartTime=" + paramAdStartTime + ",paramAdEndTime=" + paramAdEndTime
					+ "] can not be parese to Date.The default Date range is applied", e);
		}
		Map<String, Object> esAdData = elasticQueryService.flowRateQuery(esIndexName, adStartTime, adEndTime,
				advertAssortList);

		// push data to view layer
		mav.addObject("resultMap", esAdData);
		mav.addObject("paramAdName", paramAdName);
		mav.addObject("paramAdProject", StringUtils.isBlank(paramAdProject) || "0".equals(paramAdProject) ? 7
				: paramAdProject);
		mav.addObject("paramAdType", paramAdType);
		mav.addObject("paramAdSatus", paramAdSatus);
		mav.addObject("paramAdStartTime", DateUtils.formatDate(adStartTime));
		mav.addObject("paramAdEndTime", DateUtils.formatDate(adEndTime));
		mav.setViewName("flow-analysis");

		return mav;

	}
}
