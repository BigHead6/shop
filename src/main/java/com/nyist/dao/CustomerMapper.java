package com.nyist.dao;

import com.nyist.entity.Customer;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer custId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer custId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    Customer selectByName(String custName);

    List<Customer> findAll();
    List<Customer> findToday();
    List<Customer> findWeek();
    List<Customer> findYestoday();

    List<Customer> selectAll();
}