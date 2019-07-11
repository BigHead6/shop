package com.nyist.vo;

import com.nyist.entity.Product;
import com.nyist.entity.SaleItem;

import java.math.BigDecimal;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/4/16 9:50
 * @description：退货单vo
 * @version: $version$
 */
public class RefundVo extends Product {
    private String invoice;
    private int qty;
    private BigDecimal total;
    private String orderDate;
    private String proDesc;

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
