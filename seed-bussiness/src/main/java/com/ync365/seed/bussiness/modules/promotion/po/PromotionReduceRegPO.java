package com.ync365.seed.bussiness.modules.promotion.po;

import java.math.BigDecimal;

public class PromotionReduceRegPO {
    private BigDecimal targetAmount;

    private BigDecimal reduceAmount;

    private Integer sort;

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public BigDecimal getReduceAmount() {
        return reduceAmount;
    }

    public void setReduceAmount(BigDecimal reduceAmount) {
        this.reduceAmount = reduceAmount;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}