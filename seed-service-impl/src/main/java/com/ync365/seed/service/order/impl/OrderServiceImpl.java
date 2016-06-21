package com.ync365.seed.service.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ync365.seed.bussiness.modules.goods.biz.PayBiz;
import com.ync365.seed.bussiness.modules.goods.biz.StocksBiz;
import com.ync365.seed.bussiness.modules.order.biz.CartBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderContactBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderCouponBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderEngineerBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderExpenseBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderGoodsBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderGoodsExpenseBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderMergeBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderNoGenBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderOptLogBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderPayBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderShippingInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderStoreBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderSubInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.UserCouponBiz;
import com.ync365.seed.bussiness.modules.order.bo.OrderBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderManagerSearchBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderPayBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderSearchBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderTypeNum;
import com.ync365.seed.bussiness.modules.order.entity.OrderContact;
import com.ync365.seed.bussiness.modules.order.entity.OrderCoupon;
import com.ync365.seed.bussiness.modules.order.entity.OrderEngineer;
import com.ync365.seed.bussiness.modules.order.entity.OrderExpense;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoods;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpenseManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderListInfoManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderMergeDetail;
import com.ync365.seed.bussiness.modules.order.entity.OrderMergeInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderOptLog;
import com.ync365.seed.bussiness.modules.order.entity.OrderShippingInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderStore;
import com.ync365.seed.bussiness.modules.order.entity.OrderSubInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderSubManual;
import com.ync365.seed.bussiness.modules.user.biz.SysPopBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserYnbBiz;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.entity.SysPop;
import com.ync365.seed.bussiness.modules.user.entity.SysUserYnb;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.order.input.OrderManagerSearchDTO;
import com.ync365.seed.dto.order.input.OrderSearchDTO;
import com.ync365.seed.dto.order.input.PreparedPayInputDTO;
import com.ync365.seed.dto.order.output.ManageOrderDTO;
import com.ync365.seed.dto.order.output.ManageOrderPageDTO;
import com.ync365.seed.dto.order.output.OrderDTO;
import com.ync365.seed.dto.order.output.OrderDetailDTO;
import com.ync365.seed.dto.order.output.OrderDetailPageDTO;
import com.ync365.seed.dto.order.output.OrderGoodsDTO;
import com.ync365.seed.dto.order.output.OrderPageDTO;
import com.ync365.seed.dto.order.output.OrderPayDetailDTO;
import com.ync365.seed.dto.order.output.OrderSplitPageDTO;
import com.ync365.seed.dto.order.output.OrderTrackDTO;
import com.ync365.seed.dto.order.output.PreparedPayOrderDTO;
import com.ync365.seed.dto.order.output.PreparedPayOutputDTO;
import com.ync365.seed.dto.order.output.SubOrderDTO;
import com.ync365.seed.dto.order.output.UserCouponDTO;
import com.ync365.seed.service.annotation.FormTokenAnnotation;
import com.ync365.seed.service.order.OrderService;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;

/**
 * 
 * 
 * @Title：OrderServiceImpl
 * @Description: TODO C网订单服务实现
 * @author: sunyf
 * @date: 2015年10月8日 下午5:59:34
 * @version
 *
 */
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderBiz orderBiz;

    @Autowired
    private OrderOptLogBiz orderOptLogBiz;

    @Autowired
    private OrderGoodsBiz orderGoodsBiz;

    @Autowired
    private OrderInfoBiz orderInfoBiz;

    @Autowired
    private SysUserInfoBiz sysUserInfoBiz;

    @Autowired
    private OrderEngineerBiz orderEngineerBiz;

    @Autowired
    private StocksBiz stocksBiz;

    @Autowired
    private OrderMergeBiz orderMergeBiz;

    @Autowired
    private OrderShippingInfoBiz orderShippingInfoBiz;

    @Autowired
    private OrderNoGenBiz orderNoGenBiz;

    @Autowired
    private OrderSubInfoBiz orderSubInfoBiz;

    @Autowired
    private SysPopStoreBiz sysPopStoreBiz;

    @Autowired
    private OrderExpenseBiz orderExpenseBiz;

    @Autowired
    private OrderGoodsExpenseBiz orderGoodsExpenseBiz;

    @Autowired
    private OrderContactBiz orderContactBiz;

    @Autowired
    private OrderStoreBiz orderStoreBiz;

    @Autowired
    private CartBiz cartBiz;

    @Autowired
    private OrderCouponBiz orderCouponBiz;

    @Autowired
    private SysPopBiz sysPopBiz;

    @Autowired
    private SysUserYnbBiz sysUserYnbBiz;

    @Autowired
    private OrderPayBiz orderPayBiz;

    @Autowired
    private PayBiz payBiz;

    @Autowired
    private UserCouponBiz userCouponBiz;

    @Override
    public ResponseDTO getOrderList(String userNum, OrderSearchDTO orderSearchDTO) {
        logger.error("订单，用户订单列表，参数，getOrderList,userNum，{}，orderSearchDTO，{}", userNum, orderSearchDTO.toString());
        ResponseDTO dto = new ResponseDTO();
        OrderSearchBO orderSearchBO = new OrderSearchBO();
        OrderPageDTO orderPageDTO = new OrderPageDTO();
        List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
        List<Integer> orderIdList = new ArrayList<Integer>();
        List<OrderManual> resultList = new ArrayList<OrderManual>();
        CloneUtils.cloneObject(orderSearchDTO, orderSearchBO);
        orderSearchBO.setUser_num(userNum);
        //判断是否为查询操作
        if (!StringUtils.isBlank(orderSearchDTO.getSearchInfo())) {
            orderIdList = orderBiz.selectSearchOrder(orderSearchBO);//查询
            //用于处理分页
            orderSearchBO.setOrderIdList(orderIdList);
            resultList = orderBiz.selectSearchOrderList(orderSearchBO);
        } else {
            resultList = orderBiz.selectOrder(orderSearchBO);
        }
        Long count = orderBiz.countSelectList(orderSearchBO);
        if (count < orderSearchDTO.getStartIndex()) {
            dto.setCode(ResponseCode.OrderCode.ORDER_ERROR_REQUEST.v());
            dto.setMsg(ResponseCode.OrderCode.ORDER_ERROR_REQUEST.c());
            return dto;
        }
        toOrderDTO(resultList, orderDTOList);
        orderPageDTO.setOrderList(orderDTOList);
        orderPageDTO.setTotalNum(count);
        dto.setData(orderPageDTO);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
    public ResponseDTO getOrderDetail(String userNum, String orderId, Integer orderType) {
        logger.error("订单，订单详情，参数，getOrderDetail,orderNo，{}，orderId,{},orderType，{}", userNum, orderId, orderType);
        ResponseDTO dto = new ResponseDTO();
        OrderDetailPageDTO orderDetailPageDTO = new OrderDetailPageDTO();
        // 获取订单记录
        List<OrderOptLog> orderOptLogList = new ArrayList<OrderOptLog>();
        List<OrderTrackDTO> trackList = new ArrayList<OrderTrackDTO>();
        OrderShippingInfo orderShippingInfo = orderShippingInfoBiz.selectByOrderId(Integer.parseInt(orderId));
        orderOptLogList = orderOptLogBiz.getOrderLogByOrderTypeId(Integer.parseInt(orderId), orderType);
        trackList = toTrackList(orderOptLogList, trackList);
        orderDetailPageDTO.setTrackList(trackList);
        // 获取商品列表
        List<OrderGoodsDTO> goodsList = new ArrayList<OrderGoodsDTO>();
        List<OrderGoods> reGoodsList = new ArrayList<OrderGoods>();
        if (orderType.equals(Integer.parseInt(Constants.OrderNoType.Master.v()))) {
            reGoodsList = orderGoodsBiz.getOrderGoodsListByOrderId(Integer.parseInt(orderId));
            goodsList = toGoodsList(reGoodsList, goodsList);

        } else if (orderType.equals(Integer.parseInt(Constants.OrderNoType.Sub.v()))) {
            reGoodsList = orderGoodsBiz.getSubGoodsBySubOrderId(Integer.parseInt(orderId));
            goodsList = toGoodsList(reGoodsList, goodsList);
        }
        orderDetailPageDTO.setGoodsList(goodsList);
        // 获取订单详情
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        OrderInfo orderInfo = new OrderInfo();
        OrderSubInfo orderSubInfo = new OrderSubInfo();
        UserInfoBO urserInfoBO = new UserInfoBO();
        OrderEngineer orderEngineer = new OrderEngineer();
        for (OrderOptLog orderOptLog : orderOptLogList) {
            if (orderOptLog.getOrderStatus().equals(Constants.OrderStatus.NotPay.v().intValue())) {
                orderDetailPageDTO.setCreateTime(orderOptLog.getOptTime().getTime());
            }
            if (orderOptLog.getOrderStatus().equals(Constants.OrderStatus.Paid.v().intValue())) {
                orderDetailPageDTO.setPayTime(orderOptLog.getOptTime().getTime());
            }
            if (orderOptLog.getOrderStatus().equals(Constants.OrderStatus.Completed.v().intValue())) {
                orderDetailPageDTO.setAffirmTime(orderOptLog.getOptTime().getTime());
            }
            if (orderOptLog.getOrderStatus().equals(Constants.OrderStatus.Completed.v().intValue())) {
                orderDetailPageDTO.setCompleteTime(orderOptLog.getOptTime().getTime());
            }
        }
        if (Constants.OrderType.SUB.v().equals(orderType)) {
            orderSubInfo = orderSubInfoBiz.selectByPrimaryKey(Integer.parseInt(orderId));
            orderInfo.setCreateTime(orderSubInfo.getCreateTime());
            orderInfo.setOrderNo(orderSubInfo.getOrderSubNo());
            orderInfo.setActualAmount(orderSubInfo.getActualAmount());
            orderInfo.setPayType(orderSubInfo.getPayType());
            orderInfo.setPayWay(orderSubInfo.getPayWay());
            orderInfo.setOrderStatus(orderSubInfo.getOrderStatus());
            orderInfo.setTotalAmount(orderSubInfo.getTotalAmount());
            orderInfo.setSaveAmount(orderSubInfo.getSaveAmount());
            orderInfo.setUserNum(orderSubInfo.getUserNum());
            orderInfo.setOrderStatus(orderSubInfo.getOrderStatus());
        } else if (Constants.OrderType.MAIN.v().equals(orderType)) {
            orderInfo = orderInfoBiz.selectByPrimaryKey(Integer.parseInt(orderId));
        }
        urserInfoBO = sysUserInfoBiz.selectUserInfoByUserNum(orderInfo.getUserNum());
        orderEngineer = orderEngineerBiz.selectByOrderId(Integer.parseInt(orderId));
        orderDetailDTO = toOrderDetailDTO(orderInfo, urserInfoBO, orderDetailDTO, orderEngineer, orderShippingInfo);
        orderDetailPageDTO.setOrderDetail(orderDetailDTO);
        orderDetailPageDTO.setOrderId(Integer.parseInt(orderId));
        orderDetailPageDTO.setOrderType(orderType);
        // 支付详情
        OrderPayDetailDTO orderPayDetailDTO = new OrderPayDetailDTO();
        orderPayDetailDTO.setBounsFee(orderInfo.getCouponAmount());
        orderPayDetailDTO.setPayFee(orderInfo.getActualAmount());
        orderPayDetailDTO.setSaveFee(orderInfo.getSaveAmount());
        orderDetailPageDTO.setOrderPayDetail(orderPayDetailDTO);
        orderDetailPageDTO.setOrderFee(orderInfo.getTotalAmount());
        dto.setData(orderDetailPageDTO);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
    public ResponseDTO cancelOrder(String userNum, OrderDTO orderDTO) {
        logger.error("订单，取消订单，cancelOrder,参数，userNum，{}，orderDTO:，{}", userNum, orderDTO.toString());
        ResponseDTO dto = new ResponseDTO();
        OrderBO orderBO = new OrderBO();
        OrderInfo orderInfo = new OrderInfo();
        List<OrderBO> orderBOList = new ArrayList<OrderBO>();
        List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
        List<OrderGoods> goodsList = new ArrayList<OrderGoods>();
        List<String> orderNoList = new ArrayList<String>();
        UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(userNum);
        CloneUtils.cloneObject(orderDTO, orderBO);
        orderBOList.add(orderBO);
        orderInfo = orderInfoBiz.selectByPrimaryKey(orderBO.getOrderId());
        orderNoList = orderBiz.getOrderNoByMainOrderId(orderDTO.getOrderId());
        goodsList = orderGoodsBiz.getOrderGoodsListByOrderId(orderBO.getOrderId());
        for (OrderGoods orderGood : goodsList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("skuId", orderGood.getSkuId());
            map.put("orderId", orderBO.getOrderId());
            map.put("num", orderGood.getGoodsCount());
            orderList.add(map);
        }
        if (orderInfo.getOrderStatus().equals(Constants.OrderStatus.NotPay.v().intValue())
                || orderInfo.getOrderStatus().equals(Constants.OrderStatus.ChangePriceNotCheck.v().intValue())
                || orderInfo.getOrderStatus().equals(Constants.OrderStatus.ChangePriceChecked.v().intValue())) {
            for (String orderNo : orderNoList) {
                payBiz.cancelOrderSynchronousYnb(orderNo, "用户取消");
            }
            userCouponBiz.UnlockCouponByOrderNo(orderNoList);
            orderBiz.updateOrderStatusByOrderId(orderBO.getOrderId(), orderBO.getOrderType(),
                    orderInfo.getOrderStatus(), Constants.OrderStatus.Cancled.v().intValue());
            orderOptLogBiz.insertOrderOptLog(orderBOList, userNum, userInfoBO);
            stocksBiz.lockQtyReduction(orderList);
        }
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
    public ResponseDTO getSplitOrderDetail(String userNum, String orderId) {
        logger.error("订单，拆单详情，参数，getSplitOrderDetail,userNum，{}，orderId，{}", userNum, orderId);
        ResponseDTO dto = new ResponseDTO();
        OrderSplitPageDTO split = new OrderSplitPageDTO();
        // 获取商品列表
        List<OrderGoodsDTO> goodsList = new ArrayList<OrderGoodsDTO>();
        List<OrderGoods> reGoodsList = new ArrayList<OrderGoods>();
        OrderShippingInfo orderShippingInfo = orderShippingInfoBiz.selectByOrderId(Integer.parseInt(orderId));
        reGoodsList = orderGoodsBiz.getOrderGoodsListByOrderId(Integer.parseInt(orderId));
        goodsList = toGoodsList(reGoodsList, goodsList);
        split.setGoodsList(goodsList);
        // 获取订单详情
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        OrderInfo orderInfo = new OrderInfo();
        UserInfoBO urserInfoBO = new UserInfoBO();
        OrderEngineer orderEngineer = new OrderEngineer();
        orderInfo = orderInfoBiz.selectByPrimaryKey(Integer.parseInt(orderId));
        urserInfoBO = sysUserInfoBiz.selectUserInfoByUserNum(userNum);
        orderEngineer = orderEngineerBiz.selectByOrderId(Integer.parseInt(orderId));
        orderDetailDTO = toOrderDetailDTO(orderInfo, urserInfoBO, orderDetailDTO, orderEngineer, orderShippingInfo);
        split.setOrderDetail(orderDetailDTO);
        dto.setData(split);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
    public ResponseDTO changeOrder(String userNum, Map<String, String> condition) {
        logger.error("订单，修改订单，参数，userNum，{}，condition，{}", userNum, condition);
        return null;
    }

    @Override
    public ResponseDTO getManageOrderList(String userNum, OrderManagerSearchDTO orderManagerSearchDTO) {
        logger.error("订单，pop管理订单列表，参数，getManageOrderList，userNum，{}，orderManagerSearchDTO:，{}", userNum,
                orderManagerSearchDTO.toString());
        ResponseDTO dto = new ResponseDTO();
        ManageOrderPageDTO manageOrderPageDTO = new ManageOrderPageDTO();
        UserUsInfoSearchBo userUsInfoSearchBO = new UserUsInfoSearchBo();
        List<ManageOrderDTO> orderDTOList = new ArrayList<ManageOrderDTO>();
        List<UserInfoBO> userListFinal = new ArrayList<UserInfoBO>();
        Long count = 0L;
        Boolean flag = false;
        PopStoreBO popStoreBO = sysPopStoreBiz.selectPopStoreBOByPopNum(userNum);
        List<OrderListInfoManual> orderList = new ArrayList<OrderListInfoManual>();
        OrderManagerSearchBO orderManagerSearchBO = new OrderManagerSearchBO();
        CloneUtils.cloneObject(orderManagerSearchDTO, orderManagerSearchBO);
        if (!StringUtils.isBlank(orderManagerSearchDTO.getVsName())) {
            userUsInfoSearchBO.setVsName(orderManagerSearchDTO.getVsName());
            flag = true;
        }
        if (!StringUtils.isBlank(orderManagerSearchDTO.getVsTel())) {
            userUsInfoSearchBO.setVsName(orderManagerSearchDTO.getVsName());
            flag = true;
        }
        if (!StringUtils.isBlank(orderManagerSearchDTO.getUserTel())) {
            userUsInfoSearchBO.setUserMobile(orderManagerSearchDTO.getUserTel());
            flag = true;
        }
        if (flag) {
            userListFinal = sysUserInfoBiz.selectUserInfoByParam(userUsInfoSearchBO);
        }
        orderManagerSearchBO.setUserList(userListFinal);
        orderManagerSearchBO.setStoreId(popStoreBO.getId());
        count = orderBiz.countManagerListByPageInfo(orderManagerSearchBO);
        if (count < orderManagerSearchDTO.getStartIndex()) {
            dto.setCode(ResponseCode.OrderCode.ORDER_ERROR_REQUEST.v());
            dto.setMsg(ResponseCode.OrderCode.ORDER_ERROR_REQUEST.c());
            return dto;
        }
        orderList = orderBiz.selectManagerListByPageInfo(orderManagerSearchBO);
        orderDTOList = toManageOrderDTO(orderList, orderDTOList);
        manageOrderPageDTO.setOrderList(orderDTOList);
        manageOrderPageDTO.setTotalNum(count);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData(manageOrderPageDTO);
        return dto;
    }

    @Override
    public ResponseDTO getSupplierOrderList(String userNum, OrderManagerSearchDTO orderManagerSearchDTO) {
        logger.error("订单，获取供应商列表，参数，getSupplierOrderList，userNum，{}，orderManagerSearchDTO:，{}", userNum,
                orderManagerSearchDTO.toString());
        ResponseDTO dto = new ResponseDTO();
        ManageOrderPageDTO manageOrderPageDTO = new ManageOrderPageDTO();
        UserUsInfoSearchBo userUsInfoSearchBo = new UserUsInfoSearchBo();
        List<ManageOrderDTO> orderDTOList = new ArrayList<ManageOrderDTO>();
        List<UserInfoBO> userList = new ArrayList<UserInfoBO>();
        OrderManagerSearchBO orderManagerSearchBO = new OrderManagerSearchBO();
        List<OrderListInfoManual> orderList = new ArrayList<OrderListInfoManual>();
        if (Constants.UserRole.VS.v() == orderManagerSearchDTO.getUserType()) {
            userUsInfoSearchBo.setVsNum(userNum);
            userList = sysUserInfoBiz.selectUserInfoByParam(userUsInfoSearchBo);
        } else if (Constants.UserRole.LC.v() == orderManagerSearchDTO.getUserType()) {
            userUsInfoSearchBo.setLcNum(userNum);
            userList = sysUserInfoBiz.selectUserInfoByParam(userUsInfoSearchBo);
        }
        CloneUtils.cloneObject(orderManagerSearchDTO, orderManagerSearchBO);
        orderManagerSearchBO.setUserList(userList);
        Long count = 0L;
        orderList = orderBiz.orderAllListByPageInfo(orderManagerSearchBO);
        orderDTOList = toManageOrderDTO(orderList, orderDTOList);
        count = orderBiz.countOrderListInfo(orderManagerSearchBO);
        if (count < orderManagerSearchDTO.getStartIndex()) {
            dto.setCode(ResponseCode.OrderCode.ORDER_ERROR_REQUEST.v());
            dto.setMsg(ResponseCode.OrderCode.ORDER_ERROR_REQUEST.c());
            return dto;
        }
        manageOrderPageDTO.setOrderList(orderDTOList);
        manageOrderPageDTO.setTotalNum(count);
        dto.setData(manageOrderPageDTO);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
   @FormTokenAnnotation
    public ResponseDTO mergePay(String userNum, Integer mergeType, List<String> orderIdList) {
        logger.error("订单，合并订单支付页面，参数，mergePay，userNum，{}，condition，{}", userNum, orderIdList);
        ResponseDTO dto = new ResponseDTO();
        ManageOrderPageDTO manageOrderPageDTO = new ManageOrderPageDTO();
        List<ManageOrderDTO> orderDTOList = new ArrayList<ManageOrderDTO>();
        List<OrderListInfoManual> orderList = new ArrayList<OrderListInfoManual>();
        String orderNoChannel;
        BigDecimal totalPrice = new BigDecimal("0");
        Long temp = 0l;
        Long count = 0L;
        Map<String, Object> map = new HashMap<String, Object>();
        OrderMergeInfo orderMergeInfo = new OrderMergeInfo();
        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO = sysUserInfoBiz.selectUserInfoByUserNum(userNum);
        map.put("orderIdList", orderIdList);
        orderList = orderBiz.orderListByOrderID(map);
        for (OrderListInfoManual orderInfo : orderList) {
            totalPrice = totalPrice.add(orderInfo.getPayFee());
            temp = temp + Long.parseLong(orderInfo.getOrderNo().substring(1, 3));
        }
        if (temp == orderList.size()) {
            orderNoChannel = Constants.OrderNoChannelType.Self.v();
        } else {
            orderNoChannel = Constants.OrderNoChannelType.Mix.v();
        }
        String mergeNo = orderNoGenBiz.genOrderNo(String.valueOf(mergeType), orderNoChannel, null,
                String.valueOf(orderList.size()));
        orderDTOList = toManageOrderDTO(orderList, orderDTOList);
        count = orderBiz.countOrderListByOrderID(map);
        orderMergeInfo = orderMergeBiz.createMergeOrder(orderList, userInfoBO, mergeType, mergeNo);
        manageOrderPageDTO.setOrderList(orderDTOList);
        manageOrderPageDTO.setTotalNum(count);
        manageOrderPageDTO.setOrderPrice(orderMergeInfo.getGoodsAmount());
        manageOrderPageDTO.setMergeOrderNo(orderMergeInfo.getOrderMergeNo());
        manageOrderPageDTO.setMergeOrderId(orderMergeInfo.getId());
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData(manageOrderPageDTO);
        return dto;
    }

    @Override
    public ResponseDTO buyOrderAgain(String userNum, Map<String, String> condition) {
        logger.error("订单，再次购买，参数，userNum，buyOrderAgain，{},condition,{}", userNum, condition);
        ResponseDTO dto = new ResponseDTO();
        Integer orderType = Integer.parseInt(condition.get("orderType"));
        Integer orderId = Integer.parseInt(condition.get("orderId"));
        List<OrderGoods> orderGoodsList = new ArrayList<OrderGoods>();
        try {
            logger.error("购物车，添加商品，开始，参数，userNum，{}，cityId，{}，storeId，{}，skuId，{}，num，{}", userNum, orderGoodsList);
            Integer count = cartBiz.countCart(userNum);
            if (count >= CartBiz.getCartCount()) {
                dto.setCode(ResponseCode.CartCode.CART_IS_FULL.v());
                dto.setMsg(ResponseCode.CartCode.CART_IS_FULL.c());
                logger.error("购物车，添加商品，满了，参数，userNum，{}，cityId，{}，storeId，{}，skuId，{}，num，{}", userNum, orderGoodsList);
            } else {
                if (orderType.equals(Constants.OrderType.MAIN.v())) {
                    orderGoodsList = orderGoodsBiz.getOrderGoodsListByOrderId(orderId);
                } else if (orderType.equals(Constants.OrderType.SUB.v())) {
                    orderGoodsList = orderGoodsBiz.getSubGoodsBySubOrderId(orderId);
                }
                for (OrderGoods orderGoods : orderGoodsList) {

                    cartBiz.addCart(userNum, orderGoods.getStoreId(), orderGoods.getSkuId(),
                            orderGoods.getGoodsCount());
                }
            }
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } catch (Exception e) {
            dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("购物车，添加商品，异常，参数，userNum，{}，cityId，{}，storeId，{}，skuId，{}，num，{}，异常信息，{}", orderGoodsList,
                    e.getMessage());
            e.getMessage();
        }
        return dto;
    }

    @Override
    public ResponseDTO getRecycleList(String userNum, OrderSearchDTO orderSearchDTO) {
        logger.error("订单，订单回收站，参数，getRecycleList，userNum，{}，orderSearchDTO，{}", userNum, orderSearchDTO.toString());
        ResponseDTO dto = new ResponseDTO();
        OrderSearchBO orderSearchBO = new OrderSearchBO();
        OrderPageDTO orderPageDTO = new OrderPageDTO();
        List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
        CloneUtils.cloneObject(orderSearchDTO, orderSearchBO);
        List<OrderManual> resultList = orderBiz.selectRecOrder(orderSearchBO);
        Long count = orderBiz.countRecSelectList(orderSearchBO);
        if (count < orderSearchDTO.getStartIndex()) {
            dto.setCode(ResponseCode.OrderCode.ORDER_ERROR_REQUEST.v());
            dto.setMsg(ResponseCode.OrderCode.ORDER_ERROR_REQUEST.c());
            return dto;
        }
        orderDTOList = toAllOrderDTO(resultList, orderDTOList);
        orderPageDTO.setOrderList(orderDTOList);
        orderPageDTO.setTotalNum(count);
        dto.setData(orderPageDTO);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
    public ResponseDTO affirmOrder(String userNum, OrderDTO orderDTO) {
        logger.error("订单，确认收货，参数，affirmOrder，userNum，{},condition,{}", userNum, orderDTO.toString());
        ResponseDTO dto = new ResponseDTO();
        List<OrderBO> orderBOList = new ArrayList<OrderBO>();
        OrderInfo orderInfo = new OrderInfo();
        UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(userNum);
        OrderExpense orderExpense = new OrderExpense();
        OrderBO orderBO = new OrderBO();
        CloneUtils.cloneObject(orderDTO, orderBO);
        orderBOList.add(orderBO);
        if (orderDTO.getOrderType().equals(Constants.OrderType.MAIN.v())) {
            orderInfo = orderInfoBiz.selectByPrimaryKey(orderDTO.getOrderId());
            if (orderInfo.getOrderStatus().equals(Constants.OrderStatus.Delivery.v().intValue())) {
                orderInfo.setSigninTime(new Date());
                orderInfoBiz.updateByPrimaryKeySelective(orderInfo);
                orderBiz.updateOrderStatusByOrderId(orderDTO.getOrderId(), orderDTO.getOrderType(),
                        Constants.OrderStatus.Delivery.v().intValue(), Constants.OrderStatus.Completed.v().intValue());
                orderExpense = orderExpenseBiz.selectOrderExpenseByOrderId(orderDTO.getOrderId(), orderDTO.getOrderType());
                orderOptLogBiz.insertOrderOptLog(orderBOList, userNum, userInfoBO);
            }
        } else if (orderDTO.getOrderType().equals(Constants.OrderType.SUB.v())) {
            OrderSubInfo orderSubInfo = orderSubInfoBiz.selectByPrimaryKey(orderDTO.getOrderId());
            if (orderSubInfo.getOrderStatus().equals(Constants.OrderStatus.Delivery.v().intValue())) {
                orderSubInfo.setSigninTime(new Date());
                orderSubInfoBiz.updateByPrimaryKeySelective(orderSubInfo);
                orderBiz.updateOrderStatusByOrderId(orderDTO.getOrderId(), orderDTO.getOrderType(),
                        Constants.OrderStatus.Delivery.v().intValue(), Constants.OrderStatus.Completed.v().intValue());
                orderExpense = orderExpenseBiz.selectOrderExpenseByOrderId(orderDTO.getOrderId(), orderDTO.getOrderType());
                orderOptLogBiz.insertOrderOptLog(orderBOList, userNum, userInfoBO);
            }
        }
        dto.setData(orderExpense);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
    public ResponseDTO delRecycleList(String userNum, List<OrderDTO> orderList) {
        logger.error("订单，订单回收站删除，参数，delRecycleList，userNum，{}，orderList，{}", userNum, orderList);
        ResponseDTO dto = new ResponseDTO();
        List<OrderBO> orderBOList = new ArrayList<OrderBO>();
        UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(userNum);
        for (OrderDTO order : orderList) {
            OrderBO orderBO = new OrderBO();
            CloneUtils.cloneObject(order, orderBO);
            orderBOList.add(orderBO);
        }
        orderBiz.delRecycle(orderBOList);
        orderOptLogBiz.insertOrderOptLog(orderBOList, userNum, userInfoBO);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
    public ResponseDTO restoreRecycleList(String userNum, List<OrderDTO> orderList) {
        logger.error("订单，订单回收站还原，参数，restoreRecycleList，userNum，{}，orderList，{}", userNum, orderList);
        List<OrderBO> orderBOList = new ArrayList<OrderBO>();
        UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(userNum);
        ResponseDTO dto = new ResponseDTO();
        for (OrderDTO order : orderList) {
            OrderBO orderBO = new OrderBO();
            CloneUtils.cloneObject(order, orderBO);
            orderBOList.add(orderBO);
        }
        orderBiz.resRecycle(orderBOList);
        orderOptLogBiz.insertOrderOptLog(orderBOList, userNum, userInfoBO);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
    public ResponseDTO affirmDelivery(String userNum, OrderDTO orderDTO) {
        logger.error("订单，确认发货，参数，affirmDelivery，userNum，{}，condition，{}", userNum, orderDTO.toString());
        ResponseDTO dto = new ResponseDTO();
        List<OrderBO> orderBOList = new ArrayList<OrderBO>();
        UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(userNum);
        OrderBO orderBO = new OrderBO();
        CloneUtils.cloneObject(orderDTO, orderBO);
        orderBOList.add(orderBO);
        orderBiz.updateOrderStatusByOrderId(orderDTO.getOrderId(), orderDTO.getOrderType(), orderDTO.getStatus(),
                Constants.OrderStatus.Delivery.v().intValue());
        orderOptLogBiz.insertOrderOptLog(orderBOList, userNum, userInfoBO);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
    public ResponseDTO delOrder(String userNum, OrderDTO orderDTO) {
        logger.error("订单，订单删除，参数，delOrder，userNum，{}，condition，{}", userNum, orderDTO);
        if (checkOrderInfo(userNum, orderDTO) != null) {
            return checkOrderInfo(userNum, orderDTO);
        }
        ResponseDTO dto = new ResponseDTO();

        Map<String, Object> map = new HashMap<String, Object>();
        UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(userNum);
        map.put("subId", orderDTO.getOrderId());
        map.put("idDel", Constants.Whether.Yes.v());
        List<OrderBO> orderBOList = new ArrayList<OrderBO>();
        OrderBO orderBO = new OrderBO();
        OrderBO orderSubBO = new OrderBO();
        CloneUtils.cloneObject(orderDTO, orderBO);
        orderBOList.add(orderBO);
        orderBiz.delOrder(orderBO);
        //当删除的是子订单是判断是否全部兄弟子订单删除，若删除则将主订单删除
        if (orderDTO.getOrderType() == Constants.OrderType.SUB.v()) {
            orderSubBO = orderBiz.delOrderAfterDelSub(map);
            if (orderSubBO.getOrderId() != null) {
                orderBOList.add(orderSubBO);
            }
        }
        orderOptLogBiz.insertOrderOptLog(orderBOList, userNum, userInfoBO);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
    public ResponseDTO orderTypeNum(String userNum, Map<String, String> map) {
        logger.error("订单，不同状态订单数量，参数，orderTypeNum，userNum，{}，condition，{}", userNum, map.get("userType"));
        ResponseDTO dto = new ResponseDTO();
        OrderTypeNum orderTypeNum = new OrderTypeNum();
        UserUsInfoSearchBo userUsInfoSearchBO = new UserUsInfoSearchBo();
        List<UserInfoBO> userList = new ArrayList<UserInfoBO>();
        if (Constants.UserRole.VS.v() == Integer.parseInt(map.get("userType"))) {
            userUsInfoSearchBO.setVsNum(userNum);
            userList = sysUserInfoBiz.selectUserInfoByParam(userUsInfoSearchBO);
        } else if (Constants.UserRole.LC.v() == Integer.parseInt(map.get("userType"))) {
            userUsInfoSearchBO.setLcNum(userNum);
            userList = sysUserInfoBiz.selectUserInfoByParam(userUsInfoSearchBO);
        } else {
            userUsInfoSearchBO.setUserNum(userNum);
            userList = sysUserInfoBiz.selectUserInfoByParam(userUsInfoSearchBO);
        }
        orderTypeNum = orderBiz.orderTypeNum(userList);
        dto.setData(orderTypeNum);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    private ResponseDTO checkOrderInfo(String userNum, OrderDTO orderDTO) {
        ResponseDTO response = null;
        if (orderDTO.getOrderId() == null || orderDTO.getOrderId().equals(null)) {
            response = new ResponseDTO();
            response.setCode(ResponseCode.CommonCode.OBJECT_NOT_EXISTS.v());
            response.setMsg(ResponseCode.CommonCode.OBJECT_NOT_EXISTS.c());
        }
        return response;
    }

    /////////////////////////////////////// 类型转换方法/////////////////////////////////////
    private List<ManageOrderDTO> toManageOrderDTO(List<OrderListInfoManual> in, List<ManageOrderDTO> out) {
        for (OrderListInfoManual orderListInfoManual : in) {
            ManageOrderDTO manageOrderDTO = new ManageOrderDTO();
            manageOrderDTO.setCreateTime(orderListInfoManual.getCreateTime());
            manageOrderDTO.setDealFee(orderListInfoManual.getDealFee());
            manageOrderDTO.setDealTime(orderListInfoManual.getDealTime());
            manageOrderDTO.setOrderNo(orderListInfoManual.getOrderNo());
            manageOrderDTO.setOrderType(orderListInfoManual.getOrderType());
            manageOrderDTO.setPayFee(orderListInfoManual.getPayFee());
            manageOrderDTO.setSource(orderListInfoManual.getSource());
            manageOrderDTO.setStatus(orderListInfoManual.getStatus());
            manageOrderDTO.setUserName(orderListInfoManual.getUserName());
            manageOrderDTO.setUserTel(orderListInfoManual.getUserTel());
            manageOrderDTO.setUserNum(orderListInfoManual.getUserNum());
            manageOrderDTO.setOrderId(orderListInfoManual.getOrderId());
            out.add(manageOrderDTO);
        }
        return out;
    }

    private List<OrderDTO> toAllOrderDTO(List<OrderManual> inList, List<OrderDTO> outList) {
        for (OrderManual order : inList) {
            OrderDTO orderDTO = new OrderDTO();
            CloneUtils.cloneObject(order, orderDTO);
            List<OrderGoodsDTO> goodsList = new ArrayList<OrderGoodsDTO>();// 子订单商品
            for (OrderGoods orderGoods : order.getGoodsList()) {// 子订单商品
                OrderGoodsDTO orderGoodsDTO = new OrderGoodsDTO();
                CloneUtils.cloneObject(orderGoods, orderGoodsDTO);
                goodsList.add(orderGoodsDTO);
            }
            orderDTO.setGoodsList(goodsList);
            outList.add(orderDTO);
        }
        return outList;
    }

    private List<OrderDTO> toOrderDTO(List<OrderManual> inList, List<OrderDTO> outList) {
        for (OrderManual order : inList) {
            OrderDTO orderDTO = new OrderDTO();
            CloneUtils.cloneObject(order, orderDTO);
            orderDTO.setOrderType(order.getHasSub());
            if (order.getHasSub() == 1) {// 订单包含子订单
                List<SubOrderDTO> subList = new ArrayList<SubOrderDTO>();// 子订单列表
                for (OrderSubManual orderSubInfo : order.getSubList()) {
                    SubOrderDTO subOrderDTO = new SubOrderDTO();
                    List<OrderGoodsDTO> goodsList = new ArrayList<OrderGoodsDTO>();// 子订单商品
                    for (OrderGoods orderGoods : orderSubInfo.getGoodsList()) {// 子订单商品
                        OrderGoodsDTO orderGoodsDTO = new OrderGoodsDTO();
                        CloneUtils.cloneObject(orderGoods, orderGoodsDTO);
                        orderGoodsDTO.setGoodsPriceSum(orderGoods.getTotalAmount());
                        goodsList.add(orderGoodsDTO);
                    }
                    CloneUtils.cloneObject(orderSubInfo, subOrderDTO);
                    subOrderDTO.setOrderNo(orderSubInfo.getOrderSubNo());
                    subOrderDTO.setGoodsList(goodsList);
                    subOrderDTO.setOrderId(orderSubInfo.getId());
                    subList.add(subOrderDTO);
                }
                orderDTO.setSubList(subList);
                orderDTO.setGoodsList(null);
            } else {// 订单不包含子订单
                List<OrderGoodsDTO> goodsList = new ArrayList<OrderGoodsDTO>();// 子订单商品
                for (OrderGoods orderGoods : order.getGoodsList()) {// 子订单商品
                    OrderGoodsDTO orderGoodsDTO = new OrderGoodsDTO();
                    CloneUtils.cloneObject(orderGoods, orderGoodsDTO);
                    goodsList.add(orderGoodsDTO);
                }
                orderDTO.setGoodsList(goodsList);
                orderDTO.setSubList(null);
            }
            outList.add(orderDTO);
        }
        return outList;
    }

    private OrderDetailDTO toOrderDetailDTO(OrderInfo in, UserInfoBO user, OrderDetailDTO out,
            OrderEngineer orderEngineer, OrderShippingInfo orderShippingInfo) {
        if (in != null) {
            out.setCreateTime(in.getCreateTime().getTime());
            out.setOrderNo(in.getOrderNo());
            out.setPayFee(in.getActualAmount());
            out.setPayType(in.getPayType());
            out.setPayWay(in.getPayWay());
            out.setStatus(in.getOrderStatus());
        }
        if (orderShippingInfo != null) {
            out.setDeliveryNo(orderShippingInfo.getDelivelyNo());
        }
        if (user != null) {
            out.setUserAddress(user.getSysUserAddressDetail());
            out.setUserName(user.getSysUserRealName());
            out.setUserTel(user.getUserMobile());
            out.setVsAddress(user.getVsAddressDetail());
            out.setVsName(user.getVsName());
            out.setVsTel(user.getVsMobile());
        }
        if (orderEngineer != null) {
            out.setServerName(orderEngineer.getEngineerName());
            out.setServerTel(orderEngineer.getEngineerPhone());
        }
        if (in.getPayTime() != null) {
            out.setSplitTime(in.getPayTime().getTime() / 1000);
        }
        return out;
    }

    /**
     * 
     * @Title: toGoodsList
     * @Description: TODO ： 订单商品映射值DTO
     * @author: sunyf
     * @date: 2015年10月16日 下午2:53:38
     * @version:
     *
     * @param inList
     * @param outList
     * @return
     *
     */
    private List<OrderGoodsDTO> toGoodsList(List<OrderGoods> inList, List<OrderGoodsDTO> outList) {
        for (OrderGoods orderGoods : inList) {
            OrderGoodsDTO orderGoodsDTO = new OrderGoodsDTO();
            orderGoodsDTO.setGoodsCount(orderGoods.getGoodsCount());
            orderGoodsDTO.setGoodsName(orderGoods.getGoodsName());
            orderGoodsDTO.setGoodsPrice(orderGoods.getGoodsPrice());
            orderGoodsDTO.setGoodsPriceSum(orderGoods.getActualAmount());
            orderGoodsDTO.setGoodsUrl(orderGoods.getGoodsUrl());
            orderGoodsDTO.setSkuId(orderGoods.getSkuId());
            orderGoodsDTO.setStoreId(orderGoods.getStoreId());
            outList.add(orderGoodsDTO);
        }
        return outList;
    }

    /**
     * 
     * @Title: toTrackList
     * @Description: TODO ： 将订单操作信息转换为订单流程信息
     * @author: sunyf
     * @date: 2015年10月16日 下午1:53:31
     * @version:
     *
     * @param inList
     * @param outList
     * @return
     *
     */
    private List<OrderTrackDTO> toTrackList(List<OrderOptLog> inList, List<OrderTrackDTO> outList) {
        for (OrderOptLog orderOptLog : inList) {
            OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
            orderTrackDTO.setStatus(orderOptLog.getOrderStatus());
            orderTrackDTO.setTrackTime(orderOptLog.getOptTime().getTime());
            outList.add(orderTrackDTO);
        }
        return outList;
    }

    /**
     * 
     * @Title: preparedPay
     * @Description:    
     * @author: Ken    
     * @date: 2015年10月22日 下午6:36:35       
     * @version: 
     *
     * @param param
     * @return
     *
     */
    @Override
    public ResponseDTO preparedPay(PreparedPayInputDTO param) {
        ResponseDTO response = null;
        List<PreparedPayOrderDTO> orders = new ArrayList<PreparedPayOrderDTO>();
        try {
            Integer orderId = param.getOrderId();
            Integer orderType = param.getOrderType();
            PreparedPayOutputDTO preparedPayOutputDTO = new PreparedPayOutputDTO();

            if (orderType == Constants.OrderType.MAIN.v()) { // 主订单
                OrderInfo orderInfo = orderInfoBiz.selectByPrimaryKey(orderId);
                response = checkOrderStatus(orderInfo);
                if (response != null) {
                    return response;
                }
                List<PreparedPayOrderDTO> preparedPayOrderDTOList = getPreparedPayOrderDTO(orderInfo);
                orders.addAll(preparedPayOrderDTOList);
            } else { // 合并订单
                List<OrderMergeDetail> orderMergeList = orderMergeBiz.getOrderListByMergeInfoId(orderId);
                if (orderMergeList == null || orderMergeList.size() == 0) {
                    response = new ResponseDTO();
                    response.setCode(ResponseCode.OrderCode.ORDER_NOT_EXISTS.v());
                    response.setMsg(ResponseCode.OrderCode.ORDER_NOT_EXISTS.c());
                    return response;
                }
                for (OrderMergeDetail orderMerge : orderMergeList) {
                    OrderInfo orderInfo = orderInfoBiz.selectByPrimaryKey(orderMerge.getOrderInfoId());
                    response = checkOrderStatus(orderInfo);
                    if (response != null) {
                        return response;
                    }
                    List<PreparedPayOrderDTO> preparedPayOrderDTOList = getPreparedPayOrderDTO(orderInfo);
                    orders.addAll(preparedPayOrderDTOList);
                }
            }
            preparedPayOutputDTO.setOrders(orders);

            String userNum = param.getUserNum();
            // 支付信息
            for (PreparedPayOrderDTO order : orders) {
                OrderPayBO orderPayBO = new OrderPayBO();
                orderPayBO.setOrderNo(order.getOrderNo());
                orderPayBO.setOrderType(orderType);
                orderPayBO.setUserNum(userNum);
                orderPayBiz.setOrderPayInfo(orderPayBO);
            }

            // Response
            response = new ResponseDTO();
            response.setCode(ResponseCode.CommonCode.OK.v());
            response.setMsg(ResponseCode.CommonCode.OK.c());
            response.setData(preparedPayOutputDTO);
        } catch (Exception e) {
            // Response
            response = new ResponseDTO();
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("订单，查询订单支付金额，异常，参数，param，异常信息，{}", param, e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    private ResponseDTO checkOrderStatus(OrderInfo orderInfo) {
        ResponseDTO response = null;
        if (orderInfo == null) {
            response = new ResponseDTO();
            response.setCode(ResponseCode.OrderCode.ORDER_NOT_EXISTS.v());
            response.setMsg(ResponseCode.OrderCode.ORDER_NOT_EXISTS.c());
            return response;
        }
        if (orderInfo.getOrderStatus() != Constants.OrderStatus.NotPay.v().intValue()
                && orderInfo.getOrderStatus() != Constants.OrderStatus.ChangePriceChecked.v().intValue()) {
            response = new ResponseDTO();
            response.setCode(ResponseCode.OrderCode.ORDER_STATUS_WRONG.v());
            response.setMsg(ResponseCode.OrderCode.ORDER_STATUS_WRONG.c());
            return response;
        }
        return response;
    }

    private List<PreparedPayOrderDTO> getPreparedPayOrderDTO(OrderInfo orderInfo) {
        List<PreparedPayOrderDTO> result = new ArrayList<PreparedPayOrderDTO>();
        List<OrderStore> orderStoreList = orderBiz.selectOrderStoreListByOrderId(orderInfo.getId());
        if (orderStoreList.size() > 1) { // 有子订单
            for (OrderStore orderStore : orderStoreList) {
                Map<String, Integer> param = new HashMap<String, Integer>();
                param.put("orderId", orderInfo.getId());
                param.put("storeId", orderStore.getStoreId());
                List<OrderGoodsDTO> orderGoodsDTOList = new ArrayList<OrderGoodsDTO>();
                List<OrderGoods> orderGoodsList = orderGoodsBiz.getGoodsByOrderIdAndStoreId(param);
                for (OrderGoods orderGoods : orderGoodsList) {
                    OrderGoodsDTO orderGoodsDTO = new OrderGoodsDTO();
                    orderGoodsDTO.setSkuId(orderGoods.getSkuId());
                    orderGoodsDTO.setGoodsName(orderGoods.getGoodsName());
                    orderGoodsDTO.setGoodsPrice(orderGoods.getActualPrice());
                    orderGoodsDTO.setGoodsCount(orderGoods.getGoodsCount());
                    orderGoodsDTO.setGoodsPriceSum(orderGoods.getActualAmount());
                    orderGoodsDTO.setStoreId(orderGoods.getStoreId());
                    orderGoodsDTOList.add(orderGoodsDTO);
                }
                String orderSubNo = orderStore.getOrderSubNo();
                PreparedPayOrderDTO preparedPayOrderDTO = new PreparedPayOrderDTO();
                preparedPayOrderDTO.setOrderNo(orderSubNo);
                BigDecimal payFee = new BigDecimal(0);
                if (orderStore.getCouponAmount().compareTo(new BigDecimal(0)) == 1) { // 有红包
                    if (orderStore.getStoreId() != -1) { // 入驻商
                        payFee = orderStore.getActualAmount();
                    } else { // 自营
                        payFee = orderStore.getActualAmount().add(orderStore.getCouponAmount());
                    }
                } else { // 无红包
                    payFee = orderStore.getActualAmount();
                }

                if (orderStore.getStoreId() != -1) {
                    String popStoreNum = sysPopStoreBiz.selectPopStoreById(orderStore.getStoreId()).getPopStoreNum();
                    logger.info("popStoreNum: " + popStoreNum);
                    SysPop sysPop = sysPopBiz.selectByPopStoreNum(popStoreNum);
                    preparedPayOrderDTO.setPopNum(sysPop.getPopNum());
                    SysUserYnb sysUserYnb = sysUserYnbBiz.selectByUserNum(sysPop.getPopNum());
                    logger.info("sysUserYnb: " + sysUserYnb);
                    preparedPayOrderDTO.setYnbMemberId(sysUserYnb.getYnbMemberid());
                } else {
                    List<OrderCoupon> orderCouponList = orderCouponBiz.selectByPrimaryKey(orderSubNo);
                    if (orderCouponList != null && orderCouponList.size() > 0) {
                        List<UserCouponDTO> couponList = new ArrayList<UserCouponDTO>();
                        for (int i = 0; i < orderCouponList.size(); i++) {
                            OrderCoupon orderCoupon = orderCouponList.get(i);
                            UserCouponDTO userCouponDTO = new UserCouponDTO();
                            userCouponDTO.setUserCouponId(orderCoupon.getUserCouponId());
                            BigDecimal couponAmount = payFee;
                            if (i == orderCouponList.size() - 1) {
                                userCouponDTO.setCouponAmount(couponAmount);
                            } else {
                                userCouponDTO.setCouponAmount(orderCoupon.getCouponAmount());
                                couponAmount = couponAmount.subtract(orderCoupon.getCouponAmount());
                            }
                        }
                        preparedPayOrderDTO.setCouponList(couponList);
                    }
                }
                preparedPayOrderDTO.setPayFee(payFee);
                preparedPayOrderDTO.setCreateTime(orderInfo.getCreateTime().getTime());
                preparedPayOrderDTO.setStoreId(orderStore.getStoreId());
                preparedPayOrderDTO.setPayWay(orderInfo.getPayWay());
                preparedPayOrderDTO.setGoodsList(orderGoodsDTOList);

                result.add(preparedPayOrderDTO);
            }
        } else { // 无子订单
            PreparedPayOrderDTO preparedPayOrderDTO = new PreparedPayOrderDTO();
            List<OrderGoodsDTO> orderGoodsDTOList = new ArrayList<OrderGoodsDTO>();
            List<OrderGoods> orderGoodsList = orderGoodsBiz.getOrderGoodsListByOrderId(orderInfo.getId());
            for (OrderGoods orderGoods : orderGoodsList) {
                OrderGoodsDTO orderGoodsDTO = new OrderGoodsDTO();
                orderGoodsDTO.setSkuId(orderGoods.getSkuId());
                orderGoodsDTO.setGoodsName(orderGoods.getGoodsName());
                orderGoodsDTO.setGoodsPrice(orderGoods.getActualPrice());
                orderGoodsDTO.setGoodsCount(orderGoods.getGoodsCount());
                orderGoodsDTO.setGoodsPriceSum(orderGoods.getActualAmount());
                orderGoodsDTO.setStoreId(orderGoods.getStoreId());
                orderGoodsDTOList.add(orderGoodsDTO);
            }
            OrderStore orderStore = orderStoreList.get(0);
            preparedPayOrderDTO.setOrderId(orderInfo.getId());
            preparedPayOrderDTO.setOrderNo(orderInfo.getOrderNo());
            BigDecimal payFee = new BigDecimal(0);
            if (orderInfo.getCouponAmount().compareTo(new BigDecimal(0)) == 1) {
                if (orderStore.getStoreId() != -1) { // 入驻商
                    payFee = orderInfo.getActualAmount();
                } else { // 自营
                    payFee = orderInfo.getActualAmount().add(orderInfo.getCouponAmount());
                }
            } else {
                payFee = orderInfo.getActualAmount();
            }

            if (orderStore.getStoreId() != -1) {
                String popStoreNum = sysPopStoreBiz.selectPopStoreById(orderStoreList.get(0).getStoreId())
                        .getPopStoreNum();
                SysPop sysPop = sysPopBiz.selectByPopStoreNum(popStoreNum);
                preparedPayOrderDTO.setPopNum(sysPop.getPopNum());
                SysUserYnb sysUserYnb = sysUserYnbBiz.selectByUserNum(sysPop.getPopNum());
                logger.info("sysUserYnb: " + sysUserYnb);
                preparedPayOrderDTO.setYnbMemberId(sysUserYnb.getYnbMemberid());
            } else {
                List<OrderCoupon> orderCouponList = orderCouponBiz.selectByPrimaryKey(orderInfo.getOrderNo());
                if (orderCouponList != null && orderCouponList.size() > 0) {
                    List<UserCouponDTO> couponList = new ArrayList<UserCouponDTO>();
                    for (int i = 0; i < orderCouponList.size(); i++) {
                        OrderCoupon orderCoupon = orderCouponList.get(i);
                        UserCouponDTO userCouponDTO = new UserCouponDTO();
                        userCouponDTO.setUserCouponId(orderCoupon.getUserCouponId());
                        BigDecimal couponAmount = payFee;
                        if (i == orderCouponList.size() - 1) {
                            userCouponDTO.setCouponAmount(couponAmount);
                        } else {
                            userCouponDTO.setCouponAmount(orderCoupon.getCouponAmount());
                            couponAmount = couponAmount.subtract(orderCoupon.getCouponAmount());
                        }
                    }
                    preparedPayOrderDTO.setCouponList(couponList);
                }
            }
            preparedPayOrderDTO.setPayFee(payFee);
            preparedPayOrderDTO.setGoodsList(orderGoodsDTOList);
            preparedPayOrderDTO.setCreateTime(orderInfo.getCreateTime().getTime());
            preparedPayOrderDTO.setStoreId(-1);
            preparedPayOrderDTO.setPayWay(orderInfo.getPayWay());
            result.add(preparedPayOrderDTO);
        }
        return result;
    }

    private OrderExpense getOrderExpense(Integer mainOrderId, Integer orderType, String orderNo, Integer storeId) {
        BigDecimal villageExpense = new BigDecimal(0);
        BigDecimal platformExpense = new BigDecimal(0);
        BigDecimal lcExpense = new BigDecimal(0);
        BigDecimal integralExpense = new BigDecimal(0);
        BigDecimal planExpense = new BigDecimal(0);
        BigDecimal seExpense = new BigDecimal(0);
        BigDecimal aExpense = new BigDecimal(0);
        List<OrderGoodsExpenseManual> orderGoodsExpenseManualList = orderGoodsExpenseBiz
                .selectOrderGoodsExpenseManual(mainOrderId, storeId);
        for (OrderGoodsExpenseManual orderGoodsExpenseManual : orderGoodsExpenseManualList) {
            villageExpense = villageExpense.add(orderGoodsExpenseManual.getVillageExpense()
                    .multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            platformExpense = platformExpense.add(orderGoodsExpenseManual.getPlatformExpense()
                    .multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            lcExpense = lcExpense.add(orderGoodsExpenseManual.getLcExpense()
                    .multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            integralExpense = integralExpense.add(orderGoodsExpenseManual.getIntegralExpense()
                    .multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            planExpense = planExpense.add(orderGoodsExpenseManual.getPlanExpense()
                    .multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            seExpense = seExpense.add(orderGoodsExpenseManual.getSeExpense()
                    .multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            aExpense = aExpense.add(orderGoodsExpenseManual.getaExpense()
                    .multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
        }
        // 订单联系人
        OrderContact orderContact = orderContactBiz.selectOrderContactById(mainOrderId);
        // 订单工程师
        OrderEngineer orderEngineer = orderEngineerBiz.selectByOrderId(mainOrderId);
        // 订单佣金
        OrderExpense orderExpense = new OrderExpense();

        if (orderType == Constants.OrderType.MAIN.v()) {
            orderExpense.setOrderId(mainOrderId);
        }
        orderExpense.setOrderNo(orderNo);
        orderExpense.setOrderType(orderType);
        orderExpense.setVillageExpense(villageExpense);
        orderExpense.setPlatformExpense(platformExpense);
        orderExpense.setLcExpense(lcExpense);
        orderExpense.setIntegralExpense(integralExpense);
        orderExpense.setPlanExpense(planExpense);
        orderExpense.setSeExpense(seExpense);
        orderExpense.setaExpense(aExpense);
        orderExpense.setVsNum(orderContact.getVsNum());
        orderExpense.setLcNum(orderContact.getLcNum());
        if (orderEngineer != null) {
            orderExpense.setSeNum(orderEngineer.getEngineerNum());
        }
        orderExpense.setCreateTime(new Date());
        return orderExpense;
    }

}
