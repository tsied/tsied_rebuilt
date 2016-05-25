package com.elk.entity;

import com.elk.utils.PageSearchForm;

public class Domain extends PageSearchForm {

	private Integer domainId;
	
	private String domainName;
	
	private Integer domainProject;
	
	private Integer domainAb;

	public Integer getDomainId() {
		return domainId;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Integer getDomainProject() {
		return domainProject;
	}

	public void setDomainProject(Integer domainProject) {
		this.domainProject = domainProject;
	}

	public Integer getDomainAb() {
		return domainAb;
	}

	public void setDomainAb(Integer domainAb) {
		this.domainAb = domainAb;
	}
	
	
}
