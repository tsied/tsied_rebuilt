package com.elk.dao;

import java.util.List;

import com.elk.entity.Domain;

public interface IDomainDao {
	int getDomainCount(Domain domain);//获取广告信息总数
	List<Domain> getDomainList(Domain domain);//获取广告信息集
	

}
