package com.elk.es;

public class Script {
	private String indexName;//ElasticSearch indexName
	
	private String indexType;//ElasticSearch indexType
	
	private long startTime;//开始时间
	
	private long endTime;//结束时间
	
	private String advertAddr;//广告地址
	
	private String dateRang;
	
	public Script(String indexName,String indexType,long startTime,long endTime,String advertAddr){
		this.indexName = indexName;
		this.indexType = indexType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.advertAddr = advertAddr;
		
	}
	public Script(String indexName,String indexType,long startTime,long endTime){
		this.indexName = indexName;
		this.indexType = indexType;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String getDateRang() {
		return dateRang;
	}
	public void setDateRang(String dateRang) {
		this.dateRang = dateRang;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public String getAdvertAddr() {
		return advertAddr;
	}
	public void setAdvertAddr(String advertAddr) {
		this.advertAddr = advertAddr;
	}
	
}
