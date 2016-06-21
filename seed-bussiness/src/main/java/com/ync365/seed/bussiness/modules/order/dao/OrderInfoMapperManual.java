package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.order.bo.OrderInfoBO;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrderInfoMapperManual {

    void cancleOrder(int parseInt);

    List<OrderInfo> selectOrderListByIds(List<Integer> orderIdList);
    
    List<OrderInfoBO> getOrderListByUserNum(String userNum);

	List<OrderInfoBO> getSupplierOrderListByStoreId(Integer storeId);
    
    /**
     * 
     * @Title: selectOrderByOrderNo
     * @Description: 通过订单号查找主订单
     * @author: Ken    
     * @date: 2015年10月27日 下午6:05:49       
     * @version: 
     *
     * @param orderNo
     * @return
     *
     */
    List<OrderInfo> selectOrderByOrderNo(String orderNo);
    /**
     * 根据订单id查询已退货的订单
     * @param parseInt
     * @return
     */
	OrderInfo selectReturnOrderById(Integer orderId );
	
	/**
	 * 查询需要取消的订单
	 * @Title: selectOrderNeedCancel
	 * @Description: TODO    ：    
	 * @author: ivan    
	 * @date: 2015年11月2日 下午2:55:11       
	 * @version: 
	 *
	 * @param orderNo
	 * @return
	 *
	 */
	List<OrderInfo> selectOrderNeedCancel(Map <String,Object> map);

	int updateOrderStatusByOrderId(Map<String, Object> map);
}
