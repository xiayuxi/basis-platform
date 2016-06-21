package com.ync365.seed.dto.goods;

import java.util.List;

public class CatGoodsDTO {

	/**
	 * 分类DTO
	 */
	CategoryDTO categoryDTO ;
	
	/**
	 * 商品列表 
	 */
	List<SkuDetailDTO> goodsList;

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public List<SkuDetailDTO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<SkuDetailDTO> goodsList) {
		this.goodsList = goodsList;
	}
	
	
}
