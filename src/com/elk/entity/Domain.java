package com.elk.entity;

import java.util.Date;

import com.elk.utils.PageSearchForm;

public class Domain extends PageSearchForm {

	private Integer domainId; //域名ID
	
	private String domainName; //域名名字
	
	private Integer domainProject;//域名项目ID
	
	private String domainProjectName;//域名项目
	
	private Integer domainAb;//域名AB站
	
	private Integer clickNum;//点击量
	
	private Integer userViewNum;//独立访问用户
	
	private Integer ipViewNum;//独立IP访问量
	
	private Integer targetPageNum;//目标页面点击量
	
	private Integer sessionNum;//会话数
	
	private Integer avgSessionViewNum;//平均会话浏览页
	
	private String  avgSessionDuration;//平均会话时长
	
	private String  bounceRate;//跳出率百分比
	
	private Date adTime;//广告时间
	
	private Double  registerUsrConsumeAmount;//注册用户消费金额
	
	private Double registerUsrDayConsumeAmount;//注册用户日均消费金额
	
	private Double loginUsrConsumeAmount;//登录用户消费金额
	
	private Double loginUsrDayConsumeAmount;//登录用户日均消费金额(占比)
	
	private Double consumeUsrAmount;//付费用户消费合计

	private Double consumeUsrDayAmount; //付费用户日均付费金额
	
	private Double  consumeUsrAvgAmount;//付费用户平均付费金额（ARPPU）
	
	private Double  longinUsrAvgAmount;//登录用户平均付费金额（ARPU）
	
	private Date adStartDate;//广告开始时间
	
	private Integer registerUser;//注册用户数
	
	private Integer loginUserNum;//登录用户数
	
	private Integer consumeUserNum;//消费用户数
	
	private Integer consumeUserCount;//用户消费次数
	
	private Double nextDayRetention;//次日留存率
	
	private Double thrDaysRetention;//3日留存率
	
	private Double sevenDaysRetention;//7日留存率
	
	private Integer loginUsrWeekCount;//周登录用户数
	
	private Integer consumeUsrWeekCount;//周消费用户数
	
	private Integer loginMonthCount;//月登录用户数
	
	private Integer consumeMonthCount;//月消费用户数

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

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getUserViewNum() {
		return userViewNum;
	}

	public void setUserViewNum(Integer userViewNum) {
		this.userViewNum = userViewNum;
	}

	public Integer getIpViewNum() {
		return ipViewNum;
	}

	public void setIpViewNum(Integer ipViewNum) {
		this.ipViewNum = ipViewNum;
	}

	public Integer getTargetPageNum() {
		return targetPageNum;
	}

	public void setTargetPageNum(Integer targetPageNum) {
		this.targetPageNum = targetPageNum;
	}

	public Integer getSessionNum() {
		return sessionNum;
	}

	public void setSessionNum(Integer sessionNum) {
		this.sessionNum = sessionNum;
	}

	public Integer getAvgSessionViewNum() {
		return avgSessionViewNum;
	}

	public void setAvgSessionViewNum(Integer avgSessionViewNum) {
		this.avgSessionViewNum = avgSessionViewNum;
	}

	public String getAvgSessionDuration() {
		return avgSessionDuration;
	}

	public void setAvgSessionDuration(String avgSessionDuration) {
		this.avgSessionDuration = avgSessionDuration;
	}

	public String getBounceRate() {
		return bounceRate;
	}

	public void setBounceRate(String bounceRate) {
		this.bounceRate = bounceRate;
	}

	public Date getAdTime() {
		return adTime;
	}

	public void setAdTime(Date adTime) {
		this.adTime = adTime;
	}

	public Double getRegisterUsrConsumeAmount() {
		return registerUsrConsumeAmount;
	}

	public void setRegisterUsrConsumeAmount(Double registerUsrConsumeAmount) {
		this.registerUsrConsumeAmount = registerUsrConsumeAmount;
	}

	public Double getRegisterUsrDayConsumeAmount() {
		return registerUsrDayConsumeAmount;
	}

	public void setRegisterUsrDayConsumeAmount(Double registerUsrDayConsumeAmount) {
		this.registerUsrDayConsumeAmount = registerUsrDayConsumeAmount;
	}

	public Double getLoginUsrConsumeAmount() {
		return loginUsrConsumeAmount;
	}

	public void setLoginUsrConsumeAmount(Double loginUsrConsumeAmount) {
		this.loginUsrConsumeAmount = loginUsrConsumeAmount;
	}

	public Double getLoginUsrDayConsumeAmount() {
		return loginUsrDayConsumeAmount;
	}

	public void setLoginUsrDayConsumeAmount(Double loginUsrDayConsumeAmount) {
		this.loginUsrDayConsumeAmount = loginUsrDayConsumeAmount;
	}

	public Double getConsumeUsrAmount() {
		return consumeUsrAmount;
	}

	public void setConsumeUsrAmount(Double consumeUsrAmount) {
		this.consumeUsrAmount = consumeUsrAmount;
	}

	public Double getConsumeUsrDayAmount() {
		return consumeUsrDayAmount;
	}

	public void setConsumeUsrDayAmount(Double consumeUsrDayAmount) {
		this.consumeUsrDayAmount = consumeUsrDayAmount;
	}

	public Double getConsumeUsrAvgAmount() {
		return consumeUsrAvgAmount;
	}

	public void setConsumeUsrAvgAmount(Double consumeUsrAvgAmount) {
		this.consumeUsrAvgAmount = consumeUsrAvgAmount;
	}

	public Double getLonginUsrAvgAmount() {
		return longinUsrAvgAmount;
	}

	public void setLonginUsrAvgAmount(Double longinUsrAvgAmount) {
		this.longinUsrAvgAmount = longinUsrAvgAmount;
	}

	public Date getAdStartDate() {
		return adStartDate;
	}

	public void setAdStartDate(Date adStartDate) {
		this.adStartDate = adStartDate;
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

	public Double getNextDayRetention() {
		return nextDayRetention;
	}

	public void setNextDayRetention(Double nextDayRetention) {
		this.nextDayRetention = nextDayRetention;
	}

	public Double getThrDaysRetention() {
		return thrDaysRetention;
	}

	public void setThrDaysRetention(Double thrDaysRetention) {
		this.thrDaysRetention = thrDaysRetention;
	}

	public Double getSevenDaysRetention() {
		return sevenDaysRetention;
	}

	public void setSevenDaysRetention(Double sevenDaysRetention) {
		this.sevenDaysRetention = sevenDaysRetention;
	}

	public Integer getLoginUsrWeekCount() {
		return loginUsrWeekCount;
	}

	public void setLoginUsrWeekCount(Integer loginUsrWeekCount) {
		this.loginUsrWeekCount = loginUsrWeekCount;
	}

	public Integer getConsumeUsrWeekCount() {
		return consumeUsrWeekCount;
	}

	public void setConsumeUsrWeekCount(Integer consumeUsrWeekCount) {
		this.consumeUsrWeekCount = consumeUsrWeekCount;
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

	public String getDomainProjectName() {
		return domainProjectName;
	}

	public void setDomainProjectName(String domainProjectName) {
		this.domainProjectName = domainProjectName;
	}
	
	
}
