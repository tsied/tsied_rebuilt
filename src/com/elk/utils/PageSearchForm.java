/* 
 * Copyright (C), 2004-2010, 三五互联科技股份有限公司
 * File Name: com.c35.sofia.dm.common.pojo.SearchObject.java
 * Encoding UTF-8 
 * Version: 1.0 
 * Date: 2010-12-21
 * History:
 * 1. Date: 2010-12-21
 *    Author: guojl
 *    Modification: 新建
 * 2. ...
 */
package com.elk.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class PageSearchForm {
	private int pageNo = 1;	//第几页,初其化为1
	private int pageTotal;
	private int offset;
	private int pageSize = 10; 
	private int sortFieldNo;// 排序字段
	private int ascending;// 正倒序(0=倒,1=升)

	private boolean haveFirst;
	private boolean haveLast;
	private int first;
	private int pre;
	private int next;
	private int last;
	private int total;
	
	private String searchKey; //搜索关键字
	private String searchKeyBak; //搜索关键字
	private List<?> datas;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageNo(){
		this.pageNo = this.getOffset() / this.getPageSize() + 1;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getSortFieldNo() {
		return sortFieldNo;
	}

	public void setSortFieldNo(int sortFieldNo) {
		this.sortFieldNo = sortFieldNo;
	}

	public int getAscending() {
		return ascending;
	}

	public void setAscending(int ascending) {
		this.ascending = ascending;
	}

	public boolean isHaveFirst() {
		return haveFirst;
	}

	public void setHaveFirst(boolean haveFirst) {
		this.haveFirst = haveFirst;
	}

	public boolean isHaveLast() {
		return haveLast;
	}

	public void setHaveLast(boolean haveLast) {
		this.haveLast = haveLast;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public Map<String, Object> toParameterMap() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("offset", offset);
		retMap.put("pageSize", pageSize);
		retMap.put("sortFieldNo", sortFieldNo);
		retMap.put("ascending", ascending);
		return retMap;
	}

	public void setCurrentTotal(int currentTotal) {
		total = currentTotal;
		if (total <= offset) {
			offset = total - ((total % pageSize)==0?pageSize:(total % pageSize));
			if(offset<0){
				offset=0;
			}
		}
		doCalculate();
	}

	public void setCurrentOffset(int currentOffset) {
		if (currentOffset + pageSize > total) {
			offset = total - total % pageSize;
		} else {
			offset = currentOffset;
		}
		doCalculate();
	}

	//计算页面上的内容
	private void doCalculate() {
		this.pageNo = (offset + this.pageSize) / this.pageSize;
		this.pageTotal = (total + this.pageSize-1) / this.pageSize;
		first = 0;
		if (offset - pageSize < 0) {
			pre = 0;
		} else {
			pre = offset - pageSize;
		}
		if (offset + pageSize > total) {
			next = total - total % pageSize;
		} else {
			next = offset + pageSize;
		}
		last = total - ((total % pageSize)==0?pageSize:(total % pageSize));

		if (offset <= 0) {
			haveFirst = false;
		} else {
			haveFirst = true;
		}
		if (offset >= last) {
			haveLast = false;
		} else {
			haveLast = true;
		}
		
	}

	public String getSearchKeyBak() {
		return searchKeyBak;
	}

	public void setSearchKeyBak(String searchKeyBak) {
		this.searchKeyBak = searchKeyBak;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

	public List<?> getDatas() {
		return datas;
	}
}
