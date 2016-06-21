package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ync365.seed.bussiness.modules.user.bo.AdminInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.SysAdminInfoBO;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysAdminInfoMapper {
    /**
     * 新增
     * @param record
     * @return
     */
    int insert(SysAdminInfo record);

    /**
     * 新增（空字段剔除）
     * @param record
     * @return
     */
    int insertSelective(SysAdminInfo record);
    
    /**
	 * 编号删除信息，物理删除
	 * 
	 * @param adminNum
	 * @return
	 */
	int deleteByPrimaryKey(String adminNum);
	
	/**
	 * 编号修改信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(SysAdminInfo record);
	
	/**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
    List<SysAdminInfo> selectPageByMap(Map<String, ?> map);
    
	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
	
	/**根据用户编号查询SE
   	 * @author xieang
   	 * 2015年9月23日
   	 * @param userNum	用户编号
   	 * @param roleId		角色id
   	 * @param isAuthentication	是否审核通过
   	 * @return
   	 */
   	List<SysAdminInfo> getSysAdminInfoByUserNum(@Param("userNum") String userNum,@Param("roleId") Integer roleId,@Param("isAuthentication") Integer isAuthentication);
   	
   	/**根据se编号或者手机号查询查询SE(后台使用，无审核)
   	 * @author xieang
   	 * 2015年10月11日
   	 * @param userNum	用户编号
   	 * @param roleId		角色id
   	 * @return
   	 */
   	SysAdminInfoBO getAdminInfoByUserNumOrMobile(@Param("userNum") String userNum,@Param("roleId") Integer roleId);

   	/**
   	 * by vs 编号查询 se信息
   	 * @param userNum
   	 * @return
   	 */
    SysAdminInfo selectSysAdminInfoByVsNum(String userNum);

    /**
     * by us 编号查询 se信息
     * @param userNum
     * @return
     */
    SysAdminInfo selectSysAdminInfoByUsNum(String userNum);

    /**
     * by lc 编号查询 se信息
     * @param userNum
     * @return
     */
    SysAdminInfo selectSysAdminInfoByLcNum(String userNum);

    /**
     * by a+编号anum查询 se信息
     * @param map
     * @param startIndex
     * @param rows
     * @return
     */
    List<SysAdminInfoBO> selectSysLcInfoListByAnum(Map<String, Object> map);

    /**
     * by a+编号anum查询se数量count
     * @param map
     * @return
     */
    int selectCountSysLcInfoByAnum(Map<String, Object> map);

    /**
     * by adminNum查询SysAdminInfoBO
     * @param adminNum
     * @return
     */
    SysAdminInfo selectByPrimaryKey(String adminNum);

    /**
     * 查询SE列表
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    List<SysAdminInfoBO> selectSysSeInfoByParam(AdminInfoSearchBO adminInfoSearchBO);
    /**
     * 查询SE列表
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    int selectSysSeInfoByParamCount(AdminInfoSearchBO adminInfoSearchBO);
    /**
     * 查询A+列表
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    List<SysAdminInfoBO> selectSysAInfoByParam(AdminInfoSearchBO adminInfoSearchBO);
    /**
     * 查询A+列表
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    int selectSysAInfoByParamCount(AdminInfoSearchBO adminInfoSearchBO);
    /**
     * adminNum查询
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    SysAdminInfoBO selectSysSeInfoByNum(@Param("adminNum")String adminNUm,@Param("roleId")Integer roleId);
    /**
     * adminNum查询A+
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    SysAdminInfoBO selectSysAInfoByNum(@Param("adminNum")String adminNUm,@Param("roleId")Integer roleId);
    
    /**
     * by 用户姓名查询编号list
     * @param adminName
     * @return
     */
    List<SysAdminInfo> selectAdminNumListByAdminName(@Param("adminName")String adminName);
}