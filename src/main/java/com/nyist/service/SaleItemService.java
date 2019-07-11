package com.nyist.service;

import com.nyist.entity.Product;
import com.nyist.entity.SaleItem;
import com.nyist.vo.OrderVo;
import com.nyist.vo.RefundVo;
import com.nyist.vo.SaleItemVo;
import com.nyist.vo.shopVo;

import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:55
 * @description：${description}
 * @version: $version$
 */
public interface SaleItemService {

    int deleteByPrimaryKey(Integer orderNo);

    void insert(SaleItem record);

    int insertSelective(shopVo[] shopVo);

    SaleItem selectByPrimaryKey(Integer orderNo);

    int updateByPrimaryKeySelective(SaleItem record);

    int updateByPrimaryKey(SaleItem record);

    /**
     * 根据订单号查询所有订单详情
     * @return
     */
    List<SaleItem> findAll(String invoiceNo);

    /**
     * 根据订单查找所属商品
     * @param invoiceNo
     * @return
     */
    List<SaleItem> selectProdByInvoiceNo(String invoiceNo);

    List<SaleItemVo> selectProdByInvoiceNo1(String invoiceNo);

    /**
     *退货单
     */
    RefundVo refund(Product product,SaleItem saleItem);



}
