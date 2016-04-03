package com.elk.cache;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

/**
 * @Date 2016-03-12
 * @author rock
 *
 */
@Service("redisService")
public class RedisService {
	@Resource
	private RedisTemplate<String, String> redis;
	@Resource(name = "redisTemplate")
	private HashOperations<String, Object, String> hashOps;
	@Resource(name = "redisTemplate")
	private HashOperations<String, String, String> hashEntryOps;
	@Resource(name = "redisTemplate")
	private SetOperations<String, String> setOps;
	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOps;
	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOps;
	@Resource(name = "redisTemplate")
	private ZSetOperations<String, String> zsetOps;

	
	/**
	 * 存储ES数据
	 * @param indexName ES index name
	 * @param templateName  es templateName
	 * @param data   es data
	 */
	public void  putESData(String indexName,String templateName,String data){
		hashOps.put(indexName, templateName, data);
	}
	
	/**
	 * 根据indexName,templateName获取缓存中的ES数据
	 * @param indexName
	 * @param templateName
	 * @return
	 */
	public String getDataByTemplateName(String indexName,String templateName){
		return hashOps.get(indexName, templateName);
	}
	
	/**
	 * 存储ES脚本名称
	 * @param templateName
	 */
	public void putEsTemplate(String indexName,String templatesource){
		hashOps.put("esTemplate",indexName, templatesource);
	}
	
	/**
	 * 获取ES脚本集
	 * @return
	 */
	public Map<Object,String> getESTemplateName(){
		return hashOps.entries("esTemplate");
	}
	
	/**
	 * 存储报表模板名称
	 * @param templateName
	 */
	public void setReportTemplateName(String templateName, String templateSource){
		hashOps.put("reportTemplate",templateName, templateSource);
	}
	
	/**
	 * 获取报表模板名称集
	 * @return
	 */
	public Map<Object,String> getReportTemplateName(){
		return hashOps.entries("reportTemplate");
	}
	
	/**
	 * 存储配置好的脚本
	 * @param indexName 指标名称
	 * @param script 指标脚本
	 */
	public void setScript(String script){
		setOps.add("scripts", script);
	}
	
	/**
	 * 获取脚本
	 * @return
	 */
	public Set<String> getScript(){
		return setOps.members("scripts");
	}
	
}
