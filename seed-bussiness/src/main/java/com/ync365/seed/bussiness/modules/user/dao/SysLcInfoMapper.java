package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ync365.seed.bussiness.modules.user.bo.LcInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysLcInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysLcInfoMapper {
	
    /**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
    int insertSelective(SysLcInfo record);
    
    /**
	 * 编号删除信息，物理删除
	 * 
	 * @param userNum
	 * @return
	 */
	int deleteByPrimaryKey(String userNum);

	/**
	 * 编号获取信息
	 * 
	 * @param userNum
	 * @return
	 */
	SysLcInfo selectByPrimaryKey(String userNum);

	/**
	 * 编号修改信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(SysLcInfo record);

	/**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
	List<SysLcInfo> selectPageByMap(Map<String, Object> map);

	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
	
	/**根据用户编号查询Lc
   	 * @author xieang
   	 * 2015年9月23日
   	 * @param userNum	用户编号
   	 * @param roleId		角色id
   	 * @param isAuthentication	是否审核通过
   	 * @return
   	 */
   	List<SysLcInfo> getSysLcInfoByUserNum(@Param("userNum") String userNum,@Param("roleId") Integer roleId,@Param("isAuthentication") Integer isAuthentication);

	/**
	 * 根据A+编号获取此A+用户下 所有lc用户信息
	 * @param map
	 * @return
	 */
	int selectCountSysLcInfoByAnum(Map<String, Object> map);

	/**
	 * by Vs编号查询此vs的绑定Lc
	 * @param userNum
	 * @return
	 */
	SysLcInfo selectSysLcInfoByVsNum(String userNum);

	/**
     * by Us编号查询此us的绑定Lc
     * @param userNum
     * @return
     */
    SysLcInfo selectSysLcInfoByUsNum(String userNum);

    /**
     * 根据se编号获取此se用户下 所有lc用户信息 count
     * @param map
     * @return
     */
    int selectCountSysLcInfoBySenum(Map<String, Object> map);

    /**
     * by Primary查询lc列表
     * @param lcInfoSearchBO
     * @return
     */
    List<LcInfoBO> selectSysLcInfoListByPrimary(LcInfoSearchBO lcInfoSearchBO);

    /**
     *by Primary 查询 lc count
     * @param lcInfoSearchBO
     * @return
     */
    int selectCountSysLcInfoByPrimary(LcInfoSearchBO lcInfoSearchBO);

    /**根据编号或者手机号查询
     * @author xieang
     * 2015年10月10日
     * @param userNum
     * @return
     */
    LcInfoBO selectLcInfoByNumOrMobile(String userNum);
    
    /**根据用户编号获取Lc相信信息（包括绑定关系）
     * @author xieang
     * 2015年10月11日
     * @param userNum
     * @return
     */
    LcInfoBO getLcInfoByUserNum(String userNum);
}