package com.nyist.vo;

import java.math.BigDecimal;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/19 20:00
 * @description：${description}
 * @version: $version$
 */
public class EOrderVo {
    private Integer orderNo;
    private Integer qty;
    private Integer prodId;
    private BigDecimal price;

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
