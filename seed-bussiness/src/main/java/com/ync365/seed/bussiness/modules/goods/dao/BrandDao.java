package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.entity.Brand;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface BrandDao {
    //int deleteByPrimaryKey(Integer brandId);
    
    //设置is_del为1
    int deleteByBrandId(Integer brandId);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Integer brandId);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);
    
    public List<Brand> selectPage(Map<String,Object> map);
    
    public int  selectPageCount(Map<String,Object> map);
    
    public List<Brand> getAllbrandList();
    
    public List<Brand> selectBrandbyCategoryId(Integer categoryId);
    
    /**
     * 根据关键字，地区查询对应的品牌列表
     * @Title: selectBrandListByQuery
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月16日 下午2:07:07       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public List<Brand> selectBrandListByQuery(Map<String,Object> map);

    Brand selectByChName(String chName);

	/**
	 * 功能描述：根据 popStoreNum 查询 Brand
	 * @author liukai
	 * @param popStoreNum
	 * @return
	 */
	List<Brand> selectBrandByPopStoreNum(String popStoreNum);
	
	/**
	 * 根据品牌Id集合得到品牌列表
	 * @Title: selectBrandListByIds
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年11月6日 下午5:23:54       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	List<Brand> selectBrandListByIds(Map<String,Object> map);
 }