package com.ync365.seed.admin.controller.goods;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.controller.base.BaseController;
import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.Json;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.admin.vo.goods.GoodsVo;
import com.ync365.seed.bussiness.modules.goods.biz.AuditBiz;
import com.ync365.seed.bussiness.modules.goods.biz.BrandBiz;
import com.ync365.seed.bussiness.modules.goods.biz.CategoryBiz;
import com.ync365.seed.bussiness.modules.goods.biz.GoodsBiz;
import com.ync365.seed.bussiness.modules.goods.biz.SkuBiz;
import com.ync365.seed.bussiness.modules.goods.biz.StocksBiz;
import com.ync365.seed.bussiness.modules.goods.bo.GoodsBO;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.bussiness.modules.goods.entity.Goods;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.utils.DateUtils;
import com.ync365.seed.utils.StringUtils;

@Controller
@RequestMapping("/goodsAudit")
public class AuditController extends BaseController{
	
	@Autowired
	private SkuBiz skuBiz;
	
	@Autowired
	private GoodsBiz goodsBiz;
	
	@Autowired
	private BrandBiz brandBiz;
	
	@Autowired
	private CategoryBiz categoryBiz;
	
	@Autowired
	private StocksBiz stocksBiz; 
	
	@Autowired
	private AuditBiz auditBiz;
	
	/**
	 * 商品审核
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditList")
	@LogAnnotation(operatingContent="商品管理模块>商品审核管理>商品审核列表页面")
	public String auditList(HttpServletRequest request) {
	     /**
         * 第一级分类列表
         */
        List<Category> catList = categoryBiz.getCategoryByParentId(0);
        request.setAttribute("catList", catList);
		return "goods/auditList";
	}
	
	@RequestMapping("/auditGrid")
	@ResponseBody
	@LogAnnotation(operatingContent="商品管理模块>商品审核管理>查询")
	public Grid auditDataGrid(String name,String status,String categoryId
	        ,GoodsVo goodsVo,PageFilter filter) {
		Grid grid = new Grid();
		List<GoodsBO> list = new ArrayList<GoodsBO>();
		String storeName="";
		long count = 0;
		int qStatus = Integer.parseInt(status);
		Map<String ,Object> map=new HashMap<String,Object>();
		try {
		    map.put("name", name);
		    if(qStatus!=5){
		        map.put("status", qStatus);
		    }else{
		        map.put("allStatus", -1);
		    }
		    if(categoryId!=null&&categoryId!=""){
		        map.put("categoryId", Integer.parseInt(categoryId));
		    }
		    if(null!=goodsVo.getGoodsType()&&goodsVo.getGoodsType()!=""){
		        map.put("goodsType", Integer.parseInt(goodsVo.getGoodsType()));
		    }
		    if(goodsVo.getStoreName()!=null){
		        storeName = goodsVo.getStoreName();
		    }
		    if(goodsVo.getBrandName()!=""){
		        map.put("brandName", goodsVo.getBrandName());
		    }
			list=auditBiz.selectAuditPage(map,storeName, filter.getStartIndex(),filter.getRows());
			count = auditBiz.selectAuditPageCount(map,storeName);
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
	 * 审核通过
	 * @Title: audit
	 * @Description: TODO    ：    
	 * @author: liugl    
	 * @date: 2015年10月12日 下午3:53:54       
	 * @version: 
	 *
	 * @param goodsId
	 * @param status
	 * @return
	 *
	 */
	@RequestMapping("/audit")
	@ResponseBody
	@LogAnnotation(operatingContent="商品管理模块>商品审核管理>审核通过")
	public Json audit(String goodsId,String status){
		Json json = new Json();
		
		if(StringUtils.isBlank(goodsId) || StringUtils.isBlank(status)){
			json.setSuccess(false);
			json.setMsg("参数错误");
			return json ;
		}
		
		try{
			/**
			 * 审核通过状态为：1 审核不通过状态为 2.默认为未审核0
			 */
		    Goods goods = goodsBiz.selectByPrimaryKey(Integer.parseInt(goodsId));
		    if(null==goods.getCode()||"".equals(goods.getCode())){
		        json.setSuccess(false);
		        json.setMsg("商品编号为空，无法通过审核");
		    }else{
		        AdminBO adminBo=LoginUserUtils.getUser();
		        int update = auditBiz.audit(goodsId, Integer.parseInt(status),adminBo);
		        if(update == 1 ){
		            json.setSuccess(true);
		        }else{
		            json.setSuccess(false);
		            json.setMsg("审核失败");
		        }
		    }
		        
		}catch(Exception e){
			e.printStackTrace();
			json.setSuccess(false);
		}
		
		return json;
	}
	
	/* 跳转到审核页面 */
	@RequestMapping("/auditPage")
	@LogAnnotation(operatingContent="商品管理模块>商品审核管理>商品审核页面")
	public String auditPage(HttpServletRequest request,Integer goodsId){
		Goods auditGoods = new Goods();
		try{
			if(goodsId!=null){
			    auditGoods = auditBiz.selectGoodsByGoodsId(goodsId);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("auditGoods", auditGoods);
		return "goods/goodsAudit";
	}
	
	/**
	 * 审核不通过
	 * @Title: disPassAudit
	 * @Description: TODO    ：    
	 * @author: liugl    
	 * @date: 2015年10月12日 下午3:54:07       
	 * @version: 
	 *
	 * @param goodsId
	 * @param status
	 * @param reason
	 * @return
	 *
	 */
	@RequestMapping("/disPassAudit")
	@ResponseBody
	@LogAnnotation(operatingContent="商品管理模块>商品审核管理>审核不通过")
	public Json disPassAudit(String goodsId,String status,String reason){
        Json json = new Json();
        
        if(StringUtils.isBlank(goodsId) || StringUtils.isBlank(status)){
            json.setSuccess(false);
            json.setMsg("参数错误");
            return json ;
        }
        
        try{
            /**
             * 审核通过状态为：1 审核不通过状态为 2.默认为未审核0
             */
        	AdminBO adminBo=LoginUserUtils.getUser();
            int update = auditBiz.disPassAudit(goodsId, status,reason,adminBo);
            if(update == 1 ){
                json.setSuccess(true);
            }else{
                json.setSuccess(false);
            }
        }catch(Exception e){
            e.printStackTrace();
            json.setSuccess(false);
        }
        return json;
	}
  
  /***
   * 商品上下架复制，复制goods相关所有数据
   * @Title: copyGoods
   * @Description: TODO    ：    
   * @author: guanfl    
   * @date: 2015年10月12日 上午10:12:17       
   * @version: 
   *
   * @return
   *
   */
  @RequestMapping("copyGoods")
  @ResponseBody
  public ModelAndView copyGoods(){
      
      return new ModelAndView("redirect:/goodsAudit/auditList");
  }
	
	/**
     * 商品回收站
     * @param request
     * @return
     */
    @RequestMapping("/recycleBinList")
    @LogAnnotation(operatingContent="商品管理模块>商品回收站>商品回收站列表")
    public String recycleBinList(HttpServletRequest request) {
         /**
         * 第一级分类列表
         */
        List<Category> catList = categoryBiz.getCategoryByParentId(0);
        request.setAttribute("catList", catList);
        return "goods/recycleBinList";
    }
	
    /**
     * 商品回收站表获取数据
     * @Title: recycleBinGrid
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年10月12日 上午11:30:20       
     * @version: 
     *
     * @param name
     * @param startTime
     * @param endTime
     * @param categoryId
     * @param storeId
     * @param releaseUseId
     * @param brandName
     * @param filter
     * @return
     *
     */
    @RequestMapping("/recycleBinGrid")
    @ResponseBody
      public Grid recycleBinGrid(String name,String categoryId, GoodsVo goodsVo,PageFilter filter) {
        Grid grid = new Grid();
        List<Goods> list = new ArrayList<Goods>();
        String storeName = "";
        String releaseUser = "";
        
        long count = 0;
        Map<String ,Object> map=new HashMap<String,Object>();
        try {
              if(name!=null){
                  map.put("name", name);              
              }
              if(goodsVo.getBeginDate()!=null&&goodsVo.getBeginDate()!=""){
                  map.put("beginDate", DateUtils.stringToDate(goodsVo.getBeginDate(),
                          "yyyy-MM-dd HH:mm:ss"));
              }
              if(goodsVo.getEndDate()!=null&&goodsVo.getEndDate()!=""){
                  map.put("endDate", DateUtils.stringToDate(goodsVo.getEndDate(),
                          "yyyy-MM-dd HH:mm:ss"));
              }
              if(categoryId!=null){
                  map.put("categoryId", categoryId);
              }
              if(goodsVo.getStoreName()!=null){
                  storeName = goodsVo.getStoreName();
              }
              if(goodsVo.getReleaseUser()!=null){
                  releaseUser = goodsVo.getReleaseUser();
              }
              if(goodsVo.getBrandName()!=null){
                  map.put("brandName",goodsVo.getBrandName());
              }
              //改成传参数，在biz判断
              list=auditBiz.selectRecyclePage(map, releaseUser, storeName,filter.getStartIndex(),filter.getRows());
              count = auditBiz.selectRecyclePageCount(map,releaseUser,storeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        grid.setRows(list);
        grid.setRecords(count);
        grid.setPageSize(filter.getRows());
        return grid;
    }
    
    /**
     * 商品回收站的恢复
     * @Title: recover
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年10月12日 下午5:15:13       
     * @version: 
     *
     * @param goodsId
     * @param status
     * @return
     *
     */
    @RequestMapping("/recover")
    @ResponseBody
    @LogAnnotation(operatingContent="商品管理模块>商品回收站>恢愎")
    public Json recover(String goodsId){
        
        Json json = new Json();
        int temp = 0;
        try {
            //改is_del为0
            temp = auditBiz.recover(goodsId);
            if (temp > 0) {
                json.setSuccess(true);
                json.setMsg("ok");
            } else {
                json.setSuccess(false);
                json.setMsg("no");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    
}


