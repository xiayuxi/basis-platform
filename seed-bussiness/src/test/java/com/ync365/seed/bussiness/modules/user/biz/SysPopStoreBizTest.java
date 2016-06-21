package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.Base.BaseTest;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.entity.SysPop;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class SysPopStoreBizTest extends BaseTest {
    @Autowired
    private SysPopStoreBiz biz;
    @Autowired
    private SysPopBiz popBiz;

    @Test
    public void testOne() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popStoreNum", "5232260d12cf425184716de00f42272b");
        List<SysPopStore> list = biz.selectPageByMap(map);
        if (null != list && list.size() > 0) {
            for (SysPopStore temp : list) {
                System.out.println("============" + temp.getPopStoreNum() + "===========");
            }
        } else {
            System.out.println("list--------------为空");
        }
    }

    @Test
    public void testCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popStoreNum", "5232260d12cf425184716de00f42272b");
        int a = biz.selectPageCount(map);

        System.out.println("list--------" + a);

    }

    @Test
    public void testDel() {

        int a = biz.deleteByPrimaryKey("5232260d12cf425184716de00f42272b");

        System.out.println("list--------" + a);

    }

    @Test
    public void selectPopStoreBOByPopNum() {
                PopStoreBO bo = biz.selectPopStoreById(84);
                System.out.println(bo.getPopStoreNum());
        SysPop pp = popBiz.selectByPopStoreNum(bo.getPopStoreNum());
        System.out.println(pp.getPopNum());
    }
}
