package com.nyist.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyist.dao.ReturnMapper;
import com.nyist.dao.SalesMapper;
import com.nyist.entity.Company;
import com.nyist.entity.Returns;
import com.nyist.entity.Sales;
import com.nyist.service.ReturnService;
import com.nyist.vo.ReturnsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:06
 * @description：${description}
 * @version: $version$
 */
@Service
public class ReturnServiceImpl implements ReturnService {
    @Resource
    private ReturnMapper returnMapper;
    @Autowired
    private SalesMapper salesMapper;

    @Override
    public int deleteByPrimaryKey(String chanNo) {
        return returnMapper.deleteByPrimaryKey(chanNo);
    }

    @Override
    public int insert(Returns record) {
        return returnMapper.insert(record);
    }

    @Override
    public int insertSelective(Returns record) {
        return returnMapper.insertSelective(record);
    }

    @Override
    public Returns selectByPrimaryKey(String chanNo) {
        return returnMapper.selectByPrimaryKey(chanNo);
    }

    @Override
    public int updateByPrimaryKeySelective(Returns record) {
        return returnMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Returns record) {
        return returnMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ReturnsVo> selectByCustId(int CustId) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Returns> returnsList=returnMapper.selectByCustId(CustId);
        List<ReturnsVo> list=new ArrayList<>();
        for (int  i=0;i<returnsList.size();i++){
            ReturnsVo returnsVo=new ReturnsVo();
            Sales sales= salesMapper.selectByInvoiceNo(returnsList.get(i).getOrderNo());
            returnsVo.setTotal(sales.getTotAmt());
            returnsVo.setChanNo(returnsList.get(i).getChanNo());
            returnsVo.setChanReason(returnsList.get(i).getChanReason());
            returnsVo.setCustId(returnsList.get(i).getCustId());
            returnsVo.setOrderNo(returnsList.get(i).getOrderNo());
            returnsVo.setDelivDate(returnsList.get(i).getDelivDate());
            returnsVo.setChanReason(returnsList.get(i).getChanReason());
            returnsVo.setContent(returnsList.get(i).getContent());
            returnsVo.setStatus(returnsList.get(i).getStatus());
            list.add(returnsVo);
        }
        return list;
    }


    @Override
    public PageInfo<Returns> pageList(int currentPage, int pageSize) {
        List<Returns> list = new ArrayList<>();
        PageHelper.startPage(currentPage, pageSize);
        list = returnMapper.selectAll();
        PageInfo<Returns> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
