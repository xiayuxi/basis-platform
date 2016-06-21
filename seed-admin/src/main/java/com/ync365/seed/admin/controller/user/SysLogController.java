package com.ync365.seed.admin.controller.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.user.biz.SysLogsBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.LogsSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.entity.SysLogs;

@Controller
@RequestMapping("log")
public class SysLogController {
    @Autowired
    private SysLogsBiz sysLogsBiz;
    /**
     * list请求
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        return "user/log/loglist";
    }
    
    @RequestMapping("/grid")
    @ResponseBody
    //@LogAnnotation(operatingContent="查询日志列表")
    public Grid dataGrid(LogsSearchBO searchBo,PageFilter filter) {
        Grid grid = new Grid();
        searchBo.setPageIndex(filter.getStartIndex());
        searchBo.setPageSize(filter.getRows());
        List<SysLogs> list = sysLogsBiz.selectByLogsSearchBO(searchBo);
        Integer count = sysLogsBiz.selectCountByLogsSearchBO(searchBo);
        grid.setRows(list);
        grid.setRecords(count.longValue());
        grid.setPageSize(filter.getRows());
        grid.setPage(filter.getPage());
        return grid;
    }
    
    
    /**
     * listuser请求
     * @param request
     * @return
     */
    @RequestMapping("/listuser")
    public String listUser(HttpServletRequest request,String adminNum) {
        request.setAttribute("adminNum", adminNum);
        return "user/log/loglistPage";
    }
    
    @RequestMapping("/gridpage")
    @ResponseBody
    //@LogAnnotation(operatingContent="查询日志列表")
    public Grid dataGridPage(LogsSearchBO searchBo,PageFilter filter) {
        Grid grid = new Grid();
        searchBo.setPageIndex(filter.getStartIndex());
        searchBo.setPageSize(filter.getRows());
        List<SysLogs> list = sysLogsBiz.selectByLogsSearchBO(searchBo);
        Integer count = sysLogsBiz.selectCountByLogsSearchBO(searchBo);
        grid.setRows(list);
        grid.setRecords(count.longValue());
        grid.setPageSize(filter.getRows());
        grid.setPage(filter.getPage());
        return grid;
    }
    
    
    
    /**
    * 时间自动格式化
    * @param bin
    */
   @InitBinder
   public void InitBinder(ServletRequestDataBinder bin) {
       bin.registerCustomEditor(Date.class, new CustomDateEditor( new SimpleDateFormat("yyyy-MM-dd"), true));
   }
}
