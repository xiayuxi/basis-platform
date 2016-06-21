package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysUserYnb;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysUserYnbMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysUserYnb record);

    SysUserYnb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserYnb record);

    List<SysUserYnb> selectPageByMap(Map<String, Object> map);
}