package com.nyist.service;

import com.nyist.entity.Shopcart;

import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:57
 * @description：${description}
 * @version: $version$
 */
public interface ShopcartService {
    int deleteByPrimaryKey(Integer shopNo);

    int insert(Shopcart record);

    int insertSelective(Shopcart record);

    Shopcart selectByPrimaryKey(Integer shopNo);

    int updateById(Integer shopNo);

    int updateByShopId(Integer shopNo);

    int updateByPrimaryKey(Shopcart record);

    /**
     * 根据顾客id查询购物车
     * @param custId
     * @return
     */
    List<Shopcart> selectByCustId(Integer custId);


}
