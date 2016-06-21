package com.ync365.seed.bussiness.modules.user.entity;

public class SysUserAttentionGoods {
	/**
	 * 用户编号
	 */
    private String userNum;

    /**
     * 商品ID
     */
    private Integer goodsId;

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

}