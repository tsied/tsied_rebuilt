package com.elk.dao;

import java.util.List;

import com.elk.entity.Dic;
import com.elk.entity.Menu;
import com.elk.entity.User;

/**
 * @date 2016-03-15
 * @author rock
 */
public interface IIndexDao {
	List<Menu> getMenuList(User user);
	List<Dic>  getDicList(String dicKey);
	String getIndexTypeByValue(String dicValue);
	String getIndexByValue(Integer dicValue);
}
