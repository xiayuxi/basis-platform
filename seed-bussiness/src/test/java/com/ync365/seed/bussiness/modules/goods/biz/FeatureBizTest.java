package com.ync365.seed.bussiness.modules.goods.biz;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.goods.entity.Feature;
import com.ync365.seed.bussiness.modules.goods.entity.FeatureValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class FeatureBizTest {

    @Autowired
    private FeatureBiz featureBiz;
   
    @Test
    public void testAdd() {
//        Feature feature = new Feature();
//        feature.setName("测试属性");
//        feature.setCategoryId(12);
//        feature.setIsQuery((short)1);
//        feature.setIsSpec((short)1);
//        feature.setStatus((short)1);
//        feature.setSeq(1);
//        List<Integer> categoryIds = new ArrayList<Integer>();
//        categoryIds.add(14);
//        categoryIds.add(25);
//        categoryIds.add(50);
//        feature.setCategoryIds(categoryIds);
//        feature.setOptionals("111,222,333");
//        List<FeatureValue> fVList =new ArrayList<FeatureValue>(); 
//        FeatureValue fv1 = new FeatureValue();
//        FeatureValue fv2 = new FeatureValue();
//        FeatureValue fv3 = new FeatureValue();
//        fv1.setAttrValue("111");
//        fv2.setAttrValue("222");
//        fv3.setAttrValue("333");
//        fVList.add(fv1);
//        fVList.add(fv2);
//        fVList.add(fv3);
//        
//        int c = featureBiz.add(feature, fVList);
//        //Assert.assertEquals(1, c);
//        System.out.println(c);
    }

    @Test
    public void testSelectPage() {
        try{
            String qChName = null;
            int startIndex = 0;
            int pageSize = 10; 
            
            System.out.println("==================================================="+
                featureBiz.selectPage(qChName, startIndex, pageSize));

        }catch(Exception e ){
            fail("Not yet implemented");
        }
        
    }

    @Test
    public void testSelectPageCount() {
        try{
            long a = featureBiz.selectPageCount("");
            System.out.println(a);
            Assert.assertEquals(a, 9);
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    
    @Test
    public void testDeleteById() {
        try{
            int a = featureBiz.deleteById(144);
            System.out.println(a);
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testSelectById() {
        try{
            Feature f = featureBiz.selectById(70);
            System.out.println(f.getName());
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testEdit() {
//        try{
//            Feature feature = featureBiz.selectById(138);
//            feature.setName("测试属性++++");
//            feature.setCategoryId(12);
//            feature.setIsQuery((short)1);
//            feature.setIsSpec((short)1);
//            feature.setStatus((short)1);
//            feature.setSeq(1);
//            List<Integer> categoryIds = new ArrayList<Integer>();
//            categoryIds.add(14);
//            categoryIds.add(37);
//            feature.setCategoryIds(categoryIds);
//            feature.setOptionals("111,333");
//            List<FeatureValue> fVList =new ArrayList<FeatureValue>(); 
//            FeatureValue fv1 = new FeatureValue();
//            FeatureValue fv3 = new FeatureValue();
//            fv1.setAttrValue("111");
//            fv3.setAttrValue("333");
//            fVList.add(fv1);
//            fVList.add(fv3);
//            
//            int c = featureBiz.edit(feature, fVList);
//            //Assert.assertEquals(1, c);
//            System.out.println(c);
//        }catch(Exception e){
//            fail("Not yet implemented");
//        }
    }

    @Test
    public void testGetFeaturesBySpec() {
        try{
            List<Feature> fList = featureBiz.getFeaturesBySpec(1);
            for (Feature feature : fList) {
                System.out.println(feature.getName());
            }
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testGetFeaturesBySpecAndCategoryId() {
        try{
            List<Feature> fList = featureBiz.getFeaturesByCategoryId(1);
            for (Feature feature : fList) {
                System.out.println(feature.getName());
            }
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testGetFeaturesByCategoryId() {
        try{
            List<Feature> fList = featureBiz.getFeaturesByCategoryId(1);
            for (Feature feature : fList) {
                System.out.println(feature.getName());
            }
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

/*    @Test
    public void testGetParentFeatures() {
        try{

        }catch(Exception e){
            fail("Not yet implemented");
        }
    }
*/
    @Test
    public void testGetAllFeatures() {
        try{

        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

    @Test
    public void testGetFeatureValueByFeatureId() {
        try{
            List<FeatureValue> fVList = featureBiz.getFeatureValueByFeatureId(73);
            for (FeatureValue featureValue : fVList) {
                System.out.println(featureValue.getAttrValue());
            }
        }catch(Exception e){
            fail("Not yet implemented");
        }
    }

}
