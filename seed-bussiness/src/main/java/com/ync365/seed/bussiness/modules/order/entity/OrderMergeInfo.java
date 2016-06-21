package com.ync365.seed.bussiness.modules.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderMergeInfo {
    private Integer id;

    private String orderMergeNo;

    private String userNum;

    private String username;

    private Date createTime;

    private Integer orderStatus;

    private BigDecimal goodsAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderMergeNo() {
        return orderMergeNo;
    }

    public void setOrderMergeNo(String orderMergeNo) {
        this.orderMergeNo = orderMergeNo == null ? null : orderMergeNo.trim();
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }
}