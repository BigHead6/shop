package com.nyist.dao;

import com.nyist.entity.Warehouse;

import java.util.List;

public interface WarehouseMapper {
    int deleteByPrimaryKey(String whNo);

    int insert(Warehouse record);

    int insertSelective(Warehouse record);

    Warehouse selectByPrimaryKey(String whNo);

    int updateByPrimaryKeySelective(Warehouse record);

    int updateByPrimaryKey(Warehouse record);

    List<Warehouse> selectAll();

    Warehouse selectByName(String name);
}