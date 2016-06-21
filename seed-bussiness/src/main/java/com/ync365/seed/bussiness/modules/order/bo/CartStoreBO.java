package com.ync365.seed.bussiness.modules.order.bo;

import java.util.HashMap;
import java.util.Map;

public class CartStoreBO {
    private Integer storeId;
    private Boolean isStoreSelect = true;
    private Map<Integer,CartSkuBO> skuMap = new HashMap<Integer,CartSkuBO>();
    public Integer getStoreId() {
        return storeId;
    }
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    
    public Boolean getIsStoreSelect() {
        return isStoreSelect;
    }
    public void setIsStoreSelect(Boolean isStoreSelect) {
        this.isStoreSelect = isStoreSelect;
    }
    public Map<Integer, CartSkuBO> getSkuMap() {
        return skuMap;
    }
    public void setSkuMap(Map<Integer, CartSkuBO> skuMap) {
        this.skuMap = skuMap;
    }
    @Override
    public String toString() {
        return "CartStorePO [storeId=" + storeId + ", isStoreSelect=" + isStoreSelect + ", skuMap=" + skuMap + "]";
    }
}
