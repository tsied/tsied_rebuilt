package com.elk.entity;

/**
 * @date 2016-03-23
 * @author rock
 * @desc 门户咨询数据
 */
public class HouseholdData {
	private Integer userViewNum;//独立用户数
	private Integer ipViewNum;//独立IP访问量
	private Integer pageViewNum;//页面浏览量（PV）
	private Integer sessionNum;//会话数
	private Integer reviewUserNum;//用户回访量
	private Double reviewRate;//回访率
	private Integer registerUserNum;//结束日期注册用户总数
	private Integer newRegisterUserNum;//新增注册用户数
	private Integer bounceNum;//跳出率
	private Integer avgSessionTime;//平均会话时长
	private Integer reqPageNum;//平均每次会话浏览页数
	
	
	public Integer getBounceNum() {
		return bounceNum;
	}
	public void setBounceNum(Integer bounceNum) {
		this.bounceNum = bounceNum;
	}
	public Integer getAvgSessionTime() {
		return avgSessionTime;
	}
	public void setAvgSessionTime(Integer avgSessionTime) {
		this.avgSessionTime = avgSessionTime;
	}
	public Integer getReqPageNum() {
		return reqPageNum;
	}
	public void setReqPageNum(Integer reqPageNum) {
		this.reqPageNum = reqPageNum;
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
	public Integer getPageViewNum() {
		return pageViewNum;
	}
	public void setPageViewNum(Integer pageViewNum) {
		this.pageViewNum = pageViewNum;
	}
	public Integer getSessionNum() {
		return sessionNum;
	}
	public void setSessionNum(Integer sessionNum) {
		this.sessionNum = sessionNum;
	}
	public Integer getReviewUserNum() {
		return reviewUserNum;
	}
	public void setReviewUserNum(Integer reviewUserNum) {
		this.reviewUserNum = reviewUserNum;
	}
	public Double getReviewRate() {
		return reviewRate;
	}
	public void setReviewRate(Double reviewRate) {
		this.reviewRate = reviewRate;
	}
	public Integer getRegisterUserNum() {
		return registerUserNum;
	}
	public void setRegisterUserNum(Integer registerUserNum) {
		this.registerUserNum = registerUserNum;
	}
	public Integer getNewRegisterUserNum() {
		return newRegisterUserNum;
	}
	public void setNewRegisterUserNum(Integer newRegisterUserNum) {
		this.newRegisterUserNum = newRegisterUserNum;
	}
}
