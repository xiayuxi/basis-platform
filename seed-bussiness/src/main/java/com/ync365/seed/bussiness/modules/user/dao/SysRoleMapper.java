package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysRole;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysRoleMapper {
	
	/**
	 * 删除 by id
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
	 * 添加  
	 * @param record
	 * @return
	 */
    int insertSelective(SysRole record);

    /**
	 * 根据主键id查询
	 * @param id
	 * @return
	 */
    SysRole selectByPrimaryKey(Integer id);

    /**
	 * 编辑  更新 by id  如果对象更新字段为null 则不更新 数据库中的信息
	 * @param record
	 * @return
	 */
    int updateByPrimaryKeySelective(SysRole record);

    /**
	 * 编辑  更新 by id
	 * @param record
	 * @return
	 */
    int updateByPrimaryKey(SysRole record);

    /**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * @param map
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
    List<SysRole> selectPageByMap(Map<String, Object> map);
    
    /**
	 * 条件获取数量
	 * @param map
	 * @return
	 */
    int selectPageCount(Map<String, Object> map);

    /**
     * by 登录名获取角色 list
     * @param adminNum
     * @return
     */
    List<SysRole> selectSysRoleByAdminNum(String adminNum);

    List<SysRole> selectRoleAllByParam(Map<String, Object> map);
}