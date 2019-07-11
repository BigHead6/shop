package com.nyist.dao;

import com.nyist.entity.Address;

import java.util.List;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer addId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<Address> selectByCustId(int custId);
}