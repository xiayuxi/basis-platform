package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysUserBrowse;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysUserBrowseMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysUserBrowse record);

    SysUserBrowse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserBrowse record);

    List<SysUserBrowse> selectPageByMap(Map<String, Object> map);

    int deleteAllBrowses(String userNum);
}