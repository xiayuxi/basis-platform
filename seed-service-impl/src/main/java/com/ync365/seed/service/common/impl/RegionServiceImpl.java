package com.ync365.seed.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.common.biz.RegionBiz;
import com.ync365.seed.bussiness.modules.common.entity.Region;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.service.common.RegionService;

public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionBiz regionBiz;

    public ResponseDTO getRegion(String code) {
        ResponseDTO dto = new ResponseDTO();
        Region region = regionBiz.selectRegionByCode(code);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData(region);
        return dto;
    }

    public ResponseDTO getRegionProvinceCityList() {
        // TODO Auto-generated method stub
        ResponseDTO dto = new ResponseDTO();
        List<Region> list = regionBiz.selectRegionProvinceCityList();
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData(list);
        return dto;
    }

    public ResponseDTO getRegionProvinceCityCountyList() {
        ResponseDTO dto = new ResponseDTO();
        List<Region> list = regionBiz.selectRegionProvinceCityCountyList();
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData(list);
        return dto;
    }

    @Override
    public ResponseDTO getRegionByPId(String pId) {
        ResponseDTO dto = new ResponseDTO();
        List<Region> regions = regionBiz.selectRegionByParentId(Integer.valueOf(pId));
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData(regions);
        return dto;
    }

    @Override
    public ResponseDTO getRegionAndParentRegionById(Integer id) {
        ResponseDTO dto = new ResponseDTO();
        Region[] regions = regionBiz.selectRegionAndParentRegionById(id);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData(regions);
        return dto;
    }

}
