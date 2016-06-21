package com.ync365.seed.dto.order.input;

import java.util.ArrayList;
import java.util.List;
/**
 *     
 * @Title：CartSettleInputDTO
 * @Description: 结算购物车输入信息
 * @author: Ken        
 * @date: 2015年10月18日 上午11:58:21      
 * @version     
 *
 */
public class CartSettleInputDTO {

    private List <StoreSettleInputDTO> storeInfoList = new ArrayList<StoreSettleInputDTO>();

    public List<StoreSettleInputDTO> getStoreInfoList() {
        return storeInfoList;
    }

    public void setStoreInfoList(List<StoreSettleInputDTO> storeInfoList) {
        this.storeInfoList = storeInfoList;
    }

    @Override
    public String toString() {
        return "CartSettleInputDTO [storeInfoList=" + storeInfoList + "]";
    }

}
