package com.nyist.service;

import com.github.pagehelper.PageInfo;
import com.nyist.entity.Product;

import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:54
 * @description：${description}
 * @version: $version$
 */
public interface ProductService {
    int deleteByPrimaryKey(Integer prodId);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByHot(Integer hot);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectByName(String name);

    List<Product> selectByKindNo(String kindno);

    /**
     * 分页展示
     * @param product
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<Product> pageList(Product product, int currentPage, int pageSize);

    /**
     * 遍历全部商品
     * @return
     */
    List<Product> selectAll();

    Product selectById(int id);

    /**
     * 根据关键字查找商品
     * @param desc
     * @return
     */
    List<Product> selectByDesc(String desc);
}
