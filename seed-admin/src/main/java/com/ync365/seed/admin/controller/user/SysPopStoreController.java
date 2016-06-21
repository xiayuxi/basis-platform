package com.ync365.seed.admin.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.common.biz.RegionBiz;
import com.ync365.seed.bussiness.modules.goods.biz.CategoryBiz;
import com.ync365.seed.bussiness.modules.goods.entity.Brand;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.bussiness.modules.order.biz.OrderInfoBiz;
import com.ync365.seed.bussiness.modules.order.bo.OrderInfoGoodsBO;
import com.ync365.seed.bussiness.modules.user.biz.SysPopBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreDecorateInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysPop;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreDecorateInfo;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.StringUtils;

@Controller
@RequestMapping("/sysPopStore")
public class SysPopStoreController {
	
	@Autowired
	private SysPopStoreBiz sysPopStoreBiz;
	@Autowired
	private SysPopBiz sysPopBiz;
	@Autowired
	private OrderInfoBiz orderInfoBiz;
	@Autowired
	private CategoryBiz categoryBiz;
	@Autowired
	private RegionBiz regionBiz;
	@Autowired
	private SysPopStoreDecorateInfoBiz decorateInfoBiz;

	@RequestMapping("/list")
	@LogAnnotation(operatingContent="用户管理模块>入驻商列表>列表页面")
    public String listByAnum(HttpServletRequest request) {
        return "user/sysPopStoreList";
    }
	
	@RequestMapping("/grid")
	@ResponseBody
	@LogAnnotation(operatingContent="用户管理模块>入驻商列表>查询")
	public Grid dataGrid(HttpServletRequest request , PopStoreSearchBO popStoreSearchBo, PageFilter filter) {
		Grid grid = new Grid();
		List<PopStoreBO> list = new ArrayList<PopStoreBO>();
		long count = 0;
			popStoreSearchBo.setPageIndex(filter.getStartIndex());
			popStoreSearchBo.setPageSize(filter.getRows());
	        AdminBO adminBO =LoginUserUtils.getUser();
	        Integer roleId = adminBO.getRoleIdd();
	        if (roleId==Constants.UserRole.SE.v()) {
	        	popStoreSearchBo.setSeAdminNumSearch(adminBO.getAdminNum());
	        }
        try {
        	list= sysPopStoreBiz.selectPageBySearchBO(popStoreSearchBo);
        	//拼接所属区域
        	for (int i = 0; i < list.size(); i++) {
        		PopStoreBO popStoreBO = list.get(i);
	        	String popStoreAddress = null;
				if (StringUtils.isNotBlank(popStoreBO.getPopStoreAddress())) {
					popStoreAddress = regionBiz.selectAllParentRegionStringById(Integer.parseInt(popStoreBO.getPopStoreAddress()));
				}
				popStoreBO.setPopStoreAddress(popStoreAddress);
				//装修状态
				SysPopStoreDecorateInfo decorateInfo = decorateInfoBiz.selectByPopStoreNum(popStoreBO.getPopStoreNum());
				if (null != decorateInfo) {
					popStoreBO.setPopStoreDecorate(decorateInfo.getPopStoreDecorate());
				}
        	}
			count = sysPopStoreBiz.selectPageBySearchCount(popStoreSearchBo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		grid.setRows(list);
		grid.setRecords(count);
		grid.setPageSize(filter.getRows());
		grid.setPage(filter.getPage());
		return grid;
	}
	
	/**
	 * 查看 和 编辑页面
	 * @param popStoreNum
	 * @param popStoreViewType	操作类型：view查看，edit编辑
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewPage/{popStoreNum}/{popStoreViewType}")
	@LogAnnotation(operatingContent="用户管理模块>入驻商列表>查看/编辑页面")
	public String viewPage(@PathVariable String popStoreNum, @PathVariable String popStoreViewType, HttpServletRequest request) {
		PopStoreBO popStoreBO = null;
		List<Brand> brands = null;
		List<Category> categorys = null;
		List<OrderInfoGoodsBO> orderInfoGoodsBOs = null;
		try {
			if (popStoreViewType.equals("view")) {
				popStoreBO = sysPopStoreBiz.searchPopStoreBOByPopStoreNum(popStoreNum);
		    	//拼接字符串地址
				String popStoreAddress = null;
				if (StringUtils.isNotBlank(popStoreBO.getPopStoreAddress())) {
					popStoreAddress = regionBiz.selectAllParentRegionStringById(Integer.parseInt(popStoreBO.getPopStoreAddress()));
					//popStoreAddress += popStoreBO.getPopStoreAddressDetail();
					popStoreBO.setPopStoreAddress(popStoreAddress);
				}
				orderInfoGoodsBOs = orderInfoBiz.getSupplierOrderList(popStoreBO.getId().toString());
			} else {
				popStoreBO = sysPopStoreBiz.searchPopStoreBOByPopStoreNum(popStoreNum);
			}
			brands = sysPopStoreBiz.selectBrandByBrandIds(popStoreNum);
			categorys = categoryBiz.selectCategoryListByPopStoreNum(popStoreNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("popStoreBO", popStoreBO);
		request.setAttribute("brands", brands);
		request.setAttribute("categorys", categorys);
		request.setAttribute("orderInfoGoodsBOs", orderInfoGoodsBOs);
		switch (popStoreViewType) {
			case "view":
				return "user/sysPopStoreInfoPage";
			case "edit":
				return "user/sysPopStoreEditPage";
			default:
				return "user/sysPopStoreInfoPage";
		}
	}
	
	/**
	 * 添加供应商页面
	 * @return
	 */
	@RequestMapping("/insertPopStorePage")
	public String insertPopStorePage(HttpServletRequest request) {
		PopStoreBO popStoreBO = new PopStoreBO();
		request.setAttribute("popStoreBO", popStoreBO);
		return "user/sysPopStoreAddPage";
	}
	
	/**
	 * 添加供应商方法
	 * @return
	 */
	@RequestMapping("/insertPopStoreInfo")
	public String insertPopStoreInfo(PopStoreBO popStoreBO) {
		try {
			sysPopStoreBiz.insertPopStoreBO(popStoreBO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/sysPopStoreList";
	}
	
	
	/**
	 * 功能描述：保存编辑
	 * @author liukai
	 * @param popStoreBO
	 * @return
	 */
	@RequestMapping("/edit")
	@LogAnnotation(operatingContent="用户管理模块>入驻商列表>编辑>保存")
	public String edit(PopStoreBO popStoreBO) {
		try {
			sysPopStoreBiz.updatePopStore(popStoreBO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/sysPopStoreList";
	}
	
	/**
	 * 时间自动格式化
	 * @author xieang
	 * 2015年9月15日
	 * @param bin
	 */
	@InitBinder
    public void InitBinder(ServletRequestDataBinder bin) {
        bin.registerCustomEditor(Date.class, new CustomDateEditor( new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	
	/**
	 * 功能描述：根据品牌名称搜索品牌。
	 * @author liukai
	 * @return
	 */
	@RequestMapping("/searchBrand/{chName}")
	@ResponseBody
	@LogAnnotation(operatingContent="用户管理模块>入驻商列表>编辑>供应品牌>搜索")
	public Brand searchBrand(@PathVariable String chName) {
		Brand brand = null;
		try {
			brand = sysPopStoreBiz.selectBrandByChName(chName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brand;
	}
	
	@RequestMapping("/openAddCategoryPage")
	public String openAddCategoryPage() {
		return "user/sysPopStoreAddCategoryPage";
	}
	
	
	/**
	 * 功能描述：绑定SE负责人
	 * @author liukai
	 */
	@RequestMapping("/updateSeRelation")
	@LogAnnotation(operatingContent="用户管理模块>入驻商列表>分配>确定")
	public String insertSeRelation(@RequestParam String popStoreNum, @RequestParam String seNum) {
		try {
			sysPopStoreBiz.insertSeRelation(popStoreNum, seNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/sysPopStoreList";
	}
	
	/**
	 * 功能描述：重置密码
	 * @author liukai
	 * @return
	 */
	@RequestMapping("/resetPassword")
	@ResponseBody
	@LogAnnotation(operatingContent="用户管理模块>入驻商列表>重置密码")
	public int resetPassword(@RequestParam String popStoreNum) {
		int amount = 0;
		try {
			amount = sysPopBiz.updatePasswordByPopStoreNum(popStoreNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}
	

	/**
	 * 功能描述：功能描述：改变激活状态
	 * @author liukai
	 * @param popStoreNum
	 * @param frozen 
	 * @return
	 */
	@RequestMapping("/changeFrozen/{id}")
	@LogAnnotation(operatingContent="用户管理模块>入驻商列表>改变入驻商激活状态")
	public String changFrozen(@PathVariable Integer id) {
		try {
			sysPopStoreBiz.updateSysPopStoreFrozen(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/sysPopStoreList";
	}
	
	
	/**
	 * 功能描述：验证入驻商账号是否重复
	 * @author liukai
	 * @param popLoginName
	 * @return
	 */
	@RequestMapping("/searchSysPopLoginName")
	@ResponseBody
	public JSONObject searchSysPopByName(String popLoginName, String popStoreNum) {
		int result = 0;
		JSONObject json = new JSONObject() ;
		try {
			result = sysPopStoreBiz.checkSysPopByName(popLoginName, popStoreNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (StringUtils.isNotBlank(popStoreNum)) { //修改页面
            if (result==1) {
                json.put("ok", "");
            } else {
                json.put("error", "用户名已被使用");
            }
        } else { //添加页面
            if (result==0) {
                json.put("error", "用户名已被使用");
            } else {
                json.put("ok", "用户名未被注册");
            }
        }
		return json;
	}
	

	/**
	 * 功能描述：验证 联系电话 是否重复
	 * @author liukai
	 * @param popStoreMobile
	 * @return
	 */
	@RequestMapping("/searchSysPopStoreMobile")
	@ResponseBody
	/*public JSONObject searchSysPopStoreMobile(String popStoreMobile, String popStoreNum) {
		JSONObject json = null;
		try {
			json = sysPopStoreBiz.searchSysPopStoreMobile(popStoreMobile, popStoreNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}*/
	public JSONObject searchSysPopStoreMobile(String popStoreMobile, String popStoreNum) {
		int result = 0;
		JSONObject json = new JSONObject();
		try {
			result = sysPopStoreBiz.checkSysPopStoreMobile(popStoreMobile, popStoreNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (StringUtils.isNotBlank(popStoreNum)) { //修改页面
            if (result==1) {
                json.put("ok", "");
            } else {
                json.put("error", "联系电话已被使用");
            }
        } else { //添加页面
            if (result==0) {
                json.put("error", "联系电话已被使用");
            } else {
                json.put("ok", "");
            }
        }
		return json;
	}
	
	
	
	
	/**
	 * 功能描述：验证域名是否重复
	 * @author liukai
	 * @param popStoreMobile
	 * @return
	 */
	@RequestMapping("/checkSysPopStoreDomain")
	@ResponseBody
	public JSONObject checkSysPopStoreDomain(String popStoreDomain, String popStoreNum) {
		int result = 0;
		JSONObject json = new JSONObject();
		try {
			result = sysPopStoreBiz.checkSysPopStoreDomain(popStoreDomain, popStoreNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (StringUtils.isNotBlank(popStoreNum)) { //修改页面
            if (result==1) {
                json.put("ok", "");
            } else {
                json.put("error", "域名已被使用");
            }
        } else { //添加页面
            if (result==0) {
                json.put("error", "域名已被使用");
            } else {
                json.put("ok", "");
            }
        }
		return json;
	}
	
	/**
	 * 功能描述：验证店铺名称是否重复
	 * @author liukai
	 * @param popStoreMobile
	 * @return
	 */
	@RequestMapping("/checkSysPopStoreName")
	@ResponseBody
	/*public JSONObject checkSysPopStoreName(String popStoreName, String popStoreNum) {
		JSONObject json = null;
		try {
			json = sysPopStoreBiz.checkPopStoreName(popStoreName, popStoreNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}*/
	public JSONObject checkSysPopStoreName(String popStoreName, String popStoreNum) {
		int result = 0;
		JSONObject json = new JSONObject();
		try {
			result = sysPopStoreBiz.checkPopStoreName(popStoreName, popStoreNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (StringUtils.isNotBlank(popStoreNum)) { //修改页面
            if (result==1) {
                json.put("ok", "");
            } else {
                json.put("error", "域名已被使用");
            }
        } else { //添加页面
            if (result==0) {
                json.put("error", "域名已被使用");
            } else {
                json.put("ok", "");
            }
        }
		return json;
	}
}
