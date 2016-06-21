package com.ync365.seed.dto.order.input;

public class UserCouponDTO {

	/**
	 * 用户红包主键
	 */
	private Integer userCouponId;
	
	/**
	 * 红包金额
	 */
	private Integer amount ;

	public Integer getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Integer userCouponId) {
		this.userCouponId = userCouponId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}
