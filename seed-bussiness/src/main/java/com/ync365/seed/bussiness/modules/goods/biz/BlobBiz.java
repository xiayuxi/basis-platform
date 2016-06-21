package com.ync365.seed.bussiness.modules.goods.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.goods.dao.BlobDao;
import com.ync365.seed.bussiness.modules.goods.entity.Blob;

@Service
public class BlobBiz {
    @Autowired
    BlobDao blobDao;
    
    public Blob selectByPrimaryKey(Integer goodId){
        return blobDao.selectByPrimaryKey(goodId);
    }
    
    public Blob selectBlobByskuId(Integer skuId){
		return blobDao.selectBlobByskuId(skuId);
	}
    
    public int insert(Blob blob){
        return blobDao.insert(blob);
    }
}
