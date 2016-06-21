package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreSeRelationship;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysPopStoreSeRelationshipMapper {
    int deleteByPrimaryKey(String popStoreNum);

    int insertSelective(SysPopStoreSeRelationship record);

    SysPopStoreSeRelationship selectByPrimaryKey(String popStoreNum);

    int updateByPrimaryKeySelective(SysPopStoreSeRelationship record);

    List<SysPopStoreSeRelationship> selectSeRelationshipByMap(Map<String, Object> map);

    int selectCountByMap(Map<String, Object> map);
}