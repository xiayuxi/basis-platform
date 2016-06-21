package com.ync365.seed.admin.controller.promotion;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.vo.promotion.PromotionReturnDetailVo;
import com.ync365.seed.admin.vo.promotion.PromotionReturnVo;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionReturnBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionReturnDetailBiz;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReturn;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReturnDetail;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;

/**
 * 满返的控制层
 * @author lyh
 *
 */
@Controller
@RequestMapping("/promotionReturn")
public class PromotionReturnController {
	@Autowired
	private PromotionBiz promotionBiz;
	
	@Autowired
	private PromotionReturnBiz promotionReturnBiz;
	
	@Autowired
	private PromotionReturnDetailBiz promotionReturnDetailBiz;
	
	@Autowired
	private SysPopStoreBiz sysPopStoreBiz;
	
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	
	/**
	 * 添加满返活动
	 * @param promotionReturnVo
	 * @return
	 */
	@RequestMapping("add")
	public String addPromotionReturn(PromotionReturnVo promotionReturnVo,Model model){	
		Promotion promotion = new Promotion();
		PromotionReturn promotionReturn = new PromotionReturn();
		List<PromotionReturnDetail> list = new ArrayList<PromotionReturnDetail>();
		if(promotionReturnVo.getReturnType().byteValue() == Constants.PromotionReturnType.GeneralType.v()){
			PromotionReturnDetailVo nomarlReturnVo = promotionReturnVo.getNomarlReturnVo();
			PromotionReturnDetail generalReturnDetail = new PromotionReturnDetail();
			if(nomarlReturnVo!=null&&nomarlReturnVo.getMoneyCheckbox()!=null){
				generalReturnDetail.setReduceAmount(nomarlReturnVo.getReduceAmount());//减现金
			}
			if(nomarlReturnVo!=null&&nomarlReturnVo.getCouponCheckbox()!=null){
				generalReturnDetail.setCouponAmount(nomarlReturnVo.getCouponAmount());//送优惠券
			}
			//满额(消费金额)
			generalReturnDetail.setCostAmount(nomarlReturnVo.getCostAmount());			
			//设置红包(优惠券)有效期	
			generalReturnDetail.setValidDate(nomarlReturnVo.getValidDate());	
			list.add(generalReturnDetail);
		}else{
			List<PromotionReturnDetailVo> returnDetalisList = promotionReturnVo.getPromotionReturnDetalis();
			
			if(returnDetalisList.size()>0){
				for (PromotionReturnDetailVo returnVo : returnDetalisList) {				
					PromotionReturnDetail promotionReturnDetail = new PromotionReturnDetail();
					promotionReturnDetail.setCostAmount(returnVo.getCostAmount());
	
					if(returnVo.getMoneyCheckbox() != null){
						promotionReturnDetail.setReduceAmount(returnVo.getReduceAmount());//减现金
					}
					if(returnVo.getCouponCheckbox()!=null){					
						promotionReturnDetail.setCouponAmount(returnVo.getCouponAmount());//送优惠券
						promotionReturnDetail.setValidDate(returnVo.getValidDate());	
					}
					list.add(promotionReturnDetail);
				}
			}
		}		
		//设置活动创建人
		//promotion.setCreateUserId(sysUser.getId());	
		
		CloneUtils.cloneObject(promotionReturnVo, promotion);
		CloneUtils.cloneObject(promotionReturnVo, promotionReturn);		
		promotionReturnBiz.create(promotion, promotionReturn, list);
		model.addAttribute("promotionType", Constants.PromotionType.Retrun.v());//放活动类型是为了增加完新活动跳转到列表页
		return "promotion/list";
	}
	/**
	 * 删除满返活动(逻辑删除)
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String deletePromotionReturn(@RequestParam("id") int id){
		String result = "OK";//加一个删除成功的标记
		try {
			promotionReturnBiz.deleteById(id);			
		
		} catch (Exception e) {
			result = "Failed";
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 去修改满返活动页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(String id, String opera,Model model) {		
		PromotionReturnVo promotionReturnVo =new PromotionReturnVo();		
		Promotion promotion = promotionBiz.selectById(Integer.parseInt(id));
		PromotionReturn promotionReturn = promotionReturnBiz.selectByPromotionId(Integer.parseInt(id));
		CloneUtils.cloneObject(promotionReturn,promotionReturnVo);
		CloneUtils.cloneObject(promotion,promotionReturnVo);		
		
		//查询满返详情
		List<PromotionReturnDetail> returnList = promotionReturnDetailBiz.selectByPromotionId(Integer.parseInt(id));
		List<PromotionReturnDetailVo> returnVoList = new ArrayList<PromotionReturnDetailVo>();		
		//判断是否是多重优惠
		if(promotionReturn.getReturnType().byteValue() == Constants.PromotionReturnType.MultipleType.v()){			
			if(returnList!=null&&returnList.size()>0){
				int i= 0;//定义变量i是为了给减现金和送优惠券的那两个复选框起不同的名字
				for (PromotionReturnDetail returnDetail : returnList) {
					PromotionReturnDetailVo returnVo = new PromotionReturnDetailVo();
					if(returnDetail.getReduceAmount()!=null){
						returnVo.setMoneyCheckbox("m"+i);
					}
					if(returnDetail.getCouponAmount()!=null){
						returnVo.setCouponCheckbox("c"+i);
					}else{//如果优惠券为空，给出一个默认值0						
						returnDetail.setCouponAmount(new BigDecimal(0d));
					}
					CloneUtils.cloneObject(returnDetail,returnVo);
					returnVoList.add(returnVo);
					i++;
				}
				promotionReturnVo.setPromotionReturnDetalis(returnVoList);				
			}
		}else{//普通优惠
			if(returnList!=null&&returnList.size()==1){
				PromotionReturnDetailVo nomarlReturnVo = new PromotionReturnDetailVo();
				PromotionReturnDetail promotionReturnDetail = returnList.get(0);
				promotionReturnDetail.setCouponAmount(new BigDecimal(0d));
				CloneUtils.cloneObject(promotionReturnDetail,nomarlReturnVo);
				promotionReturnVo.setNomarlReturnVo(nomarlReturnVo);				
			}
		}
		
		model.addAttribute("promotionReturnVo",promotionReturnVo);
		model.addAttribute("opera", opera);
		return "/promotion/promotionReturn/edit";
	}
	/**
	 * 修改满返活动
	 * @param promotionReturnVo
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(PromotionReturnVo promotionReturnVo) {
		Promotion promotion = new Promotion();
		PromotionReturn pReturn = new PromotionReturn();		
		//设置活动类型
		promotion.setPromotionType(Constants.PromotionType.Retrun.v());
		
		List<PromotionReturnDetailVo> voDetalisList = promotionReturnVo.getPromotionReturnDetalis();
		List<PromotionReturnDetail> detalisList = new ArrayList<PromotionReturnDetail>();
		//判断优惠类型		
		if(promotionReturnVo.getReturnType().byteValue()==Constants.PromotionReturnType.MultipleType.v()){//多重优惠
			pReturn.setReturnType(2);
			if(voDetalisList.size()>0){
				for (PromotionReturnDetailVo voDetail : voDetalisList) {
					PromotionReturnDetail returnDetail = new PromotionReturnDetail();
					returnDetail.setPromotionId(promotionReturnVo.getPromotionId());
					returnDetail.setCouponId(voDetail.getCouponId());
					returnDetail.setCostAmount(voDetail.getCostAmount());
					if(voDetail.getMoneyCheckbox() != null){
						returnDetail.setReduceAmount(voDetail.getReduceAmount());
					}
					if(voDetail.getCouponCheckbox()!=null){					
						returnDetail.setCouponAmount(voDetail.getCouponAmount());
						returnDetail.setValidDate(voDetail.getValidDate());	
					}					
					detalisList.add(returnDetail);
				}
			}
		}else{//普通优惠
			if(promotionReturnVo.getNomarlReturnVo()!=null){
				pReturn.setReturnType(1);
				PromotionReturnDetail returnDetail = new PromotionReturnDetail();
				CloneUtils.cloneObject(promotionReturnVo.getNomarlReturnVo(),returnDetail);
				detalisList.add(returnDetail);
			}
		}
		CloneUtils.cloneObject(promotionReturnVo,promotion);
		CloneUtils.cloneObject(promotionReturnVo,pReturn);
		promotionReturnBiz.update(promotion,pReturn,detalisList);
		return "promotion/list";
	}	
	
}
