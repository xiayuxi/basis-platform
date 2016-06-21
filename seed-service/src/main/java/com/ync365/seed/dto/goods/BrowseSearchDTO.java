package com.ync365.seed.dto.goods;

import java.util.List;

public class BrowseSearchDTO {

	/**
	 * sku集合
	 */
	public List<Integer> skuList;
	
	/**
	 * region主键
	 */
	private Integer regionId;

	public List<Integer> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<Integer> skuList) {
		this.skuList = skuList;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	
	
}
