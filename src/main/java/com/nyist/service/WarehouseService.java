package com.nyist.service;

import com.github.pagehelper.PageInfo;
import com.nyist.entity.Kind;
import com.nyist.entity.Warehouse;

import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:59
 * @description：${description}
 * @version: $version$
 */
public interface WarehouseService {
    int deleteByPrimaryKey(String whNo);

    int insert(Warehouse record);

    int insertSelective(Warehouse record);

    Warehouse selectByPrimaryKey(String whNo);

    int updateByPrimaryKeySelective(Warehouse record);

    int updateByPrimaryKey(Warehouse record);

    /**
     * 查询全部品牌类别分页
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<Warehouse> pageList(int currentPage, int pageSize);

    List<String> selectAll();
}
