package com.ync365.seed.bussiness.modules.goods.entity;

public class SkuFeature {
	private Integer tid;//主键

	private Integer featureId;//属性主键

	private Integer skuId;//最小销售单元主键

	private String specValue;//规格值

	private int isSpec;//是否是规格 1是
	
	private String featureName;
	
	private int status;//是否删除
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public String getSpecValue() {
		return specValue;
	}

	public void setSpecValue(String specValue) {
		this.specValue = specValue == null ? null : specValue.trim();
	}

	public int getIsSpec() {
		return isSpec;
	}

	public void setIsSpec(int isSpec) {
		this.isSpec = isSpec;
	}
	
}