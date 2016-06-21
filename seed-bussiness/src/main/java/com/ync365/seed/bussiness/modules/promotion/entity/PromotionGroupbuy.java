package com.ync365.seed.bussiness.modules.promotion.entity;

import java.math.BigDecimal;

public class PromotionGroupbuy {
    private Integer promotionId;

    private Integer groupbuyType;

    private BigDecimal groupbuyMoney;

    private BigDecimal currentMoney;

    private Integer groupNumber;

    private Integer currentNumber;

    private Integer limitPerCount;

    private Integer isDelete;

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getGroupbuyType() {
        return groupbuyType;
    }

    public void setGroupbuyType(Integer groupbuyType) {
        this.groupbuyType = groupbuyType;
    }

    public BigDecimal getGroupbuyMoney() {
        return groupbuyMoney;
    }

    public void setGroupbuyMoney(BigDecimal groupbuyMoney) {
        this.groupbuyMoney = groupbuyMoney;
    }

    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(BigDecimal currentMoney) {
        this.currentMoney = currentMoney;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Integer getLimitPerCount() {
        return limitPerCount;
    }

    public void setLimitPerCount(Integer limitPerCount) {
        this.limitPerCount = limitPerCount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}