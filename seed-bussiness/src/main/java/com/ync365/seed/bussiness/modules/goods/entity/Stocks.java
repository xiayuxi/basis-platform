package com.ync365.seed.bussiness.modules.goods.entity;

import java.util.Date;

public class Stocks {
    private Integer stocksId;

    private Integer skuId;

    private Integer regionId;

    private Integer stockNum;

    private Integer outQty;

    private Integer inQty;

    private Integer optUser;

    private Date optDate;

    private String optTerm;

    private Integer goodsId;
    
    /**
     * 版本号
     */
    private Integer version;
    
    /**
     * 锁定库存量
     */
    private Integer lockQty;

    public Integer getStocksId() {
        return stocksId;
    }

    public void setStocksId(Integer stocksId) {
        this.stocksId = stocksId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getOutQty() {
        return outQty;
    }

    public void setOutQty(Integer outQty) {
        this.outQty = outQty;
    }

    public Integer getInQty() {
        return inQty;
    }

    public void setInQty(Integer inQty) {
        this.inQty = inQty;
    }

    public Integer getOptUser() {
        return optUser;
    }

    public void setOptUser(Integer optUser) {
        this.optUser = optUser;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public String getOptTerm() {
        return optTerm;
    }

    public void setOptTerm(String optTerm) {
        this.optTerm = optTerm == null ? null : optTerm.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getLockQty() {
		return lockQty;
	}

	public void setLockQty(Integer lockQty) {
		this.lockQty = lockQty;
	}
    
    
}