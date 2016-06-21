package com.ync365.seed.dto.order.input;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 *     
 * @Title：CartSettleInputDTO
 * @Description: 购物车提交订单那输入信息
 * @author: Ken        
 * @date: 2015年10月18日 上午11:58:21      
 * @version     
 *
 */
public class CartOrderInputDTO {

    @NotNull
    private String userNum; // 用户编号
    
    private String engineerNum; // 服务工程师编号、可为空
     
    private Integer cityId; // 村站所在城市ID
    
    private CartSettleInputDTO cartInfo; // 购物车需要购买商品信息
    
    private List<Integer> usedCoupon = new ArrayList<Integer>(); // 使用红包列表
    
    private BigDecimal balance; // 使用的余额，本期不做
    
    private Integer payType; // 支付方式
    
    private Integer orderSource; // 订单来源

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getEngineerNum() {
        return engineerNum;
    }

    public void setEngineerNum(String engineerNum) {
        this.engineerNum = engineerNum;
    }

    public CartSettleInputDTO getCartInfo() {
        return cartInfo;
    }

    public void setCartInfo(CartSettleInputDTO cartInfo) {
        this.cartInfo = cartInfo;
    }

    public List<Integer> getUsedCoupon() {
        return usedCoupon;
    }

    public void setUsedCoupon(List<Integer> usedCoupon) {
        this.usedCoupon = usedCoupon;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    @Override
    public String toString() {
        return "CartOrderInputDTO [userNum=" + userNum + ", engineerNum=" + engineerNum + ", cityId=" + cityId
                + ", cartInfo=" + cartInfo + ", usedCoupon=" + usedCoupon + ", balance=" + balance + ", payType="
                + payType + ", orderSource=" + orderSource + "]";
    }
    
}
