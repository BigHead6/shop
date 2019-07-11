package com.nyist.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyist.dao.SaleItemMapper;
import com.nyist.dao.SalesMapper;
import com.nyist.entity.Product;
import com.nyist.entity.SaleItem;
import com.nyist.entity.Sales;
import com.nyist.service.SalesService;
import com.nyist.vo.OrderVo;
import com.util.TimeUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:06
 * @description：${description}
 * @version: $version$
 */
@Service
public class SalesServiceImpl implements SalesService {

    @Resource
    private SalesMapper salesMapper;

    @Override
    public int deleteByPrimaryKey(Integer orderNo) {
        return salesMapper.deleteByPrimaryKey(orderNo);
    }

    @Override
    public int insert(Sales record) {
        return salesMapper.insert(record);
    }

    @Override
    public String insertSelective(OrderVo orderVo) {
        //生成订单号,当前日期加上用户id
        int flag = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Sales sales = new Sales();
        String orderNo = format.format(new Date()) + orderVo.getCustId();
        sales.setInvoiceNo(orderNo);
        sales.setCustId(orderVo.getCustId());
        sales.setOrderDate(new Date());
        sales.setDelivDate(TimeUtils.plusDay2(1));
        sales.setTotAmt(orderVo.getTotAmt());
        salesMapper.insertSelective(sales);
        return orderNo;
    }

    @Override
    public Sales selectByInvoiceNo(String invoiceNo) {
        Sales sales = salesMapper.selectByInvoiceNo(invoiceNo);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS ");
        sales.setExtend2(dateFormat.format(sales.getOrderDate()));
        return sales;
    }
    @Override
    public int updateByPrimaryKeySelective3(Sales record,String status) {
        record.setOrderStatus(status);
        return salesMapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public int updateByInvoiceNo(String invoiceNo) {
        return salesMapper.updateByInvoiceNo(invoiceNo);
    }

    /**
     * 查询未付款订单
     *
     * @param custId
     * @return
     */
    @Override
    public List<Sales> selectByCustId(int custId) {
        List<Sales> salesList = salesMapper.selectByCustId(custId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < salesList.size(); i++) {
            salesList.get(i).setExtend2(format.format(salesList.get(i).getOrderDate()));
        }
        return salesList;
    }


    /**
     * 查询待发货订单
     *
     * @param custId
     * @return
     */
    @Override
    public List<Sales> selectByCustId1(int custId) {
        List<Sales> salesList = salesMapper.selectByCustId1(custId);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Sales> salesList1 = new ArrayList<>();
        for (int i = 0; i < salesList.size(); i++) {
            if (TimeUtils.subtractTime(format.format(new Date()), format.format(salesList.get(i).getDelivDate()))) {
                salesList.get(i).setExtend2(format1.format(salesList.get(i).getOrderDate()));
                salesList1.add(salesList.get(i));
            }
        }
        return salesList1;
    }

    @Override
    public List<Sales> selectByCustId2(int custId) {
        List<Sales> salesList2 = salesMapper.selectByCustId2(custId);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Sales> salesList1 = new ArrayList<>();
        for (int i = 0; i < salesList2.size(); i++) {
            if (!TimeUtils.subtractTime(format.format(new Date()), format.format(salesList2.get(i).getDelivDate()))) {
                salesList2.get(i).setExtend2(format1.format(salesList2.get(i).getOrderDate()));
                salesList1.add(salesList2.get(i));
            }
        }
        return salesList1;
    }

    /**
     * 查询所有订单
     *
     * @param custId
     * @return
     */
    @Override
    public List<Sales> findAll(int custId) {
        List<Sales> salesList = salesMapper.findAll(custId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < salesList.size(); i++) {
            salesList.get(i).setExtend2(format.format(salesList.get(i).getOrderDate()));
        }
        return salesList;
    }

    @Override
    public List<Sales> findWeek() {
        return salesMapper.findWeek();
    }

    /**
     * 查找今日订单数和销售总额
     *
     * @param today
     * @return
     */
    @Override
    public List<Sales> findToday(Date today) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<Sales> salesList = salesMapper.findToday(df.format(today));
        return salesList;
    }


    /**
     * 获取本周订单
     *
     * @return
     */
    @Override
    public BigDecimal[] Lastweek() {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        //当日时间
        Date date = calendar2.getTime();
        Date date1 = DateUtils.addDays(date, -1);
        Date date2 = DateUtils.addDays(date1, -1);
        Date date3 = DateUtils.addDays(date2, -1);
        Date date4 = DateUtils.addDays(date3, -1);
        Date date5 = DateUtils.addDays(date4, -1);
        Date date6 = DateUtils.addDays(date5, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        BigDecimal total1 = new BigDecimal(0);
        BigDecimal total2 = new BigDecimal(0);
        BigDecimal total3 = new BigDecimal(0);
        BigDecimal total4 = new BigDecimal(0);
        BigDecimal total5 = new BigDecimal(0);
        BigDecimal total6 = new BigDecimal(0);
        BigDecimal total7 = new BigDecimal(0);

        String Monday = sdf.format(date);
        //前一天
        String Tuesday = sdf.format(date1);
        //前两天
        String Wednesday = sdf.format(date2);
        String Thursday = sdf.format(date3);
        String Friday = sdf.format(date4);
        String Saturday = sdf.format(date5);
        String Sunday = sdf.format(date6);
        total1 = salesMapper.selectSUMBytime(Monday);
        if (null == total1 || total1.equals(BigDecimal.ZERO)) {
            total1 = new BigDecimal(0);
        }
        total2 = salesMapper.selectSUMBytime(Tuesday);
        if (null == total2 || total2.equals(BigDecimal.ZERO)) {
            total2 = new BigDecimal(0);
        }
        total3 = salesMapper.selectSUMBytime(Wednesday);
        System.out.println(total3);
        if (null == total3 || total3.equals(BigDecimal.ZERO)) {
            total3 = new BigDecimal(0);
        }
        total4 = salesMapper.selectSUMBytime(Thursday);
        if (null == total4 || total4.equals(BigDecimal.ZERO)) {
            total4 = new BigDecimal(0);
        }
        total5 = salesMapper.selectSUMBytime(Friday);
        if (null == total5 || total5.equals(BigDecimal.ZERO)) {
            total5 = new BigDecimal(0);
        }
        total6 = salesMapper.selectSUMBytime(Saturday);
        if (null == total6 || total6.equals(BigDecimal.ZERO)) {
            total6 = new BigDecimal(0);
        }
        total7 = salesMapper.selectSUMBytime(Sunday);
        if (null == total7 || total7.equals(BigDecimal.ZERO)) {
            total7 = new BigDecimal(0);
        }
        BigDecimal[] myList = {total1, total2, total3, total4, total5, total6, total7};
        return myList;
    }

    @Override
    public BigDecimal[] Lastweek1() {

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        //当日时间
        Date date = calendar2.getTime();
        Date date1 = DateUtils.addDays(date, -7);
        Date date2 = DateUtils.addDays(date1, -1);
        Date date3 = DateUtils.addDays(date2, -1);
        Date date4 = DateUtils.addDays(date3, -1);
        Date date5 = DateUtils.addDays(date4, -1);
        Date date6 = DateUtils.addDays(date5, -1);
        Date date7 = DateUtils.addDays(date6, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        BigDecimal total1 = new BigDecimal(0);
        BigDecimal total2 = new BigDecimal(0);
        BigDecimal total3 = new BigDecimal(0);
        BigDecimal total4 = new BigDecimal(0);
        BigDecimal total5 = new BigDecimal(0);
        BigDecimal total6 = new BigDecimal(0);
        BigDecimal total7 = new BigDecimal(0);

        String Monday = sdf.format(date1);
        //前一天
        String Tuesday = sdf.format(date2);
        //前两天
        String Wednesday = sdf.format(date3);
        String Thursday = sdf.format(date4);
        String Friday = sdf.format(date5);
        String Saturday = sdf.format(date6);
        String Sunday = sdf.format(date7);
        total1 = salesMapper.selectSUMBytime(Monday);
        if (null == total1 || total1.equals(BigDecimal.ZERO)) {
            total1 = new BigDecimal(0);
        }
        total2 = salesMapper.selectSUMBytime(Tuesday);
        if (null == total2 || total2.equals(BigDecimal.ZERO)) {
            total2 = new BigDecimal(0);
        }
        total3 = salesMapper.selectSUMBytime(Wednesday);
        System.out.println(total3);
        if (null == total3 || total3.equals(BigDecimal.ZERO)) {
            total3 = new BigDecimal(0);
        }
        total4 = salesMapper.selectSUMBytime(Thursday);
        if (null == total4 || total4.equals(BigDecimal.ZERO)) {
            total4 = new BigDecimal(0);
        }
        total5 = salesMapper.selectSUMBytime(Friday);
        if (null == total5 || total5.equals(BigDecimal.ZERO)) {
            total5 = new BigDecimal(0);
        }
        total6 = salesMapper.selectSUMBytime(Saturday);
        if (null == total6 || total6.equals(BigDecimal.ZERO)) {
            total6 = new BigDecimal(0);
        }
        total7 = salesMapper.selectSUMBytime(Sunday);
        if (null == total7 || total7.equals(BigDecimal.ZERO)) {
            total7 = new BigDecimal(0);
        }
        BigDecimal[] myList = {total1, total2, total3, total4, total5, total6, total7};
        return myList;
    }

    @Override
    public PageInfo<Sales> pageList(Sales sales, int currentPage, int pageSize) {
        List<Sales> list = new ArrayList<>();
        PageHelper.startPage(currentPage, pageSize);
        //PageHelper.orderBy("orderDate asc");
        list = salesMapper.selectAll();
        PageInfo<Sales> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}
