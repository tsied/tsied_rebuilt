package com.elk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elk.dao.IAdvertDao;
import com.elk.entity.Advert;
import com.elk.service.IAdvertService;

@Service("advertService")
public class AdvertService implements IAdvertService{

	@Autowired
	private IAdvertDao advertDao;
	
	@Override
	public List<Advert> getAdvertList(Advert advert) {
		return advertDao.getAdvertList(advert);
	}
	
	@Override
	public int getAdvertCount(Advert advert) {
		return advertDao.getAdvertCount(advert);
	}

	@Override
	public void saveAdvert(Advert advert) {
		advertDao.saveAdvert(advert);
	}

	@Override
	public Advert getAdvert(Advert advert) {
		return advertDao.getAdvert(advert);
	}

	@Override
	public void modifyAdvert(Advert advert) {
		advertDao.modifyAdvert(advert);
	}

	@Override
	public void delAdvert(Advert advert) {
		advertDao.delAdvert(advert);
	}

}
