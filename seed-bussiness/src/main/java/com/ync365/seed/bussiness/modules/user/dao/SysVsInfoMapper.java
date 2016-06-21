package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ync365.seed.bussiness.modules.user.bo.VsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysVsInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysVsInfoMapper {
	
   
    /*int insert(SysVsInfo record);*/

    /**
     * 添加信息
     * 
     * @param record
     * @return
     */
    int insertSelective(SysVsInfo record);
    
    /**
	 * 根据主键userNum查询
	 * @param userNum
	 * @return
	 */
    SysVsInfo selectByPrimaryKeyUserNum(String userNum);
    
    /**
   	 * 查询
   	 * 
   	 * @param map
   	 * @return
   	 */
   	List<SysVsInfo> selectPageByMap(Map<String, Object> map);
   	
   	/**
   	* 物理  通过 userNum删除数据
   	* 
   	* @param userNum
   	* @return
   	*/
   	int deleteSysVsInfoByUserNum(String userNum);
   	
   	/**
   	 * userNum为条件更新
   	 * 
   	 * @param record
   	 * @return
   	 */
   	int update(SysVsInfo record);
   	
   	/**根据地址code查询村站
   	 * @author xieang
   	 * 2015年9月23日
   	 * @param addressCode	地址编号
   	 * @param roleId		角色id
   	 * @param isAuthentication	是否审核通过
   	 * @return
   	 */
   	List<SysVsInfo> getSysVsInfoByAddressCode(@Param("addressCode") String addressCode,@Param("roleId") Integer roleId,@Param("isAuthentication") Integer isAuthentication);
   	
   	/**根据用户编号查询村站
   	 * @author xieang
   	 * 2015年9月23日
   	 * @param userNum	用户编号
   	 * @param roleId		角色id
   	 * @param isAuthentication	是否审核通过
   	 * @return
   	 */
   	List<SysVsInfo> getSysVsInfoByUserNum(@Param("userNum") String userNum,@Param("roleId") Integer roleId,@Param("isAuthentication") Integer isAuthentication);
   	
   	/**
   	 *通过LC 编号lcNum查询VS列表
   	 * 
   	 * @param map
   	 * @return
   	 */
   	List<SysVsInfo> selectSysVsInfoListByLcNum(Map<String, Object> map);
   	
   	/**
   	 *通过LC 编号lcNum查询VS列表 数量
   	 * 
   	 * @param map
   	 * @return
   	 */
   	int  selectCountSysVsInfoByLcNum(Map<String, Object> map);

   	/**
   	 * 通过 us 编号usNum 查询vs 信息
   	 * @param map
   	 * @return
   	 */
	SysVsInfo selectSysVsInfoByUserNum(Map<String, Object> map);
	
	/**根据编号或者手机号搜索
	 * @author xieang
	 * 2015年10月10日
	 * @param userNum
	 * @return
	 */
	VsInfoBO selectVsInfoByNumOrMobile(String userNum);

	/**
	 * by se编号 获取se下vs 数量 count
	 * @param map
	 * @return
	 */
	int selectCountSysVsInfoBySenum(Map<String, Object> map);

	/**
	 * 查询vsinfo 列表
	 * @param vsInfoSearchBO
	 * @return
	 */
    List<VsInfoBO> selectSysVsInfoListByPrimary(VsInfoSearchBO vsInfoSearchBO);

    /**
     * count vsinfo 
     * @param vsInfoSearchBO
     * @return
     */
    int selectCountSysVsInfoByPrimary(VsInfoSearchBO vsInfoSearchBO);
    
    /**
     * 更具编号查询vs详细信息 包括绑定lc se a+
     * @author xieang
     * 2015年10月10日
     * @param userNum
     * @return
     */
    VsInfoBO selectVsInfoByUserNum(String userNum);
}