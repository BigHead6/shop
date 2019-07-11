package com.nyist.service.impl;

import com.nyist.dao.DiscountMapper;
import com.nyist.entity.Discount;
import com.nyist.service.DiscountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:05
 * @description：${description}
 * @version: $version$
 */
@Service
public class DiscountServiceImpl implements DiscountService {

    @Resource
    private DiscountMapper discountMapper;
    @Override
    public int deleteByPrimaryKey(String custLevel) {
        return discountMapper.deleteByPrimaryKey(custLevel);
    }

    @Override
    public int insert(Discount record) {
        return discountMapper.insert(record);
    }

    @Override
    public int insertSelective(Discount record) {
        return discountMapper.insertSelective(record);
    }

    @Override
    public Discount selectByPrimaryKey(String custLevel) {
        return discountMapper.selectByPrimaryKey(custLevel);
    }

    @Override
    public int updateByPrimaryKeySelective(Discount record) {
        return discountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Discount record) {
        return discountMapper.updateByPrimaryKey(record);
    }
}
