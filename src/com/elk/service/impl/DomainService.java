package com.elk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elk.dao.IDomainDao;

import com.elk.entity.Domain;
import com.elk.service.IDomainService;


@Service("domainService")
public class DomainService implements IDomainService{

	@Autowired
	private IDomainDao domainDao;
	
	@Override
	public List<Domain> getDomainList(Domain Domain) {
		return domainDao.getDomainList(Domain);
	}
	
	@Override
	public int getDomainCount(Domain Domain) {
		return domainDao.getDomainCount(Domain);
	}


}
