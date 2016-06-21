package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.user.bo.PopInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.PopInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.dao.SysPopInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysPopMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysPop;
import com.ync365.seed.bussiness.modules.user.entity.SysPopInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;
import com.ync365.seed.utils.StringUtils;

@Service
public class SysPopInfoBiz {
    @Autowired
    private SysPopInfoMapper sysPopInfoMapper;
    @Autowired
    private SysPopMapper sysPopMapper;
    @Autowired
    private SysPopStoreMapper sysPopStoreMapper;

    public SysPopInfo getPopInfo(String popNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popNum", popNum);
        List<SysPopInfo> list = sysPopInfoMapper.selectPageByMap(map);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Transactional
    public int updatePopInfo(PopInfoBO bo) {
        SysPop sysPop = sysPopMapper.selectByPopNum(bo.getPopNum());
        if (sysPop != null && StringUtils.isNotBlank(sysPop.getPopStoreNum())
                && StringUtils.isNotBlank(bo.getPopRealName())) {
            SysPopStore sysPopStore = new SysPopStore();
            sysPopStore.setPopStoreNum(sysPop.getPopStoreNum());
            sysPopStore.setPopStoreContact(bo.getPopRealName());
            sysPopStoreMapper.updateByPrimaryKeySelective(sysPopStore);
        }
        SysPopInfo info = new SysPopInfo();
        BeanUtils.copyProperties(bo, info);
        if (StringUtils.isNotEmpty(bo.getPopLoginName())) {
            sysPop.setPopLoginName(bo.getPopLoginName());
            sysPopMapper.updateByPrimaryKeySelective(sysPop);
        }
        return sysPopInfoMapper.updateByPrimaryKeySelective(info);
    }

    public PopInfoBO getPopInfoBOByPopNum(String popNum) {
        PopInfoSearchBO searchBo = new PopInfoSearchBO();
        searchBo.setPopNum(popNum);
        List<PopInfoBO> list = sysPopInfoMapper.selectPopInfoByPrimary(searchBo);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    public PopInfoBO getPopInfoByLoginName(String popLoginName) {
        PopInfoSearchBO searchBo = new PopInfoSearchBO();
        searchBo.setPopLoginName(popLoginName);
        List<PopInfoBO> list = sysPopInfoMapper.selectPopInfoByPrimary(searchBo);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    public PopInfoBO getPopInfoByMobile(String mobile) {
        PopInfoSearchBO searchBo = new PopInfoSearchBO();
        searchBo.setPopMobile(mobile);
        List<PopInfoBO> list = sysPopInfoMapper.selectPopInfoByPrimary(searchBo);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }
}
