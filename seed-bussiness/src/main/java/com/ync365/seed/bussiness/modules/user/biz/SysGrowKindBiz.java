package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysGrowKindMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysGrowKind;
@Service
public class SysGrowKindBiz {
    @Autowired
    private SysGrowKindMapper mapper;

    public List<SysGrowKind> getGrowKind() {
        Map<String, Object> map = new HashMap<String, Object>();
        return mapper.selectPageByMap(map);
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
    public int insertSelective(SysGrowKind record){
        return mapper.insertSelective(record);
    }
}
