package com.ync365.seed.service.ad.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.ad.biz.AdBiz;
import com.ync365.seed.bussiness.modules.ad.entity.Ad;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.service.ad.AdService;

public class AdServiceImpl implements  AdService {


	@Autowired
	AdBiz adBiz ;
	
	public ResponseDTO getAdList(int pageSize,int adPosition ) {
		
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<Ad> list =  adBiz.selectAdList(pageSize, adPosition) ;
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setData(list);
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			e.printStackTrace(); 
		}
		return dto;
	}

}
