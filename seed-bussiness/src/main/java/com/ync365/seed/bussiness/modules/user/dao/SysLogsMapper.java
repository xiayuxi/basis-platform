package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysLogs;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysLogs record);

    SysLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLogs record);

    List<SysLogs> selectPageByMap(Map<String, Object> map);

    Integer selectCountByMap(Map<String, Object> map);
}