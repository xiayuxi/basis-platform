package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysLargeCustomerPropertyInfoMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerPropertyInfo;

@Service
public class SysLargeCustomerPropertyInfoBiz {
    @Autowired
    SysLargeCustomerPropertyInfoMapper sysLargeCustomerPropertyInfoMapper;

    public int deleteByPrimaryKey(Integer id) {
        SysLargeCustomerPropertyInfo info = selectByPrimaryKey(id);
        info.setIsDel(true);
        return sysLargeCustomerPropertyInfoMapper.updateByPrimaryKeySelective(info);
    }

    public int insertSelective(SysLargeCustomerPropertyInfo record) {
        return sysLargeCustomerPropertyInfoMapper.insertSelective(record);
    }

    public SysLargeCustomerPropertyInfo selectByPrimaryKey(Integer id) {
        return sysLargeCustomerPropertyInfoMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysLargeCustomerPropertyInfo record) {
        return sysLargeCustomerPropertyInfoMapper.updateByPrimaryKeySelective(record);
    }

    public List<SysLargeCustomerPropertyInfo> selectAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("isDel", false);
        return sysLargeCustomerPropertyInfoMapper.selectPageByMap(map);
    }
}
