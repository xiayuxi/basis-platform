package com.ync365.seed.bussiness.modules.order.bo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CartBO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String userNum;
    
    private Boolean isAllSelect = true;
    
    private Map <Integer,CartStoreBO> cartStoreMap = new HashMap<Integer,CartStoreBO>();
    
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Boolean getIsAllSelect() {
        return isAllSelect;
    }

    public void setIsAllSelect(Boolean isAllSelect) {
        this.isAllSelect = isAllSelect;
    }

    public Map<Integer, CartStoreBO> getCartStoreMap() {
        return cartStoreMap;
    }

    public void setCartStoreMap(Map<Integer, CartStoreBO> cartStoreMap) {
        this.cartStoreMap = cartStoreMap;
    }
    

}
