package com.nyist.vo;

import java.math.BigDecimal;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/17 10:57
 * @description：订单VO
 * @version: $version$
 */
public class OrderVo {
    private Integer custId;
    private Integer qty;
    private Integer prodId;
    private BigDecimal totAmt;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public BigDecimal getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(BigDecimal totAmt) {
        this.totAmt = totAmt;
    }
}
