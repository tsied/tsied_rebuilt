package com.elk.entity;

import java.util.List;

/**
 * @date 2016-03-23
 * @author rock
 * @desc 报表指标选项
 */
public class ReportIndex {
	private String indexName;//指标名称
	private List<Integer> indexValue;//指标值
	private List<Long> indexDate;//指标日期
	
	public List<Long> getIndexDate() {
		return indexDate;
	}
	public void setIndexDate(List<Long> indexDate) {
		this.indexDate = indexDate;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public List<Integer> getIndexValue() {
		return indexValue;
	}
	public void setIndexValue(List<Integer> indexValue) {
		this.indexValue = indexValue;
	}
	
}
