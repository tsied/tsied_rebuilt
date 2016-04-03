package com.elk.entity;

/**
 * @Date 2016-03-15
 * @author rock
 *
 */
public class Dic {
	private Integer dicId;//字典ID
	private String dicKey;//字典KEY
	private String dicValue;//字典VALUE
	
	
	public String getDicKey() {
		return dicKey;
	}

	public Integer getDicId() {
		return dicId;
	}

	public void setDicId(Integer dicId) {
		this.dicId = dicId;
	}

	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}
	public String getDicValue() {
		return dicValue;
	}
	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}
	
}
