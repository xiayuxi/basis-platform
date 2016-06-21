package com.ync365.seed.bussiness.modules.goods.biz;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.Base.BaseTest;
import com.ync365.seed.bussiness.modules.goods.entity.BrandCategory;

 
public class BrandCategoryBizTest extends BaseTest {

    @Autowired
    BrandCategoryBiz brandCategoryBiz;
    
    @Test
    public void testAddBrandCategory() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddBrandCategorys() {
        try{
            int brandId = 2526;
            String categoryIds = "38,40,80";
            int a = brandCategoryBiz.addBrandCategorys(brandId, categoryIds);
            System.out.println(a);
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    /**
     * 没用
     * @Title: testDeleteByIdLongLong
     * @Description: TODO    ：    
     * @author: xudawei/14070696    
     * @date: 2015年9月21日 下午7:33:56       
     * @version: 
     *
     */
    @Test
    public void testDeleteById() {
        try{
            int brandId = 2526;
            int a = brandCategoryBiz.deleteById(brandId);
            System.out.println(a);
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    /**
     * 根据id删除关联关系
     * @Title: testDeleteByIdInteger
     * @Description: TODO    ：    
     * @author: xudawei/14070696    
     * @date: 2015年9月21日 下午7:34:05       
     * @version: 
     *
     *
     */
    @Test
    public void testDeleteByIdInteger() {
        try{
            int brandId = 2526;
            int a = brandCategoryBiz.deleteById(brandId);
            System.out.println(a);
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testSearchBrandCategoryList() {
        fail("Not yet implemented");
    }

    @Test
    public void testSearchBrandCategory() {
        try{
            int brandId = 13;
            int categoryId = 4800;
            BrandCategory bc = brandCategoryBiz.searchBrandCategory(brandId, categoryId);
            System.out.println(bc);
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

}
