package com.nyist.vo.Json;

import com.nyist.entity.Product;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/4/4 13:52
 * @description：${description}
 * @version: $version$
 */
public class ProductVo extends Product {
    /**
     * 新增/编辑标志
     */
    private String flag;

    private String category;

    private String wh;
    private String price;

    private String isHot1;
    private String supNo1;
    private String kindNo1;
    private String imgName;

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsHot1() {

        return isHot1;
    }

    public void setIsHot1(String isHot1) {
        this.isHot1 = isHot1;
    }

    public String getSupNo1() {
        return supNo1;
    }

    public void setSupNo1(String supNo1) {
        this.supNo1 = supNo1;
    }

    public String getKindNo1() {
        return kindNo1;
    }

    public void setKindNo1(String kindNo1) {
        this.kindNo1 = kindNo1;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWh() {
        return wh;
    }

    public void setWh(String wh) {
        this.wh = wh;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
