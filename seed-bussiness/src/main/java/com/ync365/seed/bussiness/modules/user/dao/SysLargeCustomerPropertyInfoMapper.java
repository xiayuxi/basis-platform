package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerPropertyInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysLargeCustomerPropertyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysLargeCustomerPropertyInfo record);

    SysLargeCustomerPropertyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLargeCustomerPropertyInfo record);

    List<SysLargeCustomerPropertyInfo> selectPageByMap(Map<String, Object> map);
}