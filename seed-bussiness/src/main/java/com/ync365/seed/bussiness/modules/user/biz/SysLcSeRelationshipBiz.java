package com.ync365.seed.bussiness.modules.user.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysLcSeRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysLcSeRelationship;


@Service
public class SysLcSeRelationshipBiz {
	@Autowired
	SysLcSeRelationshipMapper sysLcSeRelationshipMapper;
	
	/**
	 * 物理删除数据
	 * 
	 * @param lcNum
	 * @return
	 */
	int deleteByLcNum(String lcNum){
		return sysLcSeRelationshipMapper.deleteByLcNum(lcNum);
	}

	/**
	 * LcNum为条件更新
	 * 
	 * @param record
	 * @return
	 */
	int update(SysLcSeRelationship sysLcSeRelationship){
		return sysLcSeRelationshipMapper.update(sysLcSeRelationship);
	}

	/**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	List<SysLcSeRelationship> selectPageByMap(Map<String, Object> map){
		return sysLcSeRelationshipMapper.selectPageByMap(map);
	}

	/**
	 * 添加
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(SysLcSeRelationship record){
		return sysLcSeRelationshipMapper.insertSelective(record);
	}
	
	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	public int selectPageCount(Map<String, Object> map){
		return sysLcSeRelationshipMapper.selectPageCount(map);
	}
}
