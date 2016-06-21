package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;

public class SkuDTO {
    private Integer skuId;
    private String skuName;
    private Integer skuNum;
    private Boolean isSel;
    private BigDecimal marketPrice;
    private BigDecimal sellPrice;
    private BigDecimal lowerLimit;
    private BigDecimal upperLimit;
    private Integer status;
    private Integer isDelete;
    private Integer stockNum;
    private BigDecimal promPrice;
    private Integer promotionId;
    private String promontionName;
    private Long endTime;
    private Integer promotionType;
    private Integer hasPromotion;
    private BigDecimal skuTotalPrice;
    private Integer weight;
    private String imgUrl;
    private Integer goodsId;
    
    public Integer getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public BigDecimal getSkuTotalPrice() {
        return skuTotalPrice;
    }
    public void setSkuTotalPrice(BigDecimal skuTotalPrice) {
        this.skuTotalPrice = skuTotalPrice;
    }
    public Integer getSkuId() {
        return skuId;
    }
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }
    public String getSkuName() {
        return skuName;
    }
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }
    public BigDecimal getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }
    public BigDecimal getLowerLimit() {
        return lowerLimit;
    }
    public void setLowerLimit(BigDecimal lowerLimit) {
        this.lowerLimit = lowerLimit;
    }
    public BigDecimal getUpperLimit() {
        return upperLimit;
    }
    public void setUpperLimit(BigDecimal upperLimit) {
        this.upperLimit = upperLimit;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    
    public Integer getStockNum() {
        return stockNum;
    }
    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }
    public BigDecimal getPromPrice() {
        return promPrice;
    }
    public void setPromPrice(BigDecimal promPrice) {
        this.promPrice = promPrice;
    }
    public Integer getPromotionId() {
        return promotionId;
    }
    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }
    public String getPromontionName() {
        return promontionName;
    }
    public void setPromontionName(String promontionName) {
        this.promontionName = promontionName;
    }
    
    public Long getEndTime() {
        return endTime;
    }
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
    public Integer getPromotionType() {
        return promotionType;
    }
    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }
    public Integer getHasPromotion() {
        return hasPromotion;
    }
    public void setHasPromotion(Integer hasPromotion) {
        this.hasPromotion = hasPromotion;
    }
    public Integer getSkuNum() {
        return skuNum;
    }
    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }
    public Boolean getIsSel() {
        return isSel;
    }
    public void setIsSel(Boolean isSel) {
        this.isSel = isSel;
    }
}
