package com.elk.dao;

import java.util.List;

import com.elk.entity.Script;

/**
 * @date 2016-03-14
 * @author rock
 *
 */
public interface IScriptDao {
	
	void saveScript(Script script);//保存指标脚本
	
	List<Script> getScriptList();//获取所有指标
	
	Script getScriptByScript(Script script);//根据指标条件获取指标
}
