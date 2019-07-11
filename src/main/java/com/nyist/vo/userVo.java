package com.nyist.vo;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/5/11 23:18
 * @description：保存个人资料
 * @version: $version$
 */
public class userVo {
    private String custName;
    private String sex;
    private String telNo;
    private String zip;
    private String email;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
