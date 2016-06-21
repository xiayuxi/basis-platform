package com.ync365.seed.bussiness.modules.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderGoodsExpenseInfo {
    private Integer id;

    private Integer orderGoodsId;

    private BigDecimal villageExpense;

    private BigDecimal platformExpense;

    private BigDecimal lcExpense;

    private BigDecimal integralExpense;

    private BigDecimal planExpense;

    private BigDecimal seExpense;

    private BigDecimal aExpense;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Integer orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}