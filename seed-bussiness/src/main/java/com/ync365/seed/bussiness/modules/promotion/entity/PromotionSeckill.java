package com.ync365.seed.bussiness.modules.promotion.entity;

public class PromotionSeckill {
    private Integer promotionId;

    private Integer seckillType;

    private Integer limitPerson;

    private Integer limitPerCount;

    private Integer limitTotalCount;

    private Integer currentCount;

    private Integer isDelete;

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getSeckillType() {
        return seckillType;
    }

    public void setSeckillType(Integer seckillType) {
        this.seckillType = seckillType;
    }

    public Integer getLimitPerson() {
        return limitPerson;
    }

    public void setLimitPerson(Integer limitPerson) {
        this.limitPerson = limitPerson;
    }

    public Integer getLimitPerCount() {
        return limitPerCount;
    }

    public void setLimitPerCount(Integer limitPerCount) {
        this.limitPerCount = limitPerCount;
    }

    public Integer getLimitTotalCount() {
        return limitTotalCount;
    }

    public void setLimitTotalCount(Integer limitTotalCount) {
        this.limitTotalCount = limitTotalCount;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}