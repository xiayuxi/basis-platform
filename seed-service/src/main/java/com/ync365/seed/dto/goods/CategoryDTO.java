package com.ync365.seed.dto.goods;


/**
 * 分类DTO
 * 
 * @ClassName: CategoryDTO
 * @Description: TODO
 * @author robo
 * @date 2015年9月18日 下午4:17:44
 *
 */
public class CategoryDTO {

	private int categoryId;
	
	private String categoryName ;
	
	private int parentId;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
}
