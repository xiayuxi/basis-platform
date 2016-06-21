package com.ync365.seed.dto.goods;

import java.util.List;

/**
 * 规格属性
 *     
 * @Title：SpecDTO  
 * @Description: TODO   
 * @author: robo        
 * @date: 2015年10月2日 下午2:33:05      
 * @version     
 *
 */
public class SpecDTO {

	/**
	 * 属性ID
	 */
	private Integer featureId ;
	
	/**
	 * 属性名称 
	 */
	private String  featureName;
	
	/**
	 * 属性值列表
	 */
	List<AttrValue> attrList ;
	
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
	
	
}
