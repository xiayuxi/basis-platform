package com.ync365.seed.bussiness.modules.goods.entity;

import java.math.BigDecimal;

/**
 * 商品销售区域
* @ClassName: SaleRegion
* @Description: TODO
* @author robo
* @date 2015年9月22日 上午10:56:19
*
 */
public class SaleRegion {
	
	/**
	 * 主键
	 */
    private Integer saleRegionId;

    /**
     * 地区主键
     */
    private Integer regionId;

    /**
     * sku主键
     */
    private Integer skuId;
    

    /**
     * 商品主键
     */
    private Integer goodsId;
    
    /**
     * 价格
     * @return
     */
    
    private BigDecimal price;
    
	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSaleRegionId() {
        return saleRegionId;
    }

    public void setSaleRegionId(Integer saleRegionId) {
        this.saleRegionId = saleRegionId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
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
}