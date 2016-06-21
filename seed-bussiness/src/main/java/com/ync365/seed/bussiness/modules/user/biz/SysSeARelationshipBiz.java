package com.ync365.seed.bussiness.modules.user.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysSeARelationshipMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysSeARelationship;


@Service
public class SysSeARelationshipBiz {
	@Autowired
	SysSeARelationshipMapper sysSeARelationshipMapper;
	
	/**
	 * 编号 aNum 删除信息，物理删除
	 * 
	 * @param aNum
	 * @return
	 */
	public int deleteByPrimaryKeyByaNum(String  aNum){
		return sysSeARelationshipMapper.deleteByPrimaryKeyByaNum(aNum);
	}
	
	/**
	 * 编号 seNum 删除信息，物理删除
	 * 
	 * @param seNum
	 * @return
	 */
	public int deleteByPrimaryKeyBySeNum(String seNum){
		return sysSeARelationshipMapper.deleteByPrimaryKeyBySeNum(seNum);
	}
	
	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
	public int insert(SysSeARelationship record){
		return sysSeARelationshipMapper.insertSelective(record);
	}
	
	
	/**
	 * 多功能查询 所有List<SysSeARelationship> 
	 * 
	 * @param map
	 * @return
	 */
	public List<SysSeARelationship> selectPageByMap(Map<String, Object> map){
		return sysSeARelationshipMapper.selectPageByMap(map);
	}
	
	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	public int selectPageCount(Map<String, Object> map){
		return sysSeARelationshipMapper.selectPageCount(map);
	}
}
