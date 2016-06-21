/**    
 * 文件名：OrderStoreMapperManual.java    
 *    
 * 版本信息：    
 * 日期：2015年10月24日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.order.entity.OrderStore;
import com.ync365.seed.commons.annotation.MyBatisRepository;

/**    
 *     
 * @Title：OrderStoreMapperManual  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月24日 下午2:52:59      
 * @version     
 *     
 */

@MyBatisRepository
public interface OrderStoreMapperManual {

    List <OrderStore> selectOrderStoreList(Integer orderId);
    
    List <OrderStore> selectOrderStoreListByOrderSubNo(String orderSubNo);

	OrderStore getOrderStoreByOrderIdAndStoreId(Map<String, Object> map3);

}
