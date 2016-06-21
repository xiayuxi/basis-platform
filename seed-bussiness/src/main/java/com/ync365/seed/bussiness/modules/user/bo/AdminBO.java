package com.ync365.seed.bussiness.modules.user.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ync365.seed.bussiness.modules.user.entity.SysModule;
import com.ync365.seed.bussiness.modules.user.entity.SysRole;

/**
 * @author xieang
 * 2015年9月24日
 */
public class AdminBO implements Serializable {

    /**
     * 用户编号
     */
    private String adminNum;

    /**
     * 登录名
     */
    private String adminLoginName;

    /**
     * 用户密码
     */
    private String adminPassword;

    /**
     * 手机号
     */
    private String adminMobile;

    /**
     * 保留字段
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 最后一次操作人
     */
    private String lastOptName;

    /**
     * 是否删除
     */
    private Boolean isDel;

    /**
     * 是否冻结
     */
    private Boolean isFrozen;

    /**
     * 是否审核
     */
    private Boolean isAuthentication;

    /**
     * 角色集合
     */
    private List<SysRole> listRole;

    /**
     * admin 用户名
     */
    private String adminName;

    //    /**
    //     * 角色名称
    //     */
    //    private String roleName;

    /**
     * 权限集合
     */
    private List<SysModule> moduleList;
    private List<Integer> adminServiceDistincts;
    
    /**
     * A+添加服务区域集合
     */
    private List<Integer> adminServiceDistinctsA;

    public List<Integer> getAdminServiceDistincts() {
        return adminServiceDistincts;
    }

    public void setAdminServiceDistincts(List<Integer> adminServiceDistincts) {
        this.adminServiceDistincts = adminServiceDistincts;
    }

    /**
     * 选择添加角色 类型    用于 用户后台判断 （1 后台管理员及自定义后台管理员      2 se  3  a+）
     */
    private String roleType;

    /**
     * 座机号码
     */
    private String telephone;

    /**
     * 地址详情
     */
    private String addressDetail;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * a+编号
     */
    private String aAiAdminNum;

    /**
     * 服务区域
     */
    private Integer address;

    //    /**
    //     * 添加角色 的 角色的id
    //     */
    //    private Integer roleIdd;

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum == null ? null : adminNum.trim();
    }

    public String getAdminLoginName() {
        return adminLoginName;
    }

    public void setAdminLoginName(String adminLoginName) {
        this.adminLoginName = adminLoginName == null ? null : adminLoginName.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getAdminMobile() {
        return adminMobile;
    }

    public void setAdminMobile(String adminMobile) {
        this.adminMobile = adminMobile == null ? null : adminMobile.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastOptName() {
        return lastOptName;
    }

    public void setLastOptName(String lastOptName) {
        this.lastOptName = lastOptName == null ? null : lastOptName.trim();
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public Boolean getIsAuthentication() {
        return isAuthentication;
    }

    public void setIsAuthentication(Boolean isAuthentication) {
        this.isAuthentication = isAuthentication;
    }

    public List<SysRole> getListRole() {
        return listRole;
    }

    public void setListRole(List<SysRole> listRole) {
        this.listRole = listRole;
    }

    public List<SysModule> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<SysModule> moduleList) {
        this.moduleList = moduleList;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getRoleName() {
        SysRole role = new SysRole();
        role.setId(Integer.MAX_VALUE);
        if (listRole != null && listRole.size() > 0) {
            for (SysRole r : listRole) {
                if (role.getId() > r.getId()) {
                    role = r;
                }
            }
        }
        return role.getRoleName();
    }

    //    public void setRoleName(String roleName) {
    //        this.roleName = roleName;
    //    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getaAiAdminNum() {
        return aAiAdminNum;
    }

    public void setaAiAdminNum(String aAiAdminNum) {
        this.aAiAdminNum = aAiAdminNum;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Integer getRoleIdd() {
        SysRole role = new SysRole();
        role.setId(Integer.MAX_VALUE);
        if (listRole != null && listRole.size() > 0) {
            for (SysRole r : listRole) {
                if (role.getId() > r.getId()) {
                    role = r;
                }
            }
        }
        return role.getId() == Integer.MAX_VALUE ? null : role.getId();
    }

    public List<Integer> getAdminServiceDistinctsA() {
        return adminServiceDistinctsA;
    }

    public void setAdminServiceDistinctsA(List<Integer> adminServiceDistinctsA) {
        this.adminServiceDistinctsA = adminServiceDistinctsA;
    }

    //    public void setRoleIdd(Integer roleIdd) {
    //        this.roleIdd = roleIdd;
    //    }

    
}