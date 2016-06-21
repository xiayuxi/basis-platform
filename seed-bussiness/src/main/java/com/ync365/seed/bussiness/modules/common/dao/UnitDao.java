package com.ync365.seed.bussiness.modules.common.dao;

import java.util.List;

import com.ync365.seed.bussiness.modules.common.entity.Unit;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface UnitDao {
    int deleteByPrimaryKey(Integer unitId);

    int insert(Unit record);

    int insertSelective(Unit record);

    Unit selectByPrimaryKey(Integer unitId);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);

    List<Unit> selectAllUnit();
}