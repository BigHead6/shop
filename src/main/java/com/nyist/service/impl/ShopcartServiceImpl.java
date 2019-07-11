package com.nyist.service.impl;

import com.nyist.dao.ProductMapper;
import com.nyist.dao.ShopcartMapper;
import com.nyist.entity.Product;
import com.nyist.entity.Shopcart;
import com.nyist.service.ShopcartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:06
 * @description：${description}
 * @version: $version$
 */
@Service
public class ShopcartServiceImpl implements ShopcartService {
    @Resource
    private ShopcartMapper shopcartMapper;
    @Resource
    private ProductMapper productMapper;

    @Override
    public int deleteByPrimaryKey(Integer shopNo) {
        return shopcartMapper.deleteByPrimaryKey(shopNo);
    }

    @Override
    public int insert(Shopcart record) {
        return shopcartMapper.insert(record);
    }

    @Override
    public int insertSelective(Shopcart record) {
        return shopcartMapper.insertSelective(record);
    }

    @Override
    public Shopcart selectByPrimaryKey(Integer shopNo) {
        return shopcartMapper.selectByPrimaryKey(shopNo);
    }

    @Override
    public int updateById(Integer shopNo) {
        return shopcartMapper.updateById(shopNo);
    }

    @Override
    public int updateByShopId(Integer shopNo) {
        return shopcartMapper.updateByShopId(shopNo);
    }

    @Override
    public int updateByPrimaryKey(Shopcart record) {
        return shopcartMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Shopcart> selectByCustId(Integer custId) {

        List<Shopcart> shopcartList=  shopcartMapper.selectByCustId(custId);
        for (int i=0;i<shopcartList.size();i++){
           Product product= productMapper.selectById(shopcartList.get(i).getProdId());
           shopcartList.get(i).setExtend3(product.getProdDesc());
        }
        return  shopcartList;

    }
}
