package com.nyist.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.nyist.dao.DeliveryMapper;
import com.nyist.entity.Delivery;
import com.nyist.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:05
 * @description：${description}
 * @version: $version$
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Resource
    private DeliveryMapper deliveryMapper;


    @Override
    public int deleteByPrimaryKey(Integer delivNo) {
        return deliveryMapper.deleteByPrimaryKey(delivNo);
    }

    @Override
    public int insert(Delivery record) {
        return deliveryMapper.insert(record);
    }

    @Override
    public int insertSelective(Delivery record) {
        return deliveryMapper.insertSelective(record);
    }

    @Override
    public Delivery selectByPrimaryKey(Integer delivNo) {
        return deliveryMapper.selectByPrimaryKey(delivNo);
    }

    @Override
    public int updateByPrimaryKeySelective(Delivery record) {
        return deliveryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Delivery record) {
        return deliveryMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据配送单查找
     * @param orderNo
     * @return
     */
    @Override
    public Delivery selectByOrderNo(String orderNo) {
        return deliveryMapper.selectByOrderNo(orderNo);
    }

    @Override
    public Delivery selectByInvoiceNo(String orderNo) {
        return deliveryMapper.selectByInvoiceNo(orderNo);
    }
}
