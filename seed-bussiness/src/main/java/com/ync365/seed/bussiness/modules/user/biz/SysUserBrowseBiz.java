package com.ync365.seed.bussiness.modules.user.biz;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysUserBrowseMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysUserBrowse;

@Service
public class SysUserBrowseBiz {
    @Autowired
    private SysUserBrowseMapper sysUserBrowseMapper;

    public int deleteByPrimaryKey(Integer id) {
        return sysUserBrowseMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(SysUserBrowse record) {
        return sysUserBrowseMapper.insertSelective(record);
    }

    public SysUserBrowse selectByPrimaryKey(Integer id) {
        return sysUserBrowseMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysUserBrowse record) {
        return sysUserBrowseMapper.updateByPrimaryKeySelective(record);
    }

    public List<SysUserBrowse> selectByUserNum(String userNum, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("userNum", userNum);
        if (count > 0) {
            map.put("pageIndex", 0);
            map.put("pageSize", count);
        }
        List<SysUserBrowse> list = sysUserBrowseMapper.selectPageByMap(map);
        SysUserBrowseComparator comparator = new SysUserBrowseComparator();
        Collections.sort(list, comparator);
        return list;
    }

    class SysUserBrowseComparator implements Comparator<SysUserBrowse> {

        @Override
        public int compare(SysUserBrowse o1, SysUserBrowse o2) {
            return o2.getBrowseDate().compareTo(o1.getBrowseDate());
        }

    }

    public int deleteAllBrowses(String userNum) {
        
        return sysUserBrowseMapper.deleteAllBrowses(userNum);
    }
}
