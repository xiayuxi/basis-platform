package com.ync365.seed.bussiness.modules.ad.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.ad.dao.NavigationDao;
import com.ync365.seed.bussiness.modules.ad.entity.Navigation;

@Service
public class NavigationBiz {
	
	@Autowired
	NavigationDao navigationDao ;
	
	public List<Navigation>  selectNavigationList(){
		return navigationDao.selectNavigationList();
	}

    public List<Navigation> selectNavigationCategoryList() {
        return navigationDao.selectNavigationCategoryList();
    }

    public int selectPageCount() {
        return navigationDao.selectPageCount();
    }

    public List<Navigation> selectPage(int startIndex, int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startIndex", startIndex);
        map.put("pageSize", rows);
        return navigationDao.selectPage(map);
    }

    public int adNavigation(Navigation navigation) {
        if(null==navigation.getParentId()){
            navigation.setParentId(0);
        }
        return navigationDao.insertSelective(navigation);
    }

    public Navigation selectById(Integer id) {
        return navigationDao.selectByPrimaryKey(id);
    }

    public int edit(Navigation navigation) {
        return navigationDao.updateByPrimaryKeySelective(navigation);
    }

    public int deleteById(Integer id) {
        return navigationDao.deleteById(id);
    }
	
}
