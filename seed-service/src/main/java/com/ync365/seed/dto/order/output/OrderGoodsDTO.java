package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;

public class OrderGoodsDTO {

    private Integer skuId;//商品skuId

    private String goodsName;//商品名称

    private String goodsUrl;//商品图片URL地址
    
    private BigDecimal goodsPrice;//单价 

    private Integer goodsCount;//商品数量
    
    private BigDecimal goodsPriceSum;//小计
    
    private Integer storeId;//店铺ID
    
    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getGoodsPriceSum() {
        return goodsPriceSum;
    }

    public void setGoodsPriceSum(BigDecimal goodsPriceSum) {
        this.goodsPriceSum = goodsPriceSum;
    }

   
}
