package com.ync365.seed.bussiness.modules.user.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreTextMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreTextWithBLOBs;

/**
 * 功能描述：
 * @author liukai
 * @date 2015年10月29日 下午6:32:17 
 * @version 1.0
 */
@Service
public class SysPopStoreTextBiz {
	
	@Autowired
	private SysPopStoreTextMapper sysPopStoreTextMapper;
	
	/**
	 * 功能描述：根据id删除
	 * @author liukai
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(Integer id) {
		return sysPopStoreTextMapper.deleteByPrimaryKey(id);
	}

    /**
     * 功能描述：插入
     * @author liukai
     * @param record
     * @return
     */
    public int insertSelective(SysPopStoreTextWithBLOBs record) {
    	return sysPopStoreTextMapper.insertSelective(record);
    }

    /**
     * 功能描述：获取
     * @author liukai
     * @param id
     * @return
     */
    public SysPopStoreTextWithBLOBs selectByPrimaryKey(Integer id) {
    	return sysPopStoreTextMapper.selectByPrimaryKey(id);
    }

    /**
     * 功能描述：更新
     * @author liukai
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(SysPopStoreTextWithBLOBs record) {
    	return sysPopStoreTextMapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * 功能描述：根据 popStoreNum 查询
     * @author liukai
     * @param popStoreNum
     * @return
     */
    public SysPopStoreTextWithBLOBs selectByPopStoreNum(String popStoreNum) {
    	return sysPopStoreTextMapper.selectByPopStoreNum(popStoreNum);
    }
    
}
