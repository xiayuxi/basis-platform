package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.UserCouponLog;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface UserCouponLogMapper {
    int deleteByPrimaryKey(Integer userCouponLogId);

    int insert(UserCouponLog record);

    int insertSelective(UserCouponLog record);

    UserCouponLog selectByPrimaryKey(Integer userCouponLogId);

    int updateByPrimaryKeySelective(UserCouponLog record);

    int updateByPrimaryKey(UserCouponLog record);
}