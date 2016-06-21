package com.ync365.seed.bussiness.modules.order.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.bo.OrderBO;
import com.ync365.seed.bussiness.modules.order.dao.OrderInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderOptLogMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderOptLogMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderSubInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderOptLog;
import com.ync365.seed.bussiness.modules.order.entity.OrderSubInfo;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.utils.Constants;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderOptLogBiz {
    
    private static final Logger logger = LoggerFactory.getLogger(OrderOptLogBiz.class);
    
    @Autowired
    private OrderOptLogMapper orderOptLogMapper;
    @Autowired
    private OrderOptLogMapperManual orderOptLogMapperManual;
    @Autowired
    private OrderMapperManual  orderMapperManual;
    @Autowired
    private OrderInfoMapperManual  orderInfoMapperManual;
    @Autowired
    private OrderSubInfoMapperManual orderSubInfoMapperManual;
   /**
    * 
    * @Title: getOrderLogByOrderTypeId
    * @Description: TODO    ：    根据订单类型、订单ID获取订单操作日志
    * @author: ivan    
    * @date: 2015年10月24日 下午4:49:07       
    * @version: 
    *
    * @param orderId
    * @param orderType
    * @return
    *
    */
    public  List<OrderOptLog> getOrderLogByOrderTypeId(Integer orderId,Integer orderType){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orderId", orderId);
        map.put("orderType", orderType);
        return orderOptLogMapperManual.getOrderLogListByIdType(map);
    }
    /**
     * 
     * @Title: insertSelective
     * @Description: TODO    ：    插入单条日志
     * @author: ivan    
     * @date: 2015年10月24日 下午4:18:22       
     * @version: 
     *
     * @param record
     * @return
     *
     */
    @Transactional(readOnly = false)
    public int insertSelective(OrderOptLog record){
        return orderOptLogMapper.insertSelective(record);
    }
    @Transactional(readOnly = false)
    public int insertOrderOptLog(List<OrderBO> orderList,String userNum,UserInfoBO userInfoBO){
        int count = 0;
        List<Integer> orderIdList = new ArrayList<Integer>();
        List<Integer> subOrderIdList = new ArrayList<Integer>();
        List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
        List<OrderSubInfo> orderSubInfoList = new ArrayList<OrderSubInfo>();
        Date curDate = new Date();
        for (OrderBO orderBO : orderList) {
            if (orderBO.getOrderType() == Constants.OrderType.MAIN.v()) {
                orderIdList.add(orderBO.getOrderId());
            } else if (orderBO.getOrderType() == Constants.OrderType.SUB.v()) {
                subOrderIdList.add(orderBO.getOrderId());
            }
        }
        if (orderIdList.size()>0) {
            orderInfoList = orderInfoMapperManual.selectOrderListByIds(orderIdList);
        }
        if (subOrderIdList.size()>0) {
            orderSubInfoList = orderSubInfoMapperManual.selectSubOrdersBySubIds(subOrderIdList);
        }
        List <OrderOptLog> recordList = new ArrayList<OrderOptLog>();
        for (OrderInfo orderInfo :orderInfoList) {
            OrderOptLog orderOptLog = new OrderOptLog();
            orderOptLog.setOrderId(orderInfo.getId());
            orderOptLog.setOrderNo(orderInfo.getOrderNo());
            orderOptLog.setOrderStatus(orderInfo.getOrderStatus());
            orderOptLog.setOptName(userInfoBO==null?"":(userInfoBO.getSysUserRealName()));
            orderOptLog.setOptNum(userNum);
            orderOptLog.setOrderType(Constants.OrderType.MAIN.v());
            orderOptLog.setOptTime(curDate);
            recordList.add(orderOptLog);
        }
        for (OrderSubInfo orderSubInfo : orderSubInfoList){
            OrderOptLog orderOptLog = new OrderOptLog();
            orderOptLog.setOrderId(orderSubInfo.getId());
            orderOptLog.setOrderNo(orderSubInfo.getOrderNo());
            orderOptLog.setOrderStatus(orderSubInfo.getOrderStatus());
            orderOptLog.setOptName(userInfoBO==null?"":(userInfoBO.getSysUserRealName()));
            orderOptLog.setOptNum(userNum);
            orderOptLog.setOrderType(Constants.OrderType.SUB.v());
            orderOptLog.setOptTime(curDate);
            recordList.add(orderOptLog);
        }
        for (OrderOptLog orderOptLog : recordList) {
             count = count + orderOptLogMapper.insertSelective(orderOptLog);
        }
        return count;
    }
}
