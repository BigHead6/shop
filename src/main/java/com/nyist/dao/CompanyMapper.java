package com.nyist.dao;

import com.nyist.entity.Company;

import java.util.List;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer supNo);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer supNo);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    List<Company> selectAll();

    Company selectByName(String name);
}