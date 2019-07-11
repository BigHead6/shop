package com.nyist.dao;

import com.nyist.entity.Kind;

import java.util.List;

public interface KindMapper {
    int deleteByPrimaryKey(Integer kindNo);

    int insert(Kind record);

    int insertSelective(Kind record);

    Kind selectByPrimaryKey(Integer kindNo);

    int updateByPrimaryKeySelective(Kind record);

    int updateByPrimaryKey(Kind record);

    List<Kind> selectAll();

    Kind selectByName(String name);

}