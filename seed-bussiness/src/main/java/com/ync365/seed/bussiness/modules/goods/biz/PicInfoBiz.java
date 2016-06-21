package com.ync365.seed.bussiness.modules.goods.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.goods.dao.PicInfoDao;
import com.ync365.seed.bussiness.modules.goods.entity.PicInfo;

@Service
public class PicInfoBiz {

	@Autowired
	PicInfoDao picInfoDao;
	
	
	public int deleteByPrimaryKey(Integer id){
    	return picInfoDao.deleteByPrimaryKey(id);
    }

	public int insert(PicInfo record){
		return picInfoDao.insert(record);
    }

	public int insertSelective(PicInfo record){
		return picInfoDao.insertSelective(record);
    }

	public PicInfo selectByPrimaryKey(Integer id){
		return picInfoDao.selectByPrimaryKey(id);
    }

	public int updateByPrimaryKeySelective(PicInfo record){
		return picInfoDao.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(PicInfo record){
		return picInfoDao.updateByPrimaryKey(record);
    }
	public List<PicInfo> selectByPicInfo(PicInfo picInfo){
		return picInfoDao.selectByPicInfo(picInfo);
    }
	
	public  List<PicInfo> selectPicInfoByUuid(Map<String,Object> map){
		 return picInfoDao.selectPicInfoByUuid(map);
	}
	
	public List<PicInfo> selectPicInfoListByUuid(Map<String ,Object> map){
		return picInfoDao.selectPicInfoListByUuid(map);
	}
	
	public PicInfo selectPicInfoByPicName(Map<String ,Object> map){
		return picInfoDao.selectPicInfoByPicName(map);
	}
}
