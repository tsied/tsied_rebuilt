package com.elk.entity;

import java.util.Date;

import com.elk.utils.PageSearchForm;

/**
 * @date 2016-03-22
 * @author rock
 * @desc 月指标
 */
public class MonthIndex extends PageSearchForm{
	private String adName;//广告名称
	
	private Date adStartDate;//广告开始时间
	
	private Integer loginMonthCount;//月登录用户数
	
	private Integer consumeMonthCount;//月消费用户数
	
	private String adProject;//所属项目
	
	private String adStatus;//广告状态
	
	private String adType;//广告类型
	
	private Integer startDay;//开始天数
	
	private Integer endDay;//结束天数
	
	private String startDate;//开始日期
	
	private String endDate;//结束日期
	
	
	public String getAdStatus() {
		return adStatus;
	}
	public void setAdStatus(String adStatus) {
		this.adStatus = adStatus;
	}
	public String getAdType() {
		return adType;
	}
	public void setAdType(String adType) {
		this.adType = adType;
	}
	public Integer getStartDay() {
		return startDay;
	}
	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}
	public Integer getEndDay() {
		return endDay;
	}
	public void setEndDay(Integer endDay) {
		this.endDay = endDay;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setAdProject(String adProject) {
		this.adProject = adProject;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public Date getAdStartDate() {
		return adStartDate;
	}
	public void setAdStartDate(Date adStartDate) {
		this.adStartDate = adStartDate;
	}
	
	public Integer getLoginMonthCount() {
		return loginMonthCount;
	}
	public void setLoginMonthCount(Integer loginMonthCount) {
		this.loginMonthCount = loginMonthCount;
	}
	public Integer getConsumeMonthCount() {
		return consumeMonthCount;
	}
	public void setConsumeMonthCount(Integer consumeMonthCount) {
		this.consumeMonthCount = consumeMonthCount;
	}
	public String getAdProject() {
		return adProject;
	}
	
	
}
