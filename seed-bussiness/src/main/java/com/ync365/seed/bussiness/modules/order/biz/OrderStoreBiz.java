package com.ync365.seed.bussiness.modules.order.biz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.dao.OrderStoreMapperManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderStore;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderStoreBiz {

    private static final Logger logger = LoggerFactory.getLogger(OrderStoreBiz.class);

    @Autowired
    private OrderStoreMapperManual orderStoreMapperManual;

    
    public List<OrderStore> selectOrderStoreListByOrderSubNo(String orderSubNo) {
        return orderStoreMapperManual.selectOrderStoreListByOrderSubNo(orderSubNo);
    }
    
    public List<OrderStore>  selectOrderStoreList(Integer orderId){
        return orderStoreMapperManual.selectOrderStoreList(orderId);
    }
}
