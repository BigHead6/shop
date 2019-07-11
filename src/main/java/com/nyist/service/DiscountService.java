package com.nyist.service;

import com.nyist.entity.Discount;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:53
 * @description：${description}
 * @version: $version$
 */
public interface DiscountService {
    int deleteByPrimaryKey(String custLevel);

    int insert(Discount record);

    int insertSelective(Discount record);

    Discount selectByPrimaryKey(String custLevel);

    int updateByPrimaryKeySelective(Discount record);

    int updateByPrimaryKey(Discount record);
}
