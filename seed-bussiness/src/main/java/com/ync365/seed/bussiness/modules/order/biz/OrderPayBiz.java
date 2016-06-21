package com.ync365.seed.bussiness.modules.order.biz;



import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.bo.OrderPayBO;
import com.ync365.seed.bussiness.modules.order.redis.RedisOrderService;
import com.ync365.seed.commons.mapper.JsonMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderPayBiz { 
    
    @Autowired
    private RedisOrderService redisOrderService;
    
    public void setOrderPayInfo(OrderPayBO orderPayBO) {
        String orderNo =  orderPayBO.getOrderNo();
        JsonMapper mapper = new JsonMapper();
        String value = mapper.toJson(orderPayBO);
        redisOrderService.setOrderPayInfo(orderNo, value);
        
    }
    
    public OrderPayBO getOrderPayInfo(String orderNo){
        String str = redisOrderService.getOrderPayInfo(orderNo);
        if(StringUtils.isBlank(str)){
            return null;
        }
        JsonMapper mapper = new JsonMapper();
        OrderPayBO result = mapper.fromJson(str, OrderPayBO.class);
        return result;
    }
}
