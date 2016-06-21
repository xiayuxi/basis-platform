package com.ync365.seed.bussiness.modules.user.bo;

import com.ync365.seed.bussiness.modules.user.entity.SysUser;
import com.ync365.seed.bussiness.modules.user.entity.SysUserInfo;

public class UsVsLcBO {
    private SysUserInfo us;
    private VsBO vs;
    private LcBO lc;
    public SysUserInfo getUs() {
        return us;
    }
    public void setUs(SysUserInfo us) {
        this.us = us;
    }
    public VsBO getVs() {
        return vs;
    }
    public void setVs(VsBO vs) {
        this.vs = vs;
    }
    public LcBO getLc() {
        return lc;
    }
    public void setLc(LcBO lc) {
        this.lc = lc;
    }
}
