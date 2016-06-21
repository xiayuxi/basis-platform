package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysUserBankMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysUserBank;

@Service
public class SysUserBankBiz {
    @Autowired
    private SysUserBankMapper sysUserBankMapper;

    /**
     * 添加用户银行卡信息
     * @Title: addUserBankInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月10日 上午11:52:59       
     * @version: 
     *
     * @param userbank
     * @return
     *
     */
    public int addUserBankInfo(SysUserBank userbank) {
        return sysUserBankMapper.insertSelective(userbank);
    }

    /**
     * 修改用户银行卡信息
     * @Title: updateUserBankInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月10日 上午11:53:24       
     * @version: 
     *
     * @param userbank
     * @return
     *
     */
    public int updateUserBankInfo(SysUserBank userbank) {
        return sysUserBankMapper.updateByPrimaryKeySelective(userbank);
    }

    /**
     * 获取用户银行卡信息
     * @Title: getUserBankInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月10日 上午11:53:42       
     * @version: 
     *
     * @param usernum
     * @return
     *
     */
    public List<SysUserBank> getUserBankInfo(String usernum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", usernum);
        List<SysUserBank> list = sysUserBankMapper.selectPageByMap(map);
        return list;
    }
}
