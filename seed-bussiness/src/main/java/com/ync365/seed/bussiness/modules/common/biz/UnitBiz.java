package com.ync365.seed.bussiness.modules.common.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.common.dao.UnitDao;
import com.ync365.seed.bussiness.modules.common.entity.Unit;

@Service
public class UnitBiz {
   
    @Autowired
    UnitDao unitDao ;
    
    public List<Unit> selectAllUnit(){
        return unitDao.selectAllUnit();
    }
}
