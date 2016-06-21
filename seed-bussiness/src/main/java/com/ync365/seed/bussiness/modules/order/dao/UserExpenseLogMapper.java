package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.UserExpenseLog;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface UserExpenseLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserExpenseLog record);

    int insertSelective(UserExpenseLog record);

    UserExpenseLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserExpenseLog record);

    int updateByPrimaryKey(UserExpenseLog record);
}