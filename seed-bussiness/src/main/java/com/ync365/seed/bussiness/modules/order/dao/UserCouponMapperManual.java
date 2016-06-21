package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.order.bo.UserCouponBO;
import com.ync365.seed.bussiness.modules.order.entity.UserCoupon;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface UserCouponMapperManual {
     
	/**
     * 根据userNum,couponId列表查询红包列表
     * @Title: selectUserCouponList
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月25日 下午2:35:43       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<UserCoupon> selectUserCouponList(Map<String,Object> map);
 
    /**
     * 根据userCouponId锁定红包
     * @Title: lockUserCoupon
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月25日 下午2:37:36       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    int updateUserCoupon(Map<String,Object> map);
    
    
    /**
     * 
     * @Title: selectValidUserCouponList
     * @Description: 查询可用红包列表 
     * @author: Ken    
     * @date: 2015年10月29日 下午5:23:27       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<UserCouponBO> selectValidUserCouponList(Map<String,Object> map);
    /**
     * 
     * @Title: selectValidUserCouponListByPage
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月9日 下午5:28:18       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<UserCouponBO> selectValidUserCouponListByPage(Map<String,Object> map);
    
    /**
     * 
     * @Title: selectValidUserCouponCount
     * @Description: 查询可用红包数量
     * @author: Ken    
     * @date: 2015年10月29日 下午5:24:21       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    int selectValidUserCouponCount(Map<String,Object> map);
    
    /**
     * 
     * @Title: selectInvalidUserCouponList
     * @Description: 查询不可用红包列表
     * @author: Ken    
     * @date: 2015年10月29日 下午5:25:01       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<UserCouponBO> selectInvalidUserCouponList(Map<String,Object> map);
    /**
     * 分页查询失效红包
     * @Title: selectInvalidUserCouponList
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月9日 下午5:29:22       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<UserCouponBO> selectInvalidUserCouponListByPage(Map<String,Object> map);
    
    /**
     * 
     * @Title: selectInvalidUserCouponCount
     * @Description: 查询不可用红包数量   
     * @author: Ken    
     * @date: 2015年10月29日 下午5:25:57       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    int selectInvalidUserCouponCount(Map<String,Object> map);
    
    /**
     * 
     * @Title: selectUsedUserCouponList
     * @Description: 查询已使用红包列表
     * @author: Ken    
     * @date: 2015年10月29日 下午5:26:45       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<UserCouponBO> selectUsedUserCouponList(Map<String,Object> map);
    /**
     * 
     * @Title: selectUsedUserCouponList
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月9日 下午5:30:17       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<UserCouponBO> selectUsedUserCouponListByPage(Map<String,Object> map);
    
    /**
     * 
     * @Title: selectUsedUserCouponCount
     * @Description: 查询已使用红包数量
     * @author: Ken    
     * @date: 2015年10月29日 下午5:27:31       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    int selectUsedUserCouponCount(Map<String,Object> map);
    
    /**
     * 根据订单查询对应的用户红包
     * @Title: selectUserCouponListByOrderId
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月29日 下午6:32:56       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<UserCoupon> selectUserCouponListByOrderNo(Map<String,Object> map);
    /**
     * 根据用户编号查询参加的promotionID
     * @Title: selectPromotionIdByUserNum
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年10月30日 下午4:59:10       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<Integer> selectPromotionIdByUserNum (Map<String,Object>map);
    
}