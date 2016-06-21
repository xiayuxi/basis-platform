package com.ync365.seed.bussiness.modules.user.bo;

import java.util.List;

import com.ync365.seed.bussiness.modules.user.entity.SysAdminService;
import com.ync365.seed.bussiness.modules.user.entity.SysVsInfo;

public class VsBO {
    private SysVsInfo vs;
    private List<SysAdminService> services;

    public SysVsInfo getVs() {
        return vs;
    }

    public void setVs(SysVsInfo vs) {
        this.vs = vs;
    }

    public List<SysAdminService> getServices() {
        return services;
    }

    public void setServices(List<SysAdminService> services) {
        this.services = services;
    }
}
