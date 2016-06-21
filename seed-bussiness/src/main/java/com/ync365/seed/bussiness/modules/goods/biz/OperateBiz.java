package com.ync365.seed.bussiness.modules.goods.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.goods.dao.OperateDao;
import com.ync365.seed.bussiness.modules.goods.entity.Operate;

@Service
public class OperateBiz {
    @Autowired
    OperateDao operateDao;
    /**/
    public Operate selectOperateByGoodsId(Integer goodsId){
        return operateDao.selectByGoodsId(goodsId);
    }
}
