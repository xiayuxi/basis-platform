package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.user.dao.SysModuleMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysRoleModuleMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysModule;



@Service
public class SysModuleBiz {
	@Autowired
	SysModuleMapper sysModuleMapper;
	@Autowired
	SysRoleModuleMapper sysRoleModuleMapper;
	
	/**
	 * 分页查询list
	 * @param map
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<SysModule> selectPageByMap(Map<String, Object> map,int startIndex,int pageSize){
		map.put("pageIndex", startIndex);
		map.put("pageSize", pageSize);
		return sysModuleMapper.selectPageByMap(map);
	}

	/**
	 * 分页查询统计count
	 * @param map
	 * @return
	 */
	public int selectPageCount(Map<String, Object> map) {
		return sysModuleMapper.selectPageCount(map);
	}

	/**
	 * 根据主键id查询
	 * @param id
	 * @return
	 */
	public SysModule selectByPrimaryKey(Integer id) {
		return sysModuleMapper.selectByPrimaryKey(id);
	}

	/**
	 * 添加
	 * @param record
	 * @return
	 */
	public int insert(SysModule record) {
		//0 为父目录
		if (null == record.getParentId()) {
			record.setParentId(0);
		}
		return sysModuleMapper.insertSelective(record);
	}
	
	/**
	 * 编辑  by id
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(SysModule record) {
		return sysModuleMapper.updateByPrimaryKey(record);
	}
	
	/**
	 * 删除 by id
	 * @param id
	 * @return
	 */
	@Transactional
	public int deleteByPrimaryKey(Integer id){
		//删除角色权限关系表sysRoleModule  及 权限表 sysModule中的数据
		int a = sysRoleModuleMapper.deleteRoleModuleByModuleId(id);
		return sysModuleMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 获取所有 list  通过map对象 传递参数
	 * @return
	 */
	public List<SysModule> selectSysModuleAllByMap(Map<String, Object> map) {
		return sysModuleMapper.selectPageByMap(map);
	}
}
