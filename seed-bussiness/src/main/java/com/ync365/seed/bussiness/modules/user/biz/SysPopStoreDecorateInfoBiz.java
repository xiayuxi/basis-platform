package com.ync365.seed.bussiness.modules.user.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.bo.PopStoreDecorateBO;
import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreDecorateInfoMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreDecorateInfo;

@Service
public class SysPopStoreDecorateInfoBiz {
	
	@Autowired
	private SysPopStoreDecorateInfoMapper decorateInfoMapper;

    /**
     * 功能描述：根据id删除
     * @author liukai
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Integer id) {
    	return decorateInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 功能描述：添加
     * @author liukai
     * @param record
     * @return
     */
    public int insertSelective(SysPopStoreDecorateInfo record) {
    	return decorateInfoMapper.insertSelective(record);
    }

    /**
     * 功能描述：根据id查询
     * @author liukai
     * @param id
     * @return
     */
    public SysPopStoreDecorateInfo selectByPrimaryKey(Integer id) {
    	return decorateInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 功能描述：修改
     * @author liukai
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(SysPopStoreDecorateInfo record) {
    	return decorateInfoMapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * 功能描述：多功能查询
     * @author liukai
     * @param map
     * @return
     */
    public List<SysPopStoreDecorateInfo> selectPageByMap(Map<String, Object> map) {
    	return decorateInfoMapper.selectPageByMap(map);
    }

	/**
	 * 功能描述：根据 popStoreNum 获取装修信息
	 * @author liukai
	 * @param popStoreNum
	 * @return
	 */
	public SysPopStoreDecorateInfo selectByPopStoreNum(String popStoreNum) {
		return decorateInfoMapper.selectByPopStoreNum(popStoreNum);
	}
    
	/**
	 * 功能描述：根据 popStoreNum 更新
	 * @author liukai
	 * @param info
	 * @return
	 */
    public int updateByPopStoreNum(PopStoreDecorateBO info) {
    	return decorateInfoMapper.updateByPopStoreNum(info);
    }
    
}
