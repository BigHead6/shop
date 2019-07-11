package com.nyist.service;

import com.github.pagehelper.PageInfo;
import com.nyist.entity.Product;
import com.nyist.entity.Sales;
import com.nyist.vo.OrderVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:56
 * @description：${description}
 * @version: $version$
 */
public interface SalesService {
    int deleteByPrimaryKey(Integer orderNo);

    int insert(Sales record);

    String insertSelective(OrderVo orderVo);

    Sales selectByInvoiceNo(String invoiceNo);


    int updateByPrimaryKeySelective3(Sales record,String status);

    int updateByInvoiceNo(String invoiceNo);

    List<Sales> selectByCustId(int custId);

    /**
     * 待收货
     * @param custId
     * @return
     */
    List<Sales> selectByCustId1(int custId);

    /**
     * 待收货
     * @param custId
     * @return
     */
    List<Sales> selectByCustId2(int custId);

    List<Sales> findAll(int custId);
    List<Sales> findWeek();


    List<Sales> findToday(Date today);
    BigDecimal[] Lastweek();

    BigDecimal[] Lastweek1();

    /**
     * 订单分页查询
     * @param sales
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<Sales> pageList(Sales sales, int currentPage, int pageSize);

}
