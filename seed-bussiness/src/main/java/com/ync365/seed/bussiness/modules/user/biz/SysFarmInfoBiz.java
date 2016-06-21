package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysFarmInfoMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysFarmInfo;

@Service
public class SysFarmInfoBiz {
    @Autowired
    private SysFarmInfoMapper sysFarmInfoMapper;

    public int deleteByPrimaryKey(Integer id) {
        return sysFarmInfoMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(SysFarmInfo record) {
        return sysFarmInfoMapper.insertSelective(record);
    }

    public SysFarmInfo selectByPrimaryKey(Integer id) {
        return sysFarmInfoMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysFarmInfo record) {
        return sysFarmInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 用户编号获取农场信息
     * @Title: selectFarmInfoByUserNum
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月13日 上午11:22:14       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    public List<SysFarmInfo> selectFarmInfoByUserNum(String userNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("userNum", userNum);
        List<SysFarmInfo> list = sysFarmInfoMapper.selectListByMap(map);
        return list;
    }
}
