package com.ync365.seed.bussiness.modules.goods.biz;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.goods.bo.FeatureBO;
import com.ync365.seed.bussiness.modules.goods.dao.FeatureDao;
import com.ync365.seed.bussiness.modules.goods.dao.FeatureValueDao;
import com.ync365.seed.bussiness.modules.goods.entity.Feature;
import com.ync365.seed.bussiness.modules.goods.entity.FeatureValue;

@Service
public class FeatureBiz {

	@Autowired
	FeatureDao featureDao;
	
	@Autowired
	FeatureValueDao featureValueDao; 
	
	@Transactional
	public int add(Feature feature,List<FeatureValue> fVList, List<Integer> categoryIds) {

		//将optionals设置到feature中，便于下次展示。
		String optionals = "";
		List<FeatureValue> pageFVList = new LinkedList<FeatureValue>();
		for (FeatureValue fv: fVList) {
			if(fv.getAttrValue()!=null&&""!=fv.getAttrValue().trim()){
				optionals = optionals + fv.getAttrValue() + ",";
				pageFVList.add(fv);
			}
		}
		optionals = optionals.substring(0, optionals.length()-1);
		feature.setOptionals(optionals);
		
		//把页面选择的最低级别的类别（category）值加入到属性(feature)中
		if(categoryIds != null && categoryIds.size()>0){
			for (int i = categoryIds.size()-1; i >= 0 ; i--) {
				if(categoryIds.get(i)!=null){
				    feature.setCategoryId(categoryIds.get(i));
					break;
				}
			}
			feature.setIsDel(0);
			feature.setIsInput((short)0);
			feature.setIsMultiselect((short) 1);
			featureDao.insert(feature);
		}
		int featureId  = feature.getFeatureId();
		for (FeatureValue fv: pageFVList) {
			fv.setFeatureId(featureId);
			fv.setIsDel(0);
			featureValueDao.insert(fv);
		}
		return featureId;
		
	}

	public List<Feature> selectPage(String qName, int startIndex,
			int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", qName);
		map.put("pageIndex", startIndex);
		map.put("pageSize", pageSize);
		return featureDao.selectPage(map);
	}
	
	public List<FeatureBO> selectPageList(String qName, int startIndex,
	        int pageSize) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("name", qName);
	    map.put("pageIndex", startIndex);
	    map.put("pageSize", pageSize);
	    return featureDao.selectPageList(map);
	}

	public int selectPageCount(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return featureDao.selectPageCount(map);
	}

	//删除属性值，is_del改为1
	@Transactional
	public int deleteById(int id) {
		featureValueDao.deleteByIsDel(id);		
		return featureDao.deleteByFeatureId(id);
	}

	public Feature selectById(int id) {
		return featureDao.selectByPrimaryKey(id);
	}

	/**
	 * 1.查询出featureId对应的所有FeatureValue的featurevalue对象
	 * 2.循环list<featurevalue>对象取featurevalue值字符串
	 * 3.判断page页面传过来是否存在，没有逻辑删除。
	 * 4.循环list<String>page页面的值，如果数据库中没有，添加一个featurevalue
	 * @param list 
	 * 
	 */		
	@Transactional
	public int edit(Feature feature, List<FeatureValue> fVList, List<Integer> categoryIds) {
	    String optionals = "";
        for (FeatureValue fv: fVList) {
            if(fv.getAttrValue()!=null&&""!=fv.getAttrValue().trim()){
                optionals = optionals + fv.getAttrValue() + ",";
            }
        }
        optionals = optionals.substring(0, optionals.length()-1);
        feature.setOptionals(optionals);
        
        //把页面选择的最低级别的类别（category）值更新到属性(feature)中
        if(categoryIds != null && categoryIds.size()>0){
            for (int i = categoryIds.size()-1; i >= 0 ; i--) {
                if(categoryIds.get(i)!=null){
                    feature.setCategoryId(categoryIds.get(i));                   
                    break;
                }
            }
            featureDao.updateByPrimaryKeySelective(feature);
        }               
        
        int featureId  = feature.getFeatureId();
		
		/**
		 * 1、 编辑属性
		 * 	    将原来的属性值的is——del改为1
		 * 2、  把当前的属性值插入。
		 */
		List<FeatureValue> oldFVList = featureValueDao.selectFeatureValueListByFeatureId(featureId);
        for (FeatureValue oldFV : oldFVList) {
            oldFV.setIsDel(1);
            featureValueDao.updateByPrimaryKeySelective(oldFV);
        }
        
		for (FeatureValue newFV : fVList) {
		    if(newFV.getAttrValue()!=null&&""!=newFV.getAttrValue().trim()){
    			newFV.setFeatureId(featureId);
    			newFV.setIsDel(0);
    			featureValueDao.insert(newFV);
		    }
		}
	
		return featureId;
	}

	public List<Feature> getFeaturesBySpec(int specValue) {
		return featureDao.getFeaturesBySpec(specValue);
	}

	public List<Feature> getFeaturesBySpecAndCategoryId(int specValue,
			Integer categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSpec", specValue);
		map.put("categoryId", categoryId);
		List<Feature> listFeatures = featureDao.selectPage(map);
		return listFeatures;
	}

	public List<Feature> getFeaturesByCategoryId(Integer categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryId", categoryId);
		List<Feature> listFeatures = featureDao.selectPage(map);
		for (Feature feature : listFeatures) {
			if (feature.getIsSpec() == 1 || feature.getIsInput()!=1) {//除输入字段外全部实现可选择
				Map<String, String> map1 = new HashMap<String, String>();
				map1.put("featureId", feature.getFeatureId().toString());
				List<FeatureValue> listFeatureValues = featureValueDao
						.selectFeatureListByMap(map1);
				String optionals="";
				for (FeatureValue featureValue : listFeatureValues) {
					String value = featureValue.getAttrValue();
					optionals = optionals+value+",";
				}
				optionals.substring(0, optionals.length()-1);
				feature.setFeatureValues(listFeatureValues);
			}
		}
		
		
		return listFeatures;
	}

	public List<Feature> getAllFeatures() {
		return featureDao.getAllFeatures();
	}

	/*
	 * 通过featureId获取到FeatureValue
	 */
	public List<FeatureValue> getFeatureValueByFeatureId(Integer featureId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("featureId", featureId.toString());
		return featureValueDao.selectFeatureListByMap(map);
	}
	
	/**
	 * 
	 * @Title: selectFeaturesMap
	 * @Description:     
	 * @author: robo   
	 * @date: 2015年10月2日 下午5:09:18       
	 * @version: 
	 *
	 * @param map   isSpec 1 规格    skuId 主键sku表
	 * @return
	 *
	 */
	public List<Map<String,Object>> selectFeaturesMap(Integer isSpec,Integer skuId){
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("isSpec", isSpec);
		map.put("skuId", skuId);
		return featureDao.selectFeaturesMap(map);
	}

}
