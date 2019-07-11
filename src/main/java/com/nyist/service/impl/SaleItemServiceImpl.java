package com.nyist.service.impl;

import com.nyist.dao.ProductMapper;
import com.nyist.dao.SaleItemMapper;
import com.nyist.entity.Product;
import com.nyist.entity.SaleItem;
import com.nyist.entity.Sales;
import com.nyist.service.SaleItemService;
import com.nyist.service.SalesService;
import com.nyist.vo.OrderVo;
import com.nyist.vo.RefundVo;
import com.nyist.vo.SaleItemVo;
import com.nyist.vo.shopVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:06
 * @description：${description}
 * @version: $version$
 */
@Service
public class SaleItemServiceImpl implements SaleItemService {
    @Resource
    private SaleItemMapper saleItemMapper;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public int deleteByPrimaryKey(Integer orderNo) {
        return saleItemMapper.deleteByPrimaryKey(orderNo);
    }

    @Override
    public void insert(SaleItem record) {
        saleItemMapper.insert(record);
    }

    @Override
    public int insertSelective(shopVo[] shopVo) {
        int flag = 0;
        SaleItem saleItem = new SaleItem();
        for (int i = 0; i < shopVo.length; i++) {
            saleItem.setInvoiceNo(shopVo[0].getOrderNO());
            saleItem.setProdId(shopVo[i].getpId());
            saleItem.setQty(shopVo[i].getNum());
            saleItem.setUnitPrice(new BigDecimal(shopVo[i].getPrice()));
            saleItem.setOrderDate(new Date());
            saleItemMapper.insertSelective(saleItem);
        }
        flag = 1;
        return flag;
    }

    @Override
    public SaleItem selectByPrimaryKey(Integer orderNo) {
        return saleItemMapper.selectByPrimaryKey(orderNo);
    }

    @Override
    public int updateByPrimaryKeySelective(SaleItem record) {
        return saleItemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaleItem record) {
        return saleItemMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SaleItem> findAll(String invoiceNo) {
        List<SaleItem> saleItemList = saleItemMapper.findAll(invoiceNo);
        for (int i = 0; i < saleItemList.size(); i++) {
            Product product = productMapper.selectById(saleItemList.get(i).getProdId());
            saleItemList.get(i).setDisPrice(product.getUnitPrice());
            saleItemList.get(i).setExtend2(product.getImage());
            saleItemList.get(i).setExtend3(product.getProdDesc());
        }
        return saleItemList;
    }

    @Override
    public List<SaleItem> selectProdByInvoiceNo(String invoiceNo) {
        return saleItemMapper.selectProdByInvoiceNo(invoiceNo);
    }


    @Override
    public List<SaleItemVo> selectProdByInvoiceNo1(String invoiceNo) {
        List<SaleItem> saleItemList = saleItemMapper.selectProdByInvoiceNo(invoiceNo);
        List<SaleItemVo> saleItemVos = new ArrayList<>();
        for (int i = 0; i < saleItemList.size(); i++) {
            Product product = productMapper.selectById(saleItemList.get(i).getProdId());
            SaleItemVo saleItemVo = new SaleItemVo(saleItemList.get(i), product.getImage(), product.getName(),product.getProdDesc());
            saleItemVos.add(saleItemVo);
        }
        return saleItemVos;
    }

    /**
     * 退货单vo
     * @param product
     * @param saleItem
     * @return
     */
    @Override
    public RefundVo refund(Product product, SaleItem saleItem) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        RefundVo refundVo=new RefundVo();
        refundVo.setInvoice(saleItem.getInvoiceNo());
        refundVo.setUnitPrice(product.getUnitPrice());
        refundVo.setQty(saleItem.getQty());
        refundVo.setOrderDate(df.format(saleItem.getOrderDate()));
        refundVo.setImage(product.getImage());
        return refundVo;
    }
}
