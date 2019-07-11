package com.nyist.service;

import com.github.pagehelper.PageInfo;
import com.nyist.entity.Kind;
import com.nyist.entity.Sales;

import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:54
 * @description：${description}
 * @version: $version$
 */
public interface KindService {
    int deleteByPrimaryKey(Integer kindNo);

    int insert(Kind record);

    int insertSelective(Kind record);

    Kind selectByPrimaryKey(Integer kindNo);

    int updateByPrimaryKeySelective(Kind record);

    int updateByPrimaryKey(Kind record);

    List<String> selectAll();

    /**
     * 查询全部商品类别分页
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<Kind> pageList(int currentPage, int pageSize);
}
