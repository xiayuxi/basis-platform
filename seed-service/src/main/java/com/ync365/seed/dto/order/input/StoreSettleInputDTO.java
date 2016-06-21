package com.ync365.seed.dto.order.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *     
 * @Title：StoreSettleInputDTO  
 * @Description: 结算店铺输入信息
 * @author: Ken        
 * @date: 2015年10月18日 上午11:57:47      
 * @version     
 *
 */
public class StoreSettleInputDTO {
    private Integer storeId;
    private List<Map <String,Integer>> skuInfoList = new ArrayList<Map <String,Integer>>();
    public Integer getStoreId() {
        return storeId;
    }
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    public List<Map<String, Integer>> getSkuInfoList() {
        return skuInfoList;
    }
    public void setSkuInfoList(List<Map<String, Integer>> skuInfoList) {
        this.skuInfoList = skuInfoList;
    }
    @Override
    public String toString() {
        return "StoreSettleInputDTO [storeId=" + storeId + ", skuInfoList=" + skuInfoList + "]";
    }
}
