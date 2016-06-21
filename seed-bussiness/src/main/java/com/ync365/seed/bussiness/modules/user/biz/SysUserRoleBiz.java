package com.ync365.seed.bussiness.modules.user.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysUserRoleMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysUserRole;

@Service
public class SysUserRoleBiz {
	@Autowired
	SysUserRoleMapper sysUserRoleMapper ;
	

	/**
     * 功能描述：添加用户
     * @param record
     * @return
     */
    public int insert(SysUserRole record) {
    	return sysUserRoleMapper.insertSelective(record);
    }
    
    /**
     * 功能描述：删除 信息 by user_num
     * @param 
     * @return
     */
    public int deleteUserRoleByuserNum(String userNum) {
    	return sysUserRoleMapper.deleteUserRoleByuserNum(userNum);
    }
    
    /**
     * 功能描述：删除 信息  by roleId
     * @param 
     * @return
     */
    public int deleteUserRoleByRoleId(Integer roleId) {
    	return sysUserRoleMapper.deleteUserRoleByRoleId(roleId);
    }
    
    /**
	 * 获取所有 list  通过map对象 传递参数
	 * @return
	 */
	public List<SysUserRole> selectPageByMap(Map<String, Object> map) {
		return sysUserRoleMapper.selectPageByMap(map);
	}
	
}
