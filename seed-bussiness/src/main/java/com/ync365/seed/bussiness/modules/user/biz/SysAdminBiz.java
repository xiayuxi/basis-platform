package com.ync365.seed.bussiness.modules.user.biz;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.AdminSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.SysAdminInfoBO;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminServiceMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysAuthenInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysModuleMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysRoleMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysSeARelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserRoleMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysAdmin;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminService;
import com.ync365.seed.bussiness.modules.user.entity.SysAuthenInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysModule;
import com.ync365.seed.bussiness.modules.user.entity.SysRole;
import com.ync365.seed.bussiness.modules.user.entity.SysSeARelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysUsVsRelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysUser;
import com.ync365.seed.bussiness.modules.user.entity.SysUserRole;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.MD5Utils;
import com.ync365.seed.utils.StringUtils;

@Service
public class SysAdminBiz {

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysModuleMapper sysModuleMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    SysAdminInfoMapper sysAdminInfoMapper;

    @Autowired
    SysAdminServiceMapper sysAdminServiceMapper;

    @Autowired
    SysSeARelationshipMapper sysSeARelationshipMapper;
    
    @Autowired
    SysAuthenInfoMapper sysAuthenInfoMapper;

    /**
     * 功能描述：添加用户
     * 
     * @param record
     * @return
     */
    public int insert(SysAdmin record) {
        // 自动生成admin_num编号
        record.setAdminNum(NumGenerator.getAdminNum());
        return sysAdminMapper.insertSelective(record);
    }

    /**修改
     * @author xieang
     * 2015年10月12日
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(SysAdmin record) {
        record.setUpdateTime(Calendar.getInstance().getTime());
        if (StringUtils.isNotEmpty(record.getAdminPassword())) {
            record.setAdminPassword(MD5Utils.getMD5Str(record.getAdminPassword()));
        }
        return sysAdminMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 功能描述：查询用户by id
     * 
     * @param id
     * @return
     */
    public SysAdmin selectByNum(String adminNum) {
        return sysAdminMapper.selectByPrimaryKey(adminNum);
    }

    /**
     * 通过adminLoginName查询sysAdmin 用户 信息及 用户 sysRole权限 和 sysModule角色
     * 
     * @param adminLoginName
     * @return AdminBO
     */
    public AdminBO selectSysAdminByLoginName(String adminLoginName) {
        // 查询用户信息AdminBO
        SysAdmin sysAdmin = sysAdminMapper.selectByAdminLoginName(adminLoginName);
        AdminBO adminBO = new AdminBO();
        if (null != sysAdmin) {
            adminBO.setAdminLoginName(sysAdmin.getAdminLoginName());
            adminBO.setAdminMobile(sysAdmin.getAdminMobile());
            adminBO.setAdminNum(sysAdmin.getAdminNum());
            adminBO.setAdminPassword(sysAdmin.getAdminPassword());
            adminBO.setCreateTime(sysAdmin.getCreateTime());
            adminBO.setIsAuthentication(sysAdmin.getIsAuthentication());
            adminBO.setIsDel(sysAdmin.getIsDel());
            adminBO.setIsFrozen(sysAdmin.getIsFrozen());
            adminBO.setLastOptName(sysAdmin.getLastOptName());
            adminBO.setState(sysAdmin.getState());
            adminBO.setUpdateTime(sysAdmin.getUpdateTime());
            if (null != sysAdmin.getAdminNum() && "" != sysAdmin.getAdminNum()) {
                SysAdminInfo adminInfo = sysAdminInfoMapper.selectByPrimaryKey(sysAdmin.getAdminNum());
                if(null != adminInfo ){
                    adminBO.setAdminName(adminInfo.getName());
                    adminBO.setTelephone(adminInfo.getTelephone());
                    adminBO.setAddressDetail(adminInfo.getAddressDetail());
                    adminBO.setIdCard(adminInfo.getIdCard());
                }
                List<SysRole> listRole = sysRoleMapper.selectSysRoleByAdminNum(sysAdmin.getAdminNum());
                adminBO.setListRole(listRole);
                List<SysModule> moduleList = sysModuleMapper.selectSysModuleByAdminNum(sysAdmin.getAdminNum());
                adminBO.setModuleList(moduleList);
            }
            return adminBO;
        } else {
            return null;
        }
    }

    /**
     * 修改Admin密码  
     * @Title: changePwd
     * @Description: 修改Admin密码    ：    
     * @author: duan.zhao.qian
     * @date: 2015年9月26日 上午11:56:21       
     * @version: 
     *
     * @param adminNum 用户编号
     * @param oldPwd 原密码
     * @param newPwd 新密码
     * @return
     *
     */
    public boolean changePwd(String adminNum, String oldPwd, String newPwd) {
        boolean bool = false;
        SysAdmin admin = sysAdminMapper.selectByPrimaryKey(adminNum);
        if (admin.getAdminPassword().equals(MD5Utils.getMD5Str(oldPwd))) {
            admin.setAdminPassword(MD5Utils.getMD5Str(newPwd));
            int n = sysAdminMapper.updateByPrimaryKeySelective(admin);
            bool = n == 1;
        }
        return bool;
    }

    /**
     * 功能描述：更新用户
     * 
     * @param record
     * @return
     */
    /*
     * public int updateByNum(SysAdmin record) { return
     * sysAdminMapper.updateByNum(record); }
     */

    /**
     * 功能描述：查询所有用户。
     * 
     * @param map
     *            查询条件
     * @param pageIndex
     * @param pageSize
     * @return
     */
    /*
     * public List<SysAdmin> searchPage(Map<String, Object> parameters,int
     * pageIndex,int pageSize) { parameters.put("pageIndex", pageIndex);
     * parameters.put("pageSize", pageSize); return
     * sysAdminMapper.searchPage(parameters); }
     */

    /**
     * 功能描述：通过roleId 或 roleName 查询所有用户
     * 
     * @param parameters
     * @param pageIndex
     * @param pageSize
     * @return
     */
    /*
     * public List<SysAdmin> searchPageByRole(Map<String, Object> parameters,int
     * pageIndex,int pageSize) { parameters.put("pageIndex", pageIndex);
     * parameters.put("pageSize", pageSize); return
     * sysAdminMapper.searchPageByRole(parameters); }
     */

    /**
     * 功能描述：添加用户服务区。
     * 
     * @param adminNum
     *            用户编号
     * @param serviceDistinct
     *            服务区
     * @return
     */
    /*
     * public int insertService(String adminNum, String serviceDistinct) {
     * Map<String, Object> services = new HashMap<String, Object>();
     * 
     * if (StringUtils.isNotBlank(adminNum) &&
     * StringUtils.isNotBlank(serviceDistinct)) { services.put("adminNum",
     * adminNum); services.put("serviceDistinct", serviceDistinct); return
     * sysAdminMapper.insertService(services); } else { throw new
     * NullPointerException(); } }
     */

    /**
     * 功能描述：查询用户所属的服务区 by adminNum
     * 
     * @param adminNum
     * @return
     */
    /*
     * public SysAdminService selectServiceByNum(String adminNum) { return
     * sysAdminMapper.selectServiceByNum(adminNum); }
     */

    /**
     * 功能描述：将用户绑定分配至父类用户下 如，将SE用户分配到A+用户下。
     * 
     * @param adminNum
     *            用户id
     * @param supAdminNum
     *            父类
     * @return
     */
    /*
     * public int insertRelationship(String adminNum, String supAdminNum) {
     * Map<String, Object> relationships = new HashMap<String, Object>();
     * relationships.put("adminNum", adminNum); relationships.put("supAdminNum",
     * supAdminNum); return sysAdminMapper.insertRelationship(relationships); }
     */

    /**
     * 功能描述：查看用户绑定的关系
     * 
     * @param supAdminNum
     * @return
     */
    /*
     * public List<SysAdmin> selectRelationship(String supAdminNum) { return
     * sysAdminMapper.selectRelationship(supAdminNum); }
     */

    /**
     * 功能描述：根据用户admin_num字段删除用户。
     * 
     * @param adminNum
     * @return
     */
    /*
     * public int deleteByNum(String adminNum) { return
     * sysAdminMapper.deleteByNum(adminNum); }
     */

    /**判断用户名密码是否匹配
     * @author xieang
     * 2015年9月21日
     * @param userNum
     * @param password
     * @return
     */
    @Transactional
    public SysAdmin verificationLogin(String userNum, String password) {
        return verificationPasswordByLoginName(userNum, password);
    }

    public Boolean verificationPassword(String userNum, String password) {
        SysAdmin sysUser = sysAdminMapper.selectByPrimaryKey(userNum);
        if (sysUser != null && StringUtils.isNotBlank(sysUser.getAdminPassword()) && StringUtils.isNotBlank(password)) {
            //TODO 这里执行校验方法，加盐加密校验，规则未定，暂时简写直接对比
            if (MD5Utils.getMD5Str(password).equals(sysUser.getAdminPassword())) {
                return true;
            }
        }
        return false;
    }

    public SysAdmin verificationPasswordByLoginName(String adminLoginName, String password) {
        SysAdmin sysUser = sysAdminMapper.selectByAdminLoginName(adminLoginName);
        if (sysUser != null && StringUtils.isNotBlank(sysUser.getAdminPassword()) && StringUtils.isNotBlank(password)) {
            if (MD5Utils.getMD5Str(password).equals(sysUser.getAdminPassword())) {
                return sysUser;
            }
        }
        return new SysAdmin();
    }

    /**修改密码操作
     * @author xieang
     * 2015年9月24日
     * @param userNum
     * @param password
     * @param newPassword
     * @return
     */
    @Transactional
    public Boolean resetPassword(String userNum, String password, String newPassword) {
        if (verificationPassword(userNum, password)) {
            SysAdmin adminUser = new SysAdmin();
            adminUser.setAdminNum(userNum);
            adminUser.setAdminPassword(MD5Utils.getMD5Str(newPassword));
            int sum = sysAdminMapper.updateByPrimaryKeySelective(adminUser);
            if (sum == 1) {
                return true;
            }
        }
        return false;
    }

    /**SE/A+解绑手机号
     * @author xieang
     * 2015年9月25日
     * @param userNum
     * @return
     */
    public Boolean unbundlingMobile(String userNum) {
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setAdminNum(userNum);
        sysAdmin.setAdminMobile("");
        int sum = sysAdminMapper.updateByPrimaryKeySelective(sysAdmin);
        if (sum == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**SE/A+修改手机号
     * @author xieang
     * 2015年9月25日
     * @param userNum
     * @return
     */
    public Boolean modifyMobile(String userNum, String userMobile) {
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setAdminNum(userNum);
        sysAdmin.setAdminMobile(userMobile);
        int sum = sysAdminMapper.updateByPrimaryKeySelective(sysAdmin);
        if (sum == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取SE信息，去除密码
     * @Title: getSEByUserName
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 下午8:30:26       
     * @version: 
     *
     * @param username
     * @return
     *
     */
    public AdminBO getSEByUserName(String username) {
        AdminBO bo = new AdminBO();
        SysAdmin admin = sysAdminMapper.selectByAdminLoginName(username);
        BeanUtils.copyProperties(admin, bo);
        bo.setAdminPassword("");
        return bo;
    }

    /**
     * SE注册
     * @Title: registerAdmin
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 下午9:06:01       
     * @version: 
     *
     * @param bo
     * @return
     *
     */
    @Transactional
    public int registerAdmin(AdminBO bo) {
        SysAdmin admin = new SysAdmin();
        admin.setAdminMobile(bo.getAdminMobile());
        admin.setAdminLoginName(bo.getAdminLoginName());
        admin.setAdminNum(NumGenerator.getAdminNum());
        admin.setCreateTime(Calendar.getInstance().getTime());
        admin.setIsAuthentication(false);
        admin.setIsDel(false);
        admin.setIsFrozen(false);
        admin.setAdminPassword(MD5Utils.getMD5Str(bo.getAdminPassword()));
        admin.setState(0);
        admin.setUpdateTime(Calendar.getInstance().getTime());
        admin.setLastOptName("");
        SysUserRole userRole = new SysUserRole();
        userRole.setIsAuthentication(0);
        userRole.setUserNum(admin.getAdminNum());
        userRole.setRoleId(Constants.UserRole.SE.v());
        sysUserRoleMapper.insert(userRole);
        return sysAdminMapper.insertSelective(admin);
    }

    /**
     * 修改注册信息
     * @Title: updateAdminRegister
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月14日 下午3:22:05       
     * @version: 
     *
     * @param bo
     * @param adminNum
     * @return
     *
     */
    public int updateAdminRegister(AdminBO bo, String adminNum) {
        SysAdmin admin = new SysAdmin();
        admin.setAdminMobile(bo.getAdminMobile());
        admin.setAdminLoginName(bo.getAdminLoginName());
        admin.setAdminNum(adminNum);
        if (StringUtils.isNotEmpty(bo.getAdminPassword())) {
            admin.setAdminPassword(MD5Utils.getMD5Str(bo.getAdminPassword()));
        }
        admin.setUpdateTime(Calendar.getInstance().getTime());
        return sysAdminMapper.insertSelective(admin);
    }

    /**
     * 查询sysAdmin 列表
     * @Title: selectSysAdminListByPrimary
     * @Description: TODO    ：    
     * @author: leixc
     * @date: 2015.10.17   
     * @version: 
     *
     * @param record
     * @return
     *
     */
    public List<AdminBO> selectSysAdminListByPrimary(AdminSearchBO record) {
        List<AdminBO> list = sysAdminMapper.selectSysAdminListByPrimary(record);
        if (null != list && list.size() > 0) {
            for (AdminBO adminBO : list) {
                List<SysRole> listRole = sysRoleMapper.selectSysRoleByAdminNum(adminBO.getAdminNum());
                adminBO.setListRole(listRole);
                List<SysModule> moduleList = sysModuleMapper.selectSysModuleByAdminNum(adminBO.getAdminNum());
                adminBO.setModuleList(moduleList);
            }
        }
        return list;
    }

    /**
     * 查询sysAdmin 列表 count
     * @Title: selectSysAdminCountByPrimary
     * @Description: TODO    ：    
     * @author: leixc
     * @date: 2015.10.17   
     * @version: 
     *
     * @param record
     * @return
     *
     */
    public int selectSysAdminCountByPrimary(AdminSearchBO record) {
        return sysAdminMapper.selectSysAdminCountByPrimary(record);
    }

    /**
     * 添加管理员  se A+ 用户    
     * 后台添加方法 1：添加sysadmin  sysadminfo信息
     * 2 添加 关系表
     * @Title: insertAdminByPrimary
     * @Description: TODO    ：    
     * @author: leixc
     * @date: 2015.10.17   
     * @version: 
     *
     * @param AdminBO
     * @return
     *
     */
    @Transactional
    public int insertAdminByPrimary(AdminBO adminBO) {
        String admin_num = NumGenerator.getAdminNum();//编号
        //创建 封装sysadmin数据
        SysAdmin record = new SysAdmin();
        record.setAdminNum(admin_num);
        record.setAdminLoginName(adminBO.getAdminLoginName());
        record.setAdminMobile(adminBO.getAdminMobile());
        record.setAdminPassword(MD5Utils.getMD5Str("123456"));
        record.setState(0);
        record.setCreateTime(Calendar.getInstance().getTime());
        record.setUpdateTime(Calendar.getInstance().getTime());
        record.setIsDel(false);
        record.setIsAuthentication(false);
        record.setIsFrozen(false);
        //创建封装sysadminINfo 数据
        SysAdminInfo adminInfo = new SysAdminInfo();
        adminInfo.setAdminNum(admin_num);
        adminInfo.setTelephone(adminBO.getTelephone());
        adminInfo.setAddress(adminBO.getAddress());
        adminInfo.setAddressDetail(adminBO.getAddressDetail());
        adminInfo.setIdCard(adminBO.getIdCard());
        adminInfo.setCreateTime(Calendar.getInstance().getTime());
        adminInfo.setName(adminBO.getAdminName());
        //添加到sysadmin表
        int a = sysAdminMapper.insertSelective(record);
        //添加到sys_admin_info表信息
        int b = sysAdminInfoMapper.insertSelective(adminInfo);

        //添加用户角色对应关系
        SysUserRole sysUserRole = new SysUserRole();
        if (null != adminBO.getListRole() && adminBO.getListRole().size() > 0) {
            for (SysRole temp : adminBO.getListRole()) {
                sysUserRole.setRoleId(temp.getId());
                sysUserRole.setUserNum(admin_num);
                int bb = sysUserRoleMapper.insertSelective(sysUserRole);
            }
        }
        SysSeARelationship seAinfo = new SysSeARelationship();
        SysAdminService adminService = new SysAdminService();

        if (null != adminBO && null != adminBO.getRoleType() && "" != adminBO.getRoleType()) {
            //2 表示se 添加    3表示A+添加
            if ("2".equals(adminBO.getRoleType())) {

                seAinfo.setaNum(adminBO.getaAiAdminNum());
                seAinfo.setSeNum(admin_num);
                sysSeARelationshipMapper.insertSelective(seAinfo);

                //创建SysAdminService 封装数据 添加编号及服务区域
                List<Integer> serviceList = adminBO.getAdminServiceDistincts();
                if (serviceList != null && serviceList.size() > 0) {
                    for (Integer integer : serviceList) {
                        if (null != integer) {
                            adminService.setAdminNum(admin_num);
                            adminService.setAdminServiceDistinct(integer);
                            sysAdminServiceMapper.insertSelective(adminService);
                        }
                    }
                }
            } else if ("3".equals(adminBO.getRoleType())) {

                //创建SysAdminService 封装数据 添加编号及服务区域
                List<Integer> serviceList_a = adminBO.getAdminServiceDistinctsA();
                if (serviceList_a != null && serviceList_a.size() > 0) {
                    for (Integer integer : serviceList_a) {
                        if (null != integer) {
                            adminService.setAdminNum(admin_num);
                            adminService.setAdminServiceDistinct(integer);
                            sysAdminServiceMapper.insertSelective(adminService);
                        }
                    }
                }
            }
        }
        return a;
    }

    /**
     * by A+编号  或手机号  查询A+信息
     * @param aAdminNum
     * @return
     */
    public SysAdminInfoBO selectAiInfo(String aAdminNum) {
        return sysAdminInfoMapper.getAdminInfoByUserNumOrMobile(aAdminNum, Constants.UserRole.APlus.v());
    }

    /**用户名是否重复
     * 返回True为不重复False为重复
     * @author xieang
     * 2015年9月29日
     * @param loginName
     * @return
     */
    public Boolean validateSeLoginName(String loginName) {
        SysAdmin sysAdmin = sysAdminMapper.selectByAdminLoginName(loginName);
        if (sysAdmin != null && sysAdmin.getAdminNum() != null) {
            return false;
        }
        return true;
    }

    /**手机号是否重复
     * 返回True为不重复False为重复
     * @author xieang
     * 2015年9月29日
     * @param loginName
     * @return
     */
    public Boolean validateSeUserMobile(String userMobile) {
        SysAdmin sysAdmin = sysAdminMapper.selectByAdminLoginName(userMobile);
        if (sysAdmin != null && sysAdmin.getAdminNum() != null) {
            return false;
        }
        return true;
    }

    /**
     * 查询服务区域
     * @param adminNum
     * @return
     */
    public List<com.ync365.seed.bussiness.modules.user.entity.SysAdminService> selectServiceListByNum(String adminNum) {
        return sysAdminServiceMapper.selectServiceListByNum(adminNum);
    }

    /**
     * by seNum   查se被那个A+绑定
     * @param adminNum
     * @return
     */
    public SysAdmin selectAdminByParmartSeNum(Map<String, Object> map) {
        List<SysAdmin> list = sysAdminMapper.selectAdminByParmartSeNum(map);
        if (null != list && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Transactional
    public int updateAdminByPrimary(AdminBO record) {
        //创建 封装sysadmin数据
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setAdminNum(record.getAdminNum());
        sysAdmin.setAdminLoginName(record.getAdminLoginName());
        sysAdmin.setAdminMobile(record.getAdminMobile());
        //sysAdmin.setUpdateTime(Calendar.getInstance().getTime());

        //更新到sysadmin表
        int a = sysAdminMapper.updateByPrimaryKeySelective(sysAdmin);

        //删除服务相关区域
        int b = sysAdminServiceMapper.deleteByAdminNum(record.getAdminNum());
        //删除用户角色相应关系
        sysUserRoleMapper.deleteUserRoleByuserNum(record.getAdminNum());
        //删除se与A+关系
        sysSeARelationshipMapper.deleteByPrimaryKeyBySeNum(record.getAdminNum());

        //添加用户角色对应关系
        SysUserRole sysUserRole = new SysUserRole();
        if (null != record.getListRole() && record.getListRole().size() > 0) {
            for (SysRole temp : record.getListRole()) {
                sysUserRole.setRoleId(temp.getId());
                sysUserRole.setUserNum(record.getAdminNum());
                int bb = sysUserRoleMapper.insertSelective(sysUserRole);
            }
        }

        SysSeARelationship seAinfo = new SysSeARelationship();
        SysAdminService adminService = new SysAdminService();
        if (null != record && null != record.getRoleType() && "" != record.getRoleType()) {
            //2 表示se 添加    3表示A+添加
            if ("2".equals(record.getRoleType())) {

                seAinfo.setaNum(record.getaAiAdminNum());
                seAinfo.setSeNum(record.getAdminNum());
                sysSeARelationshipMapper.insertSelective(seAinfo);
                //创建SysAdminService 封装数据 添加编号及服务区域
                List<Integer> serviceList = record.getAdminServiceDistincts();

                if (serviceList != null && serviceList.size() > 0) {
                    for (Integer integer : serviceList) {
                        if (null != integer) {
                            adminService.setAdminNum(record.getAdminNum());
                            adminService.setAdminServiceDistinct(integer);
                            sysAdminServiceMapper.insertSelective(adminService);
                        }
                    }
                }
            } else if ("3".equals(record.getRoleType())) {
                //创建SysAdminService 封装数据 添加编号及服务区域
                List<Integer> serviceListA = record.getAdminServiceDistinctsA();
                if (serviceListA != null && serviceListA.size() > 0) {
                    for (Integer integer : serviceListA) {
                        if (null != integer) {
                            adminService.setAdminNum(record.getAdminNum());
                            adminService.setAdminServiceDistinct(integer);
                            sysAdminServiceMapper.insertSelective(adminService);
                        }
                    }
                }
            }
        }
        return a;
    }

    public int selectCountAdminByNameOrNum(Map<String, Object> map) {
        return sysAdminMapper.selectPageCount(map);
    }

    public int isAuthentication(String adminNum, Boolean authenticationState, String infoMeg, AdminBO bo) {
        //审核记录表
        SysAuthenInfo sysAuthenInfo = new SysAuthenInfo();
        sysAuthenInfo.setSource(Constants.LargeCustomerState.Other.v());
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setAdminNum(adminNum);
        if (authenticationState) {
            sysAdmin.setIsAuthentication(true);//审核通过
            sysAuthenInfo.setAuthenState(Constants.AuthenticationState.Success.v());
        } else {
            sysAdmin.setIsAuthentication(false);//审核未通过
            sysAuthenInfo.setAuthenState(Constants.AuthenticationState.Failure.v());
        }
        sysAdmin.setUpdateTime(new Date());
        int c_a = sysAdminMapper.updateByPrimaryKeySelective(sysAdmin);
        //添加记录
        if (null != adminNum && null != adminNum ) {
            List<SysRole> listRole = sysRoleMapper.selectSysRoleByAdminNum(adminNum);
            if(null != listRole && listRole.size() > 0){
                for(SysRole temp : listRole){
                    if((int)temp.getId()== (int)Constants.UserRole.SE.v()){
                        sysAuthenInfo.setSource(Constants.LargeCustomerState.SE.v());
                        break;
                    }
                    if((int)temp.getId()== (int)Constants.UserRole.APlus.v()){
                        sysAuthenInfo.setSource(Constants.LargeCustomerState.A.v());
                        break;
                    }
                    if((int)temp.getId()== (int)Constants.UserRole.ADMIN.v()){
                        sysAuthenInfo.setSource(Constants.LargeCustomerState.Admin.v());
                        break;
                    }
                }
            }
            sysAuthenInfo.setUserNum(adminNum);
            sysAuthenInfo.setAuthenReason(infoMeg);
            sysAuthenInfo.setAuthenTime(Calendar.getInstance().getTime());
            sysAuthenInfo.setAuthenAdminName(bo.getAdminName());
            sysAuthenInfo.setAuthenAdminNum(bo.getAdminNum());
            sysAuthenInfoMapper.insertSelective(sysAuthenInfo);
        }
        return c_a;
    }
}