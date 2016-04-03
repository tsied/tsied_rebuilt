package com.elk.entity;

/**
 * @date 2016-03-10
 * @author rock
 *
 */
public class ESTemplate {
	private String templateName;//ElasticSearch脚本名称
	private String templateSource;//ElasticSearch脚本文件
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateSource() {
		return templateSource;
	}
	public void setTemplateSource(String templateSource) {
		this.templateSource = templateSource;
	}
	
}
