package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.order.bo.OrderGoodsBO;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoods;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrderGoodsMapperManual {	

	int saveOrderGooodsChange(OrderGoods orderGoods);

	/**
	 * 
	 * @Title: getOrderGoodsListByOrderId
	 * @Description: TODO    ：    根据主订单ID查询订单商品列表
	 * @author: ivan    
	 * @date: 2015年10月25日 下午5:39:35       
	 * @version: 
	 *
	 * @param orderId
	 * @return
	 *
	 */
	List<OrderGoods> getOrderGoodsListByOrderId(Integer orderId);

	int orderGooodsChangeConfirmAudit(OrderGoods orderGoods);	

	OrderGoods getOrderGoodsByOrderIdAndSkuId(Map<String, Integer> map);
	
	/**
     * 
     * @Title: getSubGoodsBySubOrderId
     * @Description: TODO    ：     根据子订单编号查询订单商品
     * @author: sunyf    
     * @date: 2015年10月16日 下午2:28:19       
     * @version: 
     *
     * @param subId
     * @return
     *
     */
    List<OrderGoods> getSubGoodsBySubOrderId(int subId);

    /**
     * 
     * @Title: getGoodsByOrderIdAndStoreId
     * @Description: 
     * @author: Ken    
     * @date: 2015年10月27日 下午4:11:26       
     * @version: 
     *
     * @param param
     * @return
     *
     */
    List<OrderGoods> getGoodsByOrderIdAndStoreId(Map <String,Integer> param);
    //查询被改价且审核通过的商品
	List<OrderGoods> getOrderGoodsCheckedListByOrderId(Integer orderId);
	//查询订单中所有被价的商品
	List<OrderGoods> getOrderGoodsChangePriceList(Integer orderId);

	void updateAuditStatus(OrderGoods orderGoods);

}
