/**    
 * 文件名：OrderExpenseMapperManual.java    
 *    
 * 版本信息：    
 * 日期：2015年10月27日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.dao;

import java.util.Map;

import com.ync365.seed.bussiness.modules.order.entity.OrderExpense;
import com.ync365.seed.commons.annotation.MyBatisRepository;

/**    
 *     
 * @Title：OrderExpenseMapperManual  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月27日 下午3:31:00      
 * @version     
 *     
 */
@MyBatisRepository
public interface OrderExpenseMapperManual {

    OrderExpense selectOrderExpenseByOrderId(Map<String,Object>map);
}
