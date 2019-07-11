package com.nyist.service;

import com.github.pagehelper.PageInfo;
import com.nyist.entity.Company;
import com.nyist.entity.Returns;
import com.nyist.vo.ReturnsVo;

import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:54
 * @description：${description}
 * @version: $version$
 */
public interface ReturnService {
    int deleteByPrimaryKey(String chanNo);

    int insert(Returns record);

    int insertSelective(Returns record);

    Returns selectByPrimaryKey(String chanNo);

    int updateByPrimaryKeySelective(Returns record);

    int updateByPrimaryKey(Returns record);


    /**
     * 根据客户id查询退单信息
     * @param CustId
     * @return
     */
    List<ReturnsVo>  selectByCustId(int CustId);

    /**
     * 查询全部活动分页
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<Returns> pageList(int currentPage, int pageSize);
}
