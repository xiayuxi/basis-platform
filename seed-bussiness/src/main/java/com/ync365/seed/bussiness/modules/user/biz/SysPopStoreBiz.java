package com.ync365.seed.bussiness.modules.user.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ync365.seed.bussiness.modules.goods.dao.BrandDao;
import com.ync365.seed.bussiness.modules.goods.entity.Brand;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreSearchBO;
import com.ync365.seed.bussiness.modules.user.dao.SysPopInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysPopMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreBrandRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreCategoryRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreDecorateInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreSeRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysPop;
import com.ync365.seed.bussiness.modules.user.entity.SysPopInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreBrandRelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreCategoryRelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreDecorateInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreSeRelationship;
import com.ync365.seed.bussiness.modules.user.util.ListUtil;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;
import com.ync365.seed.utils.MD5Utils;
import com.ync365.seed.utils.StringUtils;

/**
 * 功能描述：
 * @author liukai
 * @date 2015年11月3日 下午8:09:24 
 * @version 1.0
 */
@Service
public class SysPopStoreBiz {
    @Autowired
    private SysPopStoreMapper sysPopStoreMapper;
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private SysPopStoreBrandRelationshipMapper brandRelationshipMapper;
    @Autowired
    private SysPopStoreCategoryRelationshipMapper categoryRelationshipMapper;
    @Autowired
    private SysPopMapper sysPopMapper;
    @Autowired
    private SysPopInfoMapper sysPopInfoMapper;
    @Autowired
    private SysPopStoreSeRelationshipMapper seRelationshipMapper;
    @Autowired
    private SysPopStoreDecorateInfoMapper decorateInfoMapper;

    private final Integer SUCCESS = 1;

    private final Integer FAIL = 0;

    /**
     *编号 popStoreNum 获取信息 
     * 
     * @param popStoreNum
     * @return
     */
    public SysPopStore selectByPrimaryKey(String popStoreNum) {
        return sysPopStoreMapper.selectByPrimaryKey(popStoreNum);
    }

    /**
     * 编号 popStoreNum 删除信息，物理删除
     * 
     * @param popStoreNum
     * @return
     */
    public int deleteByPrimaryKey(String popStoreNum) {
        return sysPopStoreMapper.deleteByPrimaryKey(popStoreNum);
    }

    /**
     * 通过 popStoreNum 根据编号修改信息
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(SysPopStore record) {
        return sysPopStoreMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 多功能查询 所有List<SysPopStoreMapper> 分页添加pageIndex,pageSize参数
     * 
     * @param map
     * @return
     */
    public List<SysPopStore> selectPageByMap(Map<String, Object> map) {
        return sysPopStoreMapper.selectPageByMap(map);
    }

    /**
     * 条件获取数量
     * 
     * @param map
     * @return
     */
    public int selectPageCount(Map<String, Object> map) {
        return sysPopStoreMapper.selectPageCount(map);
    }

    /**
     * 根据popStoreNum/id查询popStoreName
     */
    public SysPopStore selectPopStoreByNum(PopStoreBO pb) {
        List<SysPopStore> list = null;
        SysPopStore SysPopStore = null;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popStoreNum", pb);
        list = sysPopStoreMapper.selectPageByMap(map);
        if (!list.isEmpty()) {
            SysPopStore = list.get(0);
        } else {
            SysPopStore = new SysPopStore();
        }
        return SysPopStore;
    }

    /**
     * 根据查询条件返回结果
     * @param popStoreSearchBo
     * @return
     */
    public List<PopStoreBO> selectPageBySearchBO(PopStoreSearchBO popStoreSearchBo) {
        popStoreSearchBo.setDel(false);
        List<PopStoreBO> PopStoreBOs = sysPopStoreMapper.selectPageBySearchBO(popStoreSearchBo);
        return PopStoreBOs;
    }

    /**
     * 根据查询条件获取查询总条数
     * @param popStoreSearchBo
     * @return
     */
    public long selectPageBySearchCount(PopStoreSearchBO popStoreSearchBo) {
        return sysPopStoreMapper.selectPageBySearchCount(popStoreSearchBo);
    }

    /**
     * 功能描述：根据店铺编号，查询  供应商店铺信息  和  供应商信息
     * @param popStoreNum
     * @return
     */
    public PopStoreBO searchPopStoreBOByPopStoreNum(String popStoreNum) {
        PopStoreSearchBO searchBO = new PopStoreSearchBO();
        searchBO.setPopStoreNumSearch(popStoreNum);
        List<PopStoreBO> list = selectPageBySearchBO(searchBO);
        PopStoreBO bo = (list != null && list.size() > 0) ? list.get(0) : null;
        if (bo != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("popStoreNum", popStoreNum);
            List<SysPopStoreBrandRelationship> listBrandIds = brandRelationshipMapper.selectPageByMap(map);
            if (listBrandIds != null && listBrandIds.size() > 0) {
                bo.setBrands(new ArrayList<String>());
                for (SysPopStoreBrandRelationship sysPopStoreBrandRelationship : listBrandIds) {
                    bo.getBrands().add(String.valueOf(sysPopStoreBrandRelationship.getBrandId()));
                }
            }
            List<SysPopStoreCategoryRelationship> listCategoryIds = categoryRelationshipMapper.selectPageByMap(map);
            if(listCategoryIds!=null&&listCategoryIds.size()>0){
                bo.setCategorys(new ArrayList<String>());
                for (SysPopStoreCategoryRelationship sysPopStoreCategoryRelationship : listCategoryIds) {
                    bo.getCategorys().add(String.valueOf(sysPopStoreCategoryRelationship.getCategoryId()));
                }
            }

        }
        return bo;
    }

    /**
     * 保存修改
     * @param popStoreBO
     */
    public int updatePopStore(PopStoreBO popStoreBO) {
        String popStorePrincipaNum = popStoreBO.getPopStorePrincipaNum();
        String popStoreNum = popStoreBO.getPopStoreNum();

        //保存修改的负责人
        updateSeRelation(popStoreNum, popStorePrincipaNum);
        //保存修改品类
        updateCategory(popStoreNum, popStoreBO.getCategorys());
        //保存修改的品牌
        updateBrand(popStoreNum, popStoreBO.getBrands());
        //修改供应商账号
        updateLoginNameByPopStoreNum(popStoreBO);

        SysPopStore sysPopStore = new SysPopStore();
        BeanUtils.copyProperties(popStoreBO, sysPopStore);
        return sysPopStoreMapper.updateByPrimaryKeySelective(sysPopStore);
    }

    public int selectPopStroeDsSpFsByByParamCount(PopStoreSearchBO popStoreSearchBo) {
        return sysPopStoreMapper.selectPopStroeDsSpFsByByParamCount(popStoreSearchBo);
    }

    /**
     * 根据ss 编号查询 ss关联 的popstore列表信息
     * @param userNum
     * @return
     */
    public List<PopStoreBO> selectPopStoreBySsUserNum(String userNum) {
        return sysPopStoreMapper.selectPopStoreBySsUserNum(userNum);
    }

    /**
     * 功能描述：根据id查询
     * @author liukai
     * @param id
     * @return
     */
    public PopStoreBO selectPopStoreById(Integer id) {
        SysPopStore sysPopStore = null;
        PopStoreBO popStoreBO = new PopStoreBO();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        List<SysPopStore> list = sysPopStoreMapper.selectPageByMap(map);
        if (!list.isEmpty()) {
            sysPopStore = list.get(0);
            BeanUtils.copyProperties(sysPopStore, popStoreBO);
        } else {
            popStoreBO = new PopStoreBO();
        }
        return popStoreBO;
    }

    /**
     * 功能描述：根据 品牌名称 查询品牌
     * @author liukai
     * @return
     */
    public Brand selectBrandByChName(String chName) {
        return brandDao.selectByChName(chName);
    }

    /**
     * 功能描述：根据 popStoreNum 获取 Brand
     * @author liukai
     * @param popStoreNum
     * @return
     */
    public List<Brand> selectBrandByBrandIds(String popStoreNum) {
        List<Brand> list = brandDao.selectBrandByPopStoreNum(popStoreNum);
        return list != null ? list : new ArrayList<Brand>();
    }

    /**
     * 根据类型 DS FS popstore name查询 list
     * @param record
     * @return
     */
    public List<SysPopStore> selectPopStoreByStoreNamePrimary(PopStoreSearchBO record) {
        return sysPopStoreMapper.selectPopStoreByStoreNamePrimary(record);
    }

    /**
     * 功能描述：根据 popStore 和 seNum 更新 
     * @author liukai
     * @param popStoreNum
     * @param seNum
     * @return
     */
    public int updateSeRelationByPopStreNumAndSeNum(String popStoreNum, String seNum) {
        SysPopStoreSeRelationship seRelationship = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popStoreNum", popStoreNum);
        map.put("seNum", seNum);
        return seRelationshipMapper.updateByPrimaryKeySelective(seRelationship);
    }

    /**
     * 功能描述：修改 popStore 的品类
     * @author liukai
     * @param popStoreNum
     * @param popStoreCategorys
     * @return
     */
    public int updateCategory(String popStoreNum, List<String> popStoreCategorys) {

        if (null == popStoreCategorys) {
            popStoreCategorys = new ArrayList<String>();
        }
        if (null != popStoreCategorys) {
            List<String> categorysTemp = popStoreCategorys;
            List<String> categorys = new ArrayList<String>();
            for (String categoryId : categorysTemp) {
                if (null != categoryId) {
                    categorys.add(categoryId);
                }
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("popStoreNum", popStoreNum);
            List<SysPopStoreCategoryRelationship> categoryRelations = categoryRelationshipMapper.selectPageByMap(map);
            List<String> categoryStringIds = new ArrayList<String>();
            for (int i = 0; i < categoryRelations.size(); i++) {
                categoryStringIds.add(categoryRelations.get(i).getCategoryId().toString());
            }

            if (categorys.size() > categoryRelations.size()) {
                //新增
                List<String> categoryIncreaseIds = ListUtil.getStringUniqueIds(categorys, categoryStringIds);
                for (int j = 0; j < categoryIncreaseIds.size(); j++) {
                    SysPopStoreCategoryRelationship categoryTemp = new SysPopStoreCategoryRelationship();
                    categoryTemp.setCategoryId(Integer.parseInt(categoryIncreaseIds.get(j)));
                    categoryTemp.setPopStoreNum(popStoreNum);
                    categoryRelationshipMapper.insertSelective(categoryTemp);
                }
            } else if (categorys.size() < categoryRelations.size()) {
                //减少
                List<String> categoryReduceIds = ListUtil.getStringUniqueIds(categorys, categoryStringIds);
                for (int i = 0; i < categoryReduceIds.size(); i++) {
                    categoryRelationshipMapper.deleteByPopstoreNumAndCategoryId(popStoreNum, categoryReduceIds.get(i));
                }
            }
        }
        return SUCCESS;
    }

    /**
     * 功能描述：更新品牌
     * @author liukai
     * @param popStoreNum
     * @param popStoreBrnads
     * @return
     */
    public int updateBrand(String popStoreNum, List<String> popStoreBrnads) {
        List<String> brands = new ArrayList<String>();

        if (null == popStoreBrnads) {
            popStoreBrnads = new ArrayList<String>();
        }
        if (null != popStoreBrnads) {
            //新增
            List<String> popBrandIds = new ArrayList<String>();
            List<String> brandsTemps = popStoreBrnads;
            for (String brandStr : brandsTemps) {
                if (null != brandStr) {
                    popBrandIds.add(brandStr);
                }
            }

            List<Brand> brandsByRelation = brandDao.selectBrandByPopStoreNum(popStoreNum);
            for (Brand brandStr : brandsByRelation) {
                brands.add(brandStr.getBrandId().toString());
            }

            List<String> brandIncreaseIds = ListUtil.getStringUniqueIds(popBrandIds, brands);
            if (popBrandIds.size() > brandsByRelation.size()) {
                for (int i = 0; i < brandIncreaseIds.size(); i++) {
                    SysPopStoreBrandRelationship brandRelation = new SysPopStoreBrandRelationship();
                    brandRelation.setPopStoreNum(popStoreNum);
                    brandRelation.setBrandId(Integer.parseInt(brandIncreaseIds.get(i)));
                    brandRelationshipMapper.insertSelective(brandRelation);
                }
            } else if (popBrandIds.size() < brandsByRelation.size()) {
                //减少
                for (int i = 0; i < brandIncreaseIds.size(); i++) {
                    brandRelationshipMapper.deleteByPopStoreNumAndBrandId(popStoreNum, brandIncreaseIds.get(i));
                }
            }
        }
        return SUCCESS;
    }

    /**
     * 功能描述：更新 负责人
     * @author liukai
     * @param popStoreNum
     * @param popStorePrincipaNum
     * @return
     */
    public int updateSeRelation(String popStoreNum, String popStorePrincipaNum) {
        SysPopStoreSeRelationship seRelationship = null;
        SysPopStoreSeRelationship seRelation = seRelationshipMapper.selectByPrimaryKey(popStoreNum);
        if (null != seRelation && StringUtils.isNotBlank(popStorePrincipaNum)) {
            seRelationship = new SysPopStoreSeRelationship();
            seRelationship.setPopStoreNum(popStoreNum);
            seRelationship.setSeNum(popStorePrincipaNum);
            return seRelationshipMapper.updateByPrimaryKeySelective(seRelationship);
        } else {
            if (StringUtils.isNotBlank(popStorePrincipaNum)) {
                seRelationship = new SysPopStoreSeRelationship();
                seRelationship.setPopStoreNum(popStoreNum);
                seRelationship.setSeNum(popStorePrincipaNum);
                return seRelationshipMapper.insertSelective(seRelationship);
            } else {
                return FAIL;
            }
        }
    }

    /**
     * 功能描述：绑定se负责人
     * @author liukai
     * @param popStoreNum
     * @param seNum
     * @return
     */
    public int insertSeRelation(String popStoreNum, String seNum) {
        List<SysPopStoreSeRelationship> list = null;
        SysPopStoreSeRelationship seRelationship = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popStoreNum", popStoreNum);
        list = seRelationshipMapper.selectSeRelationshipByMap(map);
        if (null != list) {
            seRelationship = list.get(0);
            seRelationship.setSeNum(seNum);
            return seRelationshipMapper.updateByPrimaryKeySelective(seRelationship);
        } else {
            return seRelationshipMapper.insertSelective(seRelationship);
        }
    }

    /**
     * 功能描述：查询se负责人
     * @author liukai
     * @param popStoreNum
     * @param seNum
     * @return
     */
    public SysPopStoreSeRelationship selectSeRelation(String popStoreNum, String seNum) {
        List<SysPopStoreSeRelationship> list = null;
        SysPopStoreSeRelationship seRelationship = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popStoreNum", popStoreNum);
        map.put("seNum", seNum);
        list = seRelationshipMapper.selectSeRelationshipByMap(map);
        if (!list.isEmpty()) {
            seRelationship = list.get(0);
        } else {
            seRelationship = new SysPopStoreSeRelationship();
        }
        return seRelationship;
    }

    /**
     * 添加信息
     * 
     * @param popStore
     * @return
     */
    public int insertPopStoreBO(PopStoreBO popStoreBO) {
        popStoreBO.setPopStoreNum(NumGenerator.getPoPNum());
        List<String> brandIdArr = popStoreBO.getBrands();
        List<String> categoryArr = popStoreBO.getCategorys();
        SysPopStoreBrandRelationship brandRelationship = null;
        SysPopStoreCategoryRelationship categoryRelationship = null;
        String popStoreNum = popStoreBO.getPopStoreNum();

        //保存品牌id
        if (brandIdArr != null) {
            for (int i = 0; i < brandIdArr.size(); i++) {
                brandRelationship = new SysPopStoreBrandRelationship();
                brandRelationship.setPopStoreNum(popStoreBO.getPopStoreNum());
                brandRelationship.setBrandId(Integer.parseInt(brandIdArr.get(i)));
                brandRelationshipMapper.insertSelective(brandRelationship);
            }
        }

        //保存类别
        if (brandIdArr != null) {
            for (int i = 0; i < categoryArr.size(); i++) {
                categoryRelationship = new SysPopStoreCategoryRelationship();
                categoryRelationship.setCategoryId(Integer.parseInt(categoryArr.get(i)));
                categoryRelationship.setPopStoreNum(popStoreBO.getPopStoreNum());
                categoryRelationshipMapper.insertSelective(categoryRelationship);
            }
        }

        //保存SE负责人
        if (StringUtils.isNotBlank(popStoreBO.getPopStoreName())) {
            if (StringUtils.isNotBlank(popStoreBO.getPopStorePrincipal())) {
                SysPopStoreSeRelationship seRelationship = new SysPopStoreSeRelationship();
                seRelationship.setPopStoreNum(popStoreBO.getPopStoreNum());
                seRelationship.setSeNum(popStoreBO.getPopStorePrincipaNum());
                seRelationshipMapper.insertSelective(seRelationship);
            }

            //保存 sysPop 用户
            if (StringUtils.isNotBlank(popStoreBO.getPopLoginName())) {
                String password = MD5Utils.getMD5Str("123456");
                SysPop sysPop = new SysPop();
                sysPop.setPopLoginName(popStoreBO.getPopLoginName());
                sysPop.setPopNum(NumGenerator.getPoPNum());
                sysPop.setCreateTime(new Date());
                sysPop.setIsAuthentication(false);
                sysPop.setIsDel(false);
                sysPop.setIsFrozen(false);
                sysPop.setLastOptName(popStoreBO.getPopLoginName());
                sysPop.setPopPassword(password);
                sysPop.setSafeScore(0);
                sysPop.setUpdateTime(new Date());
                sysPop.setPopMobile(popStoreBO.getPopStoreMobile());
                sysPop.setPopStoreNum(popStoreBO.getPopStoreNum());
                sysPopMapper.insertSelective(sysPop);

                //保存 sysPopInfo 
                SysPopInfo sysPopInfo = new SysPopInfo();
                sysPopInfo.setPopNum(sysPop.getPopNum());
                sysPopInfo.setPopRealName(popStoreBO.getPopStoreContact());
                sysPopInfoMapper.insertSelective(sysPopInfo);
            }

            //添加装修模板
            SysPopStoreDecorateInfo decorateInfo = new SysPopStoreDecorateInfo();
            decorateInfo.setPopStoreNum(popStoreNum);
            decorateInfo.setPopStoreDecorate(0);
            decorateInfoMapper.insertSelective(decorateInfo);

            SysPopStore sysPopStore = new SysPopStore();
            BeanUtils.copyProperties(popStoreBO, sysPopStore);
            return sysPopStoreMapper.insertSelective(sysPopStore);
        } else {
            return FAIL;
        }
    }

    /**
     * 功能描述：修改供应商账号
     * @author liukai
     * @param popStoreNum
     * @param popLoginName
     * @return
     */
    public int updateLoginNameByPopStoreNum(PopStoreBO popStoreBO) {
        String popStoreNum = popStoreBO.getPopStoreNum();
        String popLoginName = popStoreBO.getPopLoginName();
        SysPop sysPop = sysPopMapper.selectByPopStoreNum(popStoreNum);
        if (null != sysPop && StringUtils.isNotBlank(sysPop.getPopNum())) {
            SysPopInfo sysPopInfo = new SysPopInfo();
            sysPopInfo.setPopRealName(popStoreBO.getPopStoreContact());
            sysPopInfo.setPopNum(sysPop.getPopNum());
            sysPopInfoMapper.updateByPrimaryKeySelective(sysPopInfo);
            SysPop record = new SysPop();
            record.setPopLoginName(popLoginName);
            record.setPopMobile(popStoreBO.getPopStoreMobile());
            record.setPopNum(sysPop.getPopNum());
            sysPopMapper.updateByPrimaryKeySelective(record);
            return SUCCESS;
        } else {
            String password = MD5Utils.getMD5Str("123456");
            sysPop = new SysPop();
            sysPop.setPopPassword(password);
            sysPop.setSafeScore(0);
            sysPop.setIsAuthentication(false);
            sysPop.setIsDel(false);
            sysPop.setLastOptName(popLoginName);
            sysPop.setPopLoginName(popLoginName);
            sysPop.setPopMobile(popStoreBO.getPopStoreMobile());
            sysPop.setPopNum(NumGenerator.getPoPNum());
            sysPop.setPopStoreNum(popStoreNum);
            sysPop.setIsFrozen(false);
            return sysPopMapper.insertSelective(sysPop);
        }
    }

    /**
     * 根据类型 DS FS popstore name查询 list
     * @param record
     * @return
     */
    public List<SysPopStore> selectPopStoreByStoreNamePrimary(String name) {
        PopStoreSearchBO popStoreSearchBO = new PopStoreSearchBO();
        popStoreSearchBO.setPopStoreNameSearch(name);
        return selectPopStoreByStoreNamePrimary(popStoreSearchBO);
    }

    /**
     * 功能描述：修改冻结状态
     * @author liukai
     * @param id
     */
    public int updateSysPopStoreFrozen(Integer id) {
        PopStoreBO popStoreBO = selectPopStoreById(id);
        SysPopStore sysPopStore = new SysPopStore();
        BeanUtils.copyProperties(popStoreBO, sysPopStore);
        if (sysPopStore.getIsFrozen() == false) {
            sysPopStore.setIsFrozen(true);
        } else {
            sysPopStore.setIsFrozen(false);
        }
        sysPopStore.setId(id);
        return updateByPrimaryKeySelective(sysPopStore);
    }

    /**
     * 功能描述：根据 popNum 获取 popStore的 popStoreNum 和 id
     * @author liukai
     * @param popNum
     * @return
     */
    public PopStoreBO selectPopStoreBOByPopNum(String popNum) {
        PopStoreBO popStoreBO = null;
        SysPopStore sysPopStore = null;
        if (StringUtils.isNotBlank(popNum)) {
            popStoreBO = new PopStoreBO();
            SysPop sysPopTemp = new SysPop();
            sysPopTemp.setPopNum(popNum);

            SysPop sysPop = sysPopMapper.selectBySysPop(sysPopTemp);
            if (null != sysPop) {
                sysPopStore = sysPopStoreMapper.selectByPrimaryKey(sysPop.getPopStoreNum());
                BeanUtils.copyProperties(sysPopStore, popStoreBO);
            }
            return popStoreBO;
        } else {
            throw new NullPointerException("popNum is null");
        }
    }

    /**
     * 功能描述：验证店铺名称是否重复
     * @author liukai
     * @param popStoreMobile
     * @return
     */
    /*public JSONObject checkPopStoreName(String popStoreName, String popStoreNum) {
        List<SysPopStore> sysPopStores = null;
        SysPopStore sysPopStore = null;
        JSONObject json = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popStoreName", popStoreName);
        sysPopStores = selectPageByMap(map);
        if (!sysPopStores.isEmpty()) {
            sysPopStore = sysPopStores.get(0);
            if (StringUtils.isNotBlank(popStoreNum)) { //编辑页面校验
                if (sysPopStore.getPopStoreNum().equals(popStoreNum)) {
                    json.put("ok", "");
                } else {
                    json.put("error", "店铺名称已存在");
                }
            } else { //添加页面校验
                if (popStoreName.equals(sysPopStore.getPopStoreName())) {
                    json.put("error", "店铺名称已存在");
                } else {
                    json.put("ok", "店铺名未被使用");
                }
            }
        }
        return json;
    }*/
    public int checkPopStoreName(String popStoreName, String popStoreNum) {
    	int result = 0;
        List<SysPopStore> sysPopStores = null;
        SysPopStore sysPopStore = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popStoreName", popStoreName);
        sysPopStores = selectPageByMap(map);
        if (!sysPopStores.isEmpty()) {
            sysPopStore = sysPopStores.get(0);
            if (StringUtils.isNotBlank(popStoreNum)) { //编辑页面校验
                if (sysPopStore.getPopStoreNum().equals(popStoreNum)) {
                    result = 1;
                    return result;
                } else {
                    result = 0;
                    return result;
                }
            } else { //添加页面校验
                if (popStoreName.equals(sysPopStore.getPopStoreName())) {
                    result = 0;
                    return result;
                } else {
                    result = 1;
                    return result;
                }
            }
        } else {
        	result = 1;
        	return result;
        }
    }
    
    
    /**
     * 功能描述：验证域名是否重复
     * @author liukai
     * @param popStoreMobile
     * @return
     */
    /*public JSONObject checkSysPopStoreDomain(String popStoreDomain, String popStoreNum) {
        List<SysPopStore> sysPopStores = null;
        SysPopStore sysPopStore = null;
        JSONObject json = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popStoreDomain", popStoreDomain);
        sysPopStores = selectPageByMap(map);
        if (!sysPopStores.isEmpty()) {
            sysPopStore = sysPopStores.get(0);
            if (StringUtils.isNotBlank(popStoreNum)) {
                if (sysPopStore.getPopStoreNum().equals(popStoreNum)) {
                    json.put("ok", "");
                } else {
                    json.put("error", "店铺名称已存在");
                }
            } else if (popStoreDomain.equals(sysPopStore.getPopStoreDomain())) {
                json.put("error", "域名已被占用");
            }
        } else {
            json.put("ok", "域名未被使用");
        }
        return json;
    }*/
    public int checkSysPopStoreDomain(String popStoreDomain, String popStoreNum) {
    	int result = 0;
        List<SysPopStore> sysPopStores = null;
        SysPopStore sysPopStore = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popStoreDomain", popStoreDomain);
        sysPopStores = selectPageByMap(map);
        if (!sysPopStores.isEmpty()) {
            sysPopStore = sysPopStores.get(0);
            if (StringUtils.isNotBlank(popStoreNum)) {
                if (sysPopStore.getPopStoreNum().equals(popStoreNum)) {
                    result = 1;
                    return result;
                } else {
                    result = 0;
                    return result;
                }
            } else if (popStoreDomain.equals(sysPopStore.getPopStoreDomain())) {
                result = 0;
                return result;
            }
        } else {
            result = 1;
        }
        return result;
    }
    

    /**
     * 功能描述：验证 联系电话 是否重复
     * @author liukai
     * @param popStoreMobile
     * @return
     */
    /*public JSONObject searchSysPopStoreMobile(String popStoreMobile, String popStoreNum) {
        List<SysPopStore> sysPopStores = null;
        SysPopStore sysPopStore = null;
        PopStoreSearchBO searchBO = new PopStoreSearchBO();
        searchBO.setMobileSearch(popStoreMobile);

        JSONObject json = new JSONObject();
        searchBO.setMobileSearch(popStoreMobile);

        sysPopStores = selectPopStoreByStoreNamePrimary(searchBO);
        if (!sysPopStores.isEmpty()) {
            sysPopStore = sysPopStores.get(0);
            if (StringUtils.isNotBlank(popStoreNum) && sysPopStore.getPopStoreNum().equals(popStoreNum)) {
                if (sysPopStore.getPopStoreNum().equals(popStoreNum)) {
                    json.put("ok", "");
                } else {
                    json.put("error", "手机号已被注册");
                }
            } else if (popStoreMobile.equals(sysPopStore.getPopStoreMobile())) {
                json.put("error", "手机号已被注册");
            }
        } else {
            json.put("ok", "手机号未被注册");
        }
        return json;
    }*/
    public int checkSysPopStoreMobile(String popStoreMobile, String popStoreNum) {
    	int result = 0;
        List<SysPopStore> sysPopStores = null;
        SysPopStore sysPopStore = null;
        PopStoreSearchBO searchBO = new PopStoreSearchBO();
        searchBO.setMobileSearch(popStoreMobile);
        sysPopStores = selectPopStoreByStoreNamePrimary(searchBO);
        if (!sysPopStores.isEmpty()) {
            sysPopStore = sysPopStores.get(0);
            if (StringUtils.isNotBlank(popStoreNum) && sysPopStore.getPopStoreNum().equals(popStoreNum)) {
                if (sysPopStore.getPopStoreNum().equals(popStoreNum)) {
                    result = 1;
                    return result;
                } else {
                	result = 0;
                    return result;
                }
            } else if (popStoreMobile.equals(sysPopStore.getPopStoreMobile())) {
            	result = 0;
                return result;
            }
        } else {
        	result = 1;
        }
        return result;
    }
    
    
    

    /**
     * 功能描述：验证入驻商账号是否重复
     * @author liukai
     * @param popLoginName
     * @return 0:存在重覆; 1:不存在重覆
     */
   /* public JSONObject searchSysPopByName(String popLoginName, String popStoreNum) {
        SysPop sysPopTemp = new SysPop();
        SysPop sysPop = null;
        JSONObject json = new JSONObject();
        sysPopTemp.setPopLoginName(popLoginName);
        sysPop = sysPopMapper.selectBySysPop(sysPopTemp);

        if (StringUtils.isNotBlank(popStoreNum)) { //修改页面
            if (null != sysPop && sysPop.getPopStoreNum().equals(popStoreNum)) {
                json.put("ok", "");
            } else if (null != sysPop && !sysPop.getPopStoreNum().equals(popStoreNum)) {
                json.put("error", "用户名已被使用");
            } else {
                json.put("ok", "");
            }
        } else { //添加页面
            if (null != sysPop && popLoginName.equals(sysPop.getPopLoginName())) {
                json.put("error", "用户名已被使用");
            } else {
                json.put("ok", "用户名未被注册");
            }
        }
        return json;
    }*/
    public int checkSysPopByName(String popLoginName, String popStoreNum) {
    	int result = 0;
        SysPop sysPopTemp = new SysPop();
        SysPop sysPop = null;
        sysPopTemp.setPopLoginName(popLoginName);
        sysPop = sysPopMapper.selectBySysPop(sysPopTemp);

        if (StringUtils.isNotBlank(popStoreNum)) { //修改页面
            if (null != sysPop && sysPop.getPopStoreNum().equals(popStoreNum)) {
            	result = 1;
            	return result;
            } else if (null != sysPop && !sysPop.getPopStoreNum().equals(popStoreNum)) {
                result = 0;
                return result;
            } else {
            	result = 1;
                return result;
            }
        } else { //添加页面
            if (null != sysPop && popLoginName.equals(sysPop.getPopLoginName())) {
                result = 0;
                return result;
            } else {
            	result = 1;
                return result;
            }
        }
    }
}
