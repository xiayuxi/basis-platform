package com.ync365.seed.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.user.biz.SysPopBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreDecorateInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreTextBiz;
import com.ync365.seed.bussiness.modules.user.bo.PopInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.PopLoginBO;
import com.ync365.seed.bussiness.modules.user.bo.PopLoginReturnBO;
import com.ync365.seed.bussiness.modules.user.bo.PopRegisterBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreDecorateBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysPopInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreDecorateInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreTextWithBLOBs;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.user.PopInfoDTO;
import com.ync365.seed.dto.user.PopLoginDTO;
import com.ync365.seed.dto.user.PopRegisterDTO;
import com.ync365.seed.dto.user.PopStoreDecorateDTO;
import com.ync365.seed.dto.user.PopStoreInfoDTO;
import com.ync365.seed.dto.user.PopStoreSearchDTO;
import com.ync365.seed.dto.user.PopStoreTextWithBLOBsDTO;
import com.ync365.seed.dto.user.ReturnPopInfoDTO;
import com.ync365.seed.service.annotation.FormTokenAnnotation;
import com.ync365.seed.service.user.IPOPService;
import com.ync365.seed.utils.StringUtils;

public class IPOPServiceImpl implements IPOPService {
    @Autowired
    private SysPopBiz sysPopBiz;
    @Autowired
    private SysPopStoreBiz sysPopStoreBiz;
    @Autowired
    private SysPopInfoBiz sysPopInfoBiz;
    @Autowired
    private SysPopStoreDecorateInfoBiz decorateBiz;
    @Autowired
    private SysPopStoreTextBiz popStoreTextBiz;

    @Override
    public ResponseDTO popLogin(PopLoginDTO popLoginDTO) {
        ResponseDTO dto = new ResponseDTO();
        PopLoginBO bo = new PopLoginBO();
        BeanUtils.copyProperties(popLoginDTO, bo);
        PopLoginReturnBO returnbo = sysPopBiz.popLogin(bo);
        if (returnbo != null && StringUtils.isNotEmpty(returnbo.getPopNum())) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(returnbo);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO updatePopRegister(PopRegisterDTO popRegisterDTO, String popNum) {
        ResponseDTO dto = new ResponseDTO();
        PopRegisterBO bo = new PopRegisterBO();
        BeanUtils.copyProperties(popRegisterDTO, bo);
        int n = sysPopBiz.updatePopRegister(bo, popNum);
        if (n == 1) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(n);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO getPopStoreInfo(String popStoreNum) {
        ResponseDTO dto = new ResponseDTO();
        PopStoreBO store = sysPopStoreBiz.searchPopStoreBOByPopStoreNum(popStoreNum);
        if (store != null && StringUtils.isNotEmpty(store.getPopStoreNum())) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(store);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO updatePopStoreInfo(PopStoreInfoDTO popStoreInfoDTO, String popStoreNum) {
        ResponseDTO dto = new ResponseDTO();
        PopStoreBO popStoreBo = new PopStoreBO();
        BeanUtils.copyProperties(popStoreInfoDTO, popStoreBo);
        int n = sysPopStoreBiz.updatePopStore(popStoreBo);
        if (n == 1) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(n);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO getPopInfo(String popNum) {
        ResponseDTO dto = new ResponseDTO();
        PopInfoBO bo = sysPopInfoBiz.getPopInfoBOByPopNum(popNum);
        if (bo != null && StringUtils.isNotEmpty(bo.getPopNum())) {
            ReturnPopInfoDTO returnPopInfoDTO = new ReturnPopInfoDTO();
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            BeanUtils.copyProperties(bo, returnPopInfoDTO);
            dto.setData(returnPopInfoDTO);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO getPopInfoByLoginName(String popLoginName) {
        ResponseDTO dto = new ResponseDTO();
        PopInfoBO bo = sysPopInfoBiz.getPopInfoByLoginName(popLoginName);
        if (bo != null && StringUtils.isNotEmpty(bo.getPopNum())) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(bo);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO getPopInfoByMobile(String mobile) {
        ResponseDTO dto = new ResponseDTO();
        PopInfoBO bo = sysPopInfoBiz.getPopInfoByMobile(mobile);
        if (bo != null && StringUtils.isNotEmpty(bo.getPopNum())) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(bo);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO updatePopInfo(PopInfoDTO popInfoDTO, String popNum) {
        ResponseDTO dto = new ResponseDTO();
        PopInfoBO bo = new PopInfoBO();
        BeanUtils.copyProperties(popInfoDTO, bo);
        bo.setPopNum(popNum);
        int n = sysPopInfoBiz.updatePopInfo(bo);
        if (n == 1) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(n);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO getPopStoreDecorateInfo(String popStoreNum) {
        ResponseDTO dto = new ResponseDTO();
        SysPopStoreDecorateInfo info = decorateBiz.selectByPopStoreNum(popStoreNum);
        if (info != null && StringUtils.isNotEmpty(info.getPopStoreNum())) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(info);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO updatePopStoreDecorateInfo(PopStoreDecorateDTO popStoreDecorateDTO) {
        ResponseDTO dto = new ResponseDTO();
        PopStoreDecorateBO info = new PopStoreDecorateBO();
        BeanUtils.copyProperties(popStoreDecorateDTO, info);
        int n = decorateBiz.updateByPopStoreNum(info);
        if (n == 1) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(n);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    @FormTokenAnnotation
    public ResponseDTO insertSelective(PopStoreDecorateDTO popStoreDecorateDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysPopStoreDecorateInfo info = new SysPopStoreDecorateInfo();
        BeanUtils.copyProperties(popStoreDecorateDTO, info);
        int n = decorateBiz.insertSelective(info);
        if (n == 1) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(n);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO getPopStoreBySearch(PopStoreSearchDTO popStoreSearchDTO) {
        ResponseDTO dto = new ResponseDTO();
        PopStoreSearchBO bo = new PopStoreSearchBO();
        bo.setPopStoreNameSearch(popStoreSearchDTO.getNameSearch());
        bo.setPageIndex(popStoreSearchDTO.getPageIndex());
        bo.setPageSize(popStoreSearchDTO.getPageSize());
        bo.setIsFrozenSearch(false);
        bo.setDel(false);
        bo.setPopStoreDecorateSearch(1);
        List<PopStoreBO> list = sysPopStoreBiz.selectPageBySearchBO(bo);
        if (list != null && list.size() > 0) {
            List<PopStoreBO> returnList = new ArrayList<>();
            for (PopStoreBO popStoreBO : list) {
                if (null != popStoreBO.getPopStoreDecorate() && 0 != popStoreBO.getPopStoreDecorate()) {
                    returnList.add(popStoreBO);
                }
            }
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(returnList);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO deletePopStoreTextWithBLOBs(Integer id) {
        ResponseDTO dto = new ResponseDTO();
        int n = popStoreTextBiz.deleteByPrimaryKey(id);
        if (n == 1) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(n);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    public ResponseDTO getPopStoreTextWithBLOBs(String popStoreNum) {
        ResponseDTO dto = new ResponseDTO();
        SysPopStoreTextWithBLOBs info = popStoreTextBiz.selectByPopStoreNum(popStoreNum);
        if (info != null && StringUtils.isNotEmpty(info.getPopStoreNum())) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(info);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    @Override
    @FormTokenAnnotation
    public ResponseDTO insertPopStoreTextWithBLOBs(PopStoreTextWithBLOBsDTO popStoreTextWithBLOBsDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysPopStoreTextWithBLOBs info = new SysPopStoreTextWithBLOBs();
        BeanUtils.copyProperties(popStoreTextWithBLOBsDTO, info);
        int n = popStoreTextBiz.insertSelective(info);
        if (n == 1) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(n);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

    /**
     * 功能描述：更新品牌优势和售后服务
     * @author liukai
     * @param popStoreTextWithBLOBsDTO
     * @return
     */
    @Override
    public ResponseDTO updateSysPopStoreTextWithBLOBs(PopStoreTextWithBLOBsDTO popStoreTextWithBLOBsDTO, Integer id) {
        ResponseDTO dto = new ResponseDTO();
        SysPopStoreTextWithBLOBs info = new SysPopStoreTextWithBLOBs();
        BeanUtils.copyProperties(popStoreTextWithBLOBsDTO, info);
        if (null != id) {
            info.setId(id);
        }
        int n = popStoreTextBiz.updateByPrimaryKeySelective(info);
        if (n == 1) {
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(n);
        } else {
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
        }
        return dto;
    }

}
