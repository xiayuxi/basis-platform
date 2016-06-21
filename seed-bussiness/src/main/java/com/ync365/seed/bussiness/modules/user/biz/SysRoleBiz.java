package com.ync365.seed.bussiness.modules.user.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.user.dao.SysModuleMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysRoleMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysRoleModuleMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserRoleMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysModule;
import com.ync365.seed.bussiness.modules.user.entity.SysRole;
import com.ync365.seed.bussiness.modules.user.entity.SysUserRole;
import com.ync365.seed.utils.Constants;



@Service
public class SysRoleBiz {
	@Autowired
	SysRoleMapper sysRoleMapper;
	@Autowired
	SysRoleModuleMapper sysRoleModuleMapper;
	
	@Autowired
    SysUserRoleMapper sysUserRoleMapper ;
	/**
	 * 分页查询list
	 * @param map
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<SysRole> searchPage(Map<String, Object> map,int startIndex,int pageSize){
		map.put("pageIndex", startIndex);
		map.put("pageSize", pageSize);
		return sysRoleMapper.selectPageByMap(map);
	}

	/**
	 * 分页查询统计count
	 * @param map
	 * @return
	 */
	public int selectPageCount(Map<String, Object> map) {
		return sysRoleMapper.selectPageCount(map);
	}

	/**
	 * 根据主键id查询
	 * @param id
	 * @return
	 */
	public SysRole selectByPrimaryKey(Integer id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

	/**
	 * 添加
	 * @param record
	 * @return
	 */
	public int insert(SysRole record) {
	    record.setIsDel(false);
		return sysRoleMapper.insertSelective(record);
	}
	
	/**
	 * 编辑  by id
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(SysRole record) {
		return sysRoleMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 删除 by id
	 * @param id
	 * @return
	 */
	@Transactional
	public int deleteByPrimaryKey(Integer id){
		//删除角色权限关系表sysRoleModule  及 角色表 sysRole中的数据
		int a = sysRoleModuleMapper.deleteRoleModuleByRoleId(id);
		return sysRoleMapper.deleteByPrimaryKey(id);
	}

    public List<SysRole> selectRoleAllByParam(Map<String, Object> map) {
        List<SysRole> list = sysRoleMapper.selectRoleAllByParam(map);
        List<SysRole> listRole = new ArrayList<SysRole>();
        if(null != list && list.size() > 0 ){
            for(SysRole temp : list){
                if(null != temp.getId() ){
                    //角色为lc  us vs  移除
                    if(Constants.UserRole.LC.v() != temp.getId() && Constants.UserRole.VS.v() != temp.getId() 
                            && Constants.UserRole.US.v() != temp.getId()){
                        listRole.add(temp);
                    }
                    
                }
            }
        }
        return listRole;
    }

    /**
     * by  角色id 查询此角色是否被 用户使用
     * @param map
     * @return
     */
    public List<SysUserRole> selectRoleUserById(Map<String, Object> map) {
        return sysUserRoleMapper.selectPageByMap(map);
    }
    
    public List<SysRole> selectSysRoleByAdminNum(String adminNum){
        return sysRoleMapper.selectSysRoleByAdminNum(adminNum);
    }

}
