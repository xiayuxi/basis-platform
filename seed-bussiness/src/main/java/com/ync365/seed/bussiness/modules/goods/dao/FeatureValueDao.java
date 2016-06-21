package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.entity.FeatureValue;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface FeatureValueDao {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByIsDel(Integer id);
    
    int deleteByFeatureId(Integer id);

    int insert(FeatureValue record);

    int insertSelective(FeatureValue record);

    FeatureValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FeatureValue record);

    int updateByPrimaryKey(FeatureValue record);

	List<FeatureValue> selectFeatureListByMap(Map<String, String> map);
	
	List<FeatureValue> selectFeatureValueListByFeatureId(Integer featureId);
}