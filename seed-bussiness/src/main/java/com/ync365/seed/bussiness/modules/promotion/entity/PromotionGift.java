package com.ync365.seed.bussiness.modules.promotion.entity;

import java.math.BigDecimal;

public class PromotionGift {
    private Integer promotionId;

    private Integer giftType;

    private Integer giftWay;

    private BigDecimal goodsAmount;

    private Integer goodsCount;

    private Integer isDelete;

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getGiftType() {
        return giftType;
    }

    public void setGiftType(Integer giftType) {
        this.giftType = giftType;
    }

    public Integer getGiftWay() {
        return giftWay;
    }

    public void setGiftWay(Integer giftWay) {
        this.giftWay = giftWay;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}