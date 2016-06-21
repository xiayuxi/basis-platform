package com.ync365.seed.bussiness.modules.user.biz;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.Base.BaseTest;
import com.ync365.seed.bussiness.modules.user.entity.SysLogs;

public class SysLogsBizTest extends BaseTest {
    @Autowired
    SysLogsBiz syslogsBiz;

    @Test
    public void testInsertSelective() {
        SysLogs log = new SysLogs();
        log.setCreateTime(Calendar.getInstance().getTime());
        log.setGroupName("g1");
        log.setRoleName("r1");
        log.setOperatorName("o1");
        log.setOperatingContent("ccc");
        log.setParameter("p1");
         syslogsBiz.insertSelective(log);
        System.out.println(" >>>>>>>>>>> "+log.getId());
    }

    @Test
    public void testSelectByPrimaryKey() {
    }

    @Test
    public void testSelectByLogsSearchBO() {
    }

}
