/**    
 * 文件名：OrderShippingMapperManual.java    
 *    
 * 版本信息：    
 * 日期：2015年10月24日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderShippingInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

/**    
 *     
 * @Title：OrderShippingMapperManual  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月24日 下午9:11:08      
 * @version     
 *     
 */
@MyBatisRepository
public interface OrderShippingInfoMapperManual {
    
     OrderShippingInfo selectByOrderId(Integer orderId);
}
