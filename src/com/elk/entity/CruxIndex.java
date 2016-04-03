package com.elk.entity;

import java.util.Date;

import com.elk.utils.PageSearchForm;

/**
 * @date 2016-03-22
 * @desc 关健指标
 * @author rock
 *
 */
public class CruxIndex extends PageSearchForm{
	private String adName;//广告名称
	private Date adStartDate;//广告开始时间
	private String adProject;//所属项目
	private Integer registerUser;//注册用户
	private Integer loginUserNum;//登录用户数
	private Integer consumeUserNum;//消费用户数
	private Integer consumeUserCount;//用户消费次数
	private Integer nextDayRetention;//次日留存率
	private Integer thrDaysRetention;//3日留存率
	private Integer sevenDaysRetention;//7日留存率
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
	public String getAdProject() {
		return adProject;
	}
	public void setAdProject(String adProject) {
		this.adProject = adProject;
	}
	public Integer getRegisterUser() {
		return registerUser;
	}
	public void setRegisterUser(Integer registerUser) {
		this.registerUser = registerUser;
	}
	public Integer getLoginUserNum() {
		return loginUserNum;
	}
	public void setLoginUserNum(Integer loginUserNum) {
		this.loginUserNum = loginUserNum;
	}
	public Integer getConsumeUserNum() {
		return consumeUserNum;
	}
	public void setConsumeUserNum(Integer consumeUserNum) {
		this.consumeUserNum = consumeUserNum;
	}
	public Integer getConsumeUserCount() {
		return consumeUserCount;
	}
	public void setConsumeUserCount(Integer consumeUserCount) {
		this.consumeUserCount = consumeUserCount;
	}
	public Integer getNextDayRetention() {
		return nextDayRetention;
	}
	public void setNextDayRetention(Integer nextDayRetention) {
		this.nextDayRetention = nextDayRetention;
	}
	
	public Integer getThrDaysRetention() {
		return thrDaysRetention;
	}
	public void setThrDaysRetention(Integer thrDaysRetention) {
		this.thrDaysRetention = thrDaysRetention;
	}
	public Integer getSevenDaysRetention() {
		return sevenDaysRetention;
	}
	public void setSevenDaysRetention(Integer sevenDaysRetention) {
		this.sevenDaysRetention = sevenDaysRetention;
	}
	
	
	
}
