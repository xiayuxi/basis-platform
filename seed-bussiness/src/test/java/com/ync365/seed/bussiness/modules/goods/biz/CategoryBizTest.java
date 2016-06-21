package com.ync365.seed.bussiness.modules.goods.biz;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.goods.entity.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class CategoryBizTest {

    @Autowired
    private CategoryBiz categoryBiz;
    
    @Test
    public void testAddCategory() {
        try{
            Category category = new Category();
            category.setName("测试类别");
            category.setParentId(4839);
            category.setKeywords("asf");
            int a = categoryBiz.add(category);
            System.out.println(a);//4907
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testUpdatePathByCategoryId() {
        try{
            categoryBiz.updatePathByCategoryId(4907);
            System.out.println(categoryBiz.selectByPrimaryKey(4907).getPath());
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testSelectPage() {
        try{
            String qChName = null;
            int startIndex = 0;
            int pageSize = 10; 
            
            System.out.println("==================================================="+
            categoryBiz.selectPage(qChName, startIndex, pageSize));

        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testSearchPageCount() {
        try{
            long a = categoryBiz.selectPageCount("肥");
            System.out.println(a);
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testSelectCategoryAll() {
        try{
            
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testUpdateByPrimaryKeyCategory() {
        try{
            Category c = categoryBiz.selectByPrimaryKey(4907);
            c.setName("测试++++");
            categoryBiz.updateByPrimaryKey(c);
            System.out.println(categoryBiz.selectByPrimaryKey(4907).getName());
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testSelectByPrimaryKey() {
        try{
            
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testSelectCountByParentId() {
        try{
            int a = categoryBiz.selectCountByParentId(4891);
            System.out.println(a);//1
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testGetCategoryByBrandId() {
        try{
            List<Category> cList = categoryBiz.getCategoryByBrandId(2);
            System.out.println("++++++"+cList);
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testGetCategoryByParentId() {
        try{
            List<Category> cList = categoryBiz.getCategoryByParentId(12);
            System.out.println(cList.size());
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    /**
     * 应该是不用了
     * @Title: testAddCategoryListOfFeature
     * @Description: TODO    ：    
     * @author: xudawei/14070696    
     * @date: 2015年9月22日 上午11:56:46       
     * @version: 
     *
     *
     */
    @Test
    public void testAddCategoryListOfFeature() {
        try{
            
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testUpdateByPrimaryKeyCategoryListOfFeature() {
        try{
            
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testSelectCategoryChain() {
        try{
            Category category = categoryBiz.selectByPrimaryKey(4841);
            List<Category> cList = categoryBiz.selectCategoryChain(category);
            for (Category category2 : cList) {
                System.out.println(category2.getName());
            }
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testSelectCategoryList() {
        try{
            
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testDeleteByPrimaryKey() {
        try{
            int a = categoryBiz.deleteByPrimaryKey(4907);
            System.out.println(a);
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }
    
    @Test
    public void testselectCategoryListByPopStoreNum() {
    	List<Category>  categorys = categoryBiz.selectCategoryListByPopStoreNum("1111");
    	System.out.println(categorys.size());
    }
}

