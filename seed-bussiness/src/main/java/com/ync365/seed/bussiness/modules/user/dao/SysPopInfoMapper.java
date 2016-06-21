package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.bo.PopInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.PopInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysPopInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysPopInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysPopInfo record);

    SysPopInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPopInfo record);

    List<SysPopInfo> selectPageByMap(Map<String, Object> map);
    
    List<PopInfoBO> selectPopInfoByPrimary(PopInfoSearchBO record);

}