package com.ync365.seed.admin.controller.promotion;

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

import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.admin.vo.promotion.PromotionReduceRegVo;
import com.ync365.seed.admin.vo.promotion.PromotionReduceVo;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionReduceBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionReduceRegBiz;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReduce;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReduceReg;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreSearchBO;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;

/**
 * 满减活动的控制层
 * @author lyh
 *
 */
@Controller
@RequestMapping("/promotionReduce")
public class PromotionReduceController {
	@Autowired
	private PromotionBiz promotionBiz;	
	@Autowired
	private PromotionReduceBiz promotionReduceBiz;
	@Autowired
	private PromotionReduceRegBiz promotionReduceRegBiz;
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
	 * 新增满减活动
	 * @param promotionReduceVo
	 * @return
	 */
	@RequestMapping("/add")
	public String addPromotionReduce(PromotionReduceVo promotionReduceVo,Model model){		
		Promotion promotion = new Promotion();
		PromotionReduce promotionReduce = new PromotionReduce();	
		
		//获取页面传递过来的满减活动规则
		List<PromotionReduceRegVo> regVoList = promotionReduceVo.getPromotionReduceRegs();
		//创建一个新的list集合用来存储阶梯满减活动规则
		List<PromotionReduceReg> regList = new ArrayList<PromotionReduceReg>();	
		
		CloneUtils.cloneObject(promotionReduceVo, promotion);
		CloneUtils.cloneObject(promotionReduceVo, promotionReduce);
		//判断满减类型是否为阶梯满减
		if(promotionReduceVo.getReduceType().byteValue() == Constants.PromotionReduceType.JieTiReduce.v()){			
			if(regVoList.size()>0){	
				for (PromotionReduceRegVo regVo : regVoList) {
					PromotionReduceReg promotionReduceReg = new PromotionReduceReg();
					CloneUtils.cloneObject(regVo, promotionReduceReg);					
					regList.add(promotionReduceReg);
				}				
			}
		}
		//设置活动创建人
		promotion.setCreateUserNum(LoginUserUtils.getUser().getAdminNum());		
		promotionReduceBiz.create(promotion, promotionReduce, regList);
		model.addAttribute("promotionType", Constants.PromotionType.Reduce.v());//放活动类型是为了增加完新活动跳转到列表页
		return "/promotion/list";
	}	
	
	/**
	 * 删除满减活动(逻辑删除)
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String deletePromotionReduce(@RequestParam("id") int id){
		String result = "";//加一个删除成功的标记
		try {
			promotionReduceBiz.deleteById(id);	
			result = "OK";
		
		} catch (Exception e) {
			result = "Failed";
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 去修改满减活动页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(String id,String opera,Model model) {
		//获取店铺列表
		List<PopStoreBO> popStoreList = sysPopStoreBiz.selectPageBySearchBO(new PopStoreSearchBO());
		model.addAttribute("popStoreList", popStoreList);		
		PromotionReduceVo promotionReduceVo =new PromotionReduceVo();
		PromotionReduce promotionReduce = promotionReduceBiz.selectById(Integer.parseInt(id));
		Promotion promotion = promotionBiz.selectById(Integer.parseInt(id));
		CloneUtils.cloneObject(promotionReduce,promotionReduceVo);
		CloneUtils.cloneObject(promotion,promotionReduceVo);
		List<PromotionReduceRegVo> regVoList = new ArrayList<PromotionReduceRegVo>();		
		if(promotionReduce.getReduceType().byteValue() == Constants.PromotionReduceType.JieTiReduce.v()){
			//查询阶梯满减活动规则
			List<PromotionReduceReg> regList = promotionReduceRegBiz.selectByPromotionId(Integer.parseInt(id));
			if(regList!=null&&regList.size()>0){
				for (PromotionReduceReg reg : regList) {
					PromotionReduceRegVo regVo = new PromotionReduceRegVo();
					CloneUtils.cloneObject(reg,regVo);
					regVoList.add(regVo);
				}
				promotionReduceVo.setPromotionReduceRegs(regVoList);
			}
		}		
		model.addAttribute("promotionReduceVo",promotionReduceVo);
		model.addAttribute("opera", opera);//放此标志是为了在页面判断该操作是编辑或查看	
		Integer popStoreId = promotion.getStoreId();
		model.addAttribute("popStoreId", popStoreId);
		return "/promotion/promotionReduce/edit";
	}
	
	/**
	 * 修改满减活动
	 * @param promotionReduceVo
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(PromotionReduceVo promotionReduceVo,Model model) {
		Promotion promotion = new Promotion();
		PromotionReduce promotionReduce = new PromotionReduce();		
		//设置活动类型为满减活动
		promotion.setPromotionType(Constants.PromotionType.Reduce.v());
		List<PromotionReduceRegVo> regVoList = promotionReduceVo.getPromotionReduceRegs();
		List<PromotionReduceReg> regList = new ArrayList<PromotionReduceReg>();
		if(regVoList.size()>0){	
			for (PromotionReduceRegVo regVo : regVoList) {
				PromotionReduceReg promotionReduceReg = new PromotionReduceReg();
				promotionReduceReg.setPromotionId(promotionReduceVo.getPromotionId());
				CloneUtils.cloneObject(regVo, promotionReduceReg);					
				regList.add(promotionReduceReg);
			}		
		}		
		CloneUtils.cloneObject(promotionReduceVo,promotion);
		CloneUtils.cloneObject(promotionReduceVo,promotionReduce);
		//活动修改人
		promotion.setUpdateUserNum(LoginUserUtils.getUser().getAdminNum());
		promotionReduceBiz.update(promotion,promotionReduce,regList);	
		model.addAttribute("promotionType", Constants.PromotionType.Reduce.v());//放活动类型是为了编辑完活动跳转到列表页
		return "/promotion/list";
	}	
}
