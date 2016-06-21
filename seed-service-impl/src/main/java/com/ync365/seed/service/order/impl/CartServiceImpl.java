package com.ync365.seed.service.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.common.biz.RegionBiz;
import com.ync365.seed.bussiness.modules.goods.biz.HoldGoldBiz;
import com.ync365.seed.bussiness.modules.goods.biz.SkuBiz;
import com.ync365.seed.bussiness.modules.goods.biz.StocksBiz;
import com.ync365.seed.bussiness.modules.goods.bo.SkuBO;
import com.ync365.seed.bussiness.modules.goods.entity.HoldGold;
import com.ync365.seed.bussiness.modules.order.biz.CartBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderGoodsBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderNoGenBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderOptLogBiz;
import com.ync365.seed.bussiness.modules.order.biz.UserCouponBiz;
import com.ync365.seed.bussiness.modules.order.bo.CartBO;
import com.ync365.seed.bussiness.modules.order.bo.CartSkuBO;
import com.ync365.seed.bussiness.modules.order.bo.CartStoreBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderGoodsBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderPromotionBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderStoreBO;
import com.ync365.seed.bussiness.modules.order.bo.UserCouponBO;
import com.ync365.seed.bussiness.modules.order.entity.OrderAddress;
import com.ync365.seed.bussiness.modules.order.entity.OrderContact;
import com.ync365.seed.bussiness.modules.order.entity.OrderCoupon;
import com.ync365.seed.bussiness.modules.order.entity.OrderEngineer;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoods;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderOptLog;
import com.ync365.seed.bussiness.modules.order.entity.OrderStore;
import com.ync365.seed.bussiness.modules.order.entity.OrderUser;
import com.ync365.seed.bussiness.modules.order.entity.UserCoupon;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionGoodsSkuBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionOnLinePO;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGoodsSku;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionDownPO;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionReducePO;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionReduceRegPO;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.SysAdminInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.order.input.CartOrderInputDTO;
import com.ync365.seed.dto.order.input.CartSettleInputDTO;
import com.ync365.seed.dto.order.input.StoreSettleInputDTO;
import com.ync365.seed.dto.order.output.CartDTO;
import com.ync365.seed.dto.order.output.PromSkuDTO;
import com.ync365.seed.dto.order.output.SkuDTO;
import com.ync365.seed.dto.order.output.StoreDTO;
import com.ync365.seed.service.order.CartService;
import com.ync365.seed.utils.Constants;

/**    
 *     
 * @Title：CartServiceImpl
 * @Description: C网购物车服务实现
 * @author: Ken        
 * @date: 2015年9月21日 上午11:01:35      
 * @version 1.0  
 *     
 */
public class CartServiceImpl implements CartService {
    
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartBiz cartBiz;
    
    @Autowired
    private SkuBiz skuBiz;
    
    @Autowired
    private HoldGoldBiz holdGoldBiz;
    
    @Autowired
    private PromotionBiz promotionBiz;
    
    @Autowired
    private SysUserInfoBiz sysUserInfoBiz;
    
    @Autowired
    private SysAdminInfoBiz sysAdminInfoBiz;
    
    @Autowired
    private RegionBiz regionBiz;
    
    @Autowired
    private SysPopStoreBiz sysPopStoreBiz;
    
    @Autowired
    private OrderNoGenBiz orderNoGenBiz;
    
    @Autowired
    private OrderInfoBiz orderInfoBiz;
    
    @Autowired
    private OrderOptLogBiz orderOptLogBiz;
    
    @Autowired
    private StocksBiz stocksBiz;
    
    @Autowired
    private OrderGoodsBiz orderGoodsBiz;
    
    @Autowired
    private UserCouponBiz userCouponBiz;
    
    @Autowired
    private PromotionGoodsSkuBiz promotionGoodsSkuBiz;

    /**
     * 
     * @Title: queryCart
     * @Description: 查看购物车中的商品 
     * @author: Ken    
     * @date: 2015年10月8日 下午12:55:23       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @Override
    public ResponseDTO queryCart(String userNum,Integer cityId) {
        ResponseDTO response = new ResponseDTO();
        try{
            logger.info("购物车，查看，开始，参数，userNum，{}，cityId，{}", userNum,cityId);
            // cartBiz.selCartAll(userNum, Constants.Status.Enabled.v());
        }catch(Exception e){
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("购物车，查看，异常，参数，userNum，{}，cityId，{}，异常信息，{}", userNum,cityId, e.getMessage());
            e.getMessage();
        }
        return getCartInfo(userNum,cityId);
    }
    
    /**
     * 
     * @Title: addCart
     * @Description: 向购物车添加商品  
     * @author: Ken    
     * @date: 2015年10月13日 下午6:29:56       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @Override
    public ResponseDTO addCart(String userNum,Integer cityId, Map<String,Integer> map) {
        ResponseDTO response;
        Integer storeId = null;
        Integer skuId = null;
        Integer num = null;
        try{
            storeId = map.get("storeId");
            skuId = map.get("skuId");
            num = map.get("num");
            logger.info("购物车，添加商品，开始，参数，userNum，{}，cityId，{}，storeId，{}，skuId，{}，num，{}", userNum,cityId,storeId,skuId,num);
            response = this.validGoodsSku(userNum, skuId, num, cityId, storeId,"add");
            if(response != null){
                return response;
            }
            cartBiz.addCart(userNum, storeId, skuId, num);
            logger.info("购物车，添加商品，满了，参数，userNum，{}，cityId，{}，storeId，{}，skuId，{}，num，{}", userNum,cityId,storeId,skuId,num);
        }catch(Exception e){
            response = new ResponseDTO();
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("购物车，添加商品，异常，参数，userNum，{}，cityId，{}，storeId，{}，skuId，{}，num，{}，异常信息，{}", userNum,cityId,storeId,skuId,num, e.getMessage());
            e.getMessage();
        }
        return getCartInfo(userNum,cityId);
    }
    
    
    private ResponseDTO validGoodsSku(String userNum,Integer skuId,Integer num,Integer cityId,Integer storeId,String sign){
        ResponseDTO response = null;
        Integer skuTotalNum = num;
        if("add".equals(sign)){
            Integer count = cartBiz.countCart(userNum);
            if(count >= CartBiz.getCartCount()){
                response = new ResponseDTO();
                response.setCode(ResponseCode.CartCode.CART_IS_FULL.v());
                response.setMsg(ResponseCode.CartCode.CART_IS_FULL.c());
                return response;
            }
            Integer cartSkuNum = cartBiz.getCartSkuCountBySkuId(userNum, storeId, skuId);
            skuTotalNum += cartSkuNum;
        }
        
        List <Integer> skuIds = new ArrayList<Integer>();
        skuIds.add(skuId);
        Map<Integer,SkuBO> skuMap = skuBiz.getSkuByIdAndRegionId(skuIds, cityId); 
        SkuBO skuBO = skuMap.get(skuId);
        // -1表示小于,0是等于,1是大于
        if((new BigDecimal(skuTotalNum)).compareTo(skuBO.getLowerLimit()) == -1 ){
            response = new ResponseDTO();
            response.setCode(ResponseCode.CartCode.SKU_LESS_THAN_LOWER_LIMIT.v());
            response.setMsg(ResponseCode.CartCode.SKU_LESS_THAN_LOWER_LIMIT.c());
            return response;
        }
        
        if((new BigDecimal(skuTotalNum)).compareTo(skuBO.getUpperLimit()) == 1 ){
            response = new ResponseDTO();
            response.setCode(ResponseCode.CartCode.SKU_MORE_THAN_UPPER_LIMIT.v());
            response.setMsg(ResponseCode.CartCode.SKU_MORE_THAN_UPPER_LIMIT.c());
            return response;
        }
        
        if((new BigDecimal(skuTotalNum)).compareTo(new BigDecimal(skuBO.getStockNum())) == 1){
            response = new ResponseDTO();
            response.setCode(ResponseCode.CartCode.SKU_STOCK_IS_NOT_ENOUGH.v());
            response.setMsg(ResponseCode.CartCode.SKU_STOCK_IS_NOT_ENOUGH.c());
            return response;
        }

        if(skuBO.getIsDelete() !=null && ( skuBO.getIsDelete() == Constants.IsDelete.YES.v())){
            response = new ResponseDTO();
            response.setCode(ResponseCode.CartCode.SKU_IS_DELETE.v());
            response.setMsg(ResponseCode.CartCode.SKU_IS_DELETE.c());
            return response;
        }
        
        if(3 != skuBO.getStatus()){
            response = new ResponseDTO();
            response.setCode(ResponseCode.CartCode.SKU_IS_NOT_ON_SALE.v());
            response.setMsg(ResponseCode.CartCode.SKU_IS_NOT_ON_SALE.c());
            return response;
        }
        
        return response;
    }

    /**
     * 
     * @Title: delCart
     * @Description: 删除、批量删除购物车中的商品    
     * @author: Ken    
     * @date: 2015年10月13日 下午6:31:18       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @Override
    public ResponseDTO delCart(String userNum, Integer cityId,Map<Integer, List<Integer>> param) {
        ResponseDTO response = new ResponseDTO();
        try{
            logger.info("购物车，删除商品，参数，userNum，{}，cityId，{}，storeSkus，{}", userNum,cityId,param);
            cartBiz.delCart(userNum, param);
        }catch(Exception e){
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("购物车，删除商品，异常，参数，userNum，{}，cityId，{}，storeSkus，{}，异常信息，{}", userNum,cityId,param, e.getMessage());
            e.getMessage();
        }
        return getCartInfo(userNum,cityId);
    }

    /**
     * 
     * @Title: numCart
     * @Description: 修改购物车中商品数量 
     * @author: Ken    
     * @date: 2015年10月13日 下午7:37:07       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @Override
    public ResponseDTO numCart(String userNum, Integer cityId, Map<String,Integer> param) {
        Integer storeId = null;
        Integer skuId = null;
        Integer num  = null;
        ResponseDTO response = null;
        try{
            storeId = param.get("storeId");
            skuId = param.get("skuId");
            num  = param.get("num");
            response = this.validGoodsSku(userNum, skuId, num, cityId, storeId,"del");
            if(response != null){
                return response;
            }
            logger.info("购物车，修改数量，开始，参数，userNum，{}，cityId，{}，storeId，{}，skuId，{}，num，{}", userNum,cityId,storeId,skuId,num);
            cartBiz.numCart(userNum, storeId, skuId, num);
        }catch(Exception e){
            response = new ResponseDTO();
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("购物车，修改数量，异常，参数，userNum，{}，cityId，{}，storeId，{}，skuId，{}，num，{}，异常信息，{}",userNum,cityId,storeId,skuId,num, e.getMessage());
            e.getMessage();
        }
        return getCartInfo(userNum,cityId);
    }
    
    /**
     * 
     * @Title: selCartAll
     * @Description: 全选、全部取消    
     * @author: Ken    
     * @date: 2015年10月13日 下午6:33:55       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @Override
    public ResponseDTO selCartAll(String userNum,Integer cityId, Map <String,Integer> param) {
        ResponseDTO response = new ResponseDTO();
        Integer isSelect = null;
        try{
            isSelect = param.get("isSelect");
            logger.info("购物车，全部选择或取消，开始，参数，userNum，{}，cityId，{}，isSelect，{}", userNum,cityId,isSelect == Constants.Status.Enabled.v() ? "选择" : "取消");
            cartBiz.selCartAll(userNum,isSelect);
        }catch(Exception e){
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("购物车，全部选择或取消，异常，参数，userNum，{}，cityId，{}，isSelect，{}，异常信息，{}", userNum,cityId,isSelect == Constants.Status.Enabled.v() ? "选择" : "取消", e.getMessage());
            e.getMessage();
        }
        return getCartInfo(userNum,cityId);
    }
    
    /**
     * 
     * @Title: selCartStore
     * @Description: 选择、取消选择店铺  
     * @author: Ken    
     * @date: 2015年10月13日 下午7:33:20       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @Override
    public ResponseDTO selCartStore(String userNum,Integer cityId, Map<String,Integer> param) {
        Integer storeId = null;
        Integer isSelect = null;
        ResponseDTO response = new ResponseDTO();
        try{
            storeId = param.get("storeId");
            isSelect = param.get("isSelect");
            logger.info("购物车，店铺选择或取消，开始，参数，userNum，{}，cityId，{}，storeId，{}，isSelect，{}", userNum,cityId,storeId,isSelect == Constants.Status.Enabled.v() ? "选择" : "取消");
            cartBiz.selCartStore(userNum, storeId, isSelect);
        }catch(Exception e){
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("购物车，店铺选择或取消，异常，参数，userNum，{}，cityId，{}，storeId，{}，isSelect，{}，异常信息，{}", userNum,cityId,storeId,isSelect == Constants.Status.Enabled.v() ? "选择" : "取消", e.getMessage());
            e.getMessage();
        }
        return getCartInfo(userNum,cityId);
    }

    /**
     * 
     * @Title: selCartSku
     * @Description:     
     * @author: Ken    
     * @date: 2015年10月9日 上午10:12:52       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @Override
    public ResponseDTO selCartSku(String userNum,Integer cityId, Map<String,Integer> param) {
        ResponseDTO response = new ResponseDTO();
        Integer storeId = null;
        Integer skuId = null;
        Integer isSelect = null;
        try{
            storeId = param.get("storeId");
            skuId = param.get("skuId");
            isSelect = param.get("isSelect");
            logger.info("购物车，商品选择或取消，开始，参数，userNum，{}，cityId，{}，storeId，{}，skuId，{}，isSelect，{}", userNum,cityId,storeId,skuId,isSelect == Constants.Status.Enabled.v() ? "选择" : "取消");
            cartBiz.selCartSku(userNum, storeId, skuId, isSelect);
        }catch(Exception e){
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("购物车，商品选择或取消，异常，参数，userNum，{}，cityId，{}，storeId，{}，skuId，{}，isSelect，{}，异常信息，{}", userNum,cityId,storeId,skuId,isSelect == Constants.Status.Enabled.v() ? "选择" : "取消", e.getMessage());
            e.getMessage();
        }
        return getCartInfo(userNum,cityId);
    }
    
    /**
     * 
     * @Title: countCart
     * @Description: 获取购物车中商品数量    
     * @author: Ken    
     * @date: 2015年10月9日 下午6:39:03       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @Override
    public ResponseDTO countCart(String userNum,Integer cityId) {
        ResponseDTO response = new ResponseDTO();
        Integer count = null;
        try {
            logger.info("购物车，获取商品数量，开始，参数，userNum，{}，cityId，{}",userNum,cityId);
            count = cartBiz.countCart(userNum);
            response.setCode(ResponseCode.CommonCode.OK.v());
            response.setMsg(ResponseCode.CommonCode.OK.c());
            response.setData(count);
        } catch (Exception e) {
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("购物车，获取商品数量，异常，参数，userNum，{}，cityId，{}，count，{}，异常信息，{}",userNum,cityId,count,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    
    /**
     * 
     * @Title: settleCart
     * @Description: 结算按钮   
     * @author: Ken    
     * @date: 2015年10月17日 下午6:06:07       
     * @version: 
     *
     * @param userNum
     * @param cityId
     * @param param
     * @return
     *
     */
    @Override
    public ResponseDTO settleCart(String userNum, Integer cityId, CartSettleInputDTO cartSettleInfo) {
        ResponseDTO response = new ResponseDTO();
        try {
            logger.info("购物车，结算，开始，参数，userNum，{}，cityId，{}，param，{}",userNum,cityId,cartSettleInfo);
            CartBO cartBO = this.getCartSettleDetailInfo(cartSettleInfo, userNum);
            // CartDTO cartDTO = getCartDTO(userNum, cityId, cartBO);
            CartDTO cartDTO = getCartDTO(cityId, cartBO);
            response.setCode(ResponseCode.CommonCode.OK.v());
            response.setMsg(ResponseCode.CommonCode.OK.c());
            response.setData(cartDTO);
            logger.info("购物车，结算，结束，参数，userNum，{}，cityId，{}，param，{}",userNum,cityId,cartSettleInfo);
        } catch (Exception e) {
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("购物车，结算，异常，参数，userNum，{}，cityId，{}，param，{}，异常信息，{}",userNum,cityId,cartSettleInfo,e.getMessage());
            e.printStackTrace();
        }
        return response;
        
        
    }
    /**
     * @Title: queryOrderValidCoupons
     * @Description: 查询可用红包    
     * @author: Ken    
     * @date: 2015年10月30日 上午11:40:31       
     * @version: 
     *
     * @param userNum
     * @param skuIds
     * @return
     *
     */
    public ResponseDTO queryOrderValidCoupons(String userNum,List<Integer> skuIds){
        ResponseDTO response = new ResponseDTO();
        try{
           
            List<UserCouponBO> userCouponBOList = userCouponBiz.selectValidUserCouponList(userNum);
            if(userCouponBOList == null || userCouponBOList.size() == 0){ // 无可用红包
                response.setCode(ResponseCode.UserCouponCode.NO_CAN_USED_COUPON.v());
                response.setMsg(ResponseCode.UserCouponCode.NO_CAN_USED_COUPON.c());
                // response.setData(cartDTO);
                return response;
            }
            
            List <UserCouponBO> canUseCoupon = new ArrayList<UserCouponBO>();
            for(UserCouponBO userCouponBO : userCouponBOList ){
                Integer promotionId = userCouponBO.getPromotionId();
                List<PromotionGoodsSku> promGoodSkuList =  promotionGoodsSkuBiz.findPromGoodsSkusBySkuIds(promotionId, skuIds);
                if(promGoodSkuList != null && promGoodSkuList.size() > 0){ // 存在活动商品，红包可用
                    canUseCoupon.add(userCouponBO);
                }
            }
            
            if(canUseCoupon.size() == 0){
                response.setCode(ResponseCode.UserCouponCode.NO_CAN_USED_COUPON.v());
                response.setMsg(ResponseCode.UserCouponCode.NO_CAN_USED_COUPON.c());
            }else{
                response.setCode(ResponseCode.CommonCode.OK.v());
                response.setMsg(ResponseCode.CommonCode.OK.c());
                response.setData(canUseCoupon);
            }
        }catch(Exception e){
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            e.printStackTrace();
        }
        return response;
    }
    
    private OrderGoodsBO getOrderGoods(SkuDTO sku,Integer storeId,Date createTime){
        OrderGoodsBO orderGoodsBO = new OrderGoodsBO();
        orderGoodsBO.setGoodsId(sku.getGoodsId());
        // orderGoods.setGoodsCode(goodsCode);
        orderGoodsBO.setSkuId(sku.getSkuId());
        orderGoodsBO.setStoreId(storeId);
        orderGoodsBO.setGoodsName(sku.getSkuName());
        orderGoodsBO.setMarketPrice(sku.getMarketPrice());
        orderGoodsBO.setGoodsPrice(sku.getSellPrice());
        /*if(sku.getPromPrice() != null && sku.getPromPrice().compareTo(new BigDecimal(0)) > 1){
            orderGoodsBO.setActualAmount(sku.getPromPrice());
        }else{
            orderGoodsBO.setActualAmount(sku.getSellPrice());
        }*/
        orderGoodsBO.setActualAmount(sku.getSellPrice());
        orderGoodsBO.setGoodsCount(sku.getSkuNum());
        orderGoodsBO.setGoodsUrl(sku.getImgUrl());
        orderGoodsBO.setTotalAmount(sku.getSkuTotalPrice());
        orderGoodsBO.setSaveAmount(new BigDecimal(0));
        orderGoodsBO.setActualAmount(sku.getSkuTotalPrice() );
        orderGoodsBO.setIsChange(Constants.OrderGoodsIsChangePrice.NO.v());
        // 佣金
        HoldGold holdGold = holdGoldBiz.selectBySkuId(sku.getSkuId());
        orderGoodsBO.setVillageExpense(holdGold.getVsAmount()); // 村站
        orderGoodsBO.setPlatformExpense(holdGold.getSupplierAmount()); // 平台
        orderGoodsBO.setLcExpense(holdGold.getLcAmount()); // LC
        orderGoodsBO.setIntegralExpense(holdGold.getIntegrationCosts()); // 积分
        orderGoodsBO.setPlanExpense(holdGold.getManpowerCosts()); // 众筹
        orderGoodsBO.setSeExpense(holdGold.getEngineerAmount());
        orderGoodsBO.setaExpense(holdGold.getaAmount());
        orderGoodsBO.setIsChange(Constants.Status.Disabled.v());
        orderGoodsBO.setCreateTime(createTime);
        return orderGoodsBO;
    }
    
    /**
     * 
     * @Title: getCartSettleDetailInfo
     * @Description: 获取购物车待结算商品详情    
     * @author: Ken    
     * @date: 2015年10月19日 上午11:01:37       
     * @version: 
     *
     * @param cartSettleInfo
     * @param userNum
     * @return
     *
     */
    private CartBO getCartSettleDetailInfo( CartSettleInputDTO cartSettleInfo,String userNum){
        CartBO cartBO = new CartBO();
        Map <Integer,CartStoreBO> cartStoreMap = new HashMap<Integer,CartStoreBO>();
        for(StoreSettleInputDTO storeInfo :cartSettleInfo.getStoreInfoList()){
            Integer storeId = storeInfo.getStoreId();
            List<Map<String,Integer>> skuInfoList = storeInfo.getSkuInfoList();
            CartStoreBO cartStoreBO = new CartStoreBO();
            Map<Integer,CartSkuBO> skuMap = new HashMap<Integer,CartSkuBO>();
            for(Map<String,Integer> skuInfo : skuInfoList){
                Integer skuId = skuInfo.get("skuId");
                Integer num = skuInfo.get("num");
                CartSkuBO cartSkuBO = new CartSkuBO();
                cartSkuBO.setStoreId(storeId);
                cartSkuBO.setSkuId(skuId);
                cartSkuBO.setNum(num);
                cartSkuBO.setIsSelect(true);
                skuMap.put(skuId, cartSkuBO);
            }
            cartStoreBO.setSkuMap(skuMap);
            cartStoreBO.setStoreId(storeId);
            cartStoreBO.setIsStoreSelect(true);
            cartStoreMap.put(storeId, cartStoreBO);
        }
        cartBO.setIsAllSelect(true);
        cartBO.setCartStoreMap(cartStoreMap);
        cartBO.setUserNum(userNum);
        
        return cartBO;
    }

    /**
     * 
     * @Title: getCartInfo
     * @Description: 获取购物车中商品详细信息
     * @author: Ken    
     * @date: 2015年10月12日 下午3:59:45       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    private ResponseDTO getCartInfo(String userNum,Integer cityId){
        // 返回值
        ResponseDTO response = new ResponseDTO();
        try {
            logger.info("购物车，获取详细信息，开始，参数，userNum，{}",userNum);
            // 获取购物车
            CartBO cartBO = cartBiz.getCart(userNum);
            if(cartBO == null || cartBO.getCartStoreMap() == null || cartBO.getCartStoreMap().size() == 0){
                response.setCode(ResponseCode.CartCode.CART_IS_EMPTY.v());
                response.setMsg(ResponseCode.CartCode.CART_IS_EMPTY.c());
                return response;
            }
            
            // 获取服务区域
            //UsVsLcBO usVsLcBO = sysUserInfoBiz.getUsVsLcBO(userNum);
            //Integer addrId = usVsLcBO.getVs().getVs().getAddress();
            
            
            /*
            UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(userNum);
            Integer addrId = userInfoBO.getVsAddress();
            Region[] region = regionBiz.selectRegionAndParentRegionById(addrId);
            Map <Integer,Integer>regionMap = new HashMap<Integer,Integer>();
            if(region != null && region.length > 0){
                for(int i = 0;i < region.length; i++){
                    regionMap.put(region[i].getLevel().intValue(), region[i].getId());
                }
            }*/
            // CartDTO cartDTO = this.getCartDTO(userNum, cityId, cartBO);
            CartDTO cartDTO = this.getCartDTO(cityId, cartBO);
            // Response
            response.setCode(ResponseCode.CommonCode.OK.v());
            response.setMsg(ResponseCode.CommonCode.OK.c());
            response.setData(cartDTO);
        } catch (Exception e) {
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("购物车，获取详细信息，异常，参数，userNum，{}，异常信息，{}",userNum,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    
    /**
     * 
     * @Title: getCouponGoods
     * @Description: 获取使用红包的商品 
     * @author: Ken    
     * @date: 2015年10月30日 下午1:54:40       
     * @version: 
     *
     * @param skuIds
     * @param userCouponBOList
     * @return
     *
     */
    private Map <String,Object> getCouponGoods(Map<Integer,List<Integer>> storeSkuIds,Integer couponId,String userNum){
        UserCoupon userCoupon = userCouponBiz.selectByPrimaryKey(couponId);
        if(userCoupon == null ){
            return null;
        }
        if(!userNum.equals(userCoupon.getUserNum())){
            return null;
        }
        
        if(userCoupon.getStatus() == Constants.Whether.Yes.v() || userCoupon.getIsLock() == Constants.Whether.Yes.v() || userCoupon.getIsValid() == Constants.Whether.Yes.v()){
            return null;
        }
        
        Map <String,Object> result = new HashMap<String,Object>();
        for(Map.Entry<Integer, List<Integer>> entry : storeSkuIds.entrySet()){
            Integer storeId = entry.getKey();
            List <PromotionGoodsSku> promotionGoodsSkuList = promotionGoodsSkuBiz.findPromGoodsSkusBySkuIds(userCoupon.getPromotionId(), entry.getValue());
            if(promotionGoodsSkuList == null || promotionGoodsSkuList.size() == 0){
                continue;
            }
            List <Integer> promSkuIds = new ArrayList<Integer>();
            for(PromotionGoodsSku promotionGoodsSku : promotionGoodsSkuList){
                promSkuIds.add(promotionGoodsSku.getSkuId());
            }
            result.put("promSkuIds", promSkuIds);
            result.put("storeId", storeId);
            break;
        }
        result.put("userCoupon", userCoupon);
        return result;
    }
    
    /**
     * 
     * @Title: getCartDTO
     * @Description: 获取CartDTO
     * @author: Ken    
     * @date: 2015年10月17日 下午6:00:45       
     * @version: 
     *
     * @param userNum
     * @param cityId
     * @param cartBO
     * @return
     *
     */
    private CartDTO getCartDTO(Integer cityId,CartBO cartBO){
        CartDTO cartDTO = new CartDTO();
        // 获取商品信息
        // List<Integer> skuIds = cartBiz.getCartSkuIds(userNum);
        // Integer selNum = cartBiz.getCartSelCount(userNum);
        Integer selNum = 0;
        List<Integer> skuIds = new ArrayList<Integer>();
        if(cartBO == null || cartBO.getCartStoreMap() == null ||cartBO.getCartStoreMap().size() == 0){
            return cartDTO;
        }
        for(CartStoreBO cartStoreBO :cartBO.getCartStoreMap().values()){
            for(CartSkuBO cartSkuBO : cartStoreBO.getSkuMap().values()){
                if(cartSkuBO.getIsSelect()){
                    selNum++;
                }
                skuIds.add(cartSkuBO.getSkuId());
            }
        }
        
        
        
        Map<Integer,SkuBO> skuMap = skuBiz.getSkuByIdAndRegionId(skuIds, cityId); //getSkuByIdAndRegion(skuIds, regionMap);
        
        // 购物车DTO
        
        Boolean isAllSel = cartBO.getIsAllSelect(); // 是否全部选择
        Integer totalNum = skuIds.size();
        
        
        BigDecimal saveAmount = new BigDecimal(0); // 节省金额
        BigDecimal totalAmount = new BigDecimal(0); // 总价格
        BigDecimal actualAmount = new BigDecimal(0); // 实际金额
        List<StoreDTO> stores = new ArrayList<StoreDTO>();
        
        // 计算店铺
        Map<Integer, CartStoreBO> cartStoreMap = cartBO.getCartStoreMap();
        for(Map.Entry<Integer, CartStoreBO> storeEntry: cartStoreMap.entrySet()){
            CartStoreBO cartStoreBO = storeEntry.getValue();
            // 店铺DTO
            StoreDTO storeDTO = this.getStoreDTO(skuMap, cartStoreBO);
            stores.add(storeDTO);
        }
        // 计算金额
        for(StoreDTO storeDTO : stores){
            actualAmount = actualAmount.add(storeDTO.getActualStoreAmount());
            saveAmount = saveAmount.add(storeDTO.getSaveStoreAmount());
            totalAmount = totalAmount.add(storeDTO.getTotalStoreAmount());
        }
        
        // 赋值
        cartDTO.setIsAllSel(isAllSel);
        cartDTO.setTotalNum(totalNum);
        cartDTO.setSelNum(selNum);
        cartDTO.setSaveAmount(saveAmount);
        cartDTO.setTotalAmount(totalAmount);
        cartDTO.setActualAmount(actualAmount);
        cartDTO.setStores(stores);
        return cartDTO;
    }
    
    /**
     * 
     * @Title: getStoreDTO
     * @Description: 获取店铺DTO    
     * @author: Ken    
     * @date: 2015年10月16日 下午3:29:06       
     * @version: 
     *
     * @param skuMap
     * @param cartStoreBO
     * @return
     *
     */
    private StoreDTO getStoreDTO(Map<Integer, SkuBO> skuMap, CartStoreBO cartStoreBO){
        
        StoreDTO result = new StoreDTO();
        Boolean hasProm = false;
        BigDecimal saveStoreAmount = new BigDecimal(0);
        BigDecimal totalStoreAmount = new BigDecimal(0);
        BigDecimal actualStoreAmount = new BigDecimal(0);
        
        // 店铺商品Map
        Map<Integer,CartSkuBO> storeSkuMap = cartStoreBO.getSkuMap();
        // 店铺商品skuId集合
        Set <Integer> storeSkuIds = storeSkuMap.keySet();
        List<PromSkuDTO> promSkus = this.getStorePromotionList(storeSkuIds, skuMap, storeSkuMap);
        List<SkuDTO> skus = this.getStoreNoPromotionList(storeSkuIds, skuMap, storeSkuMap);
        // 计算是否存在活动
        //PopStoreBO popStoreBO = sysPopStoreBiz.selectPopStoreById(cartStoreBO.getStoreId());
        if(promSkus != null && promSkus.size() > 0){
            hasProm = true;
        }
        // 计算金额
        if(hasProm){
            for(PromSkuDTO promSkuDTO : promSkus){
                totalStoreAmount = totalStoreAmount.add(promSkuDTO.getTotalAmount());
                saveStoreAmount = saveStoreAmount.add(promSkuDTO.getSaveAmount());
                actualStoreAmount = actualStoreAmount.add(promSkuDTO.getActualAmount());
            }
        }
        if(skus != null && skus.size() > 0){
            for(SkuDTO skuDTO : skus){
                if(skuDTO.getIsSel()){
                    totalStoreAmount = totalStoreAmount.add(skuDTO.getSkuTotalPrice());
                    actualStoreAmount = actualStoreAmount.add(skuDTO.getSkuTotalPrice());
                }
            }
        }
        
        // 赋值
        result.setStoreId(cartStoreBO.getStoreId());
        result.setStoreName(cartStoreBO.getStoreId() == -1 ? "自营" : sysPopStoreBiz.selectPopStoreById(cartStoreBO.getStoreId()).getPopStoreName()); 
        result.setHasProm(hasProm);
        result.setIsStoreSel(cartStoreBO.getIsStoreSelect());
        result.setSkus(skus);
        result.setPromSkus(promSkus);
        result.setActualStoreAmount(actualStoreAmount);
        result.setSaveStoreAmount(saveStoreAmount);
        result.setTotalStoreAmount(totalStoreAmount);
        return result;
    }
    
    
    /**
     * 
     * @Title: getSkuDTO
     * @Description: 获取商品DTO   
     * @author: Ken    
     * @date: 2015年10月16日 下午3:29:18       
     * @version: 
     *
     * @param skuId
     * @param skuBO
     * @param cartSkuBO
     * @return
     *
     */
    private SkuDTO getSkuDTO(Integer skuId,SkuBO skuBO,CartSkuBO cartSkuBO){
        if(skuBO == null || cartSkuBO == null){
            logger.error("购物车，生成SkuDTO失败，参数，skuId，{}，sku，{}，cartSkuBO，{}", skuId,skuBO,cartSkuBO);
            return null;
        }
        SkuDTO skuDTO = new SkuDTO();
        skuDTO.setSkuId(skuId);
        skuDTO.setSkuName(skuBO.getGoodsName());
        skuDTO.setSkuNum(cartSkuBO.getNum());
        skuDTO.setIsSel(cartSkuBO.getIsSelect());
        skuDTO.setMarketPrice(skuBO.getMarketPrice());
        skuDTO.setSellPrice(skuBO.getCostPrice());
        skuDTO.setLowerLimit(skuBO.getLowerLimit());
        skuDTO.setUpperLimit(skuBO.getUpperLimit());
        skuDTO.setStatus(skuBO.getStatus());
        skuDTO.setIsDelete(skuBO.getIsDelete());
        skuDTO.setStockNum(skuBO.getStockNum());
        skuDTO.setSkuTotalPrice(new BigDecimal(cartSkuBO.getNum()).multiply(skuBO.getCostPrice()));
        skuDTO.setWeight(skuBO.getWeight());
        skuDTO.setImgUrl(skuBO.getTagImgId());
        skuDTO.setGoodsId(skuBO.getGoodsId());
        return skuDTO;
    }
    
    /**
     * 
     * @Title: getRetainSkuSet
     * @Description: 计算参加活动的sku
     * @author: Ken    
     * @date: 2015年10月16日 下午3:29:40       
     * @version: 
     *
     * @param promotionOnLinePO
     * @param storeSkuIds
     * @return
     *
     */
    private Set <Integer> getRetainSkuSet(PromotionOnLinePO promotionOnLinePO,Set <Integer> storeSkuIds){
        // 店铺参加活动的skuId集合
        Set<Integer> retainSet = new HashSet<Integer>();
        // 指定活动的skuId集合
        Set <Integer> skuIdSet = promotionOnLinePO.getSkuIdSet();
        // 店铺与指定活动取交集
        retainSet.addAll(storeSkuIds);
        retainSet.retainAll(skuIdSet);
        // 没有活动商品
        if(retainSet.size() == 0){ 
            return null;
        }
        return retainSet;
    }
    
    
    /**
     * 
     * @Title: getReducePromSkuDTO
     * @Description: 获取满减活动DTO   
     * @author: Ken    
     * @date: 2015年10月16日 下午3:29:53       
     * @version: 
     *
     * @param promotionOnLinePO
     * @param retainSet
     * @param skuMap
     * @param storeSkuMap
     * @return
     *
     */
    private PromSkuDTO getReducePromSkuDTO(PromotionOnLinePO promotionOnLinePO, Set<Integer> retainSet,
            Map<Integer, SkuBO> skuMap, Map<Integer, CartSkuBO> storeSkuMap) {
        // 返回值
        PromSkuDTO promSkuDTO = new PromSkuDTO();
        List<SkuDTO> skuDTOList = new ArrayList<SkuDTO>();
        // 活动规则
        PromotionReducePO promotionReducePO = (PromotionReducePO) promotionOnLinePO.getPromotionPO();
        // 待计算参数
        Integer isMeet = Constants.Status.Disabled.v();// 是否满足活动 
        BigDecimal totalAmount = new BigDecimal(0); // 总价格
        BigDecimal saveAmount = new BigDecimal(0); // 节省金额
        BigDecimal actualAmount = new BigDecimal(0); // 实际金额
        BigDecimal targetAmount = new BigDecimal(0); // 满额
        BigDecimal reduceAmount = new BigDecimal(0); // 减额
        BigDecimal differAmount = new BigDecimal(0); // 差额
        // 计算选中商品总价格
        for (Integer skuId : retainSet) {
            // 组合商品数据
            SkuBO skuBO = skuMap.get(skuId);
            CartSkuBO cartSkuBO = storeSkuMap.get(skuId);
            SkuDTO skuDTO = this.getSkuDTO(skuId, skuBO, cartSkuBO);
            if (cartSkuBO.getIsSelect()) {
                totalAmount = totalAmount.add(skuDTO.getSkuTotalPrice());
            }
            skuDTOList.add(skuDTO);
        }
        // 计算是否触发满减
        if (promotionReducePO.getReduceType().byteValue() == Constants.PromotionReduceType.FixedReduce.v()) {
            // 定额
            targetAmount = promotionReducePO.getTargetAmount(); // 满额
            reduceAmount = promotionReducePO.getReduceAmount(); // 减额

            if (totalAmount.compareTo(targetAmount) >= 0) {
                // 满足
                actualAmount = totalAmount.subtract(reduceAmount); // 实际金额
                saveAmount = reduceAmount; // 节省金额
                isMeet = Constants.Status.Enabled.v();
            } else {
                // 不满足
                actualAmount = totalAmount; // 实际金额
                differAmount = targetAmount.subtract(totalAmount); // 距离活动相差金额
            }
        } else {
            // 阶梯
            List<PromotionReduceRegPO> reduceRegs = promotionReducePO.getReduceRegs();
            Collections.reverse(reduceRegs); // 倒序，从最高规则计算
            for (int i = 0; i < reduceRegs.size(); i++) {
                targetAmount = reduceRegs.get(i).getTargetAmount();
                reduceAmount = reduceRegs.get(i).getReduceAmount(); // 减额
                if (totalAmount.compareTo(targetAmount) >= 0) {
                    actualAmount = totalAmount.subtract(reduceAmount); // 实际金额
                    saveAmount = reduceAmount; // 节省金额
                    isMeet = Constants.Status.Enabled.v();
                    break;
                }
            }
            if (isMeet == Constants.Status.Disabled.v()) {
                actualAmount = totalAmount; // 实际金额
                differAmount = targetAmount.subtract(totalAmount); // 距离活动相差金额
            }
        }
        promSkuDTO.setPromotionId(promotionReducePO.getPromotionId());
        promSkuDTO.setPromontionName(promotionReducePO.getPromotionName());
        promSkuDTO.setEndTime(promotionReducePO.getEndTime().getTime());
        promSkuDTO.setPromotionType(Constants.PromotionType.Reduce.v());
        promSkuDTO.setIsMeet(isMeet);
        promSkuDTO.setTotalAmount(totalAmount);
        promSkuDTO.setActualAmount(actualAmount);
        promSkuDTO.setSaveAmount(saveAmount);
        promSkuDTO.setTargetAmount(targetAmount);
        promSkuDTO.setReduceAmount(reduceAmount);
        promSkuDTO.setDifferAmount(differAmount);
        promSkuDTO.setSkus(skuDTOList);
        
        return promSkuDTO;

    }
    
    /**
     * 
     * @Title: getDownPromSkuDTO
     * @Description: 获取直降活动DTO  
     * @author: Ken    
     * @date: 2015年10月16日 下午3:30:07       
     * @version: 
     *
     * @param promotionOnLinePO
     * @return
     *
     */
    private PromSkuDTO getDownPromSkuDTO(PromotionOnLinePO promotionOnLinePO, Set<Integer> retainSet,
            Map<Integer, SkuBO> skuMap, Map<Integer, CartSkuBO> storeSkuMap){
        // 返回值
        PromSkuDTO promSkuDTO = new PromSkuDTO();
        List<SkuDTO> skuDTOList = new ArrayList<SkuDTO>();
        // 活动规则
        PromotionDownPO promotionDownPO = (PromotionDownPO) promotionOnLinePO.getPromotionPO();
        // 活动商品
        Map<Integer, PromotionGoodsSku> promGoodsSkuMap = promotionOnLinePO.getSkuMap();
        // 待计算参数
        BigDecimal totalAmount = new BigDecimal(0); // 总价格
        BigDecimal saveAmount = new BigDecimal(0); // 节省金额
        BigDecimal actualAmount = new BigDecimal(0); // 实际金额
        BigDecimal targetAmount = new BigDecimal(0); // 满额
        BigDecimal reduceAmount = new BigDecimal(0); // 减额
        BigDecimal differAmount = new BigDecimal(0); // 差额
        
        // 计算选中商品总价格
        for (Integer skuId : retainSet) {
            // 组合商品数据
            SkuBO skuBO = skuMap.get(skuId);
            CartSkuBO cartSkuBO = storeSkuMap.get(skuId);
            PromotionGoodsSku promGoodsSku = promGoodsSkuMap.get(skuId);
            SkuDTO skuDTO = this.getSkuDTO(skuId, skuBO, cartSkuBO);
            skuDTO.setPromPrice(promGoodsSku.getDiscount().multiply(skuDTO.getSellPrice())); 
            skuDTO.setPromotionId(promotionDownPO.getPromotionId());
            skuDTO.setPromontionName(promotionDownPO.getPromotionName());
            skuDTO.setEndTime(promotionDownPO.getEndTime().getTime());
            skuDTO.setPromotionType(promotionDownPO.getPromotionType());
            skuDTO.setHasPromotion(Constants.Status.Enabled.v());
            if (cartSkuBO.getIsSelect()) {
                // totalAmount = totalAmount.add(skuDTO.getSkuTotalPrice());
                totalAmount = totalAmount.add(new BigDecimal(skuDTO.getSkuNum()).multiply(skuDTO.getPromPrice()));
                actualAmount = actualAmount.add(new BigDecimal(skuDTO.getSkuNum()).multiply(skuDTO.getPromPrice()));
            }
            skuDTOList.add(skuDTO);
        }
        
        //saveAmount = saveAmount.add(totalAmount.subtract(actualAmount));
        promSkuDTO.setPromotionId(promotionDownPO.getPromotionId());
        promSkuDTO.setPromontionName(promotionDownPO.getPromotionName());
        promSkuDTO.setEndTime(promotionDownPO.getEndTime().getTime());
        promSkuDTO.setPromotionType(Constants.PromotionType.Down.v());
        promSkuDTO.setIsMeet(Constants.Status.Enabled.v());
        promSkuDTO.setTotalAmount(totalAmount);
        promSkuDTO.setActualAmount(actualAmount);
        promSkuDTO.setSaveAmount(saveAmount);
        promSkuDTO.setTargetAmount(targetAmount);
        promSkuDTO.setReduceAmount(reduceAmount);
        promSkuDTO.setDifferAmount(differAmount);
        promSkuDTO.setSkus(skuDTOList);
        
        return promSkuDTO;
    }
    
    /**
     * 
     * @Title: getStoreNoPromotionList
     * @Description: 获取店铺无活动列表    
     * @author: Ken    
     * @date: 2015年10月16日 下午3:30:20       
     * @version: 
     *
     * @param storeSkuIds
     * @param skuMap
     * @param storeSkuMap
     * @return
     *
     */
    private List<SkuDTO> getStoreNoPromotionList(Set <Integer> storeSkuIds,Map<Integer, SkuBO> skuMap, Map<Integer, CartSkuBO> storeSkuMap){
        List<SkuDTO> result = new ArrayList<SkuDTO>();
        for(Integer skuId : storeSkuIds){
            SkuBO skuBO = skuMap.get(skuId);
            CartSkuBO cartSkuBO = storeSkuMap.get(skuId);
            SkuDTO skuDTO = this.getSkuDTO(skuId, skuBO, cartSkuBO);
            result.add(skuDTO);
        }
        return result;
    }
    
    /**
     * 
     * @Title: getStorePromotionList
     * @Description: 获取店铺活动列表  
     * @author: Ken    
     * @date: 2015年10月16日 下午3:30:34       
     * @version: 
     *
     * @param storeSkuIds
     * @param skuMap
     * @param storeSkuMap
     * @return
     *
     */
    private  List<PromSkuDTO> getStorePromotionList(Set <Integer> storeSkuIds,Map<Integer, SkuBO> skuMap, Map<Integer, CartSkuBO> storeSkuMap){
        // 返回值
        List<PromSkuDTO> result = new ArrayList<PromSkuDTO>();
        // 活动Map
        Map <Integer,List<PromotionOnLinePO>> promotionMap = promotionBiz.getOnlinePromotion();
        // 校验
        if(promotionMap == null || promotionMap.size() == 0){
            return null;
        }
        // 计算活动
        for(Map.Entry<Integer,List<PromotionOnLinePO>> entry:promotionMap.entrySet()){
            // 活动类型
            Integer type = entry.getKey(); 
            // 活动列表
            List<PromotionOnLinePO> promotionList = entry.getValue(); 
            
            for(PromotionOnLinePO promotionOnLinePO : promotionList){
                Set <Integer> retainSkuSet =  this.getRetainSkuSet(promotionOnLinePO, storeSkuIds);
                PromSkuDTO promSkuDTO = null;
                if(retainSkuSet == null || retainSkuSet.size() == 0){
                    continue;
                }
                // 满减
                if(type == Constants.PromotionType.Reduce.v()){
                    promSkuDTO = this.getReducePromSkuDTO(promotionOnLinePO, retainSkuSet, skuMap, storeSkuMap);
                }
                
                // 直降
                if(type == Constants.PromotionType.Down.v()){
                    promSkuDTO = this.getDownPromSkuDTO(promotionOnLinePO, retainSkuSet, skuMap, storeSkuMap);
                }
                
                // 移除参加活动的商品
                for(Integer retainSkuId : retainSkuSet){
                    storeSkuIds.remove(retainSkuId);
                }
                result.add(promSkuDTO);
            }
            
        }
        return result;
    }
    
    
    /**
     * 
     * @Title: orderCart
     * @Description: 提交订单    
     * @author: Ken    
     * @date: 2015年10月19日 上午10:21:38       
     * @version: 
     *
     * @param input
     * @return
     *
     */
    @Override
    public ResponseDTO orderCart(CartOrderInputDTO input) {
        ResponseDTO response = new ResponseDTO();
        try {
            // 店铺及店铺商品 
            Map<Integer,List<Integer>> storeSkuIds = this.getStoreSkuIds(input);
            // 下单人编号
            String userNum = input.getUserNum();
            // 红包及红包校验
            List <Integer> couponIds = input.getUsedCoupon();
            List <Map<String,Object>> storeCouponList = null;
            if(couponIds != null && couponIds.size() != 0){
                storeCouponList = this.getStoreCouponList(couponIds, storeSkuIds, userNum);
                if(storeCouponList.size() != couponIds.size()){
                    response.setCode(ResponseCode.UserCouponCode.COUPON_ERROR.v());
                    response.setMsg(ResponseCode.UserCouponCode.COUPON_ERROR.c());
                    return response;
                }
            }
            // 村站所在市
            Integer cityId = input.getCityId();
            // 构造虚拟购物车
            CartSettleInputDTO cartInfo = input.getCartInfo();
            CartBO cartBO = this.getCartSettleDetailInfo(cartInfo, userNum);
            CartBO cartBOClone = new CartBO();
            new DozerBeanMapper().map(cartBO, cartBOClone);
            CartDTO cartDTO = getCartDTO(cityId, cartBO);
            // 下单人
            UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(userNum);
            // 主订单号
            String orderNo = this.getMainOrderNo(storeSkuIds.keySet());
            // 订单金额
            BigDecimal totalAmount = new BigDecimal(0);
            BigDecimal saveAmount = new BigDecimal(0);
            BigDecimal actualAmount = new BigDecimal(0);
            BigDecimal couponAmount = new BigDecimal(0);
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderNo(orderNo);
           /* orderInfo.setTotalAmount(totalAmount);
            orderInfo.setUserNum(userNum);
            orderInfo.setCouponAmount(couponAmount);
            orderInfo.setSaveAmount(saveAmount);
            orderInfo.setActualAmount(actualAmount);*/
            orderInfo.setUserNum(userNum);
            orderInfo.setCreateTime(new Date());
            orderInfo.setOrderStatus(Constants.OrderStatus.NotPay.v().intValue());
            orderInfo.setOrderSource(input.getOrderSource());
            orderInfo.setHasSub(Constants.Whether.No.v());
            // orderInfo.setPayType(); // 1、合并支付 0、自主支付
            orderInfo.setPayWay(input.getPayType());
            orderInfo.setIsDel(Constants.Whether.No.v());
            orderInfo.setIsRecDel(Constants.Whether.No.v());
            // 订单下单人
            OrderUser orderUser = new OrderUser();
            orderUser.setOrderNo(orderNo);
            orderUser.setUserNum(userNum);
            orderUser.setUsername(userInfoBO.getSysUserRealName());
            orderUser.setUserTel(userInfoBO.getUserMobile());
            orderUser.setUserAddress(userInfoBO.getSysUserAddressDetail());
            orderUser.setCreateTime(new Date());
            // 订单收货信息
            OrderAddress orderAddress = new OrderAddress();
            orderAddress.setOrderNo(orderNo);
            orderAddress.setReceiverNum(userNum);
            orderAddress.setReceiverName(userInfoBO.getSysUserRealName());
            orderAddress.setReceiverTel(userInfoBO.getUserMobile());
            orderAddress.setReceiverAddress(userInfoBO.getSysUserAddressDetail());
            orderAddress.setVsNum(userInfoBO.getVsNum());
            orderAddress.setVsName(userInfoBO.getVsName());
            orderAddress.setVsTel(userInfoBO.getVsMobile());
            orderAddress.setVsAddress(userInfoBO.getVsAddressDetail());
            // 订单联系人
            OrderContact orderContact = new OrderContact();
            orderContact.setOrderNo(orderNo);
            orderContact.setLcNum(userInfoBO.getLcNum());
            orderContact.setVsNum(userInfoBO.getVsNum());
            orderContact.setUsNum(userInfoBO.getUserNum());
            orderContact.setCreateTime(new Date());
            // 订单工程师
            OrderEngineer orderEngineer = null;
            String engineerNum = input.getEngineerNum();
            if(StringUtils.isNotBlank(engineerNum)){
                orderEngineer = new OrderEngineer();
                SysAdminInfoBO sysAdminInfoBO = sysAdminInfoBiz.getSeInfoByUserNumOrMobile(engineerNum);
                orderEngineer.setOrderNo(orderNo);
                orderEngineer.setEngineerNum(engineerNum);
                orderEngineer.setEngineerName(sysAdminInfoBO.getName());
                orderEngineer.setEngineerPhone(sysAdminInfoBO.getaMobile());
                orderEngineer.setCreateTime(new Date());
            }
            // 订单供货商集合
            List<OrderStoreBO> orderStoreBOList = new ArrayList<OrderStoreBO>();
            // 订单活动集合
            List <OrderPromotionBO> orderPromotionList = new ArrayList<OrderPromotionBO>();
            // 订单商品集合
            List<OrderGoodsBO> orderGoodsBOList = new ArrayList<OrderGoodsBO>();
            // 订单店铺集合
            List<StoreDTO> storeDTOList = cartDTO.getStores();
            for(StoreDTO storeDTO : storeDTOList){
                OrderStoreBO orderStoreBO = new OrderStoreBO();
                List<OrderCoupon> orderCouponList = new ArrayList<OrderCoupon>();
                Integer storeId = storeDTO.getStoreId(); // 店铺ID
                BigDecimal storeTotalAmount = storeDTO.getTotalStoreAmount();
                BigDecimal storeSaveAmount = storeDTO.getSaveStoreAmount();
                BigDecimal storeActualAmount = storeDTO.getActualStoreAmount();
                BigDecimal storeCouponAmount = new BigDecimal(0);
                String orderSubNo = null;
                if(storeDTOList.size() > 1){
                    // 子订单号
                    orderSubNo = this.getOrderSubNo(storeId);
                }
                if(storeCouponList != null && storeCouponList.size() != 0){
                    Map<String,Object> storeCoupon = null;
                    for(Map<String,Object> mapStoreCoupon : storeCouponList){
                        if(storeId == Integer.parseInt(mapStoreCoupon.get("storeId").toString())){
                            storeCoupon = mapStoreCoupon;
                            if(storeCoupon != null){ // 存在店铺红包
                                UserCoupon userCoupon = (UserCoupon)storeCoupon.get("userCoupon");
                                List <Integer> promSkuIds = (List <Integer>)storeCoupon.get("promSkuIds");
                                BigDecimal couponSkuPrice = new BigDecimal(0);
                                BigDecimal usedCouponAmount = userCoupon.getCouponAmount();
                                for(Integer promSkuId : promSkuIds){
                                    List <Integer> param = new ArrayList<Integer>();
                                    param.add(promSkuId);
                                    Map<Integer,SkuBO> map = skuBiz.getSkuByIdAndRegionId(param, cityId);
                                    SkuBO skuBO = map.get(promSkuId);
                                    BigDecimal costPrice = skuBO.getCostPrice();
                                    // 数量
                                    CartStoreBO cb = cartBOClone.getCartStoreMap().get(storeId);
                                    CartSkuBO cs = cb.getSkuMap().get(promSkuId);
                                    Integer skuNum = cs.getNum();
                                    couponSkuPrice = couponSkuPrice.add(costPrice.multiply(new BigDecimal(skuNum)));
                                }
                                storeCouponAmount = storeCouponAmount.add(userCoupon.getCouponAmount());
                                if(couponSkuPrice.compareTo(usedCouponAmount) == -1){ // 红包商品 < 红包金额
                                    storeActualAmount = storeActualAmount.subtract(couponSkuPrice);
                                }else{
                                    storeActualAmount = storeActualAmount.subtract(usedCouponAmount);
                                }
                                /*storeActualAmount = storeActualAmount.subtract(userCoupon.getCouponAmount());
                                if(storeActualAmount.compareTo(new BigDecimal(0)) == -1){
                                    storeActualAmount = new BigDecimal(0);
                                }*/
                                OrderCoupon orderCoupon = new OrderCoupon();
                                orderCoupon.setUserCouponId(userCoupon.getUserCouponId());
                                orderCoupon.setCouponAmount(userCoupon.getCouponAmount());
                                orderCoupon.setCreateTime(new Date());
                                orderCouponList.add(orderCoupon);
                            }
                        }
                    }
                    
                }
                
                // 重新计算订单金额
                totalAmount = totalAmount.add(storeTotalAmount);
                saveAmount = saveAmount.add(storeSaveAmount);
                actualAmount = actualAmount.add(storeActualAmount);
                couponAmount = couponAmount.add(storeCouponAmount);
                
                // 订单供应商
                OrderStore orderStore = new OrderStore();
                orderStore.setOrderNo(orderNo);
                orderStore.setStoreId(storeId);
                orderStore.setCreateTime(new Date());
                orderStore.setCouponAmount(storeCouponAmount);
                orderStore.setActualAmount(storeActualAmount);
                orderStore.setTotalAmount(storeTotalAmount);
                orderStore.setSaveAmount(storeSaveAmount);
                orderStore.setOrderSubNo(orderSubNo);
                orderStoreBO.setOrderStore(orderStore);
                orderStoreBO.setOrderCouponList(orderCouponList);
                orderStoreBOList.add(orderStoreBO);
                
                // 订单商品
                List<SkuDTO> skus = storeDTO.getSkus();
                for(SkuDTO sku : skus){
                    OrderGoodsBO orderGoodsBO = this.getOrderGoods(sku, storeId, new Date());
                    orderGoodsBOList.add(orderGoodsBO);
                }
                List<PromSkuDTO> promSkus = storeDTO.getPromSkus();
                if(promSkus == null || promSkus.size() == 0){
                    continue;
                }
                
                for(PromSkuDTO promSku : promSkus){
                    Integer promotionId = promSku.getPromotionId();
                    if(promSku.getIsMeet() == Constants.Status.Enabled.v()){ // 触发活动
                        // 订单活动
                        OrderPromotionBO orderPromotionBO = new OrderPromotionBO();
                        orderPromotionBO.setStoreId(storeId);
                        orderPromotionBO.setPromotionId(promotionId);
                        orderPromotionBO.setPromotionType(promSku.getPromotionType());
                        orderPromotionBO.setSaveAmount(promSku.getSaveAmount());
                        orderPromotionBO.setCreateTime(new Date());
                        List <OrderGoodsBO> promGoodsBOList = new ArrayList<OrderGoodsBO>();
                        // 活动的商品
                        // 订单商品中添加参加活动商品
                        for(SkuDTO sku :promSku.getSkus()){
                            PromotionGoodsSku promotionGoodsSku = promotionBiz.getPromotionGoodsSku(promotionId, sku.getSkuId());
                            OrderGoodsBO orderGoodsBO = this.getOrderGoods(sku, storeId, new Date());
                            orderGoodsBO.setVillageExpense(promotionGoodsSku.getVillageExpense()); // 村站
                            orderGoodsBO.setPlatformExpense(promotionGoodsSku.getPlatformExpense()); // 平台
                            orderGoodsBO.setLcExpense(promotionGoodsSku.getLcExpense()); // LC
                            orderGoodsBO.setVillageExpense(promotionGoodsSku.getIntegralExpense()); // 积分
                            orderGoodsBO.setPlanExpense(promotionGoodsSku.getManpowercosts());
                            promGoodsBOList.add(orderGoodsBO);
                        }
                        orderPromotionBO.setPromGoodsBOList(promGoodsBOList);
                        orderPromotionList.add(orderPromotionBO);
                    }else{
                        // 订单商品
                        for(SkuDTO sku :promSku.getSkus()){
                            OrderGoodsBO orderGoodsBO = this.getOrderGoods(sku, storeId, new Date());
                            orderGoodsBOList.add(orderGoodsBO);
                        }
                    }
                }
                
            }
            
            orderInfo.setTotalAmount(totalAmount);
            orderInfo.setSaveAmount(saveAmount);
            orderInfo.setActualAmount(actualAmount);
            orderInfo.setCouponAmount(couponAmount);
            // 生成订单
            int orderId = orderInfoBiz.createOrder(orderInfo,orderGoodsBOList,orderStoreBOList,orderPromotionList,orderContact,orderEngineer,orderUser,orderAddress);
            
            // 锁定库存
            List<Map<String,Object>> lockQyList = new ArrayList<Map<String,Object>>();
            List <OrderGoods> orderGoodsList = orderGoodsBiz.getOrderGoodsListByOrderId(orderId);
            for(OrderGoods orderGoods:orderGoodsList){
                Map <String,Object> qyMap = new HashMap<String,Object>();
                qyMap.put("skuId", orderGoods.getSkuId());
                qyMap.put("num", orderGoods.getGoodsCount());
                qyMap.put("orderId", orderId);
                lockQyList.add(qyMap);
            }
            stocksBiz.lockQyAdd(lockQyList);
            // 记录日志
            OrderOptLog orderOptLog = new OrderOptLog();
            orderOptLog.setOrderId(orderId);
            orderOptLog.setOrderNo(orderNo);
            orderOptLog.setOrderStatus(Constants.OrderStatus.NotPay.v().intValue());
            orderOptLog.setOptNum(userNum);
            orderOptLog.setOptName(userInfoBO.getSysUserRealName());
            orderOptLog.setOptTime(new Date());
            orderOptLog.setOrderType(Constants.OrderType.MAIN.v());
            orderOptLog.setDelStatus(Constants.Status.Disabled.v());
            orderOptLogBiz.insertSelective(orderOptLog);
            // 清理购物车
            Map<Integer, List<Integer>> storeSkus = new HashMap<Integer, List<Integer>>();
            for(Map.Entry<Integer, CartStoreBO> entry :cartBOClone.getCartStoreMap().entrySet()){
                Integer storeId = entry.getKey();
                List <Integer> skuIds = new ArrayList<Integer>(entry.getValue().getSkuMap().keySet());
                storeSkus.put(storeId, skuIds);
            }
            cartBiz.delCart(userNum, storeSkus);
            // Response
            response.setCode(ResponseCode.CommonCode.OK.v());
            response.setMsg(ResponseCode.CommonCode.OK.c());
            Map <String,Object> data = new HashMap<String,Object>();
            data.put("orderId", orderId);
            data.put("orderNo", orderInfo.getOrderNo());
            data.put("orderStatus", Constants.OrderStatus.NotPay.v());
            response.setData(data);
        } catch (Exception e) {
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
           // logger.error("购物车，提交订单，异常，参数，param，{}，异常信息，{}",cartSettleInfo,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    
    
    @Override
    public ResponseDTO couponSettle(CartOrderInputDTO input) {
        ResponseDTO response = new ResponseDTO();
        try {
            // 店铺及店铺商品 
            Map<Integer,List<Integer>> storeSkuIds = this.getStoreSkuIds(input);
            // 下单人编号
            String userNum = input.getUserNum();
            // 红包及红包校验
            List <Integer> couponIds = input.getUsedCoupon();
            List <Map<String,Object>> storeCouponList = null;
            if(couponIds != null && couponIds.size() != 0){
                storeCouponList = this.getStoreCouponList(couponIds, storeSkuIds, userNum);
                if(storeCouponList.size() != couponIds.size()){
                    response.setCode(ResponseCode.UserCouponCode.COUPON_ERROR.v());
                    response.setMsg(ResponseCode.UserCouponCode.COUPON_ERROR.c());
                    return response;
                }
            }
            // 村站所在市
            Integer cityId = input.getCityId();
            // 构造虚拟购物车
            CartSettleInputDTO cartInfo = input.getCartInfo();
            CartBO cartBO = this.getCartSettleDetailInfo(cartInfo, userNum);
            CartBO cartBOClone = new CartBO();
            new DozerBeanMapper().map(cartBO, cartBOClone);
            CartDTO cartDTO = getCartDTO(cityId, cartBO);
            // 订单金额
            BigDecimal totalAmount = new BigDecimal(0);
            BigDecimal saveAmount = new BigDecimal(0);
            BigDecimal actualAmount = new BigDecimal(0);
            BigDecimal couponAmount = new BigDecimal(0);
            
            // 订单店铺集合
            List<StoreDTO> storeDTOList = cartDTO.getStores();
            for(StoreDTO storeDTO : storeDTOList){
                Integer storeId = storeDTO.getStoreId(); // 店铺ID
                BigDecimal storeTotalAmount = storeDTO.getTotalStoreAmount();
                BigDecimal storeSaveAmount = storeDTO.getSaveStoreAmount();
                BigDecimal storeActualAmount = storeDTO.getActualStoreAmount();
                BigDecimal storeCouponAmount = new BigDecimal(0);
                if(storeCouponList != null && storeCouponList.size() != 0){
                    Map<String,Object> storeCoupon = null;
                    for(Map<String,Object> mapStoreCoupon : storeCouponList){
                        if(storeId == Integer.parseInt(mapStoreCoupon.get("storeId").toString())){
                            storeCoupon = mapStoreCoupon;
                            if(storeCoupon != null){ // 存在店铺红包
                                UserCoupon userCoupon = (UserCoupon)storeCoupon.get("userCoupon");
                                List <Integer> promSkuIds = (List <Integer>)storeCoupon.get("promSkuIds");
                                BigDecimal couponSkuPrice = new BigDecimal(0);
                                BigDecimal usedCouponAmount = userCoupon.getCouponAmount();
                                for(Integer promSkuId : promSkuIds){
                                    List <Integer> param = new ArrayList<Integer>();
                                    param.add(promSkuId);
                                    Map<Integer,SkuBO> map = skuBiz.getSkuByIdAndRegionId(param, cityId);
                                    SkuBO skuBO = map.get(promSkuId);
                                    BigDecimal costPrice = skuBO.getCostPrice();
                                    // 数量
                                    CartStoreBO cb = cartBOClone.getCartStoreMap().get(storeId);
                                    CartSkuBO cs = cb.getSkuMap().get(promSkuId);
                                    Integer skuNum = cs.getNum();
                                    couponSkuPrice = couponSkuPrice.add(costPrice.multiply(new BigDecimal(skuNum)));
                                }
                                storeCouponAmount = storeCouponAmount.add(userCoupon.getCouponAmount());
                                if(couponSkuPrice.compareTo(usedCouponAmount) == -1){ // 红包商品 < 红包金额
                                    storeActualAmount = storeActualAmount.subtract(couponSkuPrice);
                                }else{
                                    storeActualAmount = storeActualAmount.subtract(usedCouponAmount);
                                }
                            }
                        }
                    }
                    
                }
                
                // 重新计算订单金额
                totalAmount = totalAmount.add(storeTotalAmount);
                saveAmount = saveAmount.add(storeSaveAmount);
                actualAmount = actualAmount.add(storeActualAmount);
                couponAmount = couponAmount.add(storeCouponAmount);
            }
            
            // Response
            response.setCode(ResponseCode.CommonCode.OK.v());
            response.setMsg(ResponseCode.CommonCode.OK.c());
            Map <String,Object> data = new HashMap<String,Object>();
            data.put("totalAmount", totalAmount);
            data.put("saveAmount", saveAmount);
            data.put("actualAmount", actualAmount);
            data.put("couponAmount", couponAmount);
            response.setData(data);
        } catch (Exception e) {
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            e.printStackTrace();
        }
        return response;
    }
    // **************************************************************************************
    
    
    // 订单sku集合 --> Map <storeId,skuIdList>
    private Map <Integer,List<Integer>> getStoreSkuIds(CartOrderInputDTO cartOrderInputDTO){
        Map <Integer,List<Integer>> result = new HashMap<>();
        List <StoreSettleInputDTO> storeSettleList = cartOrderInputDTO.getCartInfo().getStoreInfoList();
        for(StoreSettleInputDTO storeSettle : storeSettleList){
            List <Integer> skuIds = new ArrayList<Integer>();
            Integer storeId = storeSettle.getStoreId();
            List<Map<String, Integer>> skuMapList = storeSettle.getSkuInfoList();
            for(Map<String, Integer> map : skuMapList){
                Integer skuId = map.get("skuId");
                skuIds.add(skuId);
            }
            result.put(storeId, skuIds);
        }
        return result;
    }
    
    // 获取主订单号
    private String getMainOrderNo(Set <Integer> storeIds){
        String orderNo = null;
        String channel = null;
        if(storeIds.size() == 1){
            if(storeIds.contains(-1)){ // 自营
                channel = Constants.OrderNoChannelType.Self.v();
            }else{ // 入驻商
                channel = Constants.OrderNoChannelType.Other.v();
            }
        }else{
            channel = Constants.OrderNoChannelType.Mix.v(); // 自营 + 入驻商
        }
        orderNo = orderNoGenBiz.genOrderNo(Constants.OrderType.MAIN.v().toString(), channel, null, null);
        return orderNo;
    }
    
    // 获取子订单号
    private String getOrderSubNo(Integer storeId){
        String subOrderNo = null;
        String orderSubNoChannel = null;
        if(storeId == -1){
            orderSubNoChannel = Constants.OrderNoChannelType.Self.v();
        }else{
            orderSubNoChannel = Constants.OrderNoChannelType.Other.v();
        }
        subOrderNo = orderNoGenBiz.genOrderNo(Constants.OrderType.SUB.v().toString(), orderSubNoChannel, null, null);
        return subOrderNo;
    }
    
    // 获取店铺红包
    private List <Map<String,Object>> getStoreCouponList(List <Integer> couponIds,Map<Integer,List<Integer>> storeSkuIds,String userNum){
        List <Map<String,Object>> storeCouponList = new ArrayList<Map<String,Object>>();
        for(Integer couponId : couponIds){
            Map <String,Object> map = this.getCouponGoods(storeSkuIds, couponId, userNum);
            if(map == null){
                return storeCouponList;
            }
            storeCouponList.add(map);
        }
        return storeCouponList;
    }
}
