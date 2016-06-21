package com.ync365.seed.bussiness.modules.user.biz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.biz.UserCouponBiz;
import com.ync365.seed.bussiness.modules.user.bo.UserBO;
import com.ync365.seed.bussiness.modules.user.bo.UserRegisterBO;
import com.ync365.seed.bussiness.modules.user.dao.SysLcInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserRecommendedMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysVsInfoMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysLcInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysUser;
import com.ync365.seed.bussiness.modules.user.entity.SysUserRecommended;
import com.ync365.seed.bussiness.modules.user.entity.SysVsInfo;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.MD5Utils;

@Service
public class SysUserBiz {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRecommendedMapper sysUserRecommendedMapper;
    @Autowired
    private SysVsInfoMapper sysVsInfoMapper;
    @Autowired
    private SysLcInfoMapper sysLcInfoMapper;
    @Autowired
    UserCouponBiz userCouponBiz;

    /**
     * 通过 userNum 删除
     * @param userNum
     * @return
     */
    public int deleteByPrimaryKeyUserNum(String userNum) {
        return sysUserMapper.deleteByPrimaryKeyUserNum(userNum);
    }

    /**保存用户
     * 
     * 2015年9月7日
     * @param record
     * @return
     */
    @Transactional
    public int insertSysUser(UserRegisterBO bo) {
        if (!validateLoginName(bo.getUserName()) || !validateUserMobile(bo.getUserMobile())) {
            return 0;
        }
        SysUser record = new SysUser();
        record.setUserNum(NumGenerator.getUserNum());
        record.setUserLoginName(bo.getUserName());
        record.setUserMobile(bo.getUserMobile());
        if (StringUtils.isNotEmpty(bo.getUserPassword())) {
            record.setUserPassword(MD5Utils.getMD5Str(bo.getUserPassword()));
        }
        record.setState(0);
        record.setLastOptName("");
        record.setRegisterLocation(bo.getRegisterLocation());
        record.setCreateTime(Calendar.getInstance().getTime());
        record.setUpdateTime(Calendar.getInstance().getTime());
        record.setIsDel(false);
        record.setIsAuthentication(false);
        record.setIsFrozen(false);
        record.setSafeScore(bo.getSafeScore());
        if (StringUtils.isNotBlank(bo.getRecommendedNum())) {
            SysUserRecommended recommended = new SysUserRecommended();
            recommended.setUserNum(record.getUserNum());
            recommended.setRecommendedNum(bo.getRecommendedNum());
            sysUserRecommendedMapper.insertSelective(recommended);
        }
        if (com.ync365.seed.utils.StringUtils.isNotEmpty(bo.getUserRole())) {
            switch (bo.getUserRole().toLowerCase()) {
            case "vs":
                SysVsInfo vsinfo = new SysVsInfo();
                vsinfo.setUserNum(record.getUserNum());
                vsinfo.setAuthenticationState(0);
                vsinfo.setCreateTime(Calendar.getInstance().getTime());
                sysVsInfoMapper.insertSelective(vsinfo);
                break;
            case "lc":
                SysLcInfo lcinfo = new SysLcInfo();
                lcinfo.setUserNum(record.getUserNum());
                lcinfo.setAuthenticationState(0);
                lcinfo.setCreateTime(Calendar.getInstance().getTime());
                sysLcInfoMapper.insertSelective(lcinfo);
                break;
            }
        }
        int n = sysUserMapper.insertSelective(record);
        if (n > 0) {
            final String userNum = record.getUserNum();
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        userCouponBiz.registerGrantCoupon(userNum);
                    } catch (Exception e) {
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
        return n;
    }

    /**根据主键选择性修改用户信息
     * 2015年9月7日
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(SysUser record) {
        if (com.ync365.seed.utils.StringUtils.isNotEmpty(record.getUserPassword())) {
            record.setUserPassword(MD5Utils.getMD5Str(record.getUserPassword()));
        }
        record.setUpdateTime(Calendar.getInstance().getTime());
        return sysUserMapper.updateByPrimaryKeySelective(record);
    }

    /**保存user信息和详细信息
     * @author xieang
     * 2015年9月14日
     * @param sysUser
     */
    /*@Transactional
    public void updateSysUserAndInfoByUserNum(SysUser sysUser){
    	sysUserMapper.updateByPrimaryKeySelective(sysUser);
    	SysUserInfo sysUserInfo = sysUser.getSysUserInfo();
    	if(StringUtils.isBlank(sysUserInfo.getUserNum())){
    		sysUserInfo.setUserNum(sysUser.getUserNum());
    		sysUserInfoMapper.insert(sysUserInfo);
    	}else{
    		sysUserInfo.setUserNum(sysUser.getUserNum());
    		sysUserInfoMapper.updateByPrimaryKeySelective(sysUserInfo);
    	}
    	
    	
    }*/

    /**
     * 多功能查询 所有List<SysPopStoreMapper> 分页添加pageIndex,pageSize参数
     * 
     * @param map
     * @return
     */
    public List<SysUser> selectPageByMap(Map<String, Object> map) {
        return sysUserMapper.selectPageByMap(map);
    }

    /**
     * 条件获取数量
     * 
     * @param map
     * @return
     */
    public int selectPageCount(Map<String, Object> map) {
        return sysUserMapper.selectPageCount(map);
    }

    /**根据用户编号查询用户详细信息
     * 2015年9月14日
     * @param userNum
     * @return
     */
    public SysUser selectByPrimaryKeyUserNum(String userNum) {
        return sysUserMapper.selectByPrimaryKeyUserNum(userNum);
    }

    /**判断用户名密码是否匹配
     * @author xieang
     * 2015年9月21日
     * @param userNum
     * @param password
     * @return
     */
    @Transactional
    public SysUser verificationLogin(String userLoginName, String password) {
        return verificationPasswordByLoginName(userLoginName, password);
    }

    public Boolean verificationPassword(String userNum, String password) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKeyUserNum(userNum);
        if (sysUser != null && StringUtils.isNotBlank(sysUser.getUserPassword()) && StringUtils.isNotBlank(password)) {
            if (MD5Utils.getMD5Str(password).equals(sysUser.getUserPassword())) {
                return true;
            }
        }
        return false;
    }

    public SysUser verificationPasswordByLoginName(String userLoginName, String password) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKeyUserLoginName(userLoginName);
        if (sysUser != null && StringUtils.isNotBlank(sysUser.getUserPassword()) && StringUtils.isNotBlank(password)) {
            if (MD5Utils.getMD5Str(password).equals(sysUser.getUserPassword())) {
                return sysUser;
            }
        }
        return new SysUser();
    }

    public SysUser verificationPasswordByMobile(String userMobile, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userMobile", userMobile);
        List<SysUser> list = sysUserMapper.selectPageByMap(map);
        if (list != null && list.size() > 0 && StringUtils.isNotBlank(list.get(0).getUserPassword())
                && StringUtils.isNotBlank(password)) {
            if (MD5Utils.getMD5Str(password).equals(list.get(0).getUserPassword())) {
                return list.get(0);
            }
        }
        return new SysUser();
    }

    /**US/VS/LC修改密码操作
     * @author xieang
     * 2015年9月23日
     * @param userNum
     * @param password
     * @param newPassword
     * @return
     */
    @Transactional
    public Boolean resetPassword(String userNum, String password, String newPassword, Integer safeScore) {
        if (verificationPassword(userNum, password)) {
            SysUser sysUser = new SysUser();
            sysUser.setUserNum(userNum);
            sysUser.setUserPassword(MD5Utils.getMD5Str(newPassword));
            sysUser.setSafeScore(safeScore);
            sysUser.setUpdateTime(Calendar.getInstance().getTime());
            int sum = sysUserMapper.updateByPrimaryKeySelective(sysUser);
            if (sum == 1) {
                return true;
            }
        }
        return false;
    }

    /**US/VS/LC解绑手机号
     * @author xieang
     * 2015年9月25日
     * @param userNum
     * @return
     */
    public Boolean unbundlingMobile(String userNum) {
        SysUser sysUser = new SysUser();
        sysUser.setUserNum(userNum);
        sysUser.setUserMobile("");
        sysUser.setUpdateTime(Calendar.getInstance().getTime());
        int sum = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (sum == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**US/VS/LC修改手机号
     * @author xieang
     * 2015年9月25日
     * @param userNum
     * @return
     */
    public Boolean modifyMobile(String userNum, String userMobile) {
        SysUser sysUser = new SysUser();
        sysUser.setUserNum(userNum);
        sysUser.setUserMobile(userMobile);
        sysUser.setUpdateTime(Calendar.getInstance().getTime());
        int sum = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (sum == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**用户名是否重复
     * 返回True为不重复False为重复
     * @author xieang
     * 2015年9月29日
     * @param loginName
     * @return
     */
    public Boolean validateLoginName(String loginName) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKeyUserLoginName(loginName);
        if (sysUser != null && sysUser.getUserNum() != null) {
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
    public Boolean validateUserMobile(String userMobile) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userMobile", userMobile);
        int sum = sysUserMapper.selectPageCount(map);
        if (sum > 0) {
            return false;
        }
        return true;
    }

    /**
     * 判断用户角色
     * @Title: getUserRole
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 下午3:52:30       
     * @version: 
     *
     * @param userNum
     * @return 角色ID集合
     *
     */
    public List<Integer> getUserRole(String userNum) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(Constants.UserRole.US.v());
        SysVsInfo vsinfo = sysVsInfoMapper.selectByPrimaryKeyUserNum(userNum);
        if (vsinfo != null && vsinfo.getAuthenticationState() != null && vsinfo.getAuthenticationState() == 1) {
            list.add(Constants.UserRole.VS.v());
        }
        SysLcInfo lcinfo = sysLcInfoMapper.selectByPrimaryKey(userNum);
        if (lcinfo != null && lcinfo.getAuthenticationState() != null && lcinfo.getAuthenticationState() == 1) {
            list.add(Constants.UserRole.LC.v());
        }
        return list;
    }

    /**
     * 用户名获取User信息，去除密码
     * @Title: getUserByUserName
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 下午8:28:42       
     * @version: 
     *
     * @param username
     * @return
     *
     */
    public UserBO getUserByUserName(String username) {
        UserBO bo = new UserBO();
        SysUser user = sysUserMapper.selectByPrimaryKeyUserLoginName(username);
        BeanUtils.copyProperties(user, bo);
        bo.setUserPassword("");
        return bo;
    }

    /**
     * 修改注册信息
     * @Title: updateUserRegister
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月14日 下午2:56:53       
     * @version: 
     *
     * @param bo
     * @return
     *
     */
    public int updateUserRegister(UserRegisterBO bo, String userNum) {
        if (com.ync365.seed.utils.StringUtils.isEmpty(userNum.trim())) {
            return 0;
        }
        SysUser record = new SysUser();
        record.setUserNum(userNum);
        record.setUserLoginName(bo.getUserName());
        record.setUserMobile(bo.getUserMobile());
        record.setUpdateTime(Calendar.getInstance().getTime());
        if (StringUtils.isNotEmpty(bo.getUserPassword())) {
            record.setUserPassword(MD5Utils.getMD5Str(bo.getUserPassword()));
            record.setSafeScore(bo.getSafeScore());
        }
        record.setRegisterLocation(bo.getRegisterLocation());
        record.setUpdateTime(Calendar.getInstance().getTime());
        return sysUserMapper.updateByPrimaryKeySelective(record);
    }
}