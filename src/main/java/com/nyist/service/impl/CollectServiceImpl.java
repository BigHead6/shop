package com.nyist.service.impl;

import com.alipay.api.domain.PricingVO;
import com.nyist.dao.CollectMapper;
import com.nyist.dao.ProductMapper;
import com.nyist.entity.Collect;
import com.nyist.entity.Product;
import com.nyist.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/22 15:09
 * @description：${description}
 * @version: $version$
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return collectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Collect record) {
        return collectMapper.insert(record);
    }

    @Override
    public int insertSelective(Collect record) {
        int flag = 0;
        int i = collectMapper.insertSelective(record);
        if (i != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Collect selectByPrimaryKey(Integer id) {
        return collectMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Collect collect) {
        collect.setFlag("0");
        return collectMapper.updateByPrimaryKeySelective(collect);
    }

    @Override
    public int updateByPrimaryKey(Collect record) {
        return collectMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Product> findAll(Integer custId) {
        List<Collect> collectList = collectMapper.findAll(custId);
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < collectList.size(); i++) {
            //查询所有收藏夹商品的信息
            Product product = productMapper.selectById(collectList.get(i).getProdId());
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Collect selectByCollect(Collect collect) {
        return collectMapper.selectByCollect(collect);
    }
}
