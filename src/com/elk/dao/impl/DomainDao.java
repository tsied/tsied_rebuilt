package com.elk.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.elk.dao.IDomainDao;
import com.elk.entity.Domain;

/**
 * @date 2016-03-18
 * @author rock
 *
 */
@Repository
public class DomainDao extends BaseDao implements IDomainDao{

	@Override
	public List<Domain> getDomainList(Domain Domain) {
		return this.getSqlSession().selectList("domainList",Domain);
	}
	
	@Override
	public int getDomainCount(Domain Domain) {
		int count = 0;
		count =this.getSqlSession().selectList("Domaincount",Domain).size();
		return count;
	}

	
}
