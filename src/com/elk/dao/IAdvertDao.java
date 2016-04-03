package com.elk.dao;

import java.util.List;

import com.elk.entity.Advert;

/**
 * @date 2016-03-17
 * @author rock
 *
 */
public interface IAdvertDao {
	int getAdvertCount(Advert advert);//获取广告信息总数
	List<Advert> getAdvertList(Advert advert);//获取广告信息集
	void saveAdvert(Advert advert);//保存广告信息
	Advert getAdvert(Advert advert);//获取广告信息
	void modifyAdvert(Advert advert);//修改广告
	void delAdvert(Advert advert);//删除广告
}
