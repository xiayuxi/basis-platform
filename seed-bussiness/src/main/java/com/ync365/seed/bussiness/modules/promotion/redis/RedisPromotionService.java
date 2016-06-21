package com.ync365.seed.bussiness.modules.promotion.redis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ync365.seed.commons.redis.JedisTemplate;
import com.ync365.seed.utils.Constants;

/**
 * 
 *     
 * @Title：RedisPromotionService  
 * @Description: 封装活动缓存操作 
 * @author: Ken        
 * @date: 2015年9月24日 下午1:53:53      
 * @version     
 *
 */
@Component("redisPromotionService")
public class RedisPromotionService {


    /**
     * Redis模板对象
     */
    @Autowired
    private JedisTemplate jedisTemplate;

    /**
     * 
     * @Title: getPromotionSetKeyByType
     * @Description: 根据促销活动类型获取Set的key
     * @author: Ken    
     * @date: 2015年9月25日 下午1:32:46       
     * @version: 
     *
     * @param type
     * @return
     *
     */
    private String getPromotionSetKeyByType(Integer type){
        String key = null;
        if (Constants.PromotionType.Reduce.v() == type) { // 满减
            key = KeyGenerator.getPromotionReduceListKey();
        } else if (Constants.PromotionType.Retrun.v() == type) { // 满返
            key = KeyGenerator.getPromotionReturnListKey();
        } else if (Constants.PromotionType.Gift.v() == type) { // 满赠
            key = KeyGenerator.getPromotionGiftListKey();
        } else if (Constants.PromotionType.Down.v() == type) { // 直降
            key = KeyGenerator.getPromotionDownListKey();
        } else if (Constants.PromotionType.Coupon.v() == type) { // 红包
            key = KeyGenerator.getPromotionCouponListKey();
        } else {
            // TODO 秒杀、套装、团购
        }
        return key;
    }
    
    /**
     * 
     * @Title: addPromotionSet
     * @Description: 将活动按类型加入到redis的set中
     * @author: Ken    
     * @date: 2015年9月24日 下午3:09:55       
     * @version: 
     *
     * @param type
     * @param promotionId
     * @return
     *
     */
    public Boolean addPromotionSet(Integer type, Integer promotionId) {
        Boolean result = false;
        String key = getPromotionSetKeyByType(type);
        if (StringUtils.isNotBlank(key)) {
            result = jedisTemplate.sadd(key, String.valueOf(promotionId));
        }
        return result;
    }
    
    /**
     * 
     * @Title: delPromotionIdFromSet
     * @Description: 删除缓存set中某个活动的ID  
     * @author: Ken    
     * @date: 2015年9月24日 下午4:00:51       
     * @version: 
     *
     * @param type
     * @param promotionId
     * @return
     *
     */
    public Long delPromotionIdFromSet(Integer type, Integer promotionId) {
        Long result = 0L;
        String key = getPromotionSetKeyByType(type);
        if (StringUtils.isNotBlank(key)) {
            result = jedisTemplate.srem(key, String.valueOf(promotionId));
        }
        return result;
    }
    
    /**
     * 
     * @Title: getPromotionSet
     * @Description: 获取指定活动类型的所有活动ID    
     * @author: Ken    
     * @date: 2015年9月24日 下午4:22:26       
     * @version: 
     *
     * @param type
     * @return
     *
     */
    public Set<Integer> getPromotionSet(Integer type) {
        String key = getPromotionSetKeyByType(type);
        Set <String> jedisResult  = jedisTemplate.smembers(key);
        if(jedisResult == null){
            return null;
        }
        Set <Integer> result = new HashSet<Integer>();
        for(String str : jedisResult){
            result.add(Integer.parseInt(str));
        }
        return result;
    }
    
    
    
    /**
     * 
     * @Title: addPromotionStr
     * @Description: 将活动添加到redis中    
     * @author: Ken    
     * @date: 2015年9月24日 下午3:09:21       
     * @version: 
     *
     * @param promotionId
     * @param promotionStr
     * @param expireTime
     *
     */
    public void addPromotionStr(Integer promotionId, String promotionStr,Long expireTime){
        String key = KeyGenerator.getPromotionRegKey(promotionId);
        jedisTemplate.set(key, promotionStr);
        jedisTemplate.expireAt(key, expireTime);
    }
    
    /**
     * 
     * @Title: delPromotionStr
     * @Description: 删除redis中指定活动
     * @author: Ken    
     * @date: 2015年9月24日 下午4:03:11       
     * @version: 
     *
     * @param promotionId
     *
     */
    public void delPromotionStr(Integer promotionId){
        String key = KeyGenerator.getPromotionRegKey(promotionId);
        jedisTemplate.del(key);
    }
    
    /**
     * 
     * @Title: getPromotionStr
     * @Description: 获取指定活动的Json串
     * @author: Ken    
     * @date: 2015年9月24日 下午4:25:57       
     * @version: 
     *
     * @param promotionId
     * @return
     *
     */
    public String getPromotionStr(Integer promotionId){
        String key = KeyGenerator.getPromotionRegKey(promotionId);
        return jedisTemplate.get(key);
    }
    
    /**
     * 
     * @Title: addPromotionSkus
     * @Description: 将活动商品添加到缓存中 
     * @author: Ken    
     * @date: 2015年9月24日 下午3:28:35       
     * @version: 
     *
     * @param promotionId
     * @param skuIds
     *
     */
    public void addPromotionSkus(Integer promotionId,List <Integer> skuIds){
        String key = KeyGenerator.getPromotionSkusSetKey(promotionId);
        for(Integer skuId : skuIds){
            jedisTemplate.sadd(key, String.valueOf(skuId));
        }
    }
    
    /**
     * 
     * @Title: delPromotionSkus
     * @Description: 删除活动的所有活动商品
     * @author: Ken    
     * @date: 2015年9月24日 下午4:31:52       
     * @version: 
     *
     * @param promotionId
     * @return
     *
     */
    public Boolean delPromotionSkus(Integer promotionId){
        String key = KeyGenerator.getPromotionSkusSetKey(promotionId);
        return jedisTemplate.del(key);
    }
    
    /**
     * 
     * @Title: getPromotionSkus
     * @Description: 获取活动的skuId集合
     * @author: Ken    
     * @date: 2015年9月25日 下午5:04:20       
     * @version: 
     *
     * @param promotionId
     * @return
     *
     */
    public Set<Integer> getPromotionSkus(Integer promotionId){
        String key = KeyGenerator.getPromotionSkusSetKey(promotionId);
        Set <String> jedisResult  = jedisTemplate.smembers(key);
        if(jedisResult == null){
            return null;
        }
        Set <Integer> result = new HashSet<Integer>();
        for(String str : jedisResult){
            result.add(Integer.parseInt(str));
        }
        return result;
    } 
    
    // TODO 获取指定活动商品列表
    
    /**
     * 
     * @Title: addPromotionGoodsSkuStr
     * @Description: 添加指定活动指定商品详情  
     * @author: Ken    
     * @date: 2015年9月25日 下午2:07:02       
     * @version: 
     *
     * @param promotionId
     * @param skuId
     * @param goodsSkuStr
     * @param expireTime
     *
     */
    public void addPromotionGoodsSkuStr(Integer promotionId, Integer skuId, String goodsSkuStr,Long expireTime){
        String key = KeyGenerator.getPromotionSkuKey(promotionId, skuId);
        jedisTemplate.set(key, goodsSkuStr);
        jedisTemplate.expireAt(key, expireTime);
    }
    
    
    /**
     * 
     * @Title: getPromotionGoodsSkuStr
     * @Description: 获取指定活动指定商品详情
     * @author: Ken    
     * @date: 2015年9月25日 下午2:08:13       
     * @version: 
     *
     * @param promotionId
     * @param skuId
     * @return
     *
     */
    public String getPromotionGoodsSkuStr(Integer promotionId, Integer skuId){
        String key = KeyGenerator.getPromotionSkuKey(promotionId, skuId);
        return jedisTemplate.get(key);
    }
    
    /**
     * 
     * @Title: delPromotionGoodsSkuStr
     * @Description: 删除指定活动的指定商品    
     * @author: Ken    
     * @date: 2015年9月25日 下午2:10:16       
     * @version: 
     *
     * @param promotionId
     * @param skuId
     *
     */
    public void delPromotionSkuStr(Integer promotionId, Integer skuId){
        String key = KeyGenerator.getPromotionSkuKey(promotionId, skuId);
        jedisTemplate.del(key);
    }
    
}
