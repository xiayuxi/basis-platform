package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysPopStoreMapper {
	/**
	 * 编号删除信息，物理删除
	 * 
	 * @param popStoreNum
	 * @return
	 */
	int deleteByPrimaryKey(String  popStoreNum);

	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(SysPopStore record);
	
	/**
	 *编号获取信息 
	 * 
	 * @param popStoreNum
	 * @return
	 */
	SysPopStore selectByPrimaryKey(String  popStoreNum);

	/**
	 * 编号修改信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(SysPopStore record);
	
	/**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
	List<SysPopStore> selectPageByMap(Map<String, Object> map);

	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
	

	/**
	 * 查据前台查询条件查询列表
	 * @param popStoreSearchBo
	 * @return
	 */
	List<PopStoreBO> selectPageBySearchBO(PopStoreSearchBO popStoreSearchBo);

	/**
	 * 根据查询条件获取查询总条数
	 * @param popStoreSearchBo
	 * @return
	 */
	long selectPageBySearchCount(PopStoreSearchBO popStoreSearchBo);

	/**
	 * 根据类型 DS FS popstore name查询 list
	 * @param record
	 * @return
	 */
	List<SysPopStore> selectPopStoreByStoreNamePrimary(PopStoreSearchBO record);

	int selectPopStroeDsSpFsByByParamCount(PopStoreSearchBO popStoreSearchBo);
	
	/**
	 * by ss 管理查询 ss关联的syspopstore 信息
	 * @param userNum
	 * @return
	 */
	List<PopStoreBO> selectPopStoreBySsUserNum(String userNum);
	
   /**
    * 通过 store 编号或者 手机号 查询 信息
    * @param userNumOrMobileSearch
    * @return
    */
	SysPopStore selectPopStoreByPrimaryKey(String userNumOrMobileSearch);

}