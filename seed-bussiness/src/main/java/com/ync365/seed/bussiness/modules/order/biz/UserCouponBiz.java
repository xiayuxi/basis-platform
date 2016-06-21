package com.ync365.seed.bussiness.modules.order.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.goods.biz.StocksBiz;
import com.ync365.seed.bussiness.modules.order.bo.UserCouponBO;
import com.ync365.seed.bussiness.modules.order.dao.UserCouponLogMapper;
import com.ync365.seed.bussiness.modules.order.dao.UserCouponMapper;
import com.ync365.seed.bussiness.modules.order.dao.UserCouponMapperManual;
import com.ync365.seed.bussiness.modules.order.entity.UserCoupon;
import com.ync365.seed.bussiness.modules.order.entity.UserCouponLog;
import com.ync365.seed.bussiness.modules.promotion.dao.CouponMapperManual;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionCouponMapperManual;
import com.ync365.seed.bussiness.modules.promotion.entity.Coupon;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionCoupon;
import com.ync365.seed.utils.Constants;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class UserCouponBiz {

    @Autowired
    private UserCouponMapperManual userCouponMapperManual;

    @Autowired
    private UserCouponMapper userCouponMapper;
    
    @Autowired
    private PromotionCouponMapperManual promotionCouponMapperManual;

    @Autowired
    private CouponMapperManual couponMapperManual;
    
    @Autowired
    private OrderNoGenBiz orderNoGenBiz;
    
    @Autowired
    private UserCouponLogMapper userCouponlogMapper;
    /**
     * 根据用户红包主键查询红包
     * @Title: selectUserCouponById
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月27日 下午1:52:19       
     * @version: 
     *
     * @param userCouponId
     * @return
     *
     */
    public UserCoupon selectByPrimaryKey(Integer userCouponId) {
        return userCouponMapper.selectByPrimaryKey(userCouponId);
    }

    /**
     * 更新用户红包状态与锁定
     * @Title: updateUserCoupon
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月27日 下午2:12:20       
     * @version: 
     *
     * @param userCouponId    用户红包主键
     * @param isLock          1：锁定  
     * @param status          状态
     * @param version         版本号
     * @return
     *
     */
    public int updateUserCoupon(Integer userCouponId, Integer isLock, Integer status, Integer version) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userCouponId", userCouponId);
        map.put("isLock", isLock);
        map.put("status", status);
        map.put("version", version);
        return userCouponMapperManual.updateUserCoupon(map);
    }

    @Transactional
    public int updateUserCouponBatchReduce(List<Map<String, Object>> list) {
        int update = 0;

        /**  暂时不用检测红包金额
        for (Map<String, Object> map : list) {

            Integer userCouponId = (Integer) map.get("userCouponId");
            Integer amount = (Integer) map.get("amount");

            UserCoupon uc = selectByPrimaryKey(userCouponId);
            //判断红包金额是否相等
            if ((uc.getCouponAmount() != null && amount != null)
                    && (uc.getCouponAmount().intValue() == amount.intValue())) {
                update = 2;
                return update;
            }
        }*/

        for (Map<String, Object> map : list) {

            Integer userCouponId = (Integer) map.get("userCouponId");

            UserCoupon uc = selectByPrimaryKey(userCouponId);

            Integer version = uc.getVersion();

            //减红包把锁定变更不锁定
            Integer isLock = Constants.Whether.No.v();

            //状态为已使用
            Integer status = Constants.Whether.Yes.v();
            update = updateUserCoupon(userCouponId, isLock, status, version);
        }

        return update;
    }

    @Transactional
    public int updateUserCouponBatchCancel(List<Integer> list) {
        int update = 0;

        for (Integer userCouponId : list) {

            UserCoupon uc = selectByPrimaryKey(userCouponId);

            Integer version = uc.getVersion();

            //减红包把锁定变更不锁定
            Integer isLock = Constants.Whether.Yes.v();

            //状态为已使用
            Integer status = Constants.Whether.No.v();
            update = updateUserCoupon(userCouponId, isLock, status, version);
        }

        return update;
    }

    /**
     * 
     * @Title: selectValidUserCouponList
     * @Description: TODO    ：    查询有效的红包
     * @author: ivan    
     * @date: 2015年10月29日 下午5:37:02       
     * @version: 
     *
     * @param map
     * @return
     *
     */

    public List<UserCouponBO> selectValidUserCouponList(String userNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        map.put("currentDate", new Date());
        map.put("isLock", Constants.Whether.No.v());
        map.put("isValid", Constants.Whether.No.v());
        map.put("status", Constants.Whether.No.v());
        return userCouponMapperManual.selectValidUserCouponList(map);
    }
    /**
     * 
     * @Title: selectValidUserCouponList
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月9日 下午5:30:43       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    public List<UserCouponBO> selectValidUserCouponListByPage(String userNum,Integer startIndex,Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        map.put("currentDate", new Date());
        map.put("isLock", Constants.Whether.No.v());
        map.put("isValid", Constants.Whether.No.v());
        map.put("status", Constants.Whether.No.v());
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        return userCouponMapperManual.selectValidUserCouponListByPage(map);
    }

    /**
     * 
     * @Title: selectValidUserCouponCount
     * @Description: TODO    ：    有效红包数量
     * @author: ivan    
     * @date: 2015年10月29日 下午5:37:17       
     * @version: 
     *
     * @param map
     * @return
     *
     */

    public int selectValidUserCouponCount(String userNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        map.put("currentDate", new Date());
        map.put("isLock", Constants.Whether.No.v());
        map.put("isValid", Constants.Whether.No.v());
        map.put("status", Constants.Whether.No.v());
        return userCouponMapperManual.selectValidUserCouponCount(map);
    }

    /**
     * 
     * @Title: selectInvalidUserCouponList
     * @Description: TODO    ：    无效的红包列表
     * @author: ivan    
     * @date: 2015年10月29日 下午5:37:47       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public List<UserCouponBO> selectInvalidUserCouponList(String userNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isValid", Constants.Whether.Yes.v());
        map.put("userNum", userNum); // 
        map.put("status", Constants.Whether.No.v()); // 0、未使用; 1、已使用
        map.put("isLock", Constants.Whether.No.v()); // 0、未锁定; 1、已锁定
        map.put("currentDate", new Date());
        return userCouponMapperManual.selectInvalidUserCouponList(map);
    }
    /**
     * 
     * @Title: selectInvalidUserCouponListByPage
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月9日 下午5:32:43       
     * @version: 
     *
     * @param userNum
     * @param startIndex
     * @param endIndex
     * @return
     *
     */
    public List<UserCouponBO> selectInvalidUserCouponListByPage(String userNum,Integer startIndex,Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isValid", Constants.Whether.Yes.v());
        map.put("userNum", userNum); // 
        map.put("status", Constants.Whether.No.v()); // 0、未使用; 1、已使用
        map.put("isLock", Constants.Whether.No.v()); // 0、未锁定; 1、已锁定
        map.put("currentDate", new Date());
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        return userCouponMapperManual.selectInvalidUserCouponListByPage(map);
    }

    /**
     * 
     * @Title: selectInvalidUserCouponCount
     * @Description: TODO    ：    无效的红包数量
     * @author: ivan    
     * @date: 2015年10月29日 下午5:38:51       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public int selectInvalidUserCouponCount(String userNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isValid", Constants.Whether.Yes.v());
        map.put("userNum", userNum); // 
        map.put("status", Constants.Whether.No.v()); // 0、未使用; 1、已使用
        map.put("isLock", Constants.Whether.No.v()); // 0、未锁定; 1、已锁定
        map.put("currentDate", new Date());
        return userCouponMapperManual.selectInvalidUserCouponCount(map);
    }

    /**
     * 
     * @Title: selectUsedUserCouponList
     * @Description: TODO    ：    已使用红包列表
     * @author: ivan    
     * @date: 2015年10月29日 下午5:39:05       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public List<UserCouponBO> selectUsedUserCouponList(String userNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        map.put("currentDate", new Date());
        map.put("isLock", Constants.Whether.Yes.v());
        map.put("isValid", Constants.Whether.No.v());
        map.put("status", Constants.Whether.Yes.v());
        return userCouponMapperManual.selectUsedUserCouponList(map);
    }
    /**
     * 
     * @Title: selectUsedUserCouponList
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月9日 下午5:32:55       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    public List<UserCouponBO> selectUsedUserCouponListByPage(String userNum,Integer startIndex,Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        map.put("currentDate", new Date());
        map.put("isLock", Constants.Whether.Yes.v());
        map.put("isValid", Constants.Whether.No.v());
        map.put("status", Constants.Whether.Yes.v());
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        return userCouponMapperManual.selectUsedUserCouponListByPage(map);
    }

    /**
     * 
     * @Title: selectUsedUserCouponCount
     * @Description: TODO    ：    已使用红包数量
     * @author: ivan    
     * @date: 2015年10月29日 下午5:39:21       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public int selectUsedUserCouponCount(String userNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        map.put("currentDate", new Date());
        map.put("isLock", Constants.Whether.Yes.v());
        map.put("isValid", Constants.Whether.No.v());
        map.put("status", Constants.Whether.Yes.v());
        return userCouponMapperManual.selectUsedUserCouponCount(map);
    }

    public Integer insertUserCoupon(UserCoupon record) {
        return userCouponMapper.insert(record);
    }

    /**
     * 查询订单 对应的红包使用状态 
     * @Title: selectUserCouponListByOrderId
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月29日 下午6:37:51       
     * @version: 
     *
     * @param orderId
     * @return   0未使用 1已使用 2已过期 
     *
     */
    public int selectUserCouponStatusByOrderNo(String orderNo) {
        /**红包状态  0未使用 1已使用 2已过期 3 不存在*/
        int status = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        List<UserCoupon> list = userCouponMapperManual.selectUserCouponListByOrderNo(map);
        if (list == null || list.size() == 0) {

            status = 3;
            return status;
        }
        for (UserCoupon uc : list) {
            if (uc.getStatus().intValue() == Constants.Status.Enabled.v()) {
                status = Constants.Whether.Yes.v();

            } else if (uc.getStatus().intValue() == Constants.Status.Disabled.v()) {
                status = 0;
                break;
            } else {
                status = 2;
                break;
            }
        }

        return status;
    }

    public List<UserCoupon> selectUserCouponListByOrderNo(String orderNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        return userCouponMapperManual.selectUserCouponListByOrderNo(map);
    }

    /**
     * 
     * @Title: registerGrantCoupon
     * @Description: TODO    ：    注册发放红包
     * @author: ivan    
     * @date: 2015年10月30日 下午3:43:14       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @Transactional(readOnly = false)
    public Integer registerGrantCoupon(String userNum) {
        Map<String,Object> map = new HashMap<String,Object> ();
        map.put("couponType", Constants.CouponType.REGISTER.v());
        map.put("userNum", userNum);
        map.put("promotionStatus", Constants.PromotionStatus.Begin.v());
        map.put("currentDate", new Date());
        //获取当前生效的活动列表
        List<PromotionCoupon> prmotionCouponList = promotionCouponMapperManual.getPromotionCouponByCouponType(map);
        Set<Integer> promotionSet = new HashSet<Integer>();
        Set<Integer> userSet = new HashSet<Integer>();
        for (PromotionCoupon promotionCoupon : prmotionCouponList) {
            promotionSet.add(promotionCoupon.getPromotionId());
        }
        List <Integer> userCouponList = userCouponMapperManual.selectPromotionIdByUserNum(map);
        userSet.addAll(userCouponList);
        //获取用户没有参加的活动
        promotionSet.removeAll(userSet);
        Iterator<Integer> it = promotionSet.iterator();
        while(it.hasNext()) {
            UserCoupon userCoupon = new UserCoupon();
            UserCouponLog userCouponLog = new UserCouponLog(); 
            Coupon coupon = new Coupon();
            Integer promotionId = it.next();
            coupon = couponMapperManual.selectByPromotionId(promotionId);
            userCoupon.setCouponAmount(coupon.getCouponAmount());
            userCoupon.setCouponno(orderNoGenBiz.getCoupnoNo(promotionId));
            userCoupon.setCreateTime(new Date());
            userCoupon.setIsLock(Constants.Whether.No.v());
            userCoupon.setIsValid(Constants.Whether.No.v());
            userCoupon.setStatus(Constants.Whether.No.v());
            userCoupon.setVersion(1);
            userCoupon.setPromotionId(promotionId);
            userCoupon.setUserNum(userNum);
            userCoupon.setCouponId(coupon.getCouponId());
            userCouponMapper.insert(userCoupon);
            userCouponLog.setUserCouponId(userCoupon.getUserCouponId());
            userCouponLog.setCreateTime(new Date());
            userCouponLog.setStatus(Constants.CouponStatus.Valid.v());
            userCouponLog.setUserNum(userNum);
            userCouponLog.setCouponAmount(userCoupon.getCouponAmount());
            userCouponlogMapper.insert(userCouponLog);
        }
        return Constants.Whether.Yes.v();
    }

    /**
     * 
     * @Title: loginGrantCoupon
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年10月30日 下午3:43:41       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @Transactional(readOnly = false)
    public Integer loginGrantCoupon(String userNum) {
        Map<String,Object> map = new HashMap<String,Object> ();
        map.put("couponChannel", Constants.CouponType.ONLINE.v());
        map.put("userNum", userNum);
        map.put("promotionStatus", Constants.PromotionStatus.Begin.v());
        map.put("currentDate", new Date());
        //获取当前生效的活动列表
        List<PromotionCoupon> prmotionCouponList = promotionCouponMapperManual.getPromotionCouponByCouponType(map);
        Set<Integer> promotionSet = new HashSet<Integer>();
        Set<Integer> userSet = new HashSet<Integer>();
        for (PromotionCoupon promotionCoupon : prmotionCouponList) {
            promotionSet.add(promotionCoupon.getPromotionId());
        }
        List <Integer> userCouponList = userCouponMapperManual.selectPromotionIdByUserNum(map);
        userSet.addAll(userCouponList);
        //获取用户没有参加的活动
        promotionSet.removeAll(userSet);
        Iterator<Integer> it = promotionSet.iterator();
        while(it.hasNext()) {
            UserCoupon userCoupon = new UserCoupon();
            UserCouponLog userCouponLog = new UserCouponLog(); 
            Coupon coupon = new Coupon();
            Integer promotionId = it.next();
            coupon = couponMapperManual.selectByPromotionId(promotionId);
            userCoupon.setCouponAmount(coupon.getCouponAmount());
            userCoupon.setCouponno(orderNoGenBiz.getCoupnoNo(promotionId));
            userCoupon.setCreateTime(new Date());
            userCoupon.setIsLock(Constants.Whether.No.v());
            userCoupon.setIsValid(Constants.Whether.No.v());
            userCoupon.setStatus(Constants.Whether.No.v());
            userCoupon.setVersion(1);
            userCoupon.setPromotionId(promotionId);
            userCoupon.setUserNum(userNum);
            userCoupon.setCouponId(coupon.getCouponId());
            userCouponMapper.insert(userCoupon);
            userCouponLog.setUserCouponId(userCoupon.getUserCouponId());
            userCouponLog.setCreateTime(new Date());
            userCouponLog.setStatus(Constants.CouponStatus.Valid.v());
            userCouponLog.setUserNum(userNum);
            userCouponLog.setCouponAmount(userCoupon.getCouponAmount());
            userCouponlogMapper.insert(userCouponLog);
        }
        return Constants.Whether.Yes.v();
    }
    /**
     * 根据订单编号解锁红包
     * @Title: UnlockCouponByOrderNo
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月7日 下午4:23:58       
     * @version: 
     *
     * @param orderId
     *
     */
    public void UnlockCouponByOrderNo (List<String> orderNoList) {
        for (String orderNo : orderNoList) {
            Map <String,Object> map = new HashMap<String,Object> ();
            map.put("orderNo", orderNo);
            List<UserCoupon> userCouponList = userCouponMapperManual.selectUserCouponListByOrderNo(map);
            for (UserCoupon userCoupon : userCouponList) {
                userCoupon.setIsLock(Constants.Whether.No.v());
                userCoupon.setStatus(Constants.Whether.No.v());
                userCouponMapper.updateByPrimaryKeySelective(userCoupon);
                UserCouponLog userCouponLog = new UserCouponLog(); 
                userCouponLog.setUserCouponId(userCoupon.getUserCouponId());
                userCouponLog.setCreateTime(new Date());
                userCouponLog.setStatus(Constants.CouponStatus.Valid.v());
                userCouponLog.setUserNum(userCoupon.getUserNum());
                userCouponLog.setCouponAmount(userCoupon.getCouponAmount());
                userCouponlogMapper.insert(userCouponLog);
            }
        }
    }
}
