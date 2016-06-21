package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.Base.BaseTest;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysVsInfo;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class SysVsInfoBizTest extends BaseTest {
    @Autowired
    private SysVsInfoBiz biz;

    @Test
    public void testInsert() {
        SysVsInfo record = new SysVsInfo();
        record.setUserNum(NumGenerator.getPoPNum());
        //record.setName("eeee");
        record.setTelephone("23455333");
        //record.setAddress(999);
        int amount = biz.insertSelective(record);
        Assert.assertEquals(1, amount);
    }

    @Test
    public void testOne() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", "89efbc5ba6314e30b25b4e0db4b93fbd");
        List<SysVsInfo> list = biz.selectPageByMap(map);
        if (null != list && list.size() > 0) {
            for (SysVsInfo temp : list) {
                System.out.println("============" + temp.getTelephone() + "===========");
            }
        } else {
            System.out.println("list--------------为空");
        }
    }

    @Test
    public void testSelectByNum() {
        VsInfoSearchBO vs = new VsInfoSearchBO();
        vs.setUserNumSearch("0115101500000004");
        List<VsInfoBO> list = biz.selectSysVsInfoListByPrimary(vs);
        System.out.println(list.size());
    }

    @Test
    public void testDel() {
        int a = biz.deleteUserRoleByuserNum("89efbc5ba6314e30b25b4e0db4b93fbd");
        System.out.println("list--------" + a);
    }

    @Test
    public void update() {
        SysVsInfo record = new SysVsInfo();
        record.setUserNum("89efbc5ba6314e30b25b4e0db4b93fbd");
        record.setTelephone("23455333");
        int a = biz.update(record);
        System.out.println("===============" + a);

    }
}
