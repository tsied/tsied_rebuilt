package com.elk.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.elk.dao.IScriptDao;
import com.elk.entity.Script;

/**
 * @date 2016-03-14
 * @author rock
 *
 */
@Repository
public class ScriptDao extends BaseDao implements IScriptDao{

	@Override
	public void saveScript(Script script) {
		this.getSqlSession().insert("saveScript",script);
	}

	@Override
	public List<Script> getScriptList() {
		return this.getSqlSession().selectList("scriptList");
	}

	@Override
	public Script getScriptByScript(Script script) {
		return this.getSqlSession().selectOne("script",script);
	}

}
