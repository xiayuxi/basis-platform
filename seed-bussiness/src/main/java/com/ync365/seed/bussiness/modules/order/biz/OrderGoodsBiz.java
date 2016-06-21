package com.ync365.seed.bussiness.modules.order.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.bo.OrderInfoGoodsBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderSearch;
import com.ync365.seed.bussiness.modules.order.dao.OrderChangeInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsChangeInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsChangeInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsExpenseInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsExpenseInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsExpenseMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderMergeDetailMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderMergeInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderMergeInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderOptLogMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderSearchMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderStoreMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderStoreMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderSubInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderSubInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderChangeInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoods;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsChangeInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpense;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpenseInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderMergeDetail;
import com.ync365.seed.bussiness.modules.order.entity.OrderMergeInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderOptLog;
import com.ync365.seed.bussiness.modules.order.entity.OrderStore;
import com.ync365.seed.bussiness.modules.order.entity.OrderSubInfo;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionGoodsSkuMapperManual;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGoodsSku;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderGoodsBiz {
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	@Autowired
	private OrderGoodsMapperManual orderGoodsMapperManual;
	@Autowired
	private OrderOptLogMapper orderOptLogMapper;
	@Autowired
	private OrderGoodsExpenseInfoMapperManual orderGoodsExpenseInfoMapperManual;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private OrderSubInfoMapper orderSubInfoMapper;
	@Autowired
	private OrderSubInfoMapperManual orderSubInfoMapperManual;
	@Autowired
	private OrderChangeInfoMapper orderChangeInfoMapper;
	@Autowired
	private OrderGoodsExpenseMapper orderGoodsExpenseMapper;
	@Autowired
	private OrderGoodsExpenseInfoMapper orderGoodsExpenseInfoMapper;
	@Autowired
	private OrderGoodsChangeInfoMapper orderGoodsChangeInfoMapper;
	@Autowired
	private OrderGoodsChangeInfoMapperManual orderGoodsChangeInfoMapperManual;
	@Autowired
	private OrderStoreMapper orderStoreMapper;
	@Autowired
	private OrderStoreMapperManual orderStoreMapperManual;
	@Autowired
	private OrderMergeInfoMapper orderMergeInfoMapper;
	@Autowired
	private OrderMergeInfoMapperManual orderMergeInfoMapperManual;
	@Autowired
	private OrderMergeDetailMapperManual orderMergeDetailMapperManual;
	@Autowired
	private PromotionGoodsSkuMapperManual promotionGoodsSkuMapperManual;
	@Autowired
	private OrderSearchMapperManual orderSearchMapperManual;
	/**
	 * 根据订单id和skuId获取订单商品对象
	 * @param orderId
	 * @param skuId
	 * @param orderGoodsId
	 * @return
	 */
	public OrderInfoGoodsBO getOrderGoodsByOrderIdAndSkuId(String orderId,String skuId,String orderGoodsId) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(orderId !=null && skuId !=null){			
			map.put("orderId", Integer.parseInt(orderId));
			map.put("skuId", Integer.parseInt(skuId));
		}
		OrderGoods orderGoods = orderGoodsMapperManual.getOrderGoodsByOrderIdAndSkuId(map);
		//创建一个对象用于存储在页面展示的数据
		OrderInfoGoodsBO orderGoodsBo = new OrderInfoGoodsBO();
		CloneUtils.cloneObject(orderGoods, orderGoodsBo);
		
		OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(Integer.parseInt(orderId));
		//根据订单商品id获取商品的佣金改价记录			
		List<OrderGoodsExpenseInfo> list = orderGoodsExpenseInfoMapperManual.getHoldGoldByOrderGoodsId(orderGoods.getId());
		if(orderGoodsId !=null){			
			//if(orderInfo.getOrderStatus()==Constants.OrderStatus.ChangePriceNotCheck.v().intValue()){//说明商品被改价了	
			if(orderGoods.getIsChange()==Constants.OrderGoodsIsChangePrice.YES.v()){
				orderGoodsBo.setIsChange(Constants.OrderGoodsIsChangePrice.YES.v());
				//改价前商品单价				
				orderGoodsBo.setGoodsPrice(orderGoods.getGoodsPrice());
				
				//从商品改价记录中取出改价后商品的价格
				List<OrderGoodsChangeInfo> goodsChangeLogs = orderGoodsChangeInfoMapperManual.getOrderGoodsChangeLogsByOrderGoodsId(orderGoods.getId());			
				if(goodsChangeLogs!=null&& goodsChangeLogs.size()>0){
					//按时间取出最后一次的改价(在页面展示商品改后单价)
					OrderGoodsChangeInfo goodsChangeInfo = goodsChangeLogs.get(goodsChangeLogs.size()-1);
					orderGoodsBo.setActualPrice(goodsChangeInfo.getAfterGoodsPrice());			
				}				
				
				if(list !=null && list.size()>0){					
					//改价前商品的佣金				
					OrderGoodsExpenseInfo goodsExpenseInfo = list.get(0);
					orderGoodsBo.setVillageExpense(goodsExpenseInfo.getVillageExpense());
					orderGoodsBo.setPlatformExpense(goodsExpenseInfo.getPlatformExpense());
					orderGoodsBo.setLcExpense(goodsExpenseInfo.getLcExpense());
					orderGoodsBo.setIntegralExpense(goodsExpenseInfo.getIntegralExpense());
					orderGoodsBo.setPlanExpense(goodsExpenseInfo.getPlanExpense());
					
					//从佣金改价记录中取出商品改价后的佣金(按照时间取最后一条)					
					OrderGoodsExpenseInfo expnese2 = list.get(list.size()-1);
					if(expnese2 !=null ){
						orderGoodsBo.setVillageExpenseChange(expnese2.getVillageExpense());
						orderGoodsBo.setPlatformExpenseChange(expnese2.getPlatformExpense());
						orderGoodsBo.setLcExpenseChange(expnese2.getLcExpense());
						orderGoodsBo.setIntegralExpenseChange(expnese2.getIntegralExpense());
						orderGoodsBo.setPlanExpenseChange(expnese2.getPlanExpense());
					}								
					
				}
			}else{//未改价则从订单商品表中获取商品单价
				orderGoodsBo.setIsChange(Constants.OrderGoodsIsChangePrice.NO.v());
				//改价前商品单价				
				orderGoodsBo.setGoodsPrice(orderGoods.getGoodsPrice());
				if(list !=null && list.size()>0){
					//改价前商品的佣金				
					OrderGoodsExpenseInfo goodsExpenseInfo = list.get(0);
					orderGoodsBo.setVillageExpense(goodsExpenseInfo.getVillageExpense());
					orderGoodsBo.setPlatformExpense(goodsExpenseInfo.getPlatformExpense());
					orderGoodsBo.setLcExpense(goodsExpenseInfo.getLcExpense());
					orderGoodsBo.setIntegralExpense(goodsExpenseInfo.getIntegralExpense());
					orderGoodsBo.setPlanExpense(goodsExpenseInfo.getPlanExpense());
				}
			}		
			
		}		
		return orderGoodsBo;
	}
	/**
	 * 保存订单商品改价
	 * @param orderGoods
	 */
	@Transactional
	public void saveOrderGooodsChange(OrderGoods orderGoods,OrderGoodsChangeInfo orderGoodsChangeInfo,OrderGoodsExpenseInfo orderGoodsExpenseInfo) {		
		
		OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderGoods.getOrderId());		
		//if(orderInfo != null && orderInfo.getOrderStatus() == Constants.OrderStatus.NotPay.v().intValue()){//待支付状态			
			
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("orderId", orderGoods.getOrderId());
			map.put("skuId", orderGoods.getSkuId());
			//查询单个商品的信息
			OrderGoods goods = orderGoodsMapperManual.getOrderGoodsByOrderIdAndSkuId(map);	
			orderGoodsChangeInfo.setAuditStatus(Constants.OrderChangePriceAuditStatus.Auditing.v());//把状态设置为改价审核中
			orderGoodsChangeInfo.setOrderGoodsId(goods.getId());
			//修改订单商品改价状态
			goods.setIsChange(Constants.OrderGoodsIsChangePrice.YES.v());
			goods.setAuditStatus(Constants.OrderChangePriceAuditStatus.Auditing.v());
			orderGoodsMapper.updateByPrimaryKeySelective(goods);
			int row1 = orderGoodsChangeInfoMapper.insertSelective(orderGoodsChangeInfo);
			if(row1 > 0){				
				//保存订单商品佣金改价记录
				orderGoodsExpenseInfo.setOrderGoodsId(goods.getId());
				int row2 = orderGoodsExpenseInfoMapper.insertSelective(orderGoodsExpenseInfo);
				if(row2 > 0){					
					//修改订单状态
					orderInfo.setOrderStatus(Constants.OrderStatus.ChangePriceNotCheck.v().intValue());//改价待审核						
					orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
					
					//生成订单操作记录表				
					OrderOptLog orderOptLog = new OrderOptLog();
					orderOptLog.setOrderId(orderGoods.getOrderId());
					orderOptLog.setOrderNo(orderInfo.getOrderNo());//订单编号
					orderOptLog.setOrderStatus(Constants.OrderStatus.ChangePriceNotCheck.v().intValue());//改价待审核
					orderOptLog.setOrderType(Constants.OrderType.MAIN.v());//只有未支付的主订单才可以改价
					orderOptLog.setDelStatus(Constants.IsDelete.NO.v());
					orderOptLog.setOptNum(orderGoodsChangeInfo.getChangeNum());//操作人编号
					orderOptLog.setOptName(orderGoodsChangeInfo.getChangeName());//操作人名字
					orderOptLog.setOptTime(orderGoodsChangeInfo.getChangeTime());//改价时间						
					orderOptLogMapper.insertSelective(orderOptLog);					
					
				}
			}				
		//}
			
	}
	
	/**
	 * 订单商品改价确认审核
	 * @param orderGoods
	 */
	@Transactional
	public void orderGooodsChangeConfirmCheck(OrderGoods orderGoods,OrderGoodsChangeInfo orderGoodsChangeInfo,OrderGoodsExpenseInfo orderGoodsExpenseInfo) {
		OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderGoods.getOrderId());
		BigDecimal orderActualAmountBef = orderInfo.getActualAmount();//订单改价前的应付总金额
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("orderId", orderGoods.getOrderId());
		map.put("skuId", orderGoods.getSkuId());
		//查询订单中的单条商品记录
		OrderGoods orderGoodsDB = orderGoodsMapperManual.getOrderGoodsByOrderIdAndSkuId(map);
		if(orderInfo != null && orderGoodsDB.getIsChange()==Constants.OrderGoodsIsChangePrice.YES.v()){				
			orderGoodsDB.setAuditStatus(Constants.OrderChangePriceAuditStatus.AuditPass.v());							
			//订单中的所有商品
			List<OrderGoods> orderGoodsList = orderGoodsMapperManual.getOrderGoodsListByOrderId(orderGoods.getOrderId());
			//订单中单个商品的所有改价历史
			List<OrderGoodsChangeInfo> infoList = orderGoodsChangeInfoMapperManual.getOrderGoodsChangeLogsByOrderGoodsId(orderGoodsDB.getId());
			//创建一个list集合用于存储订单中被改价的商品
	        //List<OrderGoods> orderGoodsChangeList = new ArrayList<OrderGoods>();	        
	        BigDecimal orderActualAmount = new BigDecimal(0);//订单实际总金额
	        OrderGoodsChangeInfo lastChangeInfo;
			if(infoList!=null && infoList.size()>0){				
				lastChangeInfo = infoList.get(infoList.size()-1);
				lastChangeInfo.setAuditStatus(Constants.OrderChangePriceAuditStatus.AuditPass.v());//把最后一条改价记录状态设置为审核通过
				lastChangeInfo.setAuditNum(orderGoodsChangeInfo.getAuditNum());
				lastChangeInfo.setAuditName(orderGoodsChangeInfo.getAuditName());
				lastChangeInfo.setAuditTime(orderGoodsChangeInfo.getAuditTime());
				orderGoodsDB.setActualPrice(lastChangeInfo.getAfterGoodsPrice());
				orderGoodsDB.setActualAmount(lastChangeInfo.getAfterGoodsPrice().multiply(new BigDecimal(orderGoodsDB.getGoodsCount())));
				//更新订单商品表状态为审核通过
				orderGoodsMapper.updateByPrimaryKeySelective(orderGoodsDB);			
				//修改订单商品改价记录表中的审核状态
				int row1 = orderGoodsChangeInfoMapper.updateByPrimaryKeySelective(lastChangeInfo);
				
				//根据订单商品表的id获取商品的佣金改价记录			
				List<OrderGoodsExpenseInfo> list = orderGoodsExpenseInfoMapperManual.getHoldGoldByOrderGoodsId(orderGoodsDB.getId());
				//从佣金改价记录中取出商品改价后的佣金
				if(list!=null&&list.size()>0){
					OrderGoodsExpenseInfo expnese = list.get(list.size()-1);
					if(expnese !=null ){
						OrderGoodsExpense orderGoodsExpense = orderGoodsExpenseMapper.selectByPrimaryKey(orderGoodsDB.getId());
						orderGoodsExpense.setVillageExpense(expnese.getVillageExpense());
						orderGoodsExpense.setPlatformExpense(expnese.getPlatformExpense());
						orderGoodsExpense.setLcExpense(expnese.getLcExpense());
						orderGoodsExpense.setIntegralExpense(expnese.getIntegralExpense());
						orderGoodsExpense.setPlanExpense(expnese.getPlanExpense());
						//更新订单商品佣金表的最终数据
						orderGoodsExpenseMapper.updateByPrimaryKeySelective(orderGoodsExpense);
					}			
				}		
				
				//生成订单的操作记录            
				OrderOptLog orderOptLog = new OrderOptLog();
				orderOptLog.setOrderId(orderGoods.getOrderId());
				orderOptLog.setOrderNo(orderInfo.getOrderNo());
				orderOptLog.setOrderType(Constants.OrderType.MAIN.v().intValue());
				orderOptLog.setOrderStatus(Constants.OrderStatus.ChangePriceChecked.v().intValue());
				orderOptLog.setDelStatus(Constants.IsDelete.NO.v());	
				//操作人的编号和姓名				
				orderOptLog.setOptNum(orderGoodsChangeInfo.getAuditNum());
				orderOptLog.setOptName(orderGoodsChangeInfo.getAuditName());
				orderOptLog.setOptTime(orderGoodsChangeInfo.getAuditTime());
				orderOptLogMapper.insertSelective(orderOptLog);
				
				if(row1 > 0){ 							
					int times = 0;//定义个变量记录次数
					//查询订单中所有被改价的商品的			
					List<OrderGoods> goodsChangePriceList = orderGoodsMapperManual.getOrderGoodsChangePriceList(orderGoods.getOrderId());
					for (OrderGoods goods3 : goodsChangePriceList) {					
						if(goods3.getAuditStatus()==Constants.OrderChangePriceAuditStatus.AuditPass.v()){
							times += 1;
						}								
					}
					if(times == goodsChangePriceList.size()){//说明所有商品的改价都审核通过	
						//改价审核通过后订单中的所有商品
						List<OrderGoods> orderGoodsListAfter = orderGoodsMapperManual.getOrderGoodsListByOrderId(orderGoods.getOrderId());
						//计算改价审核通过后订单的应付总金额			        		 
			        	for (OrderGoods orderGoods2 : orderGoodsListAfter) {
			        		if(orderGoods2.getActualAmount() != null){
			        			orderActualAmount = orderActualAmount.add(orderGoods2.getActualAmount());
			        		}   						
			        	}
			        	BigDecimal orderReduceAmount = new BigDecimal(0);//订单改价差额
			        	if(orderActualAmountBef.compareTo(orderActualAmount)==1){//改前大于改后
			        		orderReduceAmount = orderActualAmountBef.subtract(orderActualAmount);
			        	}
			        	
			        	BigDecimal orderInfoTotalAmount = new BigDecimal(0);
			        	//改价后订单总金额
			        	orderInfoTotalAmount = orderInfo.getTotalAmount().subtract(orderReduceAmount);
			        	orderInfo.setTotalAmount(orderInfoTotalAmount);
			        	
			        	BigDecimal couponAmount = orderInfo.getCouponAmount();//订单中使用的红包金额
			        	//如果红包金额大于或等于应付总金额，则把应付金额设置为0
			        	if(couponAmount!=null&&couponAmount.compareTo(orderActualAmount)==1){							
			        		orderInfo.setActualAmount(new BigDecimal(0));
						}else if(couponAmount!=null&&couponAmount.compareTo(orderActualAmount)==0){
							orderInfo.setActualAmount(new BigDecimal(0));
						}else{//红包金额小于订单金额
							//计算订单中使用红包之后的应付总金额
							orderInfo.setActualAmount(orderActualAmount);
						}		        	
						//修改订单状态为改价已审核
						orderInfo.setOrderStatus(Constants.OrderStatus.ChangePriceChecked.v().intValue());							
						orderInfoMapper.updateByPrimaryKeySelective(orderInfo);	
						
						//审核通过后生成订单改价记录
						OrderChangeInfo orderChangeInfo = new OrderChangeInfo();
						orderChangeInfo.setOrderId(orderGoods.getOrderId());
						orderChangeInfo.setOrderNo(orderInfo.getOrderNo());
						orderChangeInfo.setBeforeAmount(orderInfo.getTotalAmount());
						orderChangeInfo.setAfterAmount(orderActualAmount);
						orderChangeInfo.setCreateTime(new Date());							
						orderChangeInfoMapper.insertSelective(orderChangeInfo);						
						
						//修改订单合并表中的商品总金额
						/*OrderMergeDetail orderMergeDetail = orderMergeDetailMapperManual.getOrderMergeDetailByOrderId(orderGoods.getOrderId());
						if(orderMergeDetail!=null){
							OrderMergeInfo orderMergeInfo = orderMergeInfoMapperManual.getOrderMergeInfoByOrderMergeNo(orderMergeDetail.getOrderMergeNo());
							orderMergeInfo.setGoodsAmount(orderActualAmount);
							orderMergeInfoMapper.updateByPrimaryKeySelective(orderMergeInfo);
						}	*/					
						
						//修改订单商户表中的实际总金额
						Map<String,Object> map3= new HashMap<String,Object>();
						map3.put("orderId", orderGoodsDB.getOrderId());
						map3.put("storeId", -1);
						OrderStore orderStore = orderStoreMapperManual.getOrderStoreByOrderIdAndStoreId(map3);						
						if(orderStore!=null){
							//改价后订单店铺的实际应付金额(此版本仅支持自营商品改价)
							orderStore.setActualAmount(orderStore.getActualAmount().subtract(orderReduceAmount));
							orderStore.setTotalAmount(orderStore.getTotalAmount().subtract(orderReduceAmount));							
							orderStoreMapper.updateByPrimaryKeySelective(orderStore);
						}	
						
					}		
					
				}		
			}			
					
		}				
				
	}
	/**
	 * 
	 * @Title: getSubGoodsBySubOrderId
	 * @Description: TODO    ：    根据子订单编号查询订单商品
	 * @author: sunyf    
	 * @date: 2015年10月16日 下午2:30:07       
	 * @version: 
	 *
	 * @param subId
	 * @return
	 *
	 */
	/*public List<OrderGoods> getSubGoodsBySubOrderId(Integer subId) {
	   return orderGoodsMapperManual.getSubGoodsBySubOrderId(subId);
	}*/
	/**
	 * 
	 * @Title: getGoodsByOrderIdAndStoreId
	 * @Description: 根据店铺ID和订单ID查询订单商品 
	 * @author: Ken    
	 * @date: 2015年10月27日 下午4:15:29       
	 * @version: 
	 *
	 * @param param
	 * @return
	 *
	 */
	public List<OrderGoods> getGoodsByOrderIdAndStoreId(Map<String,Integer> param) {
	    return orderGoodsMapperManual.getGoodsByOrderIdAndStoreId(param);
	}	
	/**
	 * 
	 * @Title: getOrderGoodsByOrderId
	 * @Description: TODO    ：    根据主订单编号查询订单商品
	 * @author: sunyf    
	 * @date: 2015年10月16日 下午2:38:42       
	 * @version: 
	 *
	 * @param orderId
	 * @return
	 *
	 */
	public List<OrderGoods> getOrderGoodsListByOrderId(Integer orderId){
	    return orderGoodsMapperManual.getOrderGoodsListByOrderId(orderId);
	}
	/**
     * 
     * @Title: getSubGoodsBySubOrderId
     * @Description: TODO    ：    根据子订单编号查询订单商品
     * @author: sunyf    
     * @date: 2015年10月16日 下午2:30:07       
     * @version: 
     *
     * @param subId
     * @return
     *
     */
    public List<OrderGoods> getSubGoodsBySubOrderId(Integer subId) {
       return orderGoodsMapperManual.getSubGoodsBySubOrderId(subId);
    }
    /**
     * 订单商品改价审核拒绝
     * @param orderGoods
     */
	public void AuditRefused(OrderGoods orderGoods,OrderGoodsChangeInfo goodsChangeInfo) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("orderId", orderGoods.getOrderId());
		map.put("skuId", orderGoods.getSkuId());
		//查询订单中的单条商品
		OrderGoods orderGoodsDB = orderGoodsMapperManual.getOrderGoodsByOrderIdAndSkuId(map);
		orderGoodsDB.setAuditStatus(Constants.OrderChangePriceAuditStatus.AuditRefused.v());
		orderGoodsMapper.updateByPrimaryKeySelective(orderGoodsDB);
		List<OrderGoodsChangeInfo> orderGoodsChangeLogs = orderGoodsChangeInfoMapperManual.getOrderGoodsChangeLogsByOrderGoodsId(orderGoodsDB.getId());
		if(orderGoodsChangeLogs!=null&&orderGoodsChangeLogs.size()>0){
			OrderGoodsChangeInfo orderGoodsChangeInfo = orderGoodsChangeLogs.get(orderGoodsChangeLogs.size()-1);
			CloneUtils.cloneObject(goodsChangeInfo, orderGoodsChangeInfo);//拷贝审核人及审核时间
			orderGoodsChangeInfo.setAuditStatus(Constants.OrderChangePriceAuditStatus.AuditRefused.v());				
			orderGoodsChangeInfo.setOrderGoodsId(orderGoodsDB.getId());
			orderGoodsChangeInfoMapperManual.updateStatusByOrderGoodsId(orderGoodsChangeInfo);
		}		
	}

}
