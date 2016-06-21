package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.bo.FeatureBO;
import com.ync365.seed.bussiness.modules.goods.entity.Feature;
import com.ync365.seed.bussiness.modules.goods.entity.FeatureValue;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface FeatureDao {
	int deleteByPrimaryKey(Integer featureId);
	
	//设置isDel为1
    int deleteByFeatureId(int id);

    int insert(Feature record);

    int insertSelective(Feature record);

    public Feature selectByPrimaryKey(Integer featureId);

    int updateByPrimaryKeySelective(Feature record);

    int updateByPrimaryKey(Feature record);
    
    public List<Feature> selectPage(Map<String,Object> map);

    //有categoryName,List<featureValue>
    public List<FeatureBO> selectPageList(Map<String,Object> map);

    public int selectPageCount(Map<String,Object> map);
    
    public List<Feature> getFeaturesBySpec(int specValue);

	List<Feature> getAllFeatures();

	List<FeatureValue> selectFeatureValues(Integer id);
	
	/**
	 * 根据skuId查询对应的属性列表
	 * @Title: selectFeaturesMap
	 * @Description: 
	 * @author: robo   
	 * @date: 2015年10月2日 下午5:07:39       
	 * @version: 
	 *
	 * @param map isSpec 1 规格      skuId 主键
	 * @return
	 *
	 */
	List<Map<String,Object>> selectFeaturesMap(Map<String,Object> map);


}