package com.ync365.seed.bussiness.modules.promotion.po;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ync365.seed.bussiness.modules.common.po.PageFilter;

/** 直降活动列表的查询条件 */
public final class PromotionSearch extends PageFilter {

	private static final long serialVersionUID = 1L;

	/** 活动类型 */
	private int promotionType;

	/** 活动名称 查询关键字 */
	private String key;

	/** 活动状态 ，默认为-1:不限*/
	private int status = -1;

	/** 活动查询起始时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/** 活动查询结束时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	/** 是否删除，默认为false:未删除 */
	public boolean isDel = false;

	public final int getPromotionType() {
		return promotionType;
	}

	public final void setPromotionType(int promotionType) {
		this.promotionType = promotionType;
	}

	public final String getKey() {
		return key;
	}

	public final void setKey(String key) {
		this.key = key;
	}

	public final int getStatus() {
		return status;
	}

	public final void setStatus(int status) {
		this.status = status;
	}

	public final Date getStartTime() {
		return startTime;
	}

	public final void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public final Date getEndTime() {
		return endTime;
	}

	public final void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public final boolean isDel() {
		return isDel;
	}

	public final void setDel(boolean isDel) {
		this.isDel = isDel;
	}

	public static final long getSerialversionuid() {
		return serialVersionUID;
	}

}
