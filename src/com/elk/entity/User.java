package com.elk.entity;

/**
 * @date 2016-03-15
 * @author rock
 * @desc 用户实体类
 */
public class User {
	private String uId;//用户ID
	private String uName;//用户名称
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	
	
}
