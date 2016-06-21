package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ync365.seed.bussiness.modules.user.entity.SysAdminService;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysAdminServiceMapper {
	/**
     * 新增
     * @param record
     * @return
     */
    int insert(SysAdminService record);

    /**
     * 新增（空字段剔除）
     * @param record
     * @return
     */
    int insertSelective(SysAdminService record);
    
    /**
	 * 编号删除信息，物理删除
	 * 
	 * @param adminNum
	 * @return
	 */
	int deleteByAdminNum(String adminNum);
	/**
	 * 编号删除信息，物理删除
	 * 
	 * @param adminNum
	 * @return
	 */
	int deleteByAdminNumAndLevel(@Param("adminNum")String adminNum,@Param("level")Integer level);
	/**
	 * 编号修改信息
	 * 
	 * @param record
	 * @return
	 */
	int update(SysAdminService record);
    
    /**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
	List<SysAdminService> selectPageByMap(Map<String, Object> map);

	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
	
	/**根据用户编号查询所有的服务区域
	 * @author xieang
	 * 2015年10月15日
	 * @param userNum
	 * @return
	 */
	List<SysAdminService> selectServiceListByNum(String userNum);
	
	/**根据用户编号查询所有的服务区域(包括等级)
	 * @author xieang
	 * 2015年10月15日
	 * @param userNum
	 * @return
	 */
	List<SysAdminService> selectServiceListByNumLevel(@Param("userNum") String userNum,@Param("level") Integer level);
}