package com.ync365.seed.admin.vo.promotion;

import java.math.BigDecimal;

/**
 * 用于展示活动商品相关的sku
 * @author ivan
 *
 */

public class PromotionGoodsSkuVo {
	
    /**活动商品ID  主键**/
    private Integer promotionGoodsSkuId;
	/**活动ID **/
    private Integer promotionId;
    
    /**商品ID **/
    private Integer goodsId;
	
    /** SKU ID **/
    private Integer skuId;
	
    /**活动价 **/
    private BigDecimal promotionPrice;

    /**折扣 **/
    private BigDecimal discount;

    /**村站佣金 **/
    private BigDecimal villageExpense;

    /**平台佣金 **/
    private BigDecimal platformExpense;

    /**LC佣金 **/
    private BigDecimal lcExpense;

    /**积分费用 **/
    private BigDecimal integralExpense;
    
    /**服务工程师**/
    private BigDecimal engineerExpense;

    /**促销费用 **/
    private BigDecimal promotionAmount;
    
    /**A+费用**/
    private BigDecimal aExpense;
    
    /**统筹费用**/
    private BigDecimal manpowercosts;
    
    /** 原价**/
    private BigDecimal marketPrice;
    
    /**是否参加活动**/
    private Integer isDelete;
    
    /**商品名称**/
    private String goodsName ;
    
    /**库存(goods_stocks表)**/
    private Integer stockNum;
    
    /** 零售价*  */
    private BigDecimal costPrice;
    
    /**销量**/
    private Long salesCount;
    
    /**每人限购数量**/
    private Integer limitPerCount;
    
    /**活动Version**/
    private String version;
 
    private Integer promotionType;

    public Integer getPromotionGoodsSkuId() {
        return promotionGoodsSkuId;
    }

    public void setPromotionGoodsSkuId(Integer promotionGoodsSkuId) {
        this.promotionGoodsSkuId = promotionGoodsSkuId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getVillageExpense() {
        return villageExpense;
    }

    public void setVillageExpense(BigDecimal villageExpense) {
        this.villageExpense = villageExpense;
    }

    public BigDecimal getPlatformExpense() {
        return platformExpense;
    }

    public void setPlatformExpense(BigDecimal platformExpense) {
        this.platformExpense = platformExpense;
    }

    public BigDecimal getLcExpense() {
        return lcExpense;
    }

    public void setLcExpense(BigDecimal lcExpense) {
        this.lcExpense = lcExpense;
    }

    public BigDecimal getIntegralExpense() {
        return integralExpense;
    }

    public void setIntegralExpense(BigDecimal integralExpense) {
        this.integralExpense = integralExpense;
    }

    public BigDecimal getEngineerExpense() {
        return engineerExpense;
    }

    public void setEngineerExpense(BigDecimal engineerExpense) {
        this.engineerExpense = engineerExpense;
    }

    public BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getaExpense() {
        return aExpense;
    }

    public void setaExpense(BigDecimal aExpense) {
        this.aExpense = aExpense;
    }

    public BigDecimal getManpowercosts() {
        return manpowercosts;
    }

    public void setManpowercosts(BigDecimal manpowercosts) {
        this.manpowercosts = manpowercosts;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Long getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Long salesCount) {
        this.salesCount = salesCount;
    }

    public Integer getLimitPerCount() {
        return limitPerCount;
    }

    public void setLimitPerCount(Integer limitPerCount) {
        this.limitPerCount = limitPerCount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }
  
}
