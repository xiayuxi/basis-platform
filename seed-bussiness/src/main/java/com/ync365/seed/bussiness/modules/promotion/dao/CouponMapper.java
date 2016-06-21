package com.ync365.seed.bussiness.modules.promotion.dao;

import com.ync365.seed.bussiness.modules.promotion.entity.Coupon;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface CouponMapper {
    int deleteByPrimaryKey(Integer couponId);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
}