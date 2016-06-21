package com.ync365.seed.bussiness.modules.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderExpense {
    private Integer id;

    private Integer orderId;

    private String orderNo;

    private Integer orderType;

    private BigDecimal villageExpense;

    private BigDecimal platformExpense;

    private BigDecimal lcExpense;

    private BigDecimal integralExpense;

    private BigDecimal planExpense;

    private BigDecimal seExpense;

    private BigDecimal aExpense;

    private String vsNum;

    private String lcNum;

    private String seNum;

    private String aNum;

    private Date createTime;
    
    private String popNum;
    
    public String getPopNum() {
        return popNum;
    }

    public void setPopNum(String popNum) {
        this.popNum = popNum;
    }

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

    public BigDecimal getVillageExpense() {
        return villageExpense;
    }

    public void setVillageExpense(BigDecimal villageExpense) {
        this.villageExpense = villageExpense;
    }

    public BigDecimal getPlatformExpense() {
        return platformExpense;
    }

    public void setPlatformExpense(BigDecimal platformExpense) {
        this.platformExpense = platformExpense;
    }

    public BigDecimal getLcExpense() {
        return lcExpense;
    }

    public void setLcExpense(BigDecimal lcExpense) {
        this.lcExpense = lcExpense;
    }

    public BigDecimal getIntegralExpense() {
        return integralExpense;
    }

    public void setIntegralExpense(BigDecimal integralExpense) {
        this.integralExpense = integralExpense;
    }

    public BigDecimal getPlanExpense() {
        return planExpense;
    }

    public void setPlanExpense(BigDecimal planExpense) {
        this.planExpense = planExpense;
    }

    public BigDecimal getSeExpense() {
        return seExpense;
    }

    public void setSeExpense(BigDecimal seExpense) {
        this.seExpense = seExpense;
    }

    public BigDecimal getaExpense() {
        return aExpense;
    }

    public void setaExpense(BigDecimal aExpense) {
        this.aExpense = aExpense;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}