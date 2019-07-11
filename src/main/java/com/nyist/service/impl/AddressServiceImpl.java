package com.nyist.service.impl;

import com.nyist.dao.AddressMapper;
import com.nyist.entity.Address;
import com.nyist.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/6 20:15
 * @description：${description}
 * @version: $version$
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public int deleteByPrimaryKey(Integer addId) {
        return addressMapper.deleteByPrimaryKey(addId);
    }

    @Override
    public int insert(Address record) {
        return addressMapper.insert(record);
    }

    @Override
    public int insertSelective(Address record) {
        return addressMapper.insertSelective(record);
    }

    @Override
    public Address selectByPrimaryKey(Integer addId) {
        return addressMapper.selectByPrimaryKey(addId);
    }

    @Override
    public int updateByPrimaryKeySelective(Address record) {
        return addressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Address record) {
        return addressMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Address> selectByCustId(int custId) {
        return addressMapper.selectByCustId(custId);
    }
}
