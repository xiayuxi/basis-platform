package com.ync365.seed.bussiness.modules.promotion.entity;

/**
 * 
 *     
 * @Title：PromotionGiftGoods  
 * @Description: TODO   
 * @author: sunyf
 * @date: 2015年9月21日 下午5:25:30      
 * @version     
 *
 */
public class PromotionGiftGoods  {

	/** serialVersionUID **/ 
	private static final long serialVersionUID = 1L;
	
	/**  **/ 
	private Integer id;
	
    /**  **/ 
	private Integer promotionId;
	
    /**  **/ 
	private Integer goodsId;
	
    /**  **/ 
	private Integer skuId;
	
    /**  **/ 
	private Integer count;
	
    /**  **/ 
	private Integer isDelete;
	

	/**
	 * 构造 
	 */
	public PromotionGiftGoods() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}