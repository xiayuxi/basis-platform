package com.ync365.seed.bussiness.modules.goods.entity;

public class FeatureValue {
	
	/**
	 * 主键
	 */
    private Integer id;

    /**
     * 属性主键
     */
    private Integer featureId;

    /**
     * 属性值
     */
    private String attrValue;
    
    /**
     * 是否删除
     */
    private Integer isDel;

    public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

	public String getAttrValue() {
		return attrValue;
	}

 
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue == null ? null : attrValue.trim();
	}

}