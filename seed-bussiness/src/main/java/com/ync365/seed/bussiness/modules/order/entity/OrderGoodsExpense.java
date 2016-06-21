package com.ync365.seed.bussiness.modules.order.entity;

import java.math.BigDecimal;

public class OrderGoodsExpense {
    private Integer id;

    private BigDecimal villageExpense;

    private BigDecimal platformExpense;

    private BigDecimal lcExpense;

    private BigDecimal integralExpense;

    private BigDecimal planExpense;

    private BigDecimal seExpense;

    private BigDecimal aExpense;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}