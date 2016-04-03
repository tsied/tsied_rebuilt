package com.elk.entity;

import java.util.List;

public class Menu {
	private Integer id;//菜单ID
	private String menuName;//菜单名称
	private Integer parentId;//菜单ID父节点
	private String menuLink;//菜单链接
	
	private List<Menu> childMenuList;
	
	
	public List<Menu> getChildMenuList() {
		return childMenuList;
	}
	public void setChildMenuList(List<Menu> childMenuList) {
		this.childMenuList = childMenuList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getMenuLink() {
		return menuLink;
	}
	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}
	
}
