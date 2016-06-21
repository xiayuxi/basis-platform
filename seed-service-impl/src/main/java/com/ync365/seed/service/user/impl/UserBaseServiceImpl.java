package com.ync365.seed.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.common.biz.FormTokenBiz;
import com.ync365.seed.bussiness.modules.common.biz.MobileValidateCodeBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysDoWorkBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysGrowKindBiz;
import com.ync365.seed.bussiness.modules.user.entity.SysDoWork;
import com.ync365.seed.bussiness.modules.user.entity.SysGrowKind;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.user.UserMobileCodeDTO;
import com.ync365.seed.service.user.IUserBaseService;
import com.ync365.seed.utils.StringUtils;

public class UserBaseServiceImpl implements IUserBaseService {
    @Autowired
    private MobileValidateCodeBiz biz;
    @Autowired
    private SysGrowKindBiz growKindbiz;
    @Autowired
    private SysDoWorkBiz doWorkBiz;
    @Autowired
    private FormTokenBiz tokenBiz;

    @Override
    public ResponseDTO getMobileValidateCode(String mobile) {
        ResponseDTO dto = new ResponseDTO();
        String validateCode = biz.getMobileValidateCode(mobile);
        if (StringUtils.isNotEmpty(validateCode)) {
            dto.setData(validateCode);
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO sendValidateCode(UserMobileCodeDTO userMobileCodeDTO) {
        ResponseDTO dto = new ResponseDTO();
        String validateCode = biz.sendValidateCode(userMobileCodeDTO.getMobile());
        if (StringUtils.isNotEmpty(validateCode)) {
            dto.setData(validateCode);
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO validateMobileAndCode(UserMobileCodeDTO userMobileCodeDTO) {
        ResponseDTO dto = new ResponseDTO();
        Boolean result = biz.validateMobileValidateCode(userMobileCodeDTO.getMobile(), userMobileCodeDTO.getCode());
        if (result) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(result);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.WRONG_VALID_CODE.v());
            dto.setData(result);
            dto.setMsg(ResponseCode.UserCode.WRONG_VALID_CODE.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO getGrowKind() {
        ResponseDTO dto = new ResponseDTO();
        List<SysGrowKind> list = growKindbiz.getGrowKind();
        if (list != null && list.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(list);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO getDoWork() {
        ResponseDTO dto = new ResponseDTO();
        List<SysDoWork> list = doWorkBiz.getDoWork();
        if (list != null && list.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(list);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }

        return dto;
    }

    @Override
    public ResponseDTO getToken() {
        ResponseDTO dto = new ResponseDTO();
        String token = tokenBiz.getToken();
        if (StringUtils.isNotEmpty(token)) {
            dto.setData(token);
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }

        return dto;
    }

}
