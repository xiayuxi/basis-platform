package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.UserCoupon;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface UserCouponMapper {
    int deleteByPrimaryKey(Integer userCouponId);

    int insert(UserCoupon record);

    int insertSelective(UserCoupon record);

    UserCoupon selectByPrimaryKey(Integer userCouponId);

    int updateByPrimaryKeySelective(UserCoupon record);

    int updateByPrimaryKey(UserCoupon record);
}