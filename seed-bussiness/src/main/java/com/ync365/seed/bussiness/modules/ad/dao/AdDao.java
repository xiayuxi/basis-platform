package com.ync365.seed.bussiness.modules.ad.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.ad.entity.Ad;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface AdDao {
    //int deleteByPrimaryKey(Integer id);
    
    //is_del改为1
    int deleteById(Integer id);

    int insert(Ad record);

    int insertSelective(Ad record);

    Ad selectByPrimaryKey(Integer id);
    
    public List<Ad> selectPage(Map<String,Object> map);
    
    public int  selectPageCount(Map<String,Object> map);

    int updateByPrimaryKeySelective(Ad record);

    int updateByPrimaryKeyWithBLOBs(Ad record);

    int updateByPrimaryKey(Ad record);
    
    /**
     * 前台调用  查询区域广告列表
     * @Title: selectAdList
     * @Description: TODO
     * @return List<Ad>    
     * @throws
     */
    List<Ad> selectAdList(Map<String,Object> map) ;
}