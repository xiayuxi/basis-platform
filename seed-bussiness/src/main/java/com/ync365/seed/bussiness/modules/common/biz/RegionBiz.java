package com.ync365.seed.bussiness.modules.common.biz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.common.dao.RegionDao;
import com.ync365.seed.bussiness.modules.common.entity.Region;

@Service
public class RegionBiz {

	@Autowired
	RegionDao regionDao ;
	
	public Region selectByPrimaryKey(Integer id) {
		return regionDao.selectByPrimaryKey(id);
	}
	
	/**
	 * 查询分页
	 * @param qChName
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Region> searchPage(String qName,int startIndex,int pageSize){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name", qName);
		map.put("pageIndex", startIndex);
		map.put("pageSize", pageSize);
		return regionDao.searchPage(map);
	}
	
	public int searchPageCount(String name) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return regionDao.searchPageCount(map);
	}
	
	/**
	 * 查询地区的一二级
	 * @return
	 */
	public List<Region> selectByLevel(Integer level) {
		List<Integer> levelList = new ArrayList<Integer>();
		levelList.add(level);
		//levelList.add(2);
		
		return regionDao.selectListByLevel(levelList);
	}

	public int add(Region region) {
		return regionDao.insert(region);
	}

	public int edit(Region region) {
		return regionDao.updateByPrimaryKeySelective(region);
	}

	public Region selectById(Integer id) {
		return regionDao.selectByPrimaryKey(id);
	}
	/**
	 * 获取区域及其父区域
	 * @param id
	 * @return 按顺序的区域数组，区域，父区域，父区域的父区域。。。最多5级
	 */
	public Region[] selectRegionAndParentRegionById(Integer id){
		Region region=regionDao.selectByPrimaryKey(id);
		List<Region> list=new ArrayList<Region>();
		if(region!=null){
		    int pid=region.getPid();
	        list.add(region);
	        while(pid!=0){
	            Region region_temp=regionDao.selectByPrimaryKey(pid);
	            list.add(region_temp);
	            pid=region_temp.getPid();
	        }
		}
		Region[] regions=new Region[list.size()];
		return list.toArray(regions);
	}
	/**
	 * 获取区域及其父区域，经过排序，首先出来的是顶级区域
	 * @param id
	 * @return 按顺序的区域数组，区域，父区域，父区域的父区域。。。最多5级
	 */
	public List<Region> selectAllParentRegionById(Integer id){
		Region region=regionDao.selectByPrimaryKey(id);
		List<Region> list=new ArrayList<Region>();
		if(region==null){
			return null;
		}
		int pid=region.getPid();
		list.add(region);
		while(pid!=0){
			Region region_temp=regionDao.selectByPrimaryKey(pid);
			list.add(region_temp);
			pid=region_temp.getPid();
		}
		Collections.reverse(list);//翻转顺序
		return list;
	}
	
	/**返回String
	 * @author xieang
	 * 2015年10月15日
	 * @param id
	 * @return
	 */
	public String selectAllParentRegionStringById(Integer id){
		List<Region> list = selectAllParentRegionById(id);
		if(list==null){
			return null;
		}
		StringBuffer stringRegion = new StringBuffer();
		for(Region region:list){
			stringRegion.append(region.getName());
		}
		return stringRegion.toString();
	}
	
	/**
	 * 根据父类查询对应的子类列表
	 * @param pid
	 * @return
	 */
	public List<Region>  selectRegionByParentId(Integer pid){
		return regionDao.selectRegionByParentId(pid);
	}

	
	/**
	 * 根据编码查询对应的区域
	 * @Title: selectRegionByCode
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年9月30日 下午2:40:54       
	 * @version: 
	 *
	 * @param code
	 * @return
	 *
	 */
	public Region  selectRegionByCode(String code){
		return regionDao.selectRegionByCode(code);
	}
	
	/**
	 * 得到省市数据
	 * @Title: selectRegionProvinceCityList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月2日 下午2:52:50       
	 * @version: 
	 *
	 * @return
	 *
	 */
	public List<Region> selectRegionProvinceCityList(){
		return regionDao.selectRegionProvinceCityList();
	}
	
	/**
	 * 得到省市县列表数据
	 * @Title: selectRegionProvinceCityCountyList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月2日 下午2:52:34       
	 * @version: 
	 *
	 * @return
	 *
	 */
	public List<Region> selectRegionProvinceCityCountyList(){
		return regionDao.selectRegionProvinceCityCountyList();
	}

	
	/**
	 * 把所有的region都查找出来放入List中
	 */
	public List<Region> selectRegionChain(Region region){
		List<Region> regionChain1=new ArrayList<Region>();
		List<Region> regionChain2=new ArrayList<Region>();
	
		//region一共三个级别

		while(region.getPid()!=0){
			regionChain1.add(region);
			Region r = regionDao.selectByPrimaryKey(region.getPid());
			region =r;
		}
		regionChain1.add(region);
		for (int i = regionChain1.size()-1; i >=0 ; i--) {
			regionChain2.add(regionChain1.get(i));
		}
		
		return regionChain2;
	}

	public List<Region> selectAllRegionByShow(){
		Region record = new Region();
		record.setIsShow((byte) 1);
		return regionDao.selectAllRegion(record);
	}
}
