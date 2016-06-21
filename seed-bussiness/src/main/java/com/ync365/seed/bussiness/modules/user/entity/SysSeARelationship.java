package com.ync365.seed.bussiness.modules.user.entity;

public class SysSeARelationship {
	/**
	 * se编号
	 */
    private String seNum;
    
	/**
	 * a+编号
	 */
    private String aNum;

    public String getSeNum() {
        return seNum;
    }

    public void setSeNum(String seNum) {
        this.seNum = seNum == null ? null : seNum.trim();
    }

    public String getaNum() {
        return aNum;
    }

    public void setaNum(String aNum) {
        this.aNum = aNum == null ? null : aNum.trim();
    }
}