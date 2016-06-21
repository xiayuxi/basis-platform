package com.ync365.seed.bussiness.modules.promotion.po;

import com.ync365.seed.bussiness.modules.common.po.PageFilter;

public class PromotionGoodSkuSearch extends PageFilter {

    private static final long serialVersionUID = 1L;

    /**所属分类**/
    public Integer categoryId;
    
    /**品牌**/
    public Integer brandId;
    
    /**商品编码**/
    public String skuIds;
    
    /**价格下限**/
    public  Double startQty;
    
    /**价格上限**/
    public Double endQty;
    
    /**商品 名称**/
    public String name;
    
    /**活动ID**/
    public Integer promotionId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(String skuIds) {
        this.skuIds = skuIds;
    }

    public Double getStartQty() {
        return startQty;
    }

    public void setStartQty(Double startQty) {
        this.startQty = startQty;
    }

    public Double getEndQty() {
        return endQty;
    }

    public void setEndQty(Double endQty) {
        this.endQty = endQty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }
    
    
}
