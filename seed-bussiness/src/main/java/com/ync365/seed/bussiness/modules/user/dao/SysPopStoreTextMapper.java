package com.ync365.seed.bussiness.modules.user.dao;

import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreTextWithBLOBs;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysPopStoreTextMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysPopStoreTextWithBLOBs record);

    SysPopStoreTextWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPopStoreTextWithBLOBs record);

	SysPopStoreTextWithBLOBs selectByPopStoreNum(String popStoreNum);

}