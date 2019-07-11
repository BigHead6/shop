package com.nyist.dao;

import com.nyist.entity.Returns;

import java.util.List;

public interface ReturnMapper {
    int deleteByPrimaryKey(String chanNo);

    int insert(Returns record);

    int insertSelective(Returns record);

    Returns selectByPrimaryKey(String chanNo);

    int updateByPrimaryKeySelective(Returns record);

    int updateByPrimaryKey(Returns record);

    Returns selectByOrderNo(String orderNo);

    List<Returns> selectByCustId(int CustId);

    List<Returns> selectAll();
}