package com.ync365.seed.bussiness.modules.goods.bo;

import java.util.Date;
import java.util.List;

import com.ync365.seed.bussiness.modules.goods.entity.FeatureValue;

public class FeatureBO {
	/**
	 * 属性主键
	 */
	private Integer featureId;

	/**
	 * 类别
	 */
	private Integer categoryId;
	
	/**
	 * 属性名
	 */
	private String name;

	/**
	 * 是否允许别名
	 */
	private Short isAlias;

	/**
	 * 是否枚举属性
	 */
	private Short isEnum;

	/**
	 * 是否输入属性
	 * 0是不输入，可以选择的
	 * 1是需要输入的
	 */
	private Short isInput;

	/**
	 * 是否关键属性
	 */
	private Short isKey;

	/**
	 * 是否销售属性
	 */
	private Short isSales;

	/**
	 * 是否搜索字段
	 */
	private Short isQuery;

	/**
	 * 是否必须
	 */
	private Short isMust;

	/**
	 * 是否规格
	 * 1为规格，0为非规格
	 */
	private Short isSpec;

	/**
	 * 是否多选
	 * 1单选，0多选
	 */
	private Short isMultiselect;

	/**
	 * 状态：1启用；2停用
	 */
	private Short status;

	/**
	 * 排序
	 */
	private Integer seq;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 操作者
	 */
	private Integer optUser;

	/**
	 * 操作时间
	 */
	private Date optDate;

	/**
	 * 操作终端
	 */
	private String optTerm;
	
	/**
	 * 可选值
	 */
	//TODO
	private String optionals;

	//默认值为0,1为已删除
	private int isDel;
	
	private String categoryName;

	/**
	 * 属性值
	 */
	//TODO
	private List<FeatureValue> featureValues;

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Short getIsAlias() {
		return isAlias;
	}

	public void setIsAlias(Short isAlias) {
		this.isAlias = isAlias;
	}

	public Short getIsEnum() {
		return isEnum;
	}

	public void setIsEnum(Short isEnum) {
		this.isEnum = isEnum;
	}

	public Short getIsInput() {
		return isInput;
	}

	public void setIsInput(Short isInput) {
		this.isInput = isInput;
	}

	public Short getIsKey() {
		return isKey;
	}

	public void setIsKey(Short isKey) {
		this.isKey = isKey;
	}

	public Short getIsSales() {
		return isSales;
	}

	public void setIsSales(Short isSales) {
		this.isSales = isSales;
	}

	public Short getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(Short isQuery) {
		this.isQuery = isQuery;
	}

	public Short getIsMust() {
		return isMust;
	}

	public void setIsMust(Short isMust) {
		this.isMust = isMust;
	}

	public Short getIsSpec() {
		return isSpec;
	}

	public void setIsSpec(Short isSpec) {
		this.isSpec = isSpec;
	}

	public Short getIsMultiselect() {
		return isMultiselect;
	}

	public void setIsMultiselect(Short isMultiselect) {
		this.isMultiselect = isMultiselect;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOptUser() {
		return optUser;
	}

	public void setOptUser(Integer optUser) {
		this.optUser = optUser;
	}

	public Date getOptDate() {
		return optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}

	public String getOptTerm() {
		return optTerm;
	}

	public void setOptTerm(String optTerm) {
		this.optTerm = optTerm == null ? null : optTerm.trim();
	}

	public String getOptionals() {
		return optionals;
	}

	public void setOptionals(String optionals) {
		this.optionals = optionals;
	}

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
    
    public List<FeatureValue> getFeatureValues() {
        return featureValues;
    }

    public void setFeatureValues(List<FeatureValue> featureValues) {
        this.featureValues = featureValues;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}