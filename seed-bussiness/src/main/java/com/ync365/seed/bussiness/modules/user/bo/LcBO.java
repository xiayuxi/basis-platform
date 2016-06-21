package com.ync365.seed.bussiness.modules.user.bo;

import java.util.List;

import com.ync365.seed.bussiness.modules.user.entity.SysAdminService;
import com.ync365.seed.bussiness.modules.user.entity.SysLcInfo;

public class LcBO {
    private SysLcInfo lc;

    public SysLcInfo getLc() {
        return lc;
    }

    public void setLc(SysLcInfo lc) {
        this.lc = lc;
    }

    public List<SysAdminService> getServices() {
        return services;
    }

    public void setServices(List<SysAdminService> services) {
        this.services = services;
    }

    private List<SysAdminService> services;
}
