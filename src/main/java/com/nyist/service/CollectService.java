package com.nyist.service;

import com.nyist.entity.Collect;
import com.nyist.entity.Product;

import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/22 15:08
 * @description：${description}
 * @version: $version$
 */
public interface CollectService {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect collect);

    int updateByPrimaryKey(Collect record);

    List<Product> findAll(Integer custId);

    Collect selectByCollect(Collect collect);

}
