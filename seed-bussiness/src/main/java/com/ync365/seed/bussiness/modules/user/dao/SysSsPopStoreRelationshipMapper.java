package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysSsPopStoreRelationship;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysSsPopStoreRelationshipMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysSsPopStoreRelationship record);

    SysSsPopStoreRelationship selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysSsPopStoreRelationship record);

    List<SysSsPopStoreRelationship> selectPageByMap(Map<String, Object> map);
}