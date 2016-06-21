package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysAuthenInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysAuthenInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysAuthenInfo record);

    SysAuthenInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAuthenInfo record);

    List<SysAuthenInfo> selectPageByMap(Map<String, Object> map);
}