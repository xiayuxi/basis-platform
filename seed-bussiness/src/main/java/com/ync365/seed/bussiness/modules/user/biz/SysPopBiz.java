package com.ync365.seed.bussiness.modules.user.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.bo.PopLoginBO;
import com.ync365.seed.bussiness.modules.user.bo.PopLoginReturnBO;
import com.ync365.seed.bussiness.modules.user.bo.PopRegisterBO;
import com.ync365.seed.bussiness.modules.user.dao.SysPopMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysPop;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;
import com.ync365.seed.utils.MD5Utils;
import com.ync365.seed.utils.StringUtils;

@Service
public class SysPopBiz {
    @Autowired
    SysPopMapper sysPopMapper;
    @Autowired
    SysPopStoreMapper sysPopStoreMapper;

    /**
     * pop登录
     * @Title: popLogin
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月14日 下午5:36:33       
     * @version: 
     *
     * @param popLoginBO
     * @return
     *
     */
    public PopLoginReturnBO popLogin(PopLoginBO popLoginBO) {
        if (!StringUtils.isNoneEmpty(popLoginBO.getPopLoginName())) {
            return null;
        }
        PopLoginReturnBO bo = new PopLoginReturnBO();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popLoginName", popLoginBO.getPopLoginName());
        List<SysPop> list = sysPopMapper.selectPageByMap(map);
        if (list == null || list.size() == 0) {
            map.remove("popLoginName");
            map.put("popMobile", popLoginBO.getPopLoginName());
            list = sysPopMapper.selectPageByMap(map);
        }
        if (list != null && list.size() > 0) {
            if (MD5Utils.getMD5Str(popLoginBO.getPopPassword()).equals(list.get(0).getPopPassword())) {
                BeanUtils.copyProperties(list.get(0), bo);
                bo.setPopPassword("");
            }
            SysPop sysPop = new SysPop();
            sysPop.setBeforeLoginTime(new Date());
            sysPop.setPopNum(bo.getPopNum());
            sysPopMapper.updateByPrimaryKeySelective(sysPop);
        }
        return bo;
    }

    /**
     * 修改注册信息
     * @Title: updatePopRegister
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月14日 下午5:45:49       
     * @version: 
     *
     * @param bo
     * @param popNum
     * @return
     *
     */
    public int updatePopRegister(PopRegisterBO bo, String popNum) {
        SysPop pop = new SysPop();
        pop.setPopNum(popNum);
        if(StringUtils.isNotBlank(bo.getPopMobile())){
        	SysPop sysPop = sysPopMapper.selectByPopNum(popNum);
        	if(sysPop!=null&&StringUtils.isNotBlank(sysPop.getPopStoreNum())){
        		SysPopStore sysPopStore = new SysPopStore();
            	sysPopStore.setPopStoreMobile(bo.getPopMobile());
            	sysPopStore.setPopStoreNum(sysPop.getPopStoreNum());
            	sysPopStoreMapper.updateByPrimaryKeySelective(sysPopStore);
        	}
        }
        pop.setPopLoginName(bo.getPopLoginName());
        pop.setPopMobile(bo.getPopMobile());
        pop.setSafeScore(bo.getSafeScore());
        if (StringUtils.isNotEmpty(bo.getPopPassword())) {
            pop.setPopPassword(MD5Utils.getMD5Str(bo.getPopPassword()));
        }
        return sysPopMapper.updateByPrimaryKeySelective(pop);
    }
    
	/**
	 * 功能描述：重置码为 123456
	 * @author liukai
	 * @param popStoreNum
	 */
	public int updatePasswordByPopStoreNum(String popStoreNum) {
		String popPassword = MD5Utils.getMD5Str("123456");
		SysPop sysPop = new SysPop();
		sysPop.setPopPassword(popPassword);
		sysPop.setPopStoreNum(popStoreNum);
		sysPop.setSafeScore(0);
		return sysPopMapper.updateByPrimaryKeySelective(sysPop);
	}
	
	/**
	 * 功能描述：根据 popStoreNum 查询 供应商
	 * @author liukai
	 * @param popStoreNum
	 * @return
	 */
	public SysPop selectByPopStoreNum(String popStoreNum) {
		return sysPopMapper.selectByPopStoreNum(popStoreNum);
	}
	
//	/**
//	 * 功能描述：根据sysPop查询对象
//	 * @author liukai
//	 * @param sysPop
//	 * @return
//	 */
//	public SysPop selectBySysPop(SysPop sysPop) {
//		return sysPopMapper.selectBySysPop(sysPop);
//	}

}
