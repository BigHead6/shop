package com.nyist.dao;

import com.nyist.entity.Delivery;
import com.nyist.entity.Sales;

public interface DeliveryMapper {
    int deleteByPrimaryKey(Integer delivNo);

    int insert(Delivery record);

    int insertSelective(Delivery record);

    Delivery selectByPrimaryKey(Integer delivNo);

    int updateByPrimaryKeySelective(Delivery record);

    int updateByPrimaryKey(Delivery record);

    Delivery selectByOrderNo(String orderNo);

    Delivery selectByInvoiceNo(String orderNo);
}