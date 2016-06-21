package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.UserExpense;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface UserExpenseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserExpense record);

    int insertSelective(UserExpense record);

    UserExpense selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserExpense record);

    int updateByPrimaryKey(UserExpense record);
}