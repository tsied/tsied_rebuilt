package com.elk.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.elk.utils.PageSearchForm;

/**
 * @date 2016-03-16
 * @author rock
 * @desc 广告管理
 */
public class Advert extends PageSearchForm{
	private Integer adId;//广告ID
	
	private String adName;//广告名称
	
	private String adStatus;//广告状态
	
	private String adProject;//所属项目
	
	private String adType;//广告类型
	
	private String adAddr;//广告地址
	
	private String adSocialSoftware;//社交软件
	
	private String adCostBudget;//成本预算
	
	private Date adStartTime;//开始时间
	
	private Date adStartEnd;//开始截止时间
	
	private Date adEndTime;//结束时间
	
	private Date adEndEnd;//结束截止时间
	
	private String adDomain;//投放域名
	
	private Integer startDay;//开始天数
	
	private Integer endDay;//结束天数
	
	private String startDate;//开始日期
	
	private String endDate;//结束日期
	
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
	public Double getRegisterUsrDayConsumeAmount() {
		return registerUsrDayConsumeAmount;
	}
	public void setRegisterUsrDayConsumeAmount(Double registerUsrDayConsumeAmount) {
		this.registerUsrDayConsumeAmount = registerUsrDayConsumeAmount;
	}
	public Double getLoginUsrDayConsumeAmount() {
		return loginUsrDayConsumeAmount;
	}
	public void setLoginUsrDayConsumeAmount(Double loginUsrDayConsumeAmount) {
		this.loginUsrDayConsumeAmount = loginUsrDayConsumeAmount;
	}
	public Double getRegisterUsrConsumeAmount() {
		return registerUsrConsumeAmount;
	}
	public void setRegisterUsrConsumeAmount(Double registerUsrConsumeAmount) {
		this.registerUsrConsumeAmount = registerUsrConsumeAmount;
	}
	public Double getLoginUsrConsumeAmount() {
		return loginUsrConsumeAmount;
	}
	public void setLoginUsrConsumeAmount(Double loginUsrConsumeAmount) {
		this.loginUsrConsumeAmount = loginUsrConsumeAmount;
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
	public String getAdDomain() {
		return adDomain;
	}
	public void setAdDomain(String adDomain) {
		this.adDomain = adDomain;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	
	public void setAdEndEnd(Timestamp adEndEnd) {
		this.adEndEnd = adEndEnd;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdStatus() {
		return adStatus;
	}
	public void setAdStatus(String adStatus) {
		this.adStatus = adStatus;
	}
	public String getAdProject() {
		return adProject;
	}
	public void setAdProject(String adProject) {
		this.adProject = adProject;
	}
	public String getAdType() {
		return adType;
	}
	public void setAdType(String adType) {
		this.adType = adType;
	}
	public String getAdAddr() {
		return adAddr;
	}
	public void setAdAddr(String adAddr) {
		this.adAddr = adAddr;
	}
	public String getAdSocialSoftware() {
		return adSocialSoftware;
	}
	public void setAdSocialSoftware(String adSocialSoftware) {
		this.adSocialSoftware = adSocialSoftware;
	}
	public String getAdCostBudget() {
		return adCostBudget;
	}
	public void setAdCostBudget(String adCostBudget) {
		this.adCostBudget = adCostBudget;
	}
	public Date getAdStartTime() {
		return adStartTime;
	}
	public void setAdStartTime(Date adStartTime) {
		this.adStartTime = adStartTime;
	}
	public Date getAdStartEnd() {
		return adStartEnd;
	}
	public void setAdStartEnd(Date adStartEnd) {
		this.adStartEnd = adStartEnd;
	}
	public Date getAdEndTime() {
		return adEndTime;
	}
	public void setAdEndTime(Date adEndTime) {
		this.adEndTime = adEndTime;
	}
	public Date getAdEndEnd() {
		return adEndEnd;
	}
	public void setAdEndEnd(Date adEndEnd) {
		this.adEndEnd = adEndEnd;
	}
	
	
}
