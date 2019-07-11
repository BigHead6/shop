package com.nyist.service;

import com.nyist.entity.Address;

import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/6 20:15
 * @description：${description}
 * @version: $version$
 */
public interface AddressService {
    int deleteByPrimaryKey(Integer addId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<Address> selectByCustId(int custId);
}
