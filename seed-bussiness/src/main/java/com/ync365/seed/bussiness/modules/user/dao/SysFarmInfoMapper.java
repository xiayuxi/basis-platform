package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysFarmInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysFarmInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysFarmInfo record);

    SysFarmInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysFarmInfo record);

    List<SysFarmInfo> selectListByMap(Map<String, Object> map);

    int selectCountByMap(Map<String, Object> map);
}