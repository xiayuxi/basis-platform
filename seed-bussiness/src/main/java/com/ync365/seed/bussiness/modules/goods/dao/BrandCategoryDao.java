package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.entity.BrandCategory;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface BrandCategoryDao {
    int insert(BrandCategory record);

    int insertSelective(BrandCategory record);
    
    public List<BrandCategory> searchBrandCategoryList(Integer brandId);
    
    public BrandCategory searchBrandCategory(Map<String,Object> map);
    
    public int deleteById(Integer brandId);    
}