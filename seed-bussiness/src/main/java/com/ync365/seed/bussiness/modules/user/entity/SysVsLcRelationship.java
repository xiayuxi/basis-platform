package com.ync365.seed.bussiness.modules.user.entity;

public class SysVsLcRelationship {
	
	/**
	 * vs编号
	 */
    private String vsNum;

    /**
     * lc编号
     */
    private String lcNum;

    public String getVsNum() {
        return vsNum;
    }

    public void setVsNum(String vsNum) {
        this.vsNum = vsNum == null ? null : vsNum.trim();
    }

    public String getLcNum() {
        return lcNum;
    }

    public void setLcNum(String lcNum) {
        this.lcNum = lcNum == null ? null : lcNum.trim();
    }
}