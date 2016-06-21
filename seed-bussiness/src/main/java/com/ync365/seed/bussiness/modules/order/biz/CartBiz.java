package com.ync365.seed.bussiness.modules.order.biz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.goods.bo.SkuBO;
import com.ync365.seed.bussiness.modules.order.bo.CartBO;
import com.ync365.seed.bussiness.modules.order.bo.CartSkuBO;
import com.ync365.seed.bussiness.modules.order.bo.CartStoreBO;
import com.ync365.seed.bussiness.modules.order.redis.RedisCartService;
import com.ync365.seed.utils.Constants;
/**
 *     
 * @Title：CartBiz  
 * @Description: 购物车Biz   
 * @author: Ken        
 * @date: 2015年9月22日 下午4:02:33      
 * @version 1.0 
 *
 */
@Service
public class CartBiz {
    
    private static final Logger logger = LoggerFactory.getLogger(CartBiz.class);
    
    private static final Integer cartCount = 18;
    
    @Autowired
    private RedisCartService redisCartService;
    
    /**
     * 
     * @Title: addCart
     * @Description: 向购物车中添加商品
     * @author: Ken    
     * @date: 2015年9月28日 下午2:05:14       
     * @version: 
     *
     * @param userNum
     * @param cartSkuBO
     *
     */
    public void addCart(String userNum,Integer storeId,Integer skuId, Integer num){
        CartBO cartBO = redisCartService.getCart(userNum);
        CartSkuBO cartSkuBO = new CartSkuBO();
        cartSkuBO.setStoreId(storeId);
        cartSkuBO.setSkuId(skuId);
        cartSkuBO.setNum(num);
        if(cartBO != null){ // 有购物车
            Map<Integer, CartStoreBO> cartStoreMap = cartBO.getCartStoreMap();
            CartStoreBO cartStore = cartStoreMap.get(storeId);
            if(cartStore != null){ // 有店铺
                Map<Integer, CartSkuBO> skuMap = cartStore.getSkuMap();
                CartSkuBO existCartSkuBO = skuMap.get(skuId);
                if(existCartSkuBO != null){ // 有Sku
                    existCartSkuBO.setNum(existCartSkuBO.getNum() + num);
                }else{ // 无Sku
                    skuMap.put(skuId, cartSkuBO);
                }
            }else{ // 无店铺
                CartStoreBO newCartStore = new CartStoreBO();
                newCartStore.setStoreId(storeId);
                Map<Integer, CartSkuBO> skuMap = new HashMap<Integer, CartSkuBO>();
                skuMap.put(skuId, cartSkuBO);
                newCartStore.setSkuMap(skuMap);
                cartStoreMap.put(storeId, newCartStore);
            }
        }else{ // 无购物车，创建购物车，添加商品
            cartBO = new CartBO();
            cartBO.setUserNum(userNum);
            Map<Integer, CartStoreBO> cartStoreMap = new HashMap<Integer, CartStoreBO>();
            CartStoreBO newCartStore = new CartStoreBO();
            newCartStore.setStoreId(storeId);
            Map<Integer, CartSkuBO> skuMap = new HashMap<Integer, CartSkuBO>();
            skuMap.put(skuId, cartSkuBO);
            newCartStore.setSkuMap(skuMap);
            cartStoreMap.put(storeId, newCartStore);
            cartBO.setCartStoreMap(cartStoreMap);
        }
        redisCartService.addCart(cartBO);
    }
    
    public Integer getCartSkuCountBySkuId(String userNum,Integer storeId,Integer skuId){
        Integer result = 0;
        CartBO cartBO = redisCartService.getCart(userNum);
        if(cartBO == null || cartBO.getCartStoreMap() == null || cartBO.getCartStoreMap().size() == 0){
            return result;
        }
        
        Map<Integer, CartStoreBO> storeMap = cartBO.getCartStoreMap();
        CartStoreBO cartStoreBO = storeMap.get(storeId);
        if(cartStoreBO == null || cartStoreBO.getSkuMap() == null || cartStoreBO.getSkuMap().size() == 0){
            return 0;
        }
        CartSkuBO cartSkuBO = cartStoreBO.getSkuMap().get(skuId);
        if(cartSkuBO == null){
            return 0;
        }
        return cartSkuBO.getNum();
    }
    
    
    /**
     * @Title: delCart
     * @Description: 删除购物车中的商品（支持删除1个、多个）
     * @author: Ken    
     * @date: 2015年10月8日 下午5:53:34       
     * @version: 
     *
     * @param userNum
     * @param storeSkus
     *
     */
    public void delCart(String userNum,Map<Integer, List<Integer>> storeSkus){
        CartBO cartBO = redisCartService.getCart(userNum);
        
        if(cartBO == null || cartBO.getCartStoreMap() == null || cartBO.getCartStoreMap().size() == 0){
            logger.info("购物车，删除商品，异常，userNum，{}，原因，{}", userNum,"购物车不存在或没有店铺");
            return;
        }
        
        Map<Integer, CartStoreBO> storeMap = cartBO.getCartStoreMap(); 
        
        for (Map.Entry<Integer, List<Integer>> entry : storeSkus.entrySet()) {  
            Integer storeId = entry.getKey();
            List <Integer> skuIds = entry.getValue();
            CartStoreBO storeBO  = storeMap.get(storeId);
            if(storeBO == null || storeBO.getSkuMap() == null || storeBO.getSkuMap().size() == 0){
                logger.info("购物车，删除商品，异常，userNum，{}，storeId，{}，原因，{}", userNum,storeId,"店铺商品不存在");
                continue;
            }
            Map<Integer,CartSkuBO> skuMap = storeBO.getSkuMap();
            // 删除商品
            for(Integer skuId : skuIds){
                skuMap.remove(skuId);
            }
            // 删除商品后，如果店铺没有商品，删除店铺
            if(skuMap.size() == 0){
                storeMap.remove(storeId);
            }
        }  
        
        if(storeMap.size() == 0){
            redisCartService.delCart(userNum);
            return;
        }
        
        // 重新计算选择状态
        for(Map.Entry<Integer, CartStoreBO> entry : storeMap.entrySet()){
            CartStoreBO storeBO = entry.getValue();
            // 商品选择状态改变是否触发店铺状态改变
            Boolean storeSel = true;
            for(Map.Entry<Integer, CartSkuBO> skuEntry : storeBO.getSkuMap().entrySet()){
                if(!skuEntry.getValue().getIsSelect()){
                    storeSel = false;
                    break;
                }
            }
            storeBO.setIsStoreSelect(storeSel);
        }
        
        // 店铺选择状态改变是否触发全选状态改变
        Boolean isAllSel = true;
        for(Map.Entry<Integer, CartStoreBO> entry : storeMap.entrySet()){
            if(!entry.getValue().getIsStoreSelect()){
                isAllSel = false;
                break;
            }
        }
        cartBO.setIsAllSelect(isAllSel);
        redisCartService.addCart(cartBO);
    }
    
    /**
     * 
     * @Title: selCartAll
     * @Description: 全部选择    
     * @author: Ken    
     * @date: 2015年10月9日 上午10:32:13       
     * @version: 
     *
     * @param userNum
     * @param isSelect
     *
     */
    public void selCartAll(String userNum, Integer isSelect){
        CartBO cartBO = redisCartService.getCart(userNum);
        if(cartBO == null || cartBO.getCartStoreMap() == null || cartBO.getCartStoreMap().size() == 0){
            logger.error("购物车，选择或取消选择全部，异常，userNum，{}，原因，{}", userNum,"购物车不存在或没有店铺");
            return;
        }
        cartBO.setIsAllSelect(isSelect == Constants.Status.Enabled.v() ? true : false);
        Map<Integer, CartStoreBO> storeMap = cartBO.getCartStoreMap();
        for (Map.Entry<Integer, CartStoreBO> storeMapEntry : storeMap.entrySet()) {  
            CartStoreBO storeBO = storeMapEntry.getValue();
            if(storeBO == null || storeBO.getSkuMap() == null || storeBO.getSkuMap().size() == 0){
                continue;
            }
            storeBO.setIsStoreSelect(isSelect == Constants.Status.Enabled.v() ? true : false);
            for(Map.Entry<Integer, CartSkuBO> skuMapEntry : storeBO.getSkuMap().entrySet()){
                CartSkuBO skuBO = skuMapEntry.getValue();
                if(skuBO == null){
                    continue;
                }
                skuBO.setIsSelect(isSelect == Constants.Status.Enabled.v() ? true : false);
            }
        }
        redisCartService.addCart(cartBO);
    }
    
    /**
     * 
     * @Title: selCartStore
     * @Description: 选择、取消选择店铺    
     * @author: Ken    
     * @date: 2015年10月9日 上午10:53:48       
     * @version: 
     *
     * @param userNum
     * @param storeId
     * @param isSelect
     *
     */
    public void selCartStore(String userNum, Integer storeId, Integer isSelect){
        CartBO cartBO = redisCartService.getCart(userNum);
        if(cartBO == null || cartBO.getCartStoreMap() == null || cartBO.getCartStoreMap().size() == 0){
            logger.error("购物车，选择或取消选择店铺，异常，userNum，{}，原因，{}", userNum,"购物车不存在或没有店铺");
            return;
        }
        Map<Integer, CartStoreBO> storeMap = cartBO.getCartStoreMap();
        CartStoreBO storeBO = storeMap.get(storeId);
        if(storeBO == null || storeBO.getSkuMap() == null || storeBO.getSkuMap().size() == 0){
            logger.error("购物车，选择或取消选择店铺，异常，userNum，{}，storeId，{}，原因，{}", userNum,storeId,"店铺不存在或没有商品");
            return;
        }
        storeBO.setIsStoreSelect(isSelect == Constants.Status.Enabled.v() ? true : false);
        for(Map.Entry<Integer, CartSkuBO> entry : storeBO.getSkuMap().entrySet()){
            CartSkuBO skuBO = entry.getValue();
            if(skuBO == null){
                continue;
            }
            skuBO.setIsSelect(isSelect == Constants.Status.Enabled.v() ? true : false);
        }
        // 店铺选择状态改变是否触发全选状态改变
        Boolean isAllSel = true;
        for(Map.Entry<Integer, CartStoreBO> entry : storeMap.entrySet()){
            if(!entry.getValue().getIsStoreSelect()){
                isAllSel = false;
                break;
            }
        }
        cartBO.setIsAllSelect(isAllSel);
        redisCartService.addCart(cartBO);
    }
    
    /**
     * 
     * @Title: selCartSku
     * @Description: 选择、取消选择商品    
     * @author: Ken    
     * @date: 2015年10月9日 上午10:12:52       
     * @version: 
     *
     * @param userNum
     * @param storeId
     * @param skuId
     * @param isSelect
     * @return
     *
     */
    public void selCartSku(String userNum, Integer storeId, Integer skuId, Integer isSelect){
        CartBO cartBO = redisCartService.getCart(userNum);
        if(cartBO == null || cartBO.getCartStoreMap() == null || cartBO.getCartStoreMap().size() == 0){
            logger.error("购物车，选择或取消选择商品，异常，userNum，{}，原因，{}", userNum,"购物车不存在或没有店铺");
            return;
        }
        Map<Integer, CartStoreBO> storeMap = cartBO.getCartStoreMap();
        CartStoreBO storeBO = storeMap.get(storeId);
        if(storeBO == null || storeBO.getSkuMap() == null || storeBO.getSkuMap().size() == 0){
            logger.error("购物车，选择或取消选择商品，异常，userNum，{}，storeId，{}，原因，{}", userNum,storeId,"店铺不存在或没有商品");
            return;
        }
        CartSkuBO skuBO = storeBO.getSkuMap().get(skuId);
        if(skuBO == null){
            logger.error("购物车，选择或取消选择商品，异常，userNum，{}，storeId，{}，skuId，{}，原因，{}", userNum,storeId,skuId,"商品不存在");
            return;
        }
        skuBO.setIsSelect(isSelect == Constants.Status.Enabled.v() ? true : false);
        // 商品选择状态改变是否触发店铺状态改变
        Boolean storeSel = true;
        for(Map.Entry<Integer, CartSkuBO> entry : storeBO.getSkuMap().entrySet()){
            if(!entry.getValue().getIsSelect()){
                storeSel = false;
                break;
            }
        }
        storeBO.setIsStoreSelect(storeSel);
        
        // 店铺选择状态改变是否触发全选状态改变
        Boolean isAllSel = true;
        for(Map.Entry<Integer, CartStoreBO> entry : storeMap.entrySet()){
            if(!entry.getValue().getIsStoreSelect()){
                isAllSel = false;
                break;
            }
        }
        cartBO.setIsAllSelect(isAllSel);
        redisCartService.addCart(cartBO);
    }
    
    /**
     * 
     * @Title: numCart
     * @Description: 修改购物车中商品数量
     * @author: Ken    
     * @date: 2015年10月9日 下午2:55:25       
     * @version: 
     *
     * @param userNum
     * @param storeId
     * @param skuId
     * @param num
     *
     */
    public void numCart(String userNum, Integer storeId, Integer skuId, Integer num){
        CartBO cartBO = redisCartService.getCart(userNum);
        if(cartBO == null || cartBO.getCartStoreMap() == null || cartBO.getCartStoreMap().size() == 0){
            logger.error("购物车，修改商品数量，异常，userNum，{}，原因，{}", userNum,"购物车不存在或没有店铺");
            return;
        }
        Map<Integer, CartStoreBO> storeMap = cartBO.getCartStoreMap();
        CartStoreBO storeBO = storeMap.get(storeId);
        if(storeBO == null || storeBO.getSkuMap() == null || storeBO.getSkuMap().size() == 0){
            logger.error("购物车，修改商品数量，异常，userNum，{}，storeId，{}，原因，{}", userNum,storeId,"店铺不存在或没有商品");
            return;
        }
        CartSkuBO skuBO = storeBO.getSkuMap().get(skuId);
        if(skuBO == null){
            logger.error("购物车，修改商品数量，异常，userNum，{}，storeId，{}，skuId，{}，原因，{}", userNum,storeId,skuId,"商品不存在");
            return;
        }
        skuBO.setNum(num);
        redisCartService.addCart(cartBO);
    }
    
    
    /**
     * 
     * @Title: queryCart
     * @Description: 查看购物车    
     * @author: Ken    
     * @date: 2015年9月28日 下午2:12:39       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    public CartBO getCart(String userNum){
        return redisCartService.getCart(userNum);
    } 
    
    /**
     * 
     * @Title: countCart
     * @Description: 计算购物车中商品数量
     * @author: Ken    
     * @date: 2015年10月9日 下午6:27:29       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    public Integer countCart(String userNum){
        int num = 0;
        CartBO cartBO = redisCartService.getCart(userNum);
        if(cartBO == null || cartBO.getCartStoreMap() == null || cartBO.getCartStoreMap().size() == 0){
            return num;
        }
        Map<Integer, CartStoreBO> storeMap = cartBO.getCartStoreMap();
        for(Map.Entry<Integer, CartStoreBO> entry : storeMap.entrySet()){
            CartStoreBO storeBO = entry.getValue();
            if(storeBO == null || storeBO.getSkuMap() == null || storeBO.getSkuMap().size() == 0){
                continue;
            }
            num += storeBO.getSkuMap().size();
        }
        
        return num;
    }
    
    /**
     * 
     * @Title: getCartSkuIds
     * @Description: 获取购物车中商品skuId集合  
     * @author: Ken    
     * @date: 2015年10月16日 下午1:40:44       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    public List<Integer> getCartSkuIds(String userNum){
        List <Integer> result = new ArrayList<Integer>();
        CartBO cartBO = this.getCart(userNum);
        if(cartBO == null ||cartBO.getCartStoreMap() == null || cartBO.getCartStoreMap().size() == 0){
            return null;
        }
        Collection <CartStoreBO> collection = cartBO.getCartStoreMap().values();
        for(CartStoreBO cartStoreBO : collection){
            Set <Integer> set = cartStoreBO.getSkuMap().keySet();
            for(Integer skuId : set){
                result.add(skuId);
            }
        }
        return result;
    }

    /**
     * 
     * @Title: getCartCount
     * @Description: 获取购物车中商品数量     
     * @author: Ken    
     * @date: 2015年10月16日 下午3:07:19       
     * @version: 
     *
     * @return
     *
     */
    public static Integer getCartCount() {
        return cartCount;
    }
    
    /**
     * 
     * @Title: getCartSelCount
     * @Description: 获取购物车中选中商品数量    
     * @author: Ken    
     * @date: 2015年10月16日 下午3:07:29       
     * @version: 
     *
     * @return
     *
     */
    public Integer getCartSelCount(String userNum) {
        Integer result = 0;
        CartBO cartBO = this.getCart(userNum);
        if(cartBO == null ||cartBO.getCartStoreMap() == null || cartBO.getCartStoreMap().size() == 0){
            return result;
        }
        Collection <CartStoreBO> cartStores = cartBO.getCartStoreMap().values();
        for(CartStoreBO cartStoreBO : cartStores){
            Collection <CartSkuBO>  cartSkus= cartStoreBO.getSkuMap().values();
            for(CartSkuBO cartSkuBO : cartSkus){
                if(cartSkuBO.getIsSelect()){
                    result += 1;
                }
            }
        }
        return result;
    }
    
}
