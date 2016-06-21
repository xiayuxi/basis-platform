package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.bo.PopStoreDecorateBO;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreDecorateInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysPopStoreDecorateInfoMapper {
	
    /**
     * 功能描述：根据id删除
     * @author liukai
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 功能描述：添加
     * @author liukai
     * @param record
     * @return
     */
    int insertSelective(SysPopStoreDecorateInfo record);

    /**
     * 功能描述：根据id查询
     * @author liukai
     * @param id
     * @return
     */
    SysPopStoreDecorateInfo selectByPrimaryKey(Integer id);

    /**
     * 功能描述：修改
     * @author liukai
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysPopStoreDecorateInfo record);
    
    /**
     * 功能描述：多功能查询
     * @author liukai
     * @param map
     * @return
     */
    List<SysPopStoreDecorateInfo> selectPageByMap(Map<String, Object> map);

	/**
	 * 功能描述：根据 popStoreNum 获取装修信息
	 * @author liukai
	 * @param popStoreNum
	 * @return
	 */
	SysPopStoreDecorateInfo selectByPopStoreNum(String popStoreNum);

	/**
	 * 功能描述：根据 popStoreNum 更新
	 * @author liukai
	 * @param info
	 * @return
	 */
	int updateByPopStoreNum(PopStoreDecorateBO info);

}