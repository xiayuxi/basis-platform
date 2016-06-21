package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderUser;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrderUserMapperManual {

	OrderUser selectOrderUserByOrderNo(String orderNo);

}
