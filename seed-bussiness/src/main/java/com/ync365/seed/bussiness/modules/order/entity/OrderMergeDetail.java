package com.ync365.seed.bussiness.modules.order.entity;

import java.util.Date;

public class OrderMergeDetail {
    private Integer id;

    private Integer orderInfoId;

    private Integer orderMergeInfoId;

    private String orderNo;

    private String orderMergeNo;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public Integer getOrderMergeInfoId() {
        return orderMergeInfoId;
    }

    public void setOrderMergeInfoId(Integer orderMergeInfoId) {
        this.orderMergeInfoId = orderMergeInfoId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOrderMergeNo() {
        return orderMergeNo;
    }

    public void setOrderMergeNo(String orderMergeNo) {
        this.orderMergeNo = orderMergeNo == null ? null : orderMergeNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}