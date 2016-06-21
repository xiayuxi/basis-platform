package com.ync365.seed.bussiness.modules.order.entity;

import java.util.Date;

public class UserExpenseLog {
    private Integer id;

    private String useNum;

    private Integer userExpenseId;

    private String orderNo;

    private Integer orderId;

    private Integer orderType;

    private Integer status;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUseNum() {
        return useNum;
    }

    public void setUseNum(String useNum) {
        this.useNum = useNum == null ? null : useNum.trim();
    }

    public Integer getUserExpenseId() {
        return userExpenseId;
    }

    public void setUserExpenseId(Integer userExpenseId) {
        this.userExpenseId = userExpenseId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}