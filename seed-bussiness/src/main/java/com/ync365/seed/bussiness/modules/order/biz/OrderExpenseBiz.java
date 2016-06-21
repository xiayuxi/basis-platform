/**    
 * 文件名：OrderExpenseBiz.java    
 *    
 * 版本信息：    
 * 日期：2015年10月27日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.biz;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.dao.OrderExpenseMapperManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderExpense;

/**    
 *     
 * @Title：OrderExpenseBiz  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月27日 下午3:12:13      
 * @version     
 *     
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderExpenseBiz {

    private static final Logger logger = LoggerFactory.getLogger(OrderExpenseBiz.class);

    @Autowired
    OrderExpenseMapperManual orderExpenseMapperManual;

    public OrderExpense selectOrderExpenseByOrderId(Integer orderId, Integer orderType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderId", orderId);
        map.put("orderType", orderType);
        return orderExpenseMapperManual.selectOrderExpenseByOrderId(map);
    }
}
