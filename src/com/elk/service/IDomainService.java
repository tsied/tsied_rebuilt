package com.elk.service;

import java.util.List;

import com.elk.entity.Domain;

public interface IDomainService {
	int getDomainCount(Domain domain);//获取域名信息总数
	List<Domain> getDomainList(Domain domain);//获取域名信息集

}
