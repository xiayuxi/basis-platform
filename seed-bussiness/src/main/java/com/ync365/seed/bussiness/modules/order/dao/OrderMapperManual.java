/**    
 * 文件名：OrderMapperManual.java    
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

import com.ync365.seed.bussiness.modules.order.entity.OrderListInfoManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderManual;
import com.ync365.seed.commons.annotation.MyBatisRepository;

/**    
 *     
 * @Title：OrderMapperManual  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月24日 下午6:50:23      
 * @version     
 *     
 */
@MyBatisRepository
public interface OrderMapperManual {
    List<OrderManual> selectOrder(Map<String, Object> map);
    
    List<OrderManual> selectSearchOrderList(Map<String, Object> map);
    
    List<Integer> selectSearchOrder(Map<String, Object> map);
    
    Long countSelectList(Map<String, Object> map);
    
    Long countRecSelectList(Map<String, Object> map);
    
    Long countOrderListInfo(Map<String, Object> map);
    
    Long countManagerListByPageInfo(Map<String, Object> map);
    
    List <OrderListInfoManual> orderListInfo(Map<String,Object>map);
    
    List <OrderListInfoManual> orderAllListByPageInfo(Map<String,Object>map);
    
    List <OrderListInfoManual> selectManagerListByPageInfo(Map<String,Object>map);
    
    List <OrderManual> selectRecOrder(Map<String,Object>map);
    
    Integer delOrder(Map<String, Object> map);
    
    Integer delSubOrder(Map<String, Object> map);
    
    Integer updateOrderStatusByOrderId(Map<String,Object> map);
    
    Integer updateSubOrderStatusByOrderId(Map<String,Object> map);
    
    Integer countUnDelSubNumBySubOrderId(Map<String,Object> map);
    
    List <OrderListInfoManual> orderListByOrderID(Map<String ,Object> map);
    
    Long  countOrderListByOrderID(Map<String ,Object> map);
    /**
     * 
     * @Title: orderTypeNum
     * @Description: TODO    ：    获取不同订单状态佣金
     * @author: ivan    
     * @date: 2015年10月27日 下午2:02:24       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    Integer orderTypeNum(Map<String ,Object>map);
    /**
     * 用户取消订单，根据主订单Id获取orderNo（当主订单包含子订单是获取子订单orderNo，当订单为单独的主订单时获取主订单orderNo）
     * @Title: getOrderNoByMainOrderId
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月6日 下午4:24:15       
     * @version: 
     *
     * @param orderId
     * @return
     *
     */
    List<String> getOrderNoByMainOrderId(Integer orderId);
}
