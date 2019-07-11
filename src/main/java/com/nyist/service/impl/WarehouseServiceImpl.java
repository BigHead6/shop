package com.nyist.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyist.dao.WarehouseMapper;
import com.nyist.entity.Kind;
import com.nyist.entity.Warehouse;
import com.nyist.service.WarehouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:08
 * @description：${description}
 * @version: $version$
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Resource
    private WarehouseMapper warehouseMapper;
    @Override
    public int deleteByPrimaryKey(String whNo) {
        return warehouseMapper.deleteByPrimaryKey(whNo);
    }

    @Override
    public int insert(Warehouse record) {
        return warehouseMapper.insert(record);
    }

    @Override
    public int insertSelective(Warehouse record) {
        return warehouseMapper.insertSelective(record);
    }
    @Override
    public Warehouse selectByPrimaryKey(String whNo) {
        return warehouseMapper.selectByPrimaryKey(whNo);
    }

    @Override
    public int updateByPrimaryKeySelective(Warehouse record) {
        return warehouseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Warehouse record) {
        return warehouseMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Warehouse> pageList(int currentPage, int pageSize) {
        List<Warehouse> list = new ArrayList<>();
        PageHelper.startPage(currentPage, pageSize);
        list = warehouseMapper.selectAll();
        PageInfo<Warehouse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<String> selectAll() {
        List<Warehouse> warehouses= warehouseMapper.selectAll();
        List<String> list=new ArrayList<>();
        for(int i=0;i<warehouses.size();i++){
            list.add(warehouses.get(i).getWhName());
        }
        return list;
    }
}
