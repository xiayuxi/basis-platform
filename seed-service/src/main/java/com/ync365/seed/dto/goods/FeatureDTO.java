package com.ync365.seed.dto.goods;

import java.util.List;

/**
 * 属性DTO
 *     
 * @Title：FeatureDTO  
 * @Description: TODO   
 * @author: robo        
 * @date: 2015年10月11日 下午1:22:24      
 * @version     
 *
 */
public class FeatureDTO {

	private Integer featureId;
	private String  featureName ;
	
	private List<AttrValue> valueList ;

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public List<AttrValue> getValueList() {
		return valueList;
	}

	public void setValueList(List<AttrValue> valueList) {
		this.valueList = valueList;
	}
	
	
}
