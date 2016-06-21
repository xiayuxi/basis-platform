package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.bo.LargeCustomerValueInfoBO;
import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerValueInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysLargeCustomerValueInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysLargeCustomerValueInfo record);

    SysLargeCustomerValueInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLargeCustomerValueInfo record);

    List<SysLargeCustomerValueInfo> selectPageByMap(Map<String, Object> map);

    List<LargeCustomerValueInfoBO> getLargeCustomerInfoByUserNum(String userNum);
}