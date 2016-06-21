package com.ync365.seed.bussiness.modules.ad.dao;

import java.util.List;

import com.ync365.seed.bussiness.modules.ad.entity.AdPosition;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface AdPositionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AdPosition record);

    int insertSelective(AdPosition record);

    AdPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdPosition record);

    int updateByPrimaryKeyWithBLOBs(AdPosition record);

    int updateByPrimaryKey(AdPosition record);
    
    /**
     * 查询所有广告位
     * @Title: selectAdPositionList
     * @Description: 
     * @return  List<AdPosition>    
     * @throws
     */
    List<AdPosition> selectAdPositionAllList();
}