package com.ync365.seed.admin.vo.goods;

import java.util.List;

import com.ync365.seed.bussiness.modules.goods.entity.FeatureValue;

/**
 * 属性VO
 *
 */
public class FeatureVo {
 
	
	/**
	 * 属性值列表
	 */
	List<FeatureValue> fVList ;

	public List<FeatureValue> getfVList() {
		return fVList;
	}

	public void setfVList(List<FeatureValue> fVList) {
		this.fVList = fVList;
	}
	
	public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    /**
     * 前台获取选择时会传来多个，截取后可得关联属性
     */
    private List<Integer> categoryIds;

	
}
