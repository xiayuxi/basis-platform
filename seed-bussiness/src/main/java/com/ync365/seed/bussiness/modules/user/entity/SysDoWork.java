package com.ync365.seed.bussiness.modules.user.entity;

public class SysDoWork {
    private Integer id;

    private String workName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName == null ? null : workName.trim();
    }
}