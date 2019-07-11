package com.nyist.dao;

import com.nyist.entity.Shopcart;

import java.util.List;

public interface ShopcartMapper {
    int deleteByPrimaryKey(Integer shopNo);

    int insert(Shopcart record);

    int insertSelective(Shopcart record);

    Shopcart selectByPrimaryKey(Integer shopNo);

    int updateById(Integer shopNo);

    int updateByPrimaryKey(Shopcart record);
    List<Shopcart> selectByCustId(Integer custId);

    int updateByShopId(Integer shopNo);


}