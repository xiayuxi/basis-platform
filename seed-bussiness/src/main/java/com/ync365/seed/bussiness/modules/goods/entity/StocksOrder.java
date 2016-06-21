package com.ync365.seed.bussiness.modules.goods.entity;

import java.util.Date;

public class StocksOrder {
    private Integer stocksOrderId;

    private Integer skuId;

    private Integer goodsId;

    private Integer orderId;

    private Integer status;

    private Integer stockNum;

    private Integer frontStockNum;

    private Integer backStockNum;
    
    /**
     * 操作是否成功  1:成功  
     */
    private Integer isSuccess;

    private Date createTime;

    public Integer getStocksOrderId() {
        return stocksOrderId;
    }

    public void setStocksOrderId(Integer stocksOrderId) {
        this.stocksOrderId = stocksOrderId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getFrontStockNum() {
        return frontStockNum;
    }

    public void setFrontStockNum(Integer frontStockNum) {
        this.frontStockNum = frontStockNum;
    }

    public Integer getBackStockNum() {
        return backStockNum;
    }

    public void setBackStockNum(Integer backStockNum) {
        this.backStockNum = backStockNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Integer getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}
    
    
}