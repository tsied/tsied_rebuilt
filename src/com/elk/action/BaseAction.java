package com.elk.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.elk.entity.Dic;
import com.elk.entity.Menu;
import com.elk.entity.User;
import com.elk.es.ElasticClient;
import com.elk.service.IAdvertService;
import com.elk.service.IDomainService;
import com.elk.service.IIndexService;
import com.elk.utils.TemplateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @date 2016-03-29
 * @author rock
 *
 */
public class BaseAction {
	
	@Resource
	public IIndexService indexService;
	
	@Resource
	public IAdvertService advertService;
	
	@Resource
	public IDomainService domainService;
	
	@Autowired
	public ElasticClient client;
	
	public static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 初始化各页面选择框数据
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void initPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Map<String,String>> responsMenuList = getMenuList();
		request.setAttribute("responsMenuList", responsMenuList);//系统菜单
		
		List<Dic> adStatusList = indexService.getDicList("adStatus");
		request.setAttribute("adStatusList", adStatusList);//广告状态
		
		List<Dic> proList = indexService.getDicList("adProject");
		request.setAttribute("proList", proList);//所属项目
		
		List<Dic> adTypeList = indexService.getDicList("adType");
		request.setAttribute("adTypeList", adTypeList);//广告类型
		
		List<Dic> socialSoftList = indexService.getDicList("socialSoft");
		request.setAttribute("socialSoftList", socialSoftList);//社交软件
		
		List<Dic> indexTypeList = indexService.getDicList("indexType");
		request.setAttribute("indexTypeList", indexTypeList);//指标类型
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getMenuList() throws IOException{
		User user = new User();
		user.setuId("1");
		user.setuName("rock");
		List<Menu> menuList = indexService.getMenuList(user);
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("templateName", "menu.ftl");
		paramMap.put("data", mapper.writeValueAsString(menuList));
		String menuData = new TemplateUtil().formatData(paramMap);
		List<Map<String,String>> menuConvertList = mapper.readValue(menuData, List.class);
		return menuConvertList;
	}
}
