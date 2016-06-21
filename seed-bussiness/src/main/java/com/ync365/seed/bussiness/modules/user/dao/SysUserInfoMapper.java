package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.entity.SysUserInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysUserInfoMapper {
	/**
	 * 编号删除信息，物理删除
	 * 
	 * @param String userNum
	 * @return
	 */
	int deleteByPrimaryKeyUserNum(String userNum);
	
	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(SysUserInfo record);
	
	/**
	 * 编号获取信息
	 * 
	 * @param userNum
	 * @return
	 */
	SysUserInfo selectByPrimaryKeyUserNum(String userNum);
	
	/**
	 * 编号修改信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(SysUserInfo record);

	/**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
	List<SysUserInfo> selectPageByMap(Map<String, Object> map);
	
	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);

	/**
	 * 通过vs 编号vsNum 连表查询 us列表信息
	 * @param map
	 * @return
	 */
	List<SysUserInfo> selectSysUsListByVsNum(Map<String, Object> map);

	/**
	 * 通过vs 编号vsNum 连表查询 us列表 count
	 * @param map
	 * @return
	 */
	int selectCouontSysUsByVsNum(Map<String, Object> map);

	/**
	 * 通过lc 编号lcNum  连表 查询 us 列表
	 * @param map
	 * @return
	 */
	List<SysUserInfo> selectSysUsInfoListByLcNum(Map<String, Object> map);
	
	
	/**
	 * 通过Lc 编号 lcNum 连接 查询 us count
	 * @param map
	 * @return
	 */
	int selectCouontSysUsByLcNum(Map<String, Object> map);


    
    /**
     * 通过us编号查询所有的关联信息
     * @author xieang
     * 2015年10月9日
     * @param userUsInfoSearchBo
     * @return
     */
    UserInfoBO getUserInfoByUserNum(String userNum);
    /**
     * 此为用户管理模块中us查询列表的sql，其中包括了需求里面的所有查询
     * @author xieang
     * 2015年10月9日
     * @param userUsInfoSearchBo
     * @return
     */
    List<UserInfoBO> selectUserInfoByParam(UserUsInfoSearchBo userUsInfoSearchBo);
    int selectUserInfoByParamCount(UserUsInfoSearchBo userUsInfoSearchBo);

    /**
     * 查询测土培肥 列表  by leixc 20151011
     * @param userUsInfoSearchBo
     * @return
     */
    List<UserInfoBO> selectUserInfoCtpfByParamy(UserUsInfoSearchBo userUsInfoSearchBo);

    /**
     * 测土配肥 count by leixc 20151011
     * @param userUsInfoSearchBo
     * @return
     */
    int selectUserInfoCtpfByParamyCount(UserUsInfoSearchBo userUsInfoSearchBo);
}