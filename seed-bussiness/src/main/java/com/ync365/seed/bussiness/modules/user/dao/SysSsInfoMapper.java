package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.bo.SsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.SsInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysSsInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SysSsInfoMapper {
    int deleteByPrimaryKey(String userNum);

    int insertSelective(SysSsInfo record);

    SysSsInfo selectByPrimaryKey(String userNum);

    int updateByPrimaryKeySelective(SysSsInfo record);

    List<SysSsInfo> selectPageByMap(Map<String, Object> map);

    SysSsInfo getSsInfoByMobileOrNum(String mobileOrNum);

    List<SsInfoBO> selectSsInfoByParam(SsInfoSearchBO ssInfoSearchBO);

    int selectSsInfoByParamCount(SsInfoSearchBO ssInfoSearchBO);

}