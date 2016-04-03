package com.elk.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.elk.dao.IIndexDao;
import com.elk.entity.Dic;
import com.elk.entity.Menu;
import com.elk.entity.User;

/**
 * @date 2016-03-15
 * @author rock
 * @desc 菜单实现类
 */
@Repository
public class IndexDao extends BaseDao implements IIndexDao{
	@Override
	public List<Menu> getMenuList(User user) {
		return this.getSqlSession().selectList("menuList",user);
	}

	@Override
	public List<Dic> getDicList(String dicKey) {
		return this.getSqlSession().selectList("dicList",dicKey);
	}

}
