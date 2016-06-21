package com.ync365.seed.bussiness.modules.order.bo;

import java.io.Serializable;

public class CartSkuBO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer storeId; // 店铺ID
    private Integer skuId; // skuID
    private Integer num; // 数量
    private Boolean isSelect = true; // 是否选择

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }
    
    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "CartSkuPO [storeId=" + storeId + ", skuId=" + skuId + ", num=" + num + ", isSelect=" + isSelect + "]";
    }
    


}
