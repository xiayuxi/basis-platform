package com.ync365.seed.admin.vo.promotion;

import java.math.BigDecimal;

public class PromotionGroupbuyRegVo {
	private BigDecimal levelPrice;

    private BigDecimal goodsPrice;

    private Integer isDelete;

    private Integer promotionId;

    private Integer sort;

	public BigDecimal getLevelPrice() {
		return levelPrice;
	}

	public void setLevelPrice(BigDecimal levelPrice) {
		this.levelPrice = levelPrice;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
    
}
