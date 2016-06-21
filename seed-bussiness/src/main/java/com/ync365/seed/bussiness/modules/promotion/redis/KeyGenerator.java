package com.ync365.seed.bussiness.modules.promotion.redis;

import org.apache.commons.lang3.StringUtils;

public class KeyGenerator {

    private final static String split = ":";

    private final static String simple = "simple";
    
    // private final static String hash = "hash";

    // private final static String list = "list";

    private final static String set = "set";
    
    // private final static String sortset = "sortset";
    
    /** 活动 */
    private final static String promotion = "promotion";
    
    /** 团购 */
    // private final static String groupbuy = "groupbuy";
    
    /** 红包 */
    private final static String coupon = "coupon";
    
    /** 满减 */
    private final static String reduce = "reduce";
    
    /** 满赠 */
    private final static String gift = "gift";
    
    /** 满返 */
    private final static String reurn = "return";
    
    /** 直降 */
    private final static String down = "down";
    
    /** 活动商品 */
    private final static String sku = "sku";
    
    /** 活动规则 */
    private final static String reg = "reg";
    
    
    /** 活动分类 */
	public static String getPromotionReduceListKey() {
		return getRedisKey(set, promotion,reduce);
	}
	
	public static String getPromotionGiftListKey() {
        return getRedisKey(set, promotion,gift);
    }

	public static String getPromotionDownListKey() {
	    return getRedisKey(set, promotion,down);
	}
	
	public static String getPromotionReturnListKey() {
        return getRedisKey(set, promotion,reurn);
    }
	
	public static String getPromotionCouponListKey() {
        return getRedisKey(set, promotion,coupon);
    }
	
	/** 活动商品List */
	public static String getPromotionSkusSetKey(Integer promotionId) {
        return getRedisKey(set, promotion,sku,String.valueOf(promotionId));
    }
	
	/** 活动规则 */
    public static String getPromotionRegKey(Integer promotionId) {
        return getRedisKey(simple, promotion,reg,String.valueOf(promotionId));
    }
    
    /** 活动商品 */
    public static String getPromotionSkuKey(Integer promotionId,Integer skuId) {
        return getRedisKey(simple, promotion,sku,String.valueOf(promotionId),String.valueOf(skuId));
    }
    
	private static String getRedisKey(String... name) {
		StringBuffer buffer = new StringBuffer();
		for (String str : name) {
			if (StringUtils.isNotBlank(str)) {
				buffer.append(str);
				buffer.append(split);
			}
		}
		buffer.deleteCharAt(buffer.lastIndexOf(split));
		return buffer.toString();
	}
	
}
