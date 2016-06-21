package com.ync365.seed.service.ad.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.ad.biz.NavigationBiz;
import com.ync365.seed.bussiness.modules.ad.entity.Navigation;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.service.ad.NavigationService;

public class NavigationServiceImpl implements NavigationService {

	@Autowired
	NavigationBiz navigationBiz ;
	
	@Override
	public ResponseDTO getNavigationList() {
		// TODO Auto-generated method stub

		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<Navigation> list =  navigationBiz.selectNavigationList();
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setData(list);
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			e.printStackTrace(); 
		}
		return dto;
	}

}
