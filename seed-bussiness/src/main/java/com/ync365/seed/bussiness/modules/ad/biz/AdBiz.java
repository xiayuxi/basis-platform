package com.ync365.seed.bussiness.modules.ad.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.ad.dao.AdDao;
import com.ync365.seed.bussiness.modules.ad.entity.Ad;
import com.ync365.seed.utils.DateUtils;
import com.ync365.seed.utils.StringUtils;


/**
 * 广告业务类
 * 
 * @ClassName: AdBiz
 * @Description:
 * @author robo
 * @date 2015年9月25日 上午11:26:33
 *
 */
@Service
public class AdBiz {

    @Autowired
    private AdDao adDao;

    /**
     * 
     * @Title: addAd
     * @Description: 添加    
     * @author: xudawei/14070696    
     * @date: 2015年9月26日 下午3:50:58       
     * @version: 
     *
     * @param ad
     * @param endDate 
     * @param beginDate 
     * @return
     *
     */
    public int addAd(Ad ad, String beginDate, String endDate){
        
        //假数据,这几个字段要求非空
        Integer type = 1;
        ad.setStatus(0);
        ad.setType(type);
        
        ad.setCreateDate(new Date(System.currentTimeMillis()));
        ad.setModifyDate(new Date(System.currentTimeMillis()));
        ad.setBeginDate(DateUtils.stringToDate(beginDate, "yyyy-MM-dd HH:mm:ss"));
        ad.setEndDate(DateUtils.stringToDate(endDate, "yyyy-MM-dd HH:mm:ss"));
      
        //把地址头部截掉
        String path = ad.getPath();
        if(path.length()>0){
            path = path.substring(30);
        }
        ad.setPath(path);
        
        return adDao.insertSelective(ad);
    }
    
    /**
     * 分页查询
     * @Title: selectPage
     * @Description:  
     * @author: xudawei/14070696    
     * @date: 2015年9月26日 下午1:51:29       
     * @version: 
     *
     * @param qName
     * @param adPosition 
     * @param status 
     * @param startIndex
     * @param pageSize
     * @return
     *
     */
    public List<Ad> selectPage(String qName, String status, String adPosition, int startIndex, int pageSize) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", qName);
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        map.put("status", status);
        map.put("adPosition", adPosition);
        List<Ad> adList = adDao.selectPage(map);
        //该页面把按钮放到下面后就不需要加这条了
        /* for (Ad ad : adList) {
            ad.setOprate(ad.getStatus());
        }*/
        return adList;
    }

    /**
     * 
     * @Title: selectPageCount
     * @Description:  计数
     * @author: xudawei/14070696    
     * @date: 2015年9月26日 下午1:51:55       
     * @version: 
     *
     * @param qName
     * @return
     *
     */
    public long selectPageCount(String qName) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name", qName);
        return adDao.selectPageCount(map);
    }
    
    /**
     * 
     * @Title: deleteById
     * @Description:  根据主键删除一条记录,设置isDel=1
     * @author: xudawei/14070696    
     * @date: 2015年9月26日 下午1:53:11       
     * @version: 
     *
     * @return
     *
     */
    public int deleteById(Integer id){
        Ad ad = adDao.selectByPrimaryKey(id);
        if(ad.getStatus()==1||ad.getStatus()==2){
            return 2;
        }else{
            return adDao.deleteById(id);
        }
    }

    /**
     * 
     * @Title: selectById
     * @Description: 根据主键查询一条记录    
     * @author: xudawei/14070696    
     * @date: 2015年9月26日 下午1:55:10       
     * @version: 
     *
     * @param id
     * @return
     *
     */
    public Ad selectById(Integer id){
        return adDao.selectByPrimaryKey(id);
    }
    
    /**
     * 编辑一条记录
     * @Title: edit
     * @Description: 
     * @author: xudawei/14070696    
     * @date: 2015年9月26日 下午1:57:11       
     * @version: 
     *
     * @param ad
     * @param endDate 
     * @param beginDate 
     * @return
     *
     */
    public int edit(Ad ad, String beginDate, String endDate){
        
        ad.setModifyDate(new Date(System.currentTimeMillis()));
        
        ad.setBeginDate(DateUtils.stringToDate(beginDate, "yyyy-MM-dd HH:mm:ss"));
        ad.setEndDate(DateUtils.stringToDate(endDate, "yyyy-MM-dd HH:mm:ss"));
        
        //把地址头部截掉
        String path = ad.getPath();
        if(StringUtils.isNotBlank(path) && path.contains("http") ){
        	 path = path.substring(30);
        }
        
        ad.setPath(path);

        return adDao.updateByPrimaryKeySelective(ad);
    }
    
    /**
     * 
     * @Title: selectAdList
     * @Description: 
     * @author: robo   
     * @date: 2015年9月28日 下午1:56:41       
     * @version: 
     *
     * @param pageSize
     * @param adPosition
     * @return
     *
     */
    public List<Ad> selectAdList(int pageSize,Integer adPosition){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageSize", pageSize);
        map.put("adPosition", adPosition);
        return adDao.selectAdList(map);
    }

    /**
     * 
     * @Title: changeStatusById
     * @Description: 更改状态，暂停/启动    
     * @author: liugl    
     * @date: 2015年10月9日 下午3:20:47       
     * @version: 
     *
     * @param id
     * @return 
     *
     */
    public int changeStatusById(int id) {
        Ad ad = adDao.selectByPrimaryKey(id);
        int status = ad.getStatus(); 
        if(status==1){
            ad.setStatus(2);
        }else if(status==2||status==0){
            ad.setStatus(1);
        }
        return adDao.updateByPrimaryKeySelective(ad);
    }

    /**
     * 
     * @Title: completeById
     * @Description:   完成操作
     * @author: liugl    
     * @date: 2015年10月9日 下午3:21:12       
     * @version: 
     *
     * @param id
     * @return 
     *
     */
    public int completeById(int id) {
        Ad ad = adDao.selectByPrimaryKey(id);
        ad.setStatus(3);
        return adDao.updateByPrimaryKeySelective(ad);
    }
}
