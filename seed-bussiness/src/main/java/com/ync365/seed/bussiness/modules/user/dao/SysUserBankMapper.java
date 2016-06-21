package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysUserBank;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysUserBankMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysUserBank record);

    SysUserBank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserBank record);

    List<SysUserBank> selectPageByMap(Map<String,Object> map);
}