package com.ync365.seed.bussiness.modules.user.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreBrandRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreBrandRelationship;




@Service
public class SysPopStoreBrandRelationshipBiz {
	@Autowired
	SysPopStoreBrandRelationshipMapper sysPopStoreBrandRelationshipMapper ;
	
	/**
	 * 添加对象 insert
	 * @param record
	 * @return
	 */
	public int insert(SysPopStoreBrandRelationship  record){
		return sysPopStoreBrandRelationshipMapper.insertSelective(record);
	}
	
	/**
	 * 通过popNum参数删除对象
	 * @param popNum
	 * @return
	 */
	public int deleteByBrandPopNum(String popNum){
		return 0 ; /*sysPopStoreBrandRelationshipMapper.deleteByBrandPopNum(popNum)*/
	}
	
	/**
	 * 通过popNum查询所有List<SysPopBrandRelationship>
	 * @param popNum
	 * @return
	 */
	public List<SysPopStoreBrandRelationship>  selectByBrandPopNum(String popNum){
		return null  ;//sysPopStoreBrandRelationshipMapper.selectByBrandPopNum(popNum);
	}
	
	/**
	 * 通过brandId查询所有List<SysPopBrandRelationship>
	 * @param brandId
	 * @return
	 */
	public List<SysPopStoreBrandRelationship> selectByBrandBrandId(Integer brandId){
		return null ; //sysPopStoreBrandRelationshipMapper.selectByBrandBrandId(brandId);
	}
	
	/**
	 * 功能描述：根据 popStoreNum 和 brandId 删除
	 * @author liukai
	 * @param popStoreNum
	 * @param brandId
	 * @return
	 */
	public int deleteByPopStoreNumAndBrandId(String popStoreNum, String brandId) {
		return sysPopStoreBrandRelationshipMapper.deleteByPopStoreNumAndBrandId(popStoreNum, brandId);
	}
}
