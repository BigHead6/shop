package com.nyist.entity;

public class Warehouse {
    private Integer whNo;

    private String whName;

    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getWhNo() {
        return whNo;
    }

    public void setWhNo(Integer whNo) {
        this.whNo = whNo;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName == null ? null : whName.trim();
    }
}