package com.ync365.seed.service.goods.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ync365.seed.bussiness.modules.goods.entity.Category;

public class CommonCategoryUtil {

	 private List<Integer> returnList = new ArrayList<Integer>();
     
	    /**
	     * 根据父节点的ID获取所有子节点
	     * @param list 分类表
	     * @param typeId 传入的父节点ID
	     * @return String
	     */
	    public List<Integer> getChildCategorys(List<Category> list, Integer typeId) {
	        if(list == null && typeId == null) return null;
	        for (Iterator<Category> iterator = list.iterator(); iterator.hasNext();) {
	            Category category = (Category) iterator.next();
	            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
	            if (category.getParentId().intValue() == 0 && typeId.intValue() ==category.getCategoryId().intValue()) {
	                recursionFn(list, category);
	            }
	           
	        }
	        return returnList;
	    }
	     
	    private void recursionFn(List<Category> list, Category category) {
	        List<Category> childList = getChildList(list, category);// 得到子节点列表
	        if (hasChild(list, category)) {// 判断是否有子节点
	            returnList.add(category.getCategoryId());
	            Iterator<Category> it = childList.iterator();
	            while (it.hasNext()) {
	                Category n = (Category) it.next();
	                recursionFn(list, n);
	            }
	        } else {
	            returnList.add(category.getCategoryId());
	        }
	    }
	     
	    // 得到子节点列表
	    private List<Category> getChildList(List<Category> list, Category category) {
	        List<Category> categoryList = new ArrayList<Category>();
	        Iterator<Category> it = list.iterator();
	        while (it.hasNext()) {
	            Category n = (Category) it.next();
	            if (n.getParentId().intValue() == category.getCategoryId().intValue()) {
	                categoryList.add(n);
	            }
	        }
	        return categoryList;
	    }
	 
	    // 判断是否有子节点
	    private boolean hasChild(List<Category> list, Category node) {
	        return getChildList(list, node).size() > 0 ? true : false;
	    }
}
