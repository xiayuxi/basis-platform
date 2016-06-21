package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGoodsSku;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionPO;

public class PromotionOnLinePO {
    private PromotionPO promotionPO;
    private Set <Integer> skuIdSet= new HashSet<Integer>();
    private Map <Integer,PromotionGoodsSku> skuMap = new HashMap<Integer,PromotionGoodsSku>();

    public PromotionPO getPromotionPO() {
        return promotionPO;
    }

    public void setPromotionPO(PromotionPO promotionPO) {
        this.promotionPO = promotionPO;
    }

    public Map<Integer, PromotionGoodsSku> getSkuMap() {
        return skuMap;
    }

    public void setSkuMap(Map<Integer, PromotionGoodsSku> skuMap) {
        this.skuMap = skuMap;
    }

    public Set<Integer> getSkuIdSet() {
        return skuIdSet;
    }

    public void setSkuIdSet(Set<Integer> skuIdSet) {
        this.skuIdSet = skuIdSet;
    }

}
