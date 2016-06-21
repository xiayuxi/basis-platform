package com.ync365.seed.bussiness.modules.goods.entity;

import java.util.Date;

public class StocksLog {
    private Integer logId;

    private Integer frontStockNum;

    private Integer backStockNum;

    private Integer stockNum;

    private Integer skuId;

    private Date optDate;

    private Integer optUser;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
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

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public Integer getOptUser() {
        return optUser;
    }

    public void setOptUser(Integer optUser) {
        this.optUser = optUser;
    }
}