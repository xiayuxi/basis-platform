package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysModule;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysModuleMapper {
	
	/**
	 * 删除 by id
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
	 * 添加  
	 * @param sysModule
	 * @return
	 */
    int insertSelective(SysModule record);

    /**
	 * 根据主键id查询
	 * @param id
	 * @return
	 */
    SysModule selectByPrimaryKey(Integer id);

    /**
	 * 编辑  更新 by id  如果对象更新字段为null 则不更新 数据库中的信息
	 * @param sysModule
	 * @return
	 */
    int updateByPrimaryKeySelective(SysModule record);

    /**
	 * 编辑  更新 by id
	 * @param sysModule
	 * @return
	 */
    int updateByPrimaryKey(SysModule record);
    
    /**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * @param map
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
    List<SysModule> selectPageByMap(Map<String, Object> map);
    
    /**
	 * 条件获取数量
	 * @param map
	 * @return
	 */
    int selectPageCount(Map<String, Object> map);

    /**
     * by 登录编号获取 权限列表
     * @param adminNum
     * @return
     */
    List<SysModule> selectSysModuleByAdminNum(String adminNum);
}