package com.ync365.seed.bussiness.modules.ad.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.ad.entity.Navigation;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface NavigationDao {
    //int deleteByPrimaryKey(Integer id);
    //设置is_del为1
    int deleteById(Integer id);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    Navigation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Navigation record);

    int updateByPrimaryKey(Navigation record);
    
    /**
     * 查询所有导航
     * @Title: selectNavigationList
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月27日 下午6:22:41       
     * @version: 
     *
     * @return
     *
     */
    List<Navigation> selectNavigationList();

    List<Navigation> selectNavigationCategoryList();

    int selectPageCount();

    List<Navigation> selectPage(Map<String,Object> map);

}