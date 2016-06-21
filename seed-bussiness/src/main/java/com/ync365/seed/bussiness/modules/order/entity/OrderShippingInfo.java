package com.ync365.seed.bussiness.modules.order.entity;

import java.util.Date;

public class OrderShippingInfo {
    private Integer id;

    private Integer orderId;

    private String orderNo;

    private Integer orderType;

    private String preparedNo;

    private Date preparedTime;

    private String stockNo;

    private Date stockTime;

    private String delivelyNo;

    private Date delivelyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getPreparedNo() {
        return preparedNo;
    }

    public void setPreparedNo(String preparedNo) {
        this.preparedNo = preparedNo == null ? null : preparedNo.trim();
    }

    public Date getPreparedTime() {
        return preparedTime;
    }

    public void setPreparedTime(Date preparedTime) {
        this.preparedTime = preparedTime;
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo == null ? null : stockNo.trim();
    }

    public Date getStockTime() {
        return stockTime;
    }

    public void setStockTime(Date stockTime) {
        this.stockTime = stockTime;
    }

    public String getDelivelyNo() {
        return delivelyNo;
    }

    public void setDelivelyNo(String delivelyNo) {
        this.delivelyNo = delivelyNo == null ? null : delivelyNo.trim();
    }

    public Date getDelivelyTime() {
        return delivelyTime;
    }

    public void setDelivelyTime(Date delivelyTime) {
        this.delivelyTime = delivelyTime;
    }
}