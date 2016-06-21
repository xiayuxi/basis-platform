package com.ync365.seed.commons.solr;

public class GoodsQueryBean {

	/**
	 * skuId
	 */
	private Integer id;
	
	/**
	 * 商品名
	 */
	private String  name;
	
	/**
	 * 商品代码
	 */
	private String  code ;
	
	/**
	 * 分类名称
	 */
	private String  categoryName ;
	
	/**
	 * 属性名
	 */
	private String  featureName ;
	
	/**
	 * 属性值 
	 */
	private String  featureValue ;
	
	/**
	 * 品牌名
	 */
	private String brandName ;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getFeatureValue() {
		return featureValue;
	}

	public void setFeatureValue(String featureValue) {
		this.featureValue = featureValue;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
	
}
