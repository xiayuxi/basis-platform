package com.ync365.seed.bussiness.modules.order.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.dao.OrderContactMapper;
import com.ync365.seed.bussiness.modules.order.entity.OrderContact;


@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderContactBiz {
    
    @Autowired
    private OrderContactMapper orderContactMapper;
    
    public OrderContact selectOrderContactById(Integer id){
        return orderContactMapper.selectByPrimaryKey(id);
    }
    
}
