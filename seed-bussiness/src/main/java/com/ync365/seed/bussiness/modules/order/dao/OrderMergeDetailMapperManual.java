/**    
 * 文件名：OrderMergeDetailMapperManual.java    
 *    
 * 版本信息：    
 * 日期：2015年10月24日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;

import com.ync365.seed.bussiness.modules.order.entity.OrderMergeDetail;
import com.ync365.seed.commons.annotation.MyBatisRepository;

/**    
 *     
 * @Title：OrderMergeDetailMapperManual  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月24日 下午3:37:16      
 * @version     
 *     
 */
@MyBatisRepository
public interface OrderMergeDetailMapperManual {
    /**
     * 
     * @Title: getMergeDListByMergeIId
     * @Description: TODO    ：    根据合并订单ID查询合并订单关联表信息
     * @author: ivan    
     * @date: 2015年10月24日 下午3:39:34       
     * @version: 
     *
     * @param orderMergeInfoId
     * @return
     *
     */
    List <OrderMergeDetail> getMergeDListByMergeIId(Integer orderMergeInfoId);
    /**
     * 
     * @Title: insertOrderMergeDetailList
     * @Description: TODO    ：    批量插入合并订单详情表
     * @author: ivan    
     * @date: 2015年10月24日 下午3:43:20       
     * @version: 
     *
     * @param orderMergeDetailList
     * @return
     *
     */
    int insertOrderMergeDetailList (List<OrderMergeDetail> orderMergeDetailList);
	OrderMergeDetail getOrderMergeDetailByOrderId(Integer orderId);
	

}
