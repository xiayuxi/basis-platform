package com.ync365.seed.bussiness.modules.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ync365.seed.bussiness.modules.common.entity.Region;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface RegionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
    
    List<Region> selectListByLevel(@Param("levelList")List<Integer> levelList) ;
    
    public List<Region> searchPage(Map<String,Object> map);

    /**搜索，无分页
     * @author xieang
     * 2015年10月14日
     * @param record
     * @return
     */
    List<Region> selectAllRegion(Region record);
    
	int searchPageCount(Map<String, Object> map);
	
	List<Region> selectRegionByParentId(Integer pid);
	
	Region selectRegionByCode(String code) ;
	
	/**
	 * 查询省市列表
	 * @Title: selectRegionProvinceCityList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年9月30日 下午3:09:25       
	 * @version: 
	 *
	 * @return
	 *
	 */
	List<Region> selectRegionProvinceCityList();
	
	/**
	 * 省市县三级列表
	 * @Title: selectRegionProvinceCityCountyList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月2日 下午3:02:46       
	 * @version: 
	 *
	 * @return
	 *
	 */
	List<Region> selectRegionProvinceCityCountyList();
	
}