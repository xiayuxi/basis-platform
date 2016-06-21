package com.ync365.seed.bussiness.modules.user.biz;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysUserYnbMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysUserYnb;

@Service
public class SysUserYnbBiz {
    @Autowired
    private SysUserYnbMapper sysUserYnbMapper;

    public int deleteByPrimaryKey(Integer id) {
        return sysUserYnbMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(SysUserYnb record) {
        record.setState(1);
        record.setCreateTime(Calendar.getInstance().getTime());
        return sysUserYnbMapper.insertSelective(record);
    }

    public SysUserYnb selectByPrimaryKey(Integer id) {
        return sysUserYnbMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysUserYnb record) {
        return sysUserYnbMapper.updateByPrimaryKeySelective(record);
    }

    public SysUserYnb selectByUserNum(String userNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        List<SysUserYnb> list = sysUserYnbMapper.selectPageByMap(map);
        return (list != null && list.size() == 1) ? list.get(0) : new SysUserYnb();
    }

}
