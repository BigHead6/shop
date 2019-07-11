package com.nyist.dao;

import com.nyist.entity.Sales;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface SalesMapper {
    int deleteByPrimaryKey(Integer orderNo);

    int insert(Sales record);

    int insertSelective(Sales record);

    Sales selectByInvoiceNo(String invoiceNo);

    int updateByPrimaryKeySelective(Sales record);

    int updateByInvoiceNo(String invoiceNo);

    List<Sales> selectByCustId(int custId);

    List<Sales> selectByCustId1(int custId);
    List<Sales> selectByCustId2(int custId);
    List<Sales> findAll(int custId);
    List<Sales> findWeek();

    /**
     *查找今日订单
     * @return
     */
    List<Sales> findToday(String time);

    /**
     * 查找最近一周订单
     * @return
     */
    List<Sales> Lastweek();

    List<Sales> Lastweek1();

    /**
     * 根据时间查询当天订单总额
     * @param time
     * @return
     */
    BigDecimal selectSUMBytime(String time);

     List<Sales> selectAll();
}