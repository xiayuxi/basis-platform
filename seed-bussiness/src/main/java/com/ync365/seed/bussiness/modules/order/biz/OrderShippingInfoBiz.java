/**    
 * 文件名：OrderShippingInfo.java    
 *    
 * 版本信息：    
 * 日期：2015年10月24日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.dao.OrderShippingInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderShippingInfo;

/**    
 *     
 * @Title：OrderShippingInfo  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月24日 下午9:07:32      
 * @version     
 *     
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderShippingInfoBiz {

    private static final Logger logger = LoggerFactory.getLogger(OrderShippingInfoBiz.class);
    
    @Autowired
    private OrderShippingInfoMapperManual orderShippingInfoMapperManual;
    
    public   OrderShippingInfo selectByOrderId(Integer orderId) {
        return orderShippingInfoMapperManual.selectByOrderId(orderId);
    }
    
}
