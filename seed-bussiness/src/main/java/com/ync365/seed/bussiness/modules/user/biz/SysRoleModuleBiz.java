package com.ync365.seed.bussiness.modules.user.biz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.user.dao.SysRoleModuleMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysRoleModule;


@Service
public class SysRoleModuleBiz {
	@Autowired
	SysRoleModuleMapper sysRoleModuleMapper;

	/**
	 * 通过觉map查询关系表中的list数据
	 * @param map
	 * @return
	 */
	public List<SysRoleModule> selectPageByMap(Map<String, Object> map) {
		return sysRoleModuleMapper.selectPageByMap(map);
	}

	/**
	 * 编辑权限(用于给角色赋予权限)
	 * @param id 角色id 
	 * @param tempId  权限 id 拼接成的 字符串
	 * @return
	 */
	@Transactional
	public int addRoleModules(int id, String tempId) {
		//转换 tempId字符串  为 字符串数组
		String [] ids = tempId.split(",");
		//封装 SysRoleModule用于insert 
		SysRoleModule sysRoleModule = new SysRoleModule();
		sysRoleModule.setRoleId(id);
		//循环插入角色权限关系
		int returnValue = 0 ;
		for(String s : ids){
			sysRoleModule.setModuleId(Integer.parseInt(s));
			returnValue = sysRoleModuleMapper.insertSelective(sysRoleModule);
		}
		return  returnValue;
	}

	/**
	 * 通过 roleId 删除 角色权限关系表中的 记录
	 * @param roleId
	 * @return
	 */
	public  int deleteRoleModuleByRoleId(Integer roleId) {
		return sysRoleModuleMapper.deleteRoleModuleByRoleId(roleId);
	}
	
	/**
	 * 通过  moduleId 删除 角色权限关系表中的 记录
	 * @param moduleId
	 * @return
	 */
	public  int deleteRoleModuleByModuleId(Integer moduleId) {
		return sysRoleModuleMapper.deleteRoleModuleByModuleId(moduleId);
	}
}
