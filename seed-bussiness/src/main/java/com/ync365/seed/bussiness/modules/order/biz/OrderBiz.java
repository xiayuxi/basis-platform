package com.ync365.seed.bussiness.modules.order.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.bo.OrderBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderManagerSearchBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderSearchBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderTypeNum;
import com.ync365.seed.bussiness.modules.order.dao.OrderCouponMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderExpenseMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsSubMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderOptLogMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderPayMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderStoreMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderSubInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderSubInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.UserCouponMapper;
import com.ync365.seed.bussiness.modules.order.dao.UserExpenseMapper;
import com.ync365.seed.bussiness.modules.order.entity.OrderCoupon;
import com.ync365.seed.bussiness.modules.order.entity.OrderExpense;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsSub;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderListInfoManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderOptLog;
import com.ync365.seed.bussiness.modules.order.entity.OrderPay;
import com.ync365.seed.bussiness.modules.order.entity.OrderStore;
import com.ync365.seed.bussiness.modules.order.entity.OrderSubInfo;
import com.ync365.seed.bussiness.modules.order.entity.UserCoupon;
import com.ync365.seed.bussiness.modules.order.entity.UserExpense;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.DateUtils;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderBiz {

    private static final Logger logger = LoggerFactory.getLogger(OrderBiz.class);

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    
    @Autowired
    private OrderSubInfoMapper orderSubInfoMapper;
    
    @Autowired
    private OrderOptLogMapper orderOptLogMapper;
    
    @Autowired
    private OrderStoreMapperManual orderStoreMapperManual;
    
    @Autowired
    private OrderGoodsSubMapper orderGoodsSubMapper;
    
    @Autowired
    private OrderMapperManual orderMapperManual;
    
    @Autowired
    private OrderInfoMapperManual orderInfoMapperManual;
    
    @Autowired
    private OrderExpenseMapper orderExpenseMapper;
    
    @Autowired
    private UserExpenseMapper userExpenseMapper;
    
    @Autowired
    private OrderSubInfoMapperManual orderSubInfoMapperManual;
    
    @Autowired
    private OrderPayMapper orderPayMapper;
    
    @Autowired
    private OrderCouponMapperManual orderCouponMapperManual;
    
    @Autowired
    private UserCouponMapper userCouponMapper;
    
    @Transactional(readOnly = false)
    /**
     * 我的订单列表查询
     * @Title: selectOrder
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月2日 下午5:43:22       
     * @version: 
     *
     * @param condition
     * @return
     *
     */
    public List<OrderManual> selectOrder(OrderSearchBO orderSearchBO) {
        Map<String, Object> map = new HashMap<String, Object>();//查询条件
        selectOrderBOToMap(orderSearchBO,map);
        return  orderMapperManual.selectOrder(map);
    }
    /**
     * 根据查询条件、查询出的订单ID查询订单详情
     * @Title: selectSearchOrderList
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月4日 下午3:51:02       
     * @version: 
     *
     * @param orderSearchBO
     * @return
     *
     */
    public List<OrderManual> selectSearchOrderList(OrderSearchBO orderSearchBO) {
        Map<String, Object> map = new HashMap<String, Object>();//查询条件
        selectSearchOrderBOToMap(orderSearchBO,map);
        map.put("startIndex", orderSearchBO.getStartIndex());
        map.put("pageSize", orderSearchBO.getPageSize());
        return  orderMapperManual.selectSearchOrderList(map);
    }
    /**
     * 我的订单根据查询条件查询符合条件的主订单ID
     * @Title: selectSearchOrder
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月2日 下午5:43:40       
     * @version: 
     *
     * @param condition
     * @return
     *
     */
    public List<Integer> selectSearchOrder(OrderSearchBO orderSearchBO) {
        Map<String, Object> map = new HashMap<String, Object>();//查询条件
        List<Integer> orderIdList = new ArrayList<Integer>();
        selectSearchOrderBOToMap(orderSearchBO,map);
        orderIdList = orderMapperManual.selectSearchOrder(map);
        int startIndex =orderSearchBO.getStartIndex();
        int endIndex= 0;
        endIndex = ((startIndex+orderSearchBO.getPageSize())>orderIdList.size())
                ? orderIdList.size() :startIndex+orderSearchBO.getPageSize();
        orderIdList = orderIdList.subList(startIndex, endIndex);
        return orderIdList;
    }
    /**
     * 
     * @Title: countSelectList
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月2日 下午5:46:23       
     * @version: 
     *
     * @param condition
     * @return
     *
     */
    public Long countSelectList(OrderSearchBO orderSearchBO) {
        Map<String, Object> map = new HashMap<String, Object>();//查询条件
        selectOrderBOToMap(orderSearchBO,map);
        return orderMapperManual.countSelectList(map);
    }
    public Long countRecSelectList(OrderSearchBO orderSearchBO) {
        Map<String, Object> map = new HashMap<String, Object>();//查询条件
        selectRecOrderBOToMap(orderSearchBO,map);
        return orderMapperManual.countSelectList(map);
    }
    public Long countSelectRecOrder(OrderSearchBO orderSearchBO) {
        Map<String, Object> map = new HashMap<String, Object>();//查询条件
        selectRecOrderBOToMap(orderSearchBO,map);
        return orderMapperManual.countRecSelectList(map);
    }
    /**
     * 
     * @Title: countOrderListByOrderID
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月2日 下午5:47:24       
     * @version: 
     *
     * @param condition
     * @return
     *
     */
    public Long countOrderListByOrderID(Map<String, Object> condition) {
        return orderMapperManual.countOrderListByOrderID(condition);
    }

    public List<OrderListInfoManual> orderAllListByPageInfo(OrderManagerSearchBO orderManagerSearchBO) {
        Map<String, Object> map = new HashMap<String, Object>();
        orderSupplierSearchBOToMap(orderManagerSearchBO,map);
        return orderMapperManual.orderAllListByPageInfo(map);
    }
    
    public List<OrderListInfoManual> selectManagerListByPageInfo(OrderManagerSearchBO orderManagerSearchBO) {
        Map<String, Object> map = new HashMap<String, Object>();
        orderManagerSearchBOToMap(orderManagerSearchBO,map);
        return orderMapperManual.selectManagerListByPageInfo(map);
    }
    
    public Long countManagerListByPageInfo(OrderManagerSearchBO orderManagerSearchBO) {
        Map<String, Object> map = new HashMap<String, Object>();
        orderManagerSearchBOToMap(orderManagerSearchBO,map);
        return orderMapperManual.countManagerListByPageInfo(map);
    }

    public List<OrderListInfoManual> orderListInfo(Map<String, Object> map) {
        return orderMapperManual.orderListInfo(map);
    }

    public List<OrderListInfoManual> orderListByOrderID(Map<String, Object> map) {
        return orderMapperManual.orderListByOrderID(map);
    }

    public List<OrderManual> selectRecOrder(OrderSearchBO orderSearchBO) {
        Map<String, Object> map = new HashMap<String, Object>();//查询条件
        selectRecOrderBOToMap(orderSearchBO,map);
        return orderMapperManual.selectRecOrder(map);
    }

    public Long countOrderListInfo(OrderManagerSearchBO orderManagerSearchBO) {
        Map<String, Object> map = new HashMap<String, Object>();
        orderSupplierSearchBOToMap(orderManagerSearchBO,map);
        return orderMapperManual.countOrderListInfo(map);
    }

    public OrderBO delOrderAfterDelSub(Map<String, Object> map) {
        map.put("isDel", Constants.Whether.Yes.v());
        int count = orderMapperManual.countUnDelSubNumBySubOrderId(map);
        OrderBO orderBO = new OrderBO();
        if (count == 0) {
            OrderSubInfo orderSubInfo = orderSubInfoMapper.selectByPrimaryKey((Integer) map.get("subId"));
            map.put("orderId", orderSubInfo.getOrderInfoId());
            map.put("isDel", String.valueOf(Constants.Whether.Yes.v()));
            map.put("isRecDel", String.valueOf(Constants.Whether.No.v()));
            orderMapperManual.delOrder(map);
            OrderInfo orderInfo = new OrderInfo();
            orderInfo = orderInfoMapper.selectByPrimaryKey(orderSubInfo.getOrderInfoId());
            orderBO.setOrderId(orderInfo.getId());
            orderBO.setOrderNo(orderInfo.getOrderNo());
            orderBO.setOrderType(Constants.OrderType.MAIN.v());
        }
        return orderBO;
    }

    @Transactional(readOnly = false)
    public Integer delRecycle(List<OrderBO> orderList) {
        int count = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        for (OrderBO orderBO : orderList) {
            if(orderBO.getOrderId()==null||orderBO.getOrderId().equals(null)){
                orderBO.setOrderId(-1);
            }
            if (orderBO.getOrderType() == Constants.OrderType.MAIN.v()) {
                map.put("orderId", orderBO.getOrderId());
                map.put("isDel", String.valueOf(Constants.Whether.Yes.v()));
                map.put("isRecDel", String.valueOf(Constants.Whether.Yes.v()));
                count = count + orderMapperManual.delOrder(map);
            } else if (orderBO.getOrderType() == Constants.OrderType.SUB.v()) {
                map.put("orderId", orderBO.getOrderId());
                map.put("isDel", String.valueOf(Constants.Whether.Yes.v()));
                map.put("isRecDel", String.valueOf(Constants.Whether.Yes.v()));
                count = count + orderMapperManual.delSubOrder(map);
            }
        }
        return count;
    }
    @Transactional(readOnly = false)
    public Integer resRecycle(List<OrderBO> orderList) {
        int count = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        for (OrderBO orderBO : orderList) {
            if(orderBO.getOrderId()==null||orderBO.getOrderId().equals(null)){
                orderBO.setOrderId(-1);
            }
            if (orderBO.getOrderType() == Constants.OrderType.MAIN.v()) {
                map.put("orderId", orderBO.getOrderId());
                map.put("isDel", String.valueOf(Constants.Whether.No.v()));
                map.put("isRecDel", String.valueOf(Constants.Whether.No.v()));
                count = count + orderMapperManual.delOrder(map);
            } else if (orderBO.getOrderType() == Constants.OrderType.SUB.v()) {
                map.put("orderId", orderBO.getOrderId());
                map.put("isDel", String.valueOf(Constants.Whether.No.v()));
                map.put("isRecDel", String.valueOf(Constants.Whether.No.v()));
                count = count + orderMapperManual.delSubOrder(map);
            }
        }
        return count;
    }

    @Transactional(readOnly = false)
    public Integer delOrder(OrderBO orderBO) {
        int count = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        if (orderBO.getOrderType() == Constants.OrderType.MAIN.v()) {
            map.put("orderId", orderBO.getOrderId());
            map.put("isDel", String.valueOf(Constants.Whether.Yes.v()));
            map.put("isRecDel", String.valueOf(Constants.Whether.No.v()));
            count = count + orderMapperManual.delOrder(map);
        } else if (orderBO.getOrderType() == Constants.OrderType.SUB.v()) {
            map.put("id", orderBO.getOrderId());
            map.put("isDel", String.valueOf(Constants.Whether.Yes.v()));
            map.put("isRecDel", String.valueOf(Constants.Whether.No.v()));
            count = count + orderMapperManual.delSubOrder(map);
        }
        return count;
    }

    @Transactional(readOnly = false)
    public Integer restoreRecycleOrder(List<OrderBO> orderList) {
        int count = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        for (OrderBO orderBO : orderList) {
            if (orderBO.getOrderType() == Constants.OrderType.MAIN.v()) {
                map.put("orderId", orderBO.getOrderId());
                map.put("isDel", String.valueOf(Constants.Whether.No.v()));
                map.put("isRecDel", String.valueOf(Constants.Whether.No.v()));
                count = count + orderMapperManual.delOrder(map);
            } else if (orderBO.getOrderType() == Constants.OrderType.SUB.v()) {
                map.put("orderId", orderBO.getOrderId());
                map.put("isDel", String.valueOf(Constants.Whether.No.v()));
                map.put("isRecDel", String.valueOf(Constants.Whether.No.v()));
                count = count + orderMapperManual.delSubOrder(map);
            }
        }
        return count;
    }

    @Transactional(readOnly = false)
    public void insert(Map<String, Object> map) {
        OrderOptLog orderOptLog = new OrderOptLog();
        Date curDate = new Date();
        orderMapperManual.updateOrderStatusByOrderId(map);
        orderOptLog.setOptName((String) map.get("userName"));
        orderOptLog.setOptNum((String) map.get("userNum"));
        orderOptLog.setOrderId((Integer) map.get("orderId"));
        orderOptLog.setOrderNo((String) map.get("orderNo"));
        orderOptLog.setOrderStatus((Integer) map.get("orderStatus"));
        orderOptLog.setOptTime(curDate);
        orderOptLog.setOrderType((Integer) map.get("orderType"));
        orderOptLogMapper.insert(orderOptLog);
    }

    @Transactional(readOnly = false)
    public Integer updateOrderStatusByOrderId(Integer orderId, Integer orderType, Integer curStatus, Integer toStatus) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderId", orderId);
        map.put("orderInfoBefStatus", curStatus);
        map.put("toStatus", toStatus);
        if (orderType == Constants.OrderType.MAIN.v()) {
            orderMapperManual.updateOrderStatusByOrderId(map);
        } else if (orderType == Constants.OrderType.SUB.v())
            orderMapperManual.updateSubOrderStatusByOrderId(map);
        return 0;
    }

    public List<OrderStore> selectOrderStoreListByOrderId(Integer orderId) {
        return orderStoreMapperManual.selectOrderStoreList(orderId);
    }

    @Transactional
    public int createSubOrder(OrderSubInfo orderSubInfo, List<OrderGoodsSub> orderGoodsSubList) {
        // 订单
        orderSubInfoMapper.insertSelective(orderSubInfo);
        Integer orderSubId = orderSubInfo.getId();
        // 订单商品
        for (OrderGoodsSub orderGoodsSub : orderGoodsSubList) {
            orderGoodsSub.setOrderSubId(orderSubId);
            orderGoodsSubMapper.insertSelective(orderGoodsSub);
        }
        return orderSubId;
    }

    public OrderTypeNum orderTypeNum(List<UserInfoBO> userList) {
        OrderTypeNum orderTypeNum = new OrderTypeNum();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userList", userList);
        map.put("orderStatus", Constants.OrderStatus.NotPay.v().intValue());
        orderTypeNum.setNoPayNum(orderMapperManual.orderTypeNum(map));
        map.put("orderStatus", Constants.OrderStatus.Delivery.v().intValue());
        orderTypeNum.setDeliveryNum(orderMapperManual.orderTypeNum(map));
        map.put("orderStatus", Constants.OrderStatus.Completed.v().intValue());
        orderTypeNum.setCompletedNum(orderMapperManual.orderTypeNum(map));
        return orderTypeNum;
    }
    
    /**
     * 
     * @Title: selectOrderByOrderNo
     * @Description: 根据订单号查询订单
     * @author: Ken    
     * @date: 2015年10月27日 下午6:12:23       
     * @version: 
     *
     * @param orderNo
     * @return
     *
     */
    public List <OrderInfo> selectOrderByOrderNo(String orderNo){
        return orderInfoMapperManual.selectOrderByOrderNo(orderNo);
    }
    
    
    @Transactional
    public void payOrder(OrderInfo orderInfo,OrderExpense orderExpense,OrderPay orderPay){
        orderInfo.setPayType(orderPay.getPayType());
        orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
        Integer orderId = orderInfo.getId();
        String orderNo = orderInfo.getOrderNo();
        userExpenseMapper.insertSelective(this.getUserExpense(orderId, orderNo, Constants.OrderType.MAIN.v(), orderExpense.getaExpense(), orderExpense.getaNum()));
        userExpenseMapper.insertSelective(this.getUserExpense(orderId, orderNo, Constants.OrderType.MAIN.v(), orderExpense.getSeExpense(), orderExpense.getSeNum()));
        userExpenseMapper.insertSelective(this.getUserExpense(orderId, orderNo, Constants.OrderType.MAIN.v(), orderExpense.getVillageExpense(), orderExpense.getVsNum()));
        userExpenseMapper.insertSelective(this.getUserExpense(orderId, orderNo, Constants.OrderType.MAIN.v(), orderExpense.getLcExpense(), orderExpense.getLcNum()));
        orderExpenseMapper.insertSelective(orderExpense);
        orderPay.setOrderid(orderId);
        orderPay.setPayAmount(orderInfo.getActualAmount());
        orderPayMapper.insertSelective(orderPay);
        if(orderNo.startsWith("002")){
            List <OrderCoupon> orderCouponList = orderCouponMapperManual.selectOrderCouponByOrderNo(orderNo);
            if(orderCouponList != null && orderCouponList.size() > 0){
                for(OrderCoupon orderCoupon : orderCouponList ){
                    UserCoupon userCoupon = new UserCoupon();
                    userCoupon.setUserCouponId(orderCoupon.getUserCouponId());
                    userCoupon.setStatus(Constants.CouponStatus.USED.v());
                    userCouponMapper.updateByPrimaryKeySelective(userCoupon);
                }
            }
        }
        // orderPay.setPayDetail(payDetail);
    }
    
    @Transactional
    public Integer paySubOrder(OrderInfo orderInfo,OrderExpense orderExpense,OrderSubInfo orderSubInfo, List<OrderGoodsSub> orderGoodsSubList,OrderPay orderPay){
        orderInfo.setPayType(orderPay.getPayType());
        orderSubInfo.setPayType(orderPay.getPayType());
        // 订单
        orderSubInfoMapper.insertSelective(orderSubInfo);
        Integer orderSubId = orderSubInfo.getId();
        orderExpense.setOrderId(orderSubId);
        // 订单商品
        for (OrderGoodsSub orderGoodsSub : orderGoodsSubList) {
            orderGoodsSub.setOrderSubId(orderSubId);
            orderGoodsSubMapper.insertSelective(orderGoodsSub);
        }
        
        if(orderInfoMapper.selectByPrimaryKey(orderInfo.getId()).getOrderStatus() < Constants.OrderStatus.Paid.v()){
            orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
        }
        Integer orderId = orderSubInfo.getId();
        String orderNo = orderSubInfo.getOrderNo();
        userExpenseMapper.insertSelective(this.getUserExpense(orderId, orderNo, Constants.OrderType.SUB.v(), orderExpense.getaExpense(), orderExpense.getaNum()));
        userExpenseMapper.insertSelective(this.getUserExpense(orderId, orderNo, Constants.OrderType.SUB.v(), orderExpense.getSeExpense(), orderExpense.getSeNum()));
        userExpenseMapper.insertSelective(this.getUserExpense(orderId, orderNo, Constants.OrderType.SUB.v(), orderExpense.getVillageExpense(), orderExpense.getVsNum()));
        userExpenseMapper.insertSelective(this.getUserExpense(orderId, orderNo, Constants.OrderType.SUB.v(), orderExpense.getLcExpense(), orderExpense.getLcNum()));
        orderExpenseMapper.insertSelective(orderExpense);
        orderPay.setOrderid(orderId);
        orderPay.setPayAmount(orderSubInfo.getActualAmount());
        orderPayMapper.insertSelective(orderPay);
        
        // 入驻商扣红包
        String orderSubNo = orderSubInfo.getOrderSubNo();
        if(orderSubNo.startsWith("102")){
            List <OrderCoupon> orderCouponList = orderCouponMapperManual.selectOrderCouponByOrderNo(orderNo);
            if(orderCouponList != null && orderCouponList.size() > 0){
                for(OrderCoupon orderCoupon : orderCouponList ){
                    UserCoupon userCoupon = new UserCoupon();
                    userCoupon.setUserCouponId(orderCoupon.getUserCouponId());
                    userCoupon.setStatus(Constants.CouponStatus.USED.v());
                    userCouponMapper.updateByPrimaryKeySelective(userCoupon);
                }
            }
        }
        
        return orderSubId;
    }
    
    private UserExpense getUserExpense(Integer orderId,String orderNo, Integer orderType,BigDecimal expense,String userNum){
        UserExpense userExpense = new UserExpense();
        userExpense.setOrderId(orderId);
        userExpense.setOrderNo(orderNo);
        userExpense.setOrderType(orderType);
        userExpense.setExpense(expense);
        userExpense.setStatus(Constants.OrderExpenseStatus.UnSettle.v());
        userExpense.setUserNum(userNum);
        userExpense.setCreateTime(new Date());
        return userExpense;
    }
    /**
     * 查询已退货的订单
     * @param orderId
     */
	public OrderInfo queryReturnOrder(String orderId) {
		OrderInfo orderInfo = new OrderInfo();
		if(StringUtils.isNotBlank(orderId)){
			orderInfo = orderInfoMapperManual.selectReturnOrderById(Integer.parseInt(orderId));			
		}		
		return orderInfo;
	}
	/**
	 * 用户取消订单，根据主订单Id获取orderNo（当主订单包含子订单是获取子订单orderNo，当订单为单独的主订单时获取主订单orderNo）
	 * @Title: getOrderNoByMainOrderId
	 * @Description: TODO    ：    
	 * @author: ivan    
	 * @date: 2015年11月6日 下午4:22:43       
	 * @version: 
	 *
	 * @param orderId
	 * @return
	 *
	 */
	public List<String> getOrderNoByMainOrderId(Integer orderId) {
	    return orderMapperManual.getOrderNoByMainOrderId(orderId);
	}
	/**
	 * 我的订单转换
	 * @Title: selectOrderBOToMap
	 * @Description: TODO    ：    
	 * @author: ivan    
	 * @date: 2015年11月3日 下午12:17:19       
	 * @version: 
	 *
	 * @param orderSearchBO
	 * @param map
	 *
	 */
	private void selectOrderBOToMap(OrderSearchBO orderSearchBO ,Map<String,Object> map ) {
	    Date beginDate = new Date(orderSearchBO.getBeginTime()*1000);
        Date endDate = new Date(orderSearchBO.getEndTime()*1000);
        map.put("status", orderSearchBO.getStatus());
        map.put("beginTime", DateUtils.formatDate(beginDate)+" 00:00:00");
        map.put("endTime", DateUtils.formatDate(endDate)+" 23:59:59");
        map.put("userNum", orderSearchBO.getUser_num());
        map.put("isDel", Constants.Whether.No.v());
        map.put("isRecDel", Constants.Whether.No.v());
        map.put("orderIdList", orderSearchBO.getOrderIdList());
        map.put("startIndex", orderSearchBO.getStartIndex());
        map.put("pageSize", orderSearchBO.getPageSize());
	}
	private void selectSearchOrderBOToMap(OrderSearchBO orderSearchBO ,Map<String,Object> map ) {
	    Date beginDate = new Date(orderSearchBO.getBeginTime()*1000);
        Date endDate = new Date(orderSearchBO.getEndTime()*1000);
        map.put("status", orderSearchBO.getStatus());
        map.put("beginTime", DateUtils.formatDate(beginDate)+" 00:00:00");
        map.put("endTime", DateUtils.formatDate(endDate)+" 23:59:59");
        map.put("userNum", orderSearchBO.getUser_num());
        map.put("isDel", Constants.Whether.No.v());
        map.put("isRecDel", Constants.Whether.No.v());
        map.put("searchInfo", orderSearchBO.getSearchInfo());
        map.put("orderIdList", orderSearchBO.getOrderIdList());
	}
	/**
	 * 订单回收站转换
	 * @Title: selectOrderBOToMap
	 * @Description: TODO    ：    
	 * @author: ivan    
	 * @date: 2015年11月3日 下午12:17:31       
	 * @version: 
	 *
	 * @param orderSearchBO
	 * @param map
	 *
	 */
	private void selectRecOrderBOToMap(OrderSearchBO orderSearchBO ,Map<String,Object> map ) {
        Date beginDate = new Date(orderSearchBO.getBeginTime()*1000);
        Date endDate = new Date(orderSearchBO.getEndTime()*1000);
        map.put("status", orderSearchBO.getStatus());
        map.put("beginTime", DateUtils.formatDate(beginDate)+" 00:00:00");
        map.put("endTime", DateUtils.formatDate(endDate)+" 23:59:59");
        map.put("userNum", orderSearchBO.getUser_num());
        map.put("isDel", Constants.Whether.Yes.v());
        map.put("isRecDel", Constants.Whether.No.v());
        map.put("orderIdList", orderSearchBO.getOrderIdList());
        map.put("searchInfo", orderSearchBO.getSearchInfo());
    }
	private void orderManagerSearchBOToMap(OrderManagerSearchBO orderManagerSearchBO ,Map<String,Object> map ) {
	    map.put("optStatus", orderManagerSearchBO.getStatus());
        map.put("status", orderManagerSearchBO.getStatus());
        map.put("userList", orderManagerSearchBO.getUserList());
        map.put("startIndex",orderManagerSearchBO.getStartIndex() );
        map.put("pageSize", orderManagerSearchBO.getPageSize());
        map.put("userTel", orderManagerSearchBO.getUserTel());
        if (orderManagerSearchBO.getBeginTime()!=null) {
            Date beginDate = new Date(orderManagerSearchBO.getBeginTime()*1000);
            map.put("beginTime", DateUtils.formatDate(beginDate)+" 00:00:00");
        }
        if (orderManagerSearchBO.getEndTime()!=null) {
            Date endDate = new Date(orderManagerSearchBO.getEndTime()*1000);
            map.put("endTime", DateUtils.formatDate(endDate)+" 23:59:59");
        }
        map.put("orderNo", orderManagerSearchBO.getOrderNo());
        map.put("userName", orderManagerSearchBO.getUserName());
        map.put("payWay", orderManagerSearchBO.getPayWay());
        map.put("orderSource", orderManagerSearchBO.getOrderSource());
        map.put("storeId", orderManagerSearchBO.getStoreId());
        map.put("noPay", Constants.OrderStatus.Paid.v().intValue());
	}
	private void orderSupplierSearchBOToMap(OrderManagerSearchBO orderManagerSearchBO ,Map<String,Object> map ) {
	    map.put("optStatus", orderManagerSearchBO.getStatus());
        map.put("status", orderManagerSearchBO.getStatus());
        map.put("status2", orderManagerSearchBO.getStatus2());
        map.put("userList", orderManagerSearchBO.getUserList());
        map.put("startIndex",orderManagerSearchBO.getStartIndex());
        map.put("pageSize", orderManagerSearchBO.getPageSize());
        map.put("userTel", orderManagerSearchBO.getUserTel());
        if (orderManagerSearchBO.getBeginTime()!=null) {
            Date beginDate = new Date(orderManagerSearchBO.getBeginTime()*1000);
            map.put("beginTime", DateUtils.formatDate(beginDate)+" 00:00:00");
        }
        if (orderManagerSearchBO.getEndTime()!=null) {
            Date endDate = new Date(orderManagerSearchBO.getEndTime()*1000);
            map.put("endTime", DateUtils.formatDate(endDate)+" 23:59:59");
        }
        map.put("orderNo", orderManagerSearchBO.getOrderNo());
        map.put("userName", orderManagerSearchBO.getUserName());
	}
	
}
