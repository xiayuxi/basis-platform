package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.AdminSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysAdmin;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysAdminMapper {
	/**
	 * 编号删除信息，物理删除
	 * 
	 * @param adminNum
	 * @return
	 */
	int deleteByPrimaryKey(String adminNum);

	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(SysAdmin record);

	/**
	 * 编号获取信息
	 * 
	 * @param adminNum
	 * @return
	 */
	SysAdmin selectByPrimaryKey(String adminNum);
	/**
	 * 根据登录名/手机号获取信息
	 * 
	 * @param adminNum
	 * @return
	 */
	SysAdmin selectByAdminLoginName(String adminLoginName);

	/**
	 * 编号修改信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(SysAdmin record);

	/**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
	List<SysAdmin> selectPageByMap(Map<String, Object> map);

	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);

	/**
     * 查询sysAdmin 列表
     * @Title: selectSysAdminListByPrimary
     * @Description: TODO    ：    
     * @author: leixc
     * @date: 2015.10.17   
     * @version: 
     *
     * @param record
     * @return
     *
     */
    List<AdminBO> selectSysAdminListByPrimary(AdminSearchBO record);

    /**
     * 查询sysAdmin 列表 count
     * @Title: selectSysAdminCountByPrimary
     * @Description: TODO    ：    
     * @author: leixc
     * @date: 2015.10.17   
     * @version: 
     *
     * @param record
     * @return
     *
     */
    int selectSysAdminCountByPrimary(AdminSearchBO record);

    /**
     * by seNum   查se被那个A+绑定
     * @Title: selectAdminByParmartSeNum
     * @Description: TODO    ：    
     * @author: leixc
     * @date: 2015.10.26   
     * @version: 
     *
     * @param record
     * @return
     *
     */
    List<SysAdmin> selectAdminByParmartSeNum(Map<String, Object> map);
}