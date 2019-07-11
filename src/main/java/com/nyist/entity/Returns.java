package com.nyist.entity;

import java.util.Date;

public class Returns {
    private String chanNo;

    private Integer custId;

    private String orderNo;

    private String delivDate;

    private String chanReason;

    private String prodId;

    private Integer qty;

    private String status;

    private String flag;

    private String content;

    private String extend3;

    public String getChanNo() {
        return chanNo;
    }

    public void setChanNo(String chanNo) {
        this.chanNo = chanNo;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDelivDate() {
        return delivDate;
    }

    public void setDelivDate(String delivDate) {
        this.delivDate = delivDate;
    }

    public String getChanReason() {
        return chanReason;
    }

    public void setChanReason(String chanReason) {
        this.chanReason = chanReason == null ? null : chanReason.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }
}