package com.ync365.seed.bussiness.modules.user.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.common.biz.RegionBiz;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminServiceMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminService;
@Service
public class SysAdminServiceBiz {
    @Autowired
    private SysAdminServiceMapper sysAdminServiceMapper;
    @Autowired
    private RegionBiz regionBiz;

    public List<String> selectStringByNumLevel(String userNum,Integer level){
    	List<SysAdminService> sysAdminServiceList = sysAdminServiceMapper.selectServiceListByNumLevel(userNum, level);
    	List<String> list = new ArrayList<String>();
    	for(SysAdminService sysAdminService:sysAdminServiceList){
    		if(sysAdminService!=null&&sysAdminService.getAdminServiceDistinct()!=null){
    			list.add(regionBiz.selectAllParentRegionStringById(sysAdminService.getAdminServiceDistinct()));
    		}
    	}
    	return list;
    }
}
