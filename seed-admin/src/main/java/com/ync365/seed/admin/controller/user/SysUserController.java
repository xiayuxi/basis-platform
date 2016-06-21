package com.ync365.seed.admin.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.user.biz.SysUserBiz;
import com.ync365.seed.bussiness.modules.user.entity.SysUser;
import com.ync365.seed.utils.StringUtils;

@Controller
@RequestMapping("/user")
public class SysUserController {


	@Autowired
	private SysUserBiz sysUserBiz;
	
	
	/**列表页
	 * @author xieang
	 * 2015年9月15日
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "user/sysUserList";
	}

	/**获取grid列表
	 * @author xieang
	 * 2015年9月15日
	 * @param name
	 * @param filter
	 * @return
	 */
	@RequestMapping("/grid")
	@ResponseBody
	public Grid dataGrid(String name, PageFilter filter) {
		Grid grid = new Grid();
		List<SysUser> list = new ArrayList<SysUser>();
		long count = 0;
		Map<String ,Object> map=new HashMap<String,Object>();
		SysUser sysUser = new SysUser();
		try {
			map.put("name", name);
			list=null; //sysUserBiz.selectSysUserByPage(sysUser, filter.getStartIndex(),filter.getRows());
			count = sysUserBiz.selectPageCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		grid.setRows(list);
		grid.setRecords(count);
		grid.setPageSize(filter.getRows());
		grid.setPage(filter.getPage());
		return grid;
	}
	
	/**用户添加页
	 * @author xieang
	 * 2015年9月15日
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(){
		return "user/sysUserAdd";
	}
	
	/**
	 * 用户编辑页
	 * @author xieang
	 * 2015年9月14日
	 * @param model
	 * @param userNum
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(Model model, String userNum){
		SysUser sysUser = sysUserBiz.selectByPrimaryKeyUserNum(userNum);
		/*if(sysUser.getSysUserInfo()==null){
			sysUser.setSysUserInfo(new SysUserInfo());
		}*/
		model.addAttribute("sysUser", sysUser);
		return "user/sysUserEdit";
	}
	/**
	 * 用户详情页
	 * @author xieang
	 * 2015年9月14日
	 * @param model
	 * @param userNum
	 * @return
	 */
	@RequestMapping("/infoPage")
	public String infoPage(Model model, String userNum){
		SysUser sysUser = sysUserBiz.selectByPrimaryKeyUserNum(userNum);
		/*if(sysUser.getSysUserInfo()==null){
			sysUser.setSysUserInfo(new SysUserInfo());
		}*/
		model.addAttribute("sysUser", sysUser);
		return "user/sysUserInfo";
	}
	
	/**编辑保存操作
	 * @author xieang
	 * 2015年9月15日
	 * @param sysUser
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(SysUser sysUser){
		if(sysUser.getUserNum()!=null){
			//sysUserBiz.updateSysUserAndInfoByUserNum(sysUser);
		}
		return "redirect:list";
	}
	/**重置密码
	 * @author xieang
	 * 2015年10月10日
	 * @param sysUser
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resetPassword")
	public String resetPassword(SysUser sysUser){
		SysUser record = new SysUser();
		record.setUserNum(sysUser.getUserNum());
		record.setSafeScore(0);//安全级别重置为0
		record.setUserPassword(StringUtils.isNotBlank(sysUser.getUserPassword())?sysUser.getUserPassword():"123456");
		int i = sysUserBiz.updateByPrimaryKeySelective(record);
		if(i==1){
			return "OK";
		}else{
			return "ERR";
		}
	}
	@ResponseBody
	@RequestMapping("/lockedUser")
	public String lockedUser(String userNum){
		SysUser record = new SysUser();
		record.setUserNum(userNum);
		SysUser sysUser = sysUserBiz.selectByPrimaryKeyUserNum(userNum);
		if(sysUser==null||sysUser.getIsFrozen()==null){
			return "ERR";
		}
		if(sysUser.getIsFrozen()){
			record.setIsFrozen(false);
		}else{
			record.setIsFrozen(true);
		}
		int i = sysUserBiz.updateByPrimaryKeySelective(record);
		if(i==1){
			return "OK";
		}else{
			return "ERR";
		}
	}
	@ResponseBody
	@RequestMapping("/isAuthentication")
	public String isAuthentication(String userNum){
		SysUser record = new SysUser();
		record.setUserNum(userNum);
		SysUser sysUser = sysUserBiz.selectByPrimaryKeyUserNum(userNum);
		if(sysUser==null||sysUser.getIsAuthentication()==null){
			return "ERR";
		}
		if(sysUser.getIsAuthentication()){
			record.setIsAuthentication(false);
		}else{
			record.setIsAuthentication(true);
		}
		int i = sysUserBiz.updateByPrimaryKeySelective(record);
		if(i==1){
			return "OK";
		}else{
			return "ERR";
		}
	}
	
    /**校验手机号是否重复
     * 返回True为不重复False为重复
     * @author xieang
     * 2015年11月1日
     * @param Mobile 手机号
     * @param userNum 用户编号，用于判断是否为当前手机号
     * @return
     */
    @RequestMapping("/validateMobileRepeat")
    @ResponseBody
    public Object validateMobileRepeat(String mobile,String userNum){
    	Boolean temp = false;
    	if(StringUtils.isNotBlank(userNum)){
    		SysUser sysUser = sysUserBiz.selectByPrimaryKeyUserNum(userNum);
        	if(sysUser==null){
        		
        	}else if(StringUtils.isBlank(sysUser.getUserMobile())||!sysUser.getUserMobile().equals(mobile)){
            	temp= sysUserBiz.validateUserMobile(mobile);
        	}else{
        		temp = true;
        	}
    	}
        return temp.toString();
    }
	
	/**时间自动格式化
	 * @author xieang
	 * 2015年9月15日
	 * @param bin
	 */
	@InitBinder
    public void InitBinder(ServletRequestDataBinder bin) {
        bin.registerCustomEditor(Date.class, new CustomDateEditor( new SimpleDateFormat("yyyy-MM-dd"), true));

    }
}
