package com.ync365.seed.bussiness.modules.goods.entity;

public class Blob {
    private Integer goodsId;

    /**
     * 商品详情描述
     */
    private String goodsDesc;
    
    /**
     * 商品模板
     * */
    private String packingList;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getPackingList() {
		return packingList;
	}

	public void setPackingList(String packingList) {
		this.packingList = packingList;
	}
}