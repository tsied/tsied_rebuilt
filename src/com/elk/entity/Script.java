package com.elk.entity;


/**
 * 2016.03.11
 * @author rock
 *
 */
public class Script {
	private String id; //primary key
	
	private String esIndex;//ElasticSearch Index
	
	private String eSType;//ElasticSearch Type
	
	private String scriptName;//执行ElasticSearch脚本名称
	
	private String chartType;//图表类型（柱状图，折线图）
	
	private String divId;//展现层DIV ID
	
	private String chartName;//指标名称
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEsIndex() {
		return esIndex;
	}

	public void setEsIndex(String esIndex) {
		this.esIndex = esIndex;
	}

	public String geteSType() {
		return eSType;
	}

	public void seteSType(String eSType) {
		this.eSType = eSType;
	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	
}
