package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.bo.LogsSearchBO;
import com.ync365.seed.bussiness.modules.user.dao.SysLogsMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysLogs;

@Service
public class SysLogsBiz {
    @Autowired
    private SysLogsMapper logsMapper;

    /**
     * 添加日志
     * @Title: insertSelective
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月16日 下午2:31:24       
     * @version: 
     *
     * @param record
     * @return
     *
     */
    public int insertSelective(SysLogs record) {
        return logsMapper.insertSelective(record);
    }

    public SysLogs selectByPrimaryKey(Integer id) {
        return logsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysLogs record) {
        return logsMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 搜索日志
     * @Title: selectByLogsSearchBO
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月16日 下午2:31:14       
     * @version: 
     *
     * @param searchBo
     * @return
     *
     */
    public List<SysLogs> selectByLogsSearchBO(LogsSearchBO searchBo) {
        Map<String, Object> map = new HashMap<>();
        map.put("operatorName", searchBo.getOperatorName());
        map.put("groupName", searchBo.getGroupName());
        map.put("roleName", searchBo.getRoleName());
        map.put("operatingContent", searchBo.getOperatingContent());
        map.put("beginOpratingTime", searchBo.getBeginOpratingTime());
        map.put("endOpratingTime", searchBo.getEndOpratingTime());
        map.put("operatingContent", searchBo.getOperatingContent());
        map.put("operatingAdminNum", searchBo.getOperatingAdminNum());
        map.put("parameter", searchBo.getParameter());
        map.put("pageIndex", searchBo.getPageIndex());
        map.put("pageSize", searchBo.getPageSize());
        return logsMapper.selectPageByMap(map);
    }

    public Integer selectCountByLogsSearchBO(LogsSearchBO searchBo) {
        Map<String, Object> map = new HashMap<>();
        map.put("operatorName", searchBo.getOperatorName());
        map.put("groupName", searchBo.getGroupName());
        map.put("roleName", searchBo.getRoleName());
        map.put("operatingContent", searchBo.getOperatingContent());
        map.put("beginOpratingTime", searchBo.getBeginOpratingTime());
        map.put("endOpratingTime", searchBo.getEndOpratingTime());
        map.put("operatingContent", searchBo.getOperatingContent());
        map.put("operatingAdminNum", searchBo.getOperatingAdminNum());
        map.put("parameter", searchBo.getParameter());
        map.put("pageIndex", searchBo.getPageIndex());
        map.put("pageSize", searchBo.getPageSize());
        return logsMapper.selectCountByMap(map);
    }
}
