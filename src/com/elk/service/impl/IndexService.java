package com.elk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elk.dao.IIndexDao;
import com.elk.entity.Dic;
import com.elk.entity.Menu;
import com.elk.entity.User;
import com.elk.service.IIndexService;

/**
 * @date 2015-03-15
 * @author rock
 */
@Service("indexService")
public class IndexService implements IIndexService{

	@Autowired
	private IIndexDao indexDao;
	
	@Override
	public List<Menu> getMenuList(User user) {
		return indexDao.getMenuList(user);
	}

	@Override
	public List<Dic> getDicList(String dicKey) {
		return indexDao.getDicList(dicKey);
	}

	@Override
	public String getIndexTypeByValue(String dicValue) {
		return indexDao.getIndexTypeByValue(dicValue);
	}

}
