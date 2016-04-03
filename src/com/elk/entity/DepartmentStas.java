package com.elk.entity;

/**
 * @date 2016-03-24
 * @author rock
 * @desc IE事业群各项目合计数值
 */
public class DepartmentStas {
	private String projectName;//项目名称
	
	private Integer incomeStas;//总收入
	
	private Integer registerUserNum;//注册用户数
	
	private Integer newRegisterUserNum;//新增注册用户数
	
	private Integer viewUserNum;//独立用户访问量
	
	private Integer viewNum;//浏览量
	
	private Integer loginUserNum;//登录用户数
	
	private Integer clickNum;//点击量
	
	

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getIncomeStas() {
		return incomeStas;
	}

	public void setIncomeStas(Integer incomeStas) {
		this.incomeStas = incomeStas;
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

	public Integer getViewUserNum() {
		return viewUserNum;
	}

	public void setViewUserNum(Integer viewUserNum) {
		this.viewUserNum = viewUserNum;
	}

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public Integer getLoginUserNum() {
		return loginUserNum;
	}

	public void setLoginUserNum(Integer loginUserNum) {
		this.loginUserNum = loginUserNum;
	}
	
	
}
