package com.elk.service;

import java.util.List;

import com.elk.entity.Script;

/**
 * @date 2016-03-14
 * @author rock
 *
 */
public interface IScriptService {
	void saveScript(Script script);
	
	List<Script> getScriptList();
	
	Script getScriptByScript(Script script);
}
