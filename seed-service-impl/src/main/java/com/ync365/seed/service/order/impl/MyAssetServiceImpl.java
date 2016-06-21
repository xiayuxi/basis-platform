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

import com.ync365.seed.bussiness.modules.order.biz.UserCouponBiz;
import com.ync365.seed.bussiness.modules.order.biz.UserExpenseBiz;
import com.ync365.seed.bussiness.modules.order.bo.UserCouponBO;
import com.ync365.seed.bussiness.modules.order.entity.UserExpense;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.order.input.MyHoldGoldSearchDTO;
import com.ync365.seed.dto.order.output.MyBalanceInfoDTO;
import com.ync365.seed.dto.order.output.MyBalancePageDTO;
import com.ync365.seed.dto.order.output.MyCouponInfoDTO;
import com.ync365.seed.dto.order.output.MyCouponPageDTO;
import com.ync365.seed.dto.order.output.MyHoldGoldInfoDTO;
import com.ync365.seed.dto.order.output.MyHoldGoldPageDTO;
import com.ync365.seed.service.order.MyAssetService;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.DateUtils;

public class MyAssetServiceImpl implements MyAssetService {
    private static final Logger logger = LoggerFactory.getLogger(MyAssetServiceImpl.class);

    @Autowired
    private UserExpenseBiz userExpenseBiz;
    
    @Autowired
    private UserCouponBiz userCounponBiz;
    
    @Override
    public ResponseDTO getMyHoldGold(String userNum, MyHoldGoldSearchDTO myHoldGoldSearchDTO) {
        logger.info("我的资产，我的佣金，参数，userNum，{}，condition，{}", userNum, myHoldGoldSearchDTO.toString());
        ResponseDTO dto = new ResponseDTO();
        MyHoldGoldPageDTO page = new MyHoldGoldPageDTO();
        Map<String,Object> map = new HashMap<String,Object>();
        Long count = 0L;
        if(myHoldGoldSearchDTO.getBeginTime() != null) {
            Date beginDate = new Date(myHoldGoldSearchDTO.getBeginTime()*1000);
            map.put("beginTime", DateUtils.formatDate(beginDate)+" 00:00:00");
        }
        if(myHoldGoldSearchDTO.getEndTime() != null) {
            Date endDate = new Date(myHoldGoldSearchDTO.getEndTime()*1000);
            map.put("endTime", DateUtils.formatDate(endDate)+" 23:59:59");
        }
        List<UserExpense> userExpenseList  = new ArrayList<UserExpense>();
        List<MyHoldGoldInfoDTO> holdGoldList = new ArrayList<MyHoldGoldInfoDTO>();
        map.put("orderNo", myHoldGoldSearchDTO.getOrderNo());
        map.put("userNum", userNum);
        map.put("startIndex", myHoldGoldSearchDTO.getStartIndex());
        map.put("pageSize", myHoldGoldSearchDTO.getPageSize());
        map.put("status", myHoldGoldSearchDTO.getStatus());
        page.setMyHoldGold(userExpenseBiz.getSumExpenseByStatus(userNum, null));
        page.setTakenHoldGold(userExpenseBiz.getSumExpenseByStatus(userNum, Constants.OrderExpenseStatus.Settle.v()));
        userExpenseList = userExpenseBiz.getUserExpenseListPageByUserNum(map);
        count = userExpenseBiz.getUserExpenseCountPageByUserNum(map);
        if (count < myHoldGoldSearchDTO.getStartIndex()) {
            dto.setCode(ResponseCode.OrderCode.ORDER_ERROR_REQUEST.v());
            dto.setMsg(ResponseCode.OrderCode.ORDER_ERROR_REQUEST.c());
            return dto;
        } 
        holdGoldList = toHoldGoldList(userExpenseList,holdGoldList);
        page.setHoldGoldList(holdGoldList);
        page.setTotalNum(count);
        dto.setData(page);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    @Override
    public ResponseDTO getMyCoupon(String userNum,Map<String,String>condition) {
        logger.info("我的资产，我的红包，参数，userNum，{}，condition，{}", userNum,condition);
        ResponseDTO dto = new ResponseDTO();
        MyCouponPageDTO couPage = new MyCouponPageDTO();
        List<MyCouponInfoDTO> couponDTOList = new ArrayList<MyCouponInfoDTO>();
        List<UserCouponBO> couponBOList = new ArrayList<UserCouponBO>();
        Integer couponStatus = Integer.parseInt(condition.get("CouponStatus"));
        Integer startIndex = Integer.parseInt(condition.get("startIndex"));
        Integer pageSize = Integer.parseInt(condition.get("pageSize"));
        if (couponStatus==Constants.CouponStatus.Valid.v()) {
            couponBOList = userCounponBiz.selectValidUserCouponListByPage(userNum,startIndex,pageSize);
            couPage.setTotalNum(userCounponBiz.selectValidUserCouponCount(userNum));
        } else if (couponStatus==Constants.CouponStatus.InValid.v()){
            couponBOList = userCounponBiz.selectInvalidUserCouponListByPage(userNum,startIndex,pageSize);
            couPage.setTotalNum(userCounponBiz.selectInvalidUserCouponCount(userNum));
        } else if (couponStatus==Constants.CouponStatus.USED.v()){
            couponBOList = userCounponBiz.selectUsedUserCouponListByPage(userNum,startIndex,pageSize);
            couPage.setTotalNum(userCounponBiz.selectUsedUserCouponCount(userNum));
        }
        toMyCoupon(couponBOList,couponDTOList);
        couPage.setCouponInfoList(couponDTOList);
        couPage.setTotalValid(userCounponBiz.selectValidUserCouponCount(userNum));
        couPage.setTotalInvalid(userCounponBiz.selectInvalidUserCouponCount(userNum));
        couPage.setTotalUsed(userCounponBiz.selectUsedUserCouponCount(userNum));
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData(couPage);
        return dto;
    }
    @Override
    public ResponseDTO getMyBalance(String userNum, Map<String, String> condition) {
        logger.info("我的资产，我的余额，参数，userNum，{}，condition，{}", userNum,condition);
        return  myBalance();
    }
    
    private void toMyCoupon( List<UserCouponBO> in, List<MyCouponInfoDTO> out) {
        for (UserCouponBO userCouponBO : in) {
            MyCouponInfoDTO myCouponInfoDTO = new MyCouponInfoDTO();
            CloneUtils.cloneObject(userCouponBO, myCouponInfoDTO);
            String couponName = new String();
            couponName = userCouponBO.getPromotionName()+userCouponBO.getCouponAmount().intValue()+"元红包";
            myCouponInfoDTO.setCouponName(couponName);
            out.add(myCouponInfoDTO);
        }
    }
    
    private List<MyHoldGoldInfoDTO> toHoldGoldList(List<UserExpense> userExpenseList,List<MyHoldGoldInfoDTO>holdGoldList) {
        for (UserExpense userExpense : userExpenseList ) {
            MyHoldGoldInfoDTO myHoldGoldInfoDTO = new MyHoldGoldInfoDTO();
            CloneUtils.cloneObject(userExpense, myHoldGoldInfoDTO);
            holdGoldList.add(myHoldGoldInfoDTO);
        }
        return holdGoldList;
    }
    
    private ResponseDTO myBalance(){
        ResponseDTO dto = new ResponseDTO();
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        MyBalancePageDTO balPage = new MyBalancePageDTO();
        List<MyBalanceInfoDTO> balList = new ArrayList<MyBalanceInfoDTO> ();
        MyBalanceInfoDTO bal1 = new MyBalanceInfoDTO();
        MyBalanceInfoDTO bal2 = new MyBalanceInfoDTO();
        MyBalanceInfoDTO bal3 = new MyBalanceInfoDTO();
        MyBalanceInfoDTO bal4 = new MyBalanceInfoDTO();
        
        bal1.setBalanceFee(new BigDecimal("123.123"));
        bal2.setBalanceFee(new BigDecimal("123.123"));
        bal3.setBalanceFee(new BigDecimal("123.123"));
        bal4.setBalanceFee(new BigDecimal("123.123"));
        
        bal1.setBalanceType(1);
        bal2.setBalanceType(1);
        bal3.setBalanceType(1);
        bal4.setBalanceType(1);
        
        bal1.setTimeStamp(1436959775L);
        bal2.setTimeStamp(1436959775L);
        bal3.setTimeStamp(1436959775L);
        bal4.setTimeStamp(1436959775L);
        
        bal1.setWay("种类测试：订单使用:1231321");
        bal2.setWay("种类测试：订单使用:1231321");
        bal3.setWay("种类测试：订单使用:1231321");
        bal4.setWay("种类测试：订单使用:1231321");
        
        balList.add(bal1);
        balList.add(bal2);
        balList.add(bal3);
        balList.add(bal4);
        
        balPage.setBalanceList(balList);
        balPage.setAvailableBalance(new BigDecimal("123.12"));
        balPage.setCurrentPage(1);
        balPage.setTotalPage(3);
        dto.setData(balPage);
        return dto;
    }
}
