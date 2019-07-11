package com.nyist.vo;

import com.nyist.entity.Product;
import com.nyist.entity.SaleItem;

import java.util.PrimitiveIterator;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/4/8 16:41
 * @description：${description}
 * @version: $version$
 */
public class SaleItemVo  extends SaleItem{
    private String image;
    private String ProdName;
    private String proDesc;
    private SaleItem saleItem;
    public SaleItemVo(){

    }
    public SaleItemVo(SaleItem saleItem,String image,String ProdName,String proDesc){
        this.setOrderNo(saleItem.getOrderNo());
        this.setProdId(saleItem.getProdId());
        this.setUnitPrice(saleItem.getUnitPrice());
        this.setQty(saleItem.getQty());
        this.setOrderDate(saleItem.getOrderDate());
        this.setInvoiceNo(saleItem.getInvoiceNo());
        this.setDisPrice(saleItem.getDisPrice());
        this.image=image;
        this.ProdName=ProdName;
        this.proDesc=proDesc;

    }

    public SaleItem getSaleItem() {

        return saleItem;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public void setSaleItem(SaleItem saleItem) {
        this.saleItem = saleItem;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        ProdName = prodName;
    }
}
