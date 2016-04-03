package com.elk.service;

import java.util.List;

import com.elk.entity.Dic;
import com.elk.entity.Menu;
import com.elk.entity.User;

/**
 * @date 2016-03-15
 * @author rock
 */
public interface IIndexService {
	List<Menu> getMenuList(User user);//获取菜单栏
	List<Dic> getDicList(String dicKey);//获取字典表
}
