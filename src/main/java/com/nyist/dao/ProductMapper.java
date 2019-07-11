package com.nyist.dao;

import com.nyist.entity.Product;

import java.beans.PropertyDescriptor;
import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByHot(Integer hot);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectAll();

    /**
     * 根据商品名称模糊查询
     * @param name
     * @return
     */
    List<Product> selectByName(String name);

    /**
     * 根据商品种类id进行查询
     * @param kindno
     * @return
     */
    List<Product> selectByKindNo(String kindno);

    Product selectById(int id);

    /**
     * 根据关键字查找商品
     * @param prodDesc
     * @return
     */
    List<Product> selectByDesc(String prodDesc);

}