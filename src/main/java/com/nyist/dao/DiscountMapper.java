package com.nyist.dao;

import com.nyist.entity.Discount;

public interface DiscountMapper {
    int deleteByPrimaryKey(String custLevel);

    int insert(Discount record);

    int insertSelective(Discount record);

    Discount selectByPrimaryKey(String custLevel);

    int updateByPrimaryKeySelective(Discount record);

    int updateByPrimaryKey(Discount record);
}