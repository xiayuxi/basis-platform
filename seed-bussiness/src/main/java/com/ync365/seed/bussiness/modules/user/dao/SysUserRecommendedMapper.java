package com.ync365.seed.bussiness.modules.user.dao;

import com.ync365.seed.bussiness.modules.user.entity.SysUserRecommended;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysUserRecommendedMapper {

    int insertSelective(SysUserRecommended record);
}