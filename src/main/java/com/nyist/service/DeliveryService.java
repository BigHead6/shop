package com.nyist.service;

import com.nyist.entity.Delivery;
import com.nyist.entity.Sales;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:53
 * @description：${description}
 * @version: $version$
 */
public interface DeliveryService {
    int deleteByPrimaryKey(Integer delivNo);

    int insert(Delivery record);

    int insertSelective(Delivery record);

    Delivery selectByPrimaryKey(Integer delivNo);

    int updateByPrimaryKeySelective(Delivery record);

    int updateByPrimaryKey(Delivery record);

    Delivery selectByOrderNo(String orderNo);

    /**
     *根据订单号查询配送表
     * @param orderNo
     * @return
     */
    Delivery selectByInvoiceNo(String orderNo);
}
