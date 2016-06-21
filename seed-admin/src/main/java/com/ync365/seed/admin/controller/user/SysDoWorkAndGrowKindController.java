package com.ync365.seed.admin.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.bussiness.modules.user.biz.SysDoWorkBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysGrowKindBiz;
import com.ync365.seed.bussiness.modules.user.entity.SysDoWork;
import com.ync365.seed.bussiness.modules.user.entity.SysGrowKind;

/**
 * 
 * @author leixc
 * 注册内容维护 controller
 *
 */
@Controller
@RequestMapping("/sysdowork")
public class SysDoWorkAndGrowKindController {
    
    @Autowired
    private SysGrowKindBiz sysGrowKindBiz;
    
    @Autowired
    private SysDoWorkBiz sysDoWorkBiz;

    /**
     * list列表
    * @param request
    * @return
    */
   @RequestMapping("/list")
   @LogAnnotation(operatingContent="用户管理模块>注册内容维护>列表页面")
   public String list(HttpServletRequest request) {
       //种植种类
       List<SysGrowKind> listGrowKind = sysGrowKindBiz.getGrowKind();
       //从事工作
       List<SysDoWork>  listDoWork = sysDoWorkBiz.getDoWork();
       
       request.setAttribute("listGrowKind", listGrowKind);
       request.setAttribute("listDoWork", listDoWork);
       return "user/sysdoworkandgrowkind/sysDoWorkList";
   }
   
   /**
    * by id 删除从事工作
    * @param id
    * @return
    */
   @RequestMapping("/deleteByDoWorkId")
   public String deleteByDoWorkId(Integer id) {
       try {
           sysDoWorkBiz.deleteByPrimaryKey(id);
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return "redirect:/sysdowork/list";
   }
   
   /**
    * by id 删除种植种类
    * @param id
    * @return
    */
   @RequestMapping("/deleteByGrowKindId")
   @LogAnnotation(operatingContent="用户管理模块>注册内容维护>删除")
   public String deleteByGrowKindId(Integer id) {
       try {
           sysGrowKindBiz.deleteByPrimaryKey(id);
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return "redirect:/sysdowork/list";
   }
   
   /**
    * 添加保存 
    * @param record
    * @return
    */
   @RequestMapping("/addgrowkind")
   @LogAnnotation(operatingContent="用户管理模块>注册内容维护>种植种类添加>确定")
   public String addGrowKind(SysGrowKind record) {
       try {
           sysGrowKindBiz.insertSelective(record);
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return "redirect:/sysdowork/list";
   }
   
   /**
    * 添加保存 
    * @param record
    * @return
    */
   @RequestMapping("/adddoword")
   @LogAnnotation(operatingContent="用户管理模块>注册内容维护>从事工作添加>确定")
   public String addDoWord(SysDoWork record) {
       try {
           sysDoWorkBiz.insertSelective(record);
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return "redirect:/sysdowork/list";
   }
}
