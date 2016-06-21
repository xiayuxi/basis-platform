package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysDoWorkMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysDoWork;
@Service
public class SysDoWorkBiz {
    @Autowired
    private SysDoWorkMapper mapper;

    public List<SysDoWork> getDoWork() {
        Map<String, Object> map=new HashMap<String, Object>();
        List<SysDoWork> list=   mapper.selectPageByMap(map);
        return list;
    }

    /**
     * by id主键删除
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Integer id){
        return mapper.deleteByPrimaryKey(id);
    }
    
    /**
     * 插入对象
     * @param record
     * @return
     */
    public int insertSelective(SysDoWork record){
        return mapper.insertSelective(record);
    }
}
