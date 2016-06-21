package com.ync365.seed.bussiness.modules.user.biz;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.util.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.SsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.SsInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysSsInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysSsPopStoreRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;
import com.ync365.seed.bussiness.modules.user.entity.SysSsInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysSsPopStoreRelationship;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;
import com.ync365.seed.utils.MD5Utils;
import com.ync365.seed.utils.StringUtils;

@Service
public class SysSsInfoBiz {
    @Autowired
    private SysSsInfoMapper sysSsInfoMapper;
    
    @Autowired
    SysPopStoreMapper sysPopStoreMapper;
    
    @Autowired
    SysSsPopStoreRelationshipMapper sysSsPopStoreRelationshipMapper;

    public int deleteByPrimaryKey(String userNum) {
        return sysSsInfoMapper.deleteByPrimaryKey(userNum);
    }

    /**
     * 添加ss信息，密码默认为123456
     * @Title: insertSelective
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月12日 下午4:36:41       
     * @version: 
     *
     * @param record
     * @return
     *
     */
    public int insertSelective(SysSsInfo record) {
        record.setCreateTime(Calendar.getInstance().getTime());
        record.setUserNum(NumGenerator.getSSNum());
        record.setUserPassword(MD5Utils.getMD5Str("123456"));
        record.setSafeScore(0);
        record.setIsFrozen(false);
        return sysSsInfoMapper.insertSelective(record);
    }

    public SysSsInfo selectByPrimaryKey(String userNum) {
        return sysSsInfoMapper.selectByPrimaryKey(userNum);
    }

    /**
     * 修改ss信息
     * @Title: updateByPrimaryKeySelective
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月12日 下午4:37:57       
     * @version: 
     *
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(SysSsInfo record) {
        if (StringUtils.isNotEmpty(record.getUserPassword())) {
            record.setUserPassword(MD5Utils.getMD5Str(record.getUserPassword()));
        }
        return sysSsInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 手机号或编号获取ss信息
     * @Title: getSsInfoByMobileOrNum
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月12日 下午4:36:20       
     * @version: 
     *
     * @param mobileOrNum
     * @return
     *
     */
    public SysSsInfo getSsInfoByMobileOrNum(String mobileOrNum) {

        SysSsInfo info= sysSsInfoMapper.getSsInfoByMobileOrNum(mobileOrNum);
        if(info!=null){
            info.setUserPassword("");
        }
        return info;
    }

    /**
     * 获取ss列表信息
     * @param ssInfoSearchBO
     * @return
     */
    public List<SsInfoBO> selectSsInfoByParam(SsInfoSearchBO ssInfoSearchBO) {
        List<SsInfoBO> list = sysSsInfoMapper.selectSsInfoByParam(ssInfoSearchBO);
        if(null != list && list.size() > 0){
            for(SsInfoBO temp : list){
                List<PopStoreBO> sysPopStoreList = sysPopStoreMapper.selectPopStoreBySsUserNum(temp.getUserNum());
                if(null != sysPopStoreList && sysPopStoreList.size() > 0){
                    temp.setSysPopStoreList(sysPopStoreList);
                }else{
                    temp.setSysPopStoreList(null);
                }
            }
        }
        return list;
    }

    /**
     * 获取ss列表信息 count
     * @param ssInfoSearchBO
     * @return
     */
    public int selectSsInfoByParamCount(SsInfoSearchBO ssInfoSearchBO) {
        return sysSsInfoMapper.selectSsInfoByParamCount(ssInfoSearchBO);
    }

    /**
     * 激活 冻结 
     * @param record2
     * @return
     */
    public int updateFrozenPrimaryKey(SysSsInfo record) {
        return sysSsInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 重置密码
     * @param userNum
     * @return
     */
    public int updateSsInfoPasswordPrimaryKey(String userNum) {
        SysSsInfo record = new SysSsInfo();
        record.setUserNum(userNum);
        record.setUserPassword(MD5Utils.getMD5Str("123456"));
        record.setSafeScore(0);
        return sysSsInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 获取popStore 信息
     * @param userNumOrMobileSearch
     * @return
     */
    public SysPopStore selectPopStoreByPrimaryKey(String userNumOrMobileSearch) {
        return sysPopStoreMapper.selectPopStoreByPrimaryKey(userNumOrMobileSearch);
    }

    /**
     * 
     * @param userNum
     * @param popStoreNum 
     */
    public int updateDistributionByPrimary(String userNum, String popStoreNum) {
        SysSsPopStoreRelationship sysSsPopStoreRelationship =  new SysSsPopStoreRelationship();
        sysSsPopStoreRelationship.setPopStoreNum(popStoreNum);
        sysSsPopStoreRelationship.setSsNum(userNum);
        int b = sysSsPopStoreRelationshipMapper.insertSelective(sysSsPopStoreRelationship) ;
        return b ;
    }

    /**
     * 方法为 编辑 ssinfo 信息   
     * 1 封装对象 更新 ssinfo  
     * 2 先删除 ssinfo 信息 与 popstore 关联  执行 sysSsPopStoreRelationshipMapper.deleteByPrimaryKey(temp);
     * 3 重新插入 关联信息 执行  sysSsPopStoreRelationshipMapper.insertSelective(sysSsPopStoreRelationship);
     * 关联表：  sys_ss_pop_store_relationship 
     * @param record
     * @param ssInfoSearchBO
     * @return
     */
    @Transactional
    public int updateSsInfoAndPopStoreRelationship(SysSsInfo record, SsInfoSearchBO ssInfoSearchBO) {
        //创建SysSsPopStoreRelationship 对象
        SysSsPopStoreRelationship sysSsPopStoreRelationship = new SysSsPopStoreRelationship();
        sysSsPopStoreRelationship.setSsNum(record.getUserNum());
        //更新ssinfo信息
        int a = sysSsInfoMapper.updateByPrimaryKeySelective(record);
        
        //删除已有的关系
        List<PopStoreBO> sysPopStoreList = sysPopStoreMapper.selectPopStoreBySsUserNum(record.getUserNum());
        if(null != sysPopStoreList && sysPopStoreList.size() > 0 ){
            for(PopStoreBO tt : sysPopStoreList){
                if(null != tt.getSsPopStoreRelationshipId()){
                    sysSsPopStoreRelationshipMapper.deleteByPrimaryKey(tt.getSsPopStoreRelationshipId());
                }
            }
        }
        //重新添加新的关系
        if(null != ssInfoSearchBO ){
            if(null != ssInfoSearchBO.getPopStoreNums() && ssInfoSearchBO.getPopStoreNums().size() > 0){
                //在重新 插入 ssinfo与 pop_Store  关系
                for(String str : ssInfoSearchBO.getPopStoreNums()){
                    if(null != str && "" != str){
                        sysSsPopStoreRelationship.setPopStoreNum(str);
                        sysSsPopStoreRelationshipMapper.insertSelective(sysSsPopStoreRelationship);
                    }else{
                        continue;
                    }
                }
            }
        }
        return a;
    }

    /**
     * 方法为添加ssInfo信息 
     * 1 添加 ssinfo信息  record
     * 2 添加ssinfo 与 popstore 相关联信息    sysSsPopStoreRelationshipMapper.insertSelective(sysSsPopStoreRelationship);
     * 关联表：  sys_ss_pop_store_relationship 
     * @param record
     * @param ssInfoSearchBO
     * @return
     */
    public int insertSsInfoAndPopStoreRelationship(SysSsInfo record, SsInfoSearchBO ssInfoSearchBO) {
        //获取 ss编号
        String userNum = NumGenerator.getSSNum();
        record.setCreateTime(Calendar.getInstance().getTime());
        record.setUserNum(userNum);
        record.setUserPassword(MD5Utils.getMD5Str("123456"));
        record.setSafeScore(0);
        record.setIsFrozen(false);//默认激活
        
        //创建SysSsPopStoreRelationship 对象
        SysSsPopStoreRelationship sysSsPopStoreRelationship = new SysSsPopStoreRelationship();
        sysSsPopStoreRelationship.setSsNum(userNum);
        
        //添加ssinfo信息
        int a = sysSsInfoMapper.insertSelective(record);
        //添加ssinfo 与popstore 对应关系
        if(null != ssInfoSearchBO ){
            if(null != ssInfoSearchBO.getPopStoreNums() && ssInfoSearchBO.getPopStoreNums().size() > 0){
                //在重新 插入 ssinfo与 pop_Store  关系
                for(String str : ssInfoSearchBO.getPopStoreNums()){
                    if(null != str && "" != str){
                        sysSsPopStoreRelationship.setPopStoreNum(str);
                        sysSsPopStoreRelationshipMapper.insertSelective(sysSsPopStoreRelationship);
                    }else{
                        continue;
                    }
                }
            }
        }
        return a;
    }

}
