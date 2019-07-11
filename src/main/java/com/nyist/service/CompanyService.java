package com.nyist.service;

import com.github.pagehelper.PageInfo;
import com.nyist.entity.Company;
import com.nyist.entity.Customer;

import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:52
 * @description：${description}
 * @version: $version$
 */
public interface CompanyService {
    int deleteByPrimaryKey(Integer supNo);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer supNo);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    /**
     * 查询全部活动分页
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<Company> pageList(int currentPage, int pageSize);

    List<String> selectAll();
}
