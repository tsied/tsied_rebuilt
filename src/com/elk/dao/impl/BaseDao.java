package com.elk.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @date 2016-03-14
 * @author rock
 *
 */
public class BaseDao extends SqlSessionDaoSupport{
	@Autowired
	 public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate)
	    {
	     super.setSqlSessionTemplate(sqlSessionTemplate);
	    }
}
