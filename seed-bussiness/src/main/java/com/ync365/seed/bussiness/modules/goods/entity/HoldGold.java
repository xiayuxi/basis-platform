package com.ync365.seed.bussiness.modules.goods.entity;

import java.math.BigDecimal;

public class HoldGold {
	
	/**
	 * sku主键
	 */
	private Integer skuId;
	
	/**
	 * 商品主键
	 */
    private Integer goodsId;

    /**
     * vs 拥金
     */
    private BigDecimal vsAmount;

    /**
     * lc 拥金 
     */
    private BigDecimal lcAmount;

    /**
     * 工程师拥金 
     */
    private BigDecimal engineerAmount;

    /**
     * A+ 组长拥金 
     */
    private BigDecimal aAmount;

    /**
     * 供应商拥金
     */
    private BigDecimal supplierAmount;
    
    /***
     * 积分费用
     */
    private BigDecimal integrationCosts;
    
    
    /***
     * 统筹费用
     */
    private BigDecimal manpowerCosts;
    
    /**
     * 百分比
     */
    private BigDecimal percent;
    
 
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

    public BigDecimal getVsAmount() {
        return vsAmount;
    }

    public void setVsAmount(BigDecimal vsAmount) {
        this.vsAmount = vsAmount;
    }

    public BigDecimal getLcAmount() {
        return lcAmount;
    }

    public void setLcAmount(BigDecimal lcAmount) {
        this.lcAmount = lcAmount;
    }

    public BigDecimal getEngineerAmount() {
        return engineerAmount;
    }

    public void setEngineerAmount(BigDecimal engineerAmount) {
        this.engineerAmount = engineerAmount;
    }

    public BigDecimal getaAmount() {
        return aAmount;
    }

    public void setaAmount(BigDecimal aAmount) {
        this.aAmount = aAmount;
    }

    public BigDecimal getSupplierAmount() {
        return supplierAmount;
    }

    public void setSupplierAmount(BigDecimal supplierAmount) {
        this.supplierAmount = supplierAmount;
    }

    public BigDecimal getIntegrationCosts() {
        return integrationCosts;
    }

    public void setIntegrationCosts(BigDecimal integrationCosts) {
        this.integrationCosts = integrationCosts;
    }

    public BigDecimal getManpowerCosts() {
        return manpowerCosts;
    }

    public void setManpowerCosts(BigDecimal manpowerCosts) {
        this.manpowerCosts = manpowerCosts;
    }

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}
    
    
    
}