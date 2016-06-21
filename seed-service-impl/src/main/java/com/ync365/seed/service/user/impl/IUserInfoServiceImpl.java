package com.ync365.seed.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.user.biz.SysLcInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysReceiveGoodsInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysReceiveGoodsInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysVsInfo;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.base.ResponseListDTO;
import com.ync365.seed.dto.user.LcInfoDTO;
import com.ync365.seed.dto.user.SysReceiveGoodsInfoDTO;
import com.ync365.seed.dto.user.UserInfoDTO;
import com.ync365.seed.dto.user.UserSearchDTO;
import com.ync365.seed.dto.user.VsInfoDTO;
import com.ync365.seed.service.annotation.FormTokenAnnotation;
import com.ync365.seed.service.user.IUserInfoService;
import com.ync365.seed.utils.StringUtils;

public class IUserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private SysUserInfoBiz sysUserInfoBiz; //用户信息biz

    @Autowired
    SysVsInfoBiz sysVsInfoBiz;//vs 信息biz 

    @Autowired
    SysReceiveGoodsInfoBiz sysReceiveGoodsInfoBiz; //收货地址biz

    @Autowired
    SysLcInfoBiz sysLcInfoBiz;//lc  biz

    // TODO US获取用户信息 
    @Override
    public ResponseDTO getUserInfoByUserNum(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        //查询sysUserInfo信息
        UserInfoBO sysUserInfo = sysUserInfoBiz.getUserInfoAllData(userNum);
        if (sysUserInfo != null && StringUtils.isNotEmpty(sysUserInfo.getUserNum())) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(sysUserInfo);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }

        return dto;
    }

    // TODO US修改用户信息 （头像 昵称 性别 真实姓名 身份证号 出生日期）by userNum
    @Override
    public ResponseDTO updateUserInfoByUserNum(UserInfoDTO userInfoDto) {
        ResponseDTO dto = new ResponseDTO();
        UserInfoBO bo = new UserInfoBO();
        BeanUtils.copyProperties(userInfoDto, bo);
        bo.setSysNickname(userInfoDto.getSysUserName());
        bo.setSysUserRealName(userInfoDto.getSysUserRealName());
        bo.setSysUserAddress(userInfoDto.getSysUserAddress());
        int num = sysUserInfoBiz.updateUsInfo(bo);
        if (num == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(num);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }
        return dto;
    }

    // TODO US获取VS服务站信息 （联系人 联系电话 地址 服务区域）
    @Override
    public ResponseDTO getVsInfoByUserNum(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        SysVsInfo sysVsInfo = sysVsInfoBiz.selectSysVsInfoByUserNum(userNum);
        if (sysVsInfo != null && StringUtils.isNotEmpty(sysVsInfo.getUserNum())) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(sysVsInfo);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    // TODO VS获取帐号信息
    @Override
    public ResponseDTO getVsInfoByVsNum(String vsNum) {
        ResponseDTO dto = new ResponseDTO();
        VsInfoBO bo = sysVsInfoBiz.getUserInfoAllData(vsNum);
        if (bo != null && StringUtils.isNotEmpty(bo.getUserNum()) && StringUtils.isNotEmpty(bo.getName())) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(bo);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    // TODO VS修改帐号信息
    @Override
    public ResponseDTO updateVsInfoByUsNum(VsInfoDTO vsInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        VsInfoBO bo = new VsInfoBO();
        BeanUtils.copyProperties(vsInfoDTO, bo);
        int num = sysVsInfoBiz.updateVsInfo(bo);
        if (num == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(num);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }
        return dto;
    }

    // TODO US 获取收货地址列表
    @Override
    public ResponseDTO getSysReceiveGoodsInfoList(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        List<SysReceiveGoodsInfo> list = sysReceiveGoodsInfoBiz.selectPageByMap(map);
        if (list != null && list.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(list);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    // TODO US 添加收货地址
    @Override
    @FormTokenAnnotation
    public ResponseDTO insertSysReceiveGoodsInfo(SysReceiveGoodsInfoDTO sysReceiveGoodsInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysReceiveGoodsInfo record = new SysReceiveGoodsInfo();
        record.setUserNum(sysReceiveGoodsInfoDTO.getUserNum());
        record.setSysReceiveGoodsName(sysReceiveGoodsInfoDTO.getSysReceiveGoodsName());
        record.setSysReceiveGoodsMobile(sysReceiveGoodsInfoDTO.getSysReceiveGoodsMobile());
        record.setSysReceiveGoodsAddressDetail(sysReceiveGoodsInfoDTO.getSysReceiveGoodsAddressDetail());
        record.setSysReceiveGoodsAddress(sysReceiveGoodsInfoDTO.getSysReceiveGoodsAddress());
        int num = sysReceiveGoodsInfoBiz.insert(record);
        if (num == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(num);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }
        return dto;
    }

    // TODO US 编辑收货地址
    @Override
    public ResponseDTO getSysReceiveGoodsInfoById(Integer id) {
        ResponseDTO dto = new ResponseDTO();
        SysReceiveGoodsInfo record = sysReceiveGoodsInfoBiz.selectByPrimaryKey(id);
        if (record != null && StringUtils.isNotEmpty(record.getUserNum())) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(record);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    // TODO US 编辑保存收货地址
    @Override
    public ResponseDTO updateSysReceiveGoodsInfoById(SysReceiveGoodsInfoDTO sysReceiveGoodsInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysReceiveGoodsInfo record = new SysReceiveGoodsInfo();
        record.setId(sysReceiveGoodsInfoDTO.getId());
        record.setSysReceiveGoodsAddress(sysReceiveGoodsInfoDTO.getSysReceiveGoodsAddress());
        record.setSysReceiveGoodsAddressDetail(sysReceiveGoodsInfoDTO.getSysReceiveGoodsAddressDetail());
        record.setSysReceiveGoodsMobile(sysReceiveGoodsInfoDTO.getSysReceiveGoodsMobile());
        record.setSysReceiveGoodsName(sysReceiveGoodsInfoDTO.getSysReceiveGoodsName());
        int num = sysReceiveGoodsInfoBiz.updateByPrimaryKeySelective(record);
        if (num == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(num);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }
        return dto;
    }

    // TODO US删除收货地址
    @Override
    public ResponseDTO deleteSysReceiveGoodsInfo(Integer id) {
        ResponseDTO dto = new ResponseDTO();
        int num = sysReceiveGoodsInfoBiz.deleteByPrimaryKey(id);
        if (num == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(num);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }
        return dto;
    }

    // TODO US 设置默认收货地址
    @Override
    public ResponseDTO updateSysReceiveGoodsIsDefaultById(Integer id, String userNum) {
        ResponseDTO dto = new ResponseDTO();
        SysReceiveGoodsInfo record = new SysReceiveGoodsInfo();
        record.setId(id);
        record.setUserNum(userNum);
        int num = sysReceiveGoodsInfoBiz.updateSysReceiveGoodsIsDefaultById(record);
        if (num == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(num);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }
        return dto;
    }

    // TODO LC获取绑定VS数量
    @Override
    public ResponseDTO getConutVsByLcNum(String lcNum) {
        ResponseDTO dto = new ResponseDTO();
        VsInfoSearchBO bo = new VsInfoSearchBO();
        bo.setLcNum(lcNum);
        int count = sysVsInfoBiz.selectCountSysVsInfoByPrimary(bo);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setData(count);
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    // TODO LC获取绑定US数量
    @Override
    public ResponseDTO getConutUsByLcNum(String lcNum) {
        ResponseDTO dto = new ResponseDTO();
        UserUsInfoSearchBo bo = new UserUsInfoSearchBo();
        bo.setLcNum(lcNum);
        int num = sysUserInfoBiz.selectUserInfoByParamCount(bo);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setData(num);
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    // TODO LC获取绑定VS列表  包含查询
    @Override
    public ResponseDTO getSysVsInfoListByLcNum(UserSearchDTO userSearchDTO) {
        ResponseDTO dto = new ResponseDTO();
        ResponseListDTO listDto = new ResponseListDTO();
        VsInfoSearchBO bo = new VsInfoSearchBO();
        bo.setLcNum(userSearchDTO.getLcNum());
        bo.setBeginTimeSearch(userSearchDTO.getBeginTime());
        bo.setEndTimeSearch(userSearchDTO.getEndTime());
        bo.setNameSearch(userSearchDTO.getSysUserRealName());
        bo.setUserMobileSearch(userSearchDTO.getUserMobile());
        bo.setPageIndex(userSearchDTO.getPageIndex());
        bo.setPageSize(userSearchDTO.getPageSize());
        List<VsInfoBO> list = sysVsInfoBiz.selectSysVsInfoListByPrimary(bo);
        int num = sysVsInfoBiz.selectCountSysVsInfoByPrimary(bo);
        if (list != null && list.size() > 0) {
            listDto.setCount(num);
            listDto.setDatas(list);
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(listDto);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    // TODO LC获取绑定US列表
    // TODO LC条件查询绑定US列表（姓名 手机号 注册时间 所属VS 所属LC）
    @Override
    public ResponseDTO getSysUsInfoListByLcNum(UserSearchDTO userSearchDTO) {
        ResponseDTO dto = new ResponseDTO();
        ResponseListDTO listDto = new ResponseListDTO();
        UserSearchBO record = new UserSearchBO();
        record.setSysUserRealName(userSearchDTO.getSysUserRealName());
        UserUsInfoSearchBo bo = new UserUsInfoSearchBo();
        bo.setCreateTimeStart(userSearchDTO.getBeginTime());
        bo.setCreateTimeEnd(userSearchDTO.getEndTime());
        bo.setVsNum(userSearchDTO.getVsNum());
        bo.setUserRealName(userSearchDTO.getSysUserRealName());
        bo.setUserMobile(userSearchDTO.getUserMobile());
        bo.setVsName(userSearchDTO.getVsName());
        bo.setPageIndex(userSearchDTO.getPageIndex());
        bo.setPageSize(userSearchDTO.getPageSize());
        bo.setLcNum(userSearchDTO.getLcNum());
        List<UserInfoBO> list = sysUserInfoBiz.selectUserInfoByParam(bo);
        int num = sysUserInfoBiz.selectUserInfoByParamCount(bo);
        listDto.setCount(num);
        listDto.setDatas(list);
        if (list != null && list.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(listDto);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    // TODO LC获取帐号信息
    @Override
    public ResponseDTO getSysLcInfoByLcNum(String lcNum) {
        ResponseDTO dto = new ResponseDTO();
        LcInfoBO bo = sysLcInfoBiz.getUserInfoAllData(lcNum);
        if (bo != null && StringUtils.isNotEmpty(bo.getUserNum()) && StringUtils.isNotEmpty(bo.getName())) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(bo);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    // TODO LC修改帐号信息
    @Override
    public ResponseDTO updateSysLcInfoByLcNum(LcInfoDTO lcInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        LcInfoBO bo = new LcInfoBO();
        BeanUtils.copyProperties(lcInfoDTO, bo);
        int num = sysLcInfoBiz.updateSysLcInfo(bo);
        if (num == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(num);
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }

        return dto;
    }

    // TODO VS获取绑定US数量
    @Override
    public ResponseDTO getCountSysUsByVsNum(String vsNum) {
        ResponseDTO dto = new ResponseDTO();
        UserUsInfoSearchBo bo = new UserUsInfoSearchBo();
        bo.setVsNum(vsNum);
        int num = sysUserInfoBiz.selectUserInfoByParamCount(bo);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setData(num);
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        return dto;
    }

    // TODO VS获取绑定US列表
    @Override
    public ResponseDTO selectSysUsListByVsNum(UserSearchDTO userSearchDTO) {
        ResponseDTO dto = new ResponseDTO();
        //存放list 列表  及 count 
        ResponseListDTO listDTO = new ResponseListDTO();
        UserUsInfoSearchBo bo = new UserUsInfoSearchBo();
        bo.setCreateTimeStart(userSearchDTO.getBeginTime());
        bo.setCreateTimeEnd(userSearchDTO.getEndTime());
        bo.setVsNum(userSearchDTO.getVsNum());
        bo.setUserRealName(userSearchDTO.getSysUserRealName());
        bo.setUserMobile(userSearchDTO.getUserMobile());
        bo.setVsName(userSearchDTO.getVsName());
        bo.setPageIndex(userSearchDTO.getPageIndex());
        bo.setPageSize(userSearchDTO.getPageSize());
        List<UserInfoBO> list = sysUserInfoBiz.selectUserInfoByParam(bo);
        int num = sysUserInfoBiz.selectUserInfoByParamCount(bo);
        listDTO.setDatas(list);
        listDTO.setCount(num);
        if (list != null && list.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setData(listDTO);
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }
        return dto;
    }

}
