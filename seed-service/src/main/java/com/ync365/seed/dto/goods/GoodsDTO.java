package com.ync365.seed.dto.goods;

import java.math.BigDecimal;

/**
 * 商品DTO
 * 
 * @ClassName: GoodsDTO
 * @Description: TODO
 * @author robo
 * @date 2015年9月18日 下午4:16:30
 *
 */
public class GoodsDTO {
 
	private int goodsId;
	
	private String goodsName ;
	
	/**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 零售价
     */
    private BigDecimal sellPrice;
    
    /**
     * 成本价
     */
    private BigDecimal costPrice ;
    
    /**
     * 销量
     */
    private int salesCount ;

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}
    
    
}
