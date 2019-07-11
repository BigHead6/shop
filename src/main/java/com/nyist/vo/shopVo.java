package com.nyist.vo;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/18 11:36
 * @description：${description}
 * @version: $version$
 */
public class shopVo {
    private Integer pId;
    private String orderNO;
    private Integer shopId;
    private Integer num;
    private String price;
    private Integer flag;
    private  Integer custId;

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getFlag() {

        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
