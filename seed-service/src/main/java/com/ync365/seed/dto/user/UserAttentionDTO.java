package com.ync365.seed.dto.user;


public class UserAttentionDTO {
    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 商品编号
     */
    private Integer goodsId;
    
    /**
     * 商户编号
     */
    private String popStoreNum;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getPopStoreNum() {
		return popStoreNum;
	}

	public void setPopStoreNum(String popStoreNum) {
		this.popStoreNum = popStoreNum;
	}

}
