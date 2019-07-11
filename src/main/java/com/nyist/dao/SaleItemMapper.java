package com.nyist.dao;

import com.nyist.entity.Product;
import com.nyist.entity.SaleItem;
import com.nyist.vo.SaleItemVo;

import java.util.List;

public interface SaleItemMapper {
    int deleteByPrimaryKey(Integer orderNo);

    void insert(SaleItem record);

    int insertSelective(SaleItem record);

    SaleItem selectByPrimaryKey(Integer orderNo);

    int updateByPrimaryKeySelective(SaleItem record);

    int updateByPrimaryKey(SaleItem record);

    List<SaleItem> findAll(String invoiceNo);

    List<SaleItem> selectProdByInvoiceNo(String invoiceNo);

    /**
     * 联合查询订单详情表和商品表
     * @param invoiceNo
     * @return
     */
    List<SaleItemVo> selectProdByInvoiceNo1(String invoiceNo);
}