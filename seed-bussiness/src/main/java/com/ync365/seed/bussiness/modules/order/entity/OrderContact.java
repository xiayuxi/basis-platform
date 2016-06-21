package com.ync365.seed.bussiness.modules.order.entity;

import java.util.Date;

public class OrderContact {
    private Integer id;

    private String orderNo;

    private String lcNum;

    private String vsNum;

    private String usNum;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getLcNum() {
        return lcNum;
    }

    public void setLcNum(String lcNum) {
        this.lcNum = lcNum == null ? null : lcNum.trim();
    }

    public String getVsNum() {
        return vsNum;
    }

    public void setVsNum(String vsNum) {
        this.vsNum = vsNum == null ? null : vsNum.trim();
    }

    public String getUsNum() {
        return usNum;
    }

    public void setUsNum(String usNum) {
        this.usNum = usNum == null ? null : usNum.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}