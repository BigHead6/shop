package com.nyist.dao;

import com.nyist.entity.SysAuthorityQuestion;

public interface SysAuthorityQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAuthorityQuestion record);

    int insertSelective(SysAuthorityQuestion record);

    SysAuthorityQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAuthorityQuestion record);

    int updateByPrimaryKey(SysAuthorityQuestion record);
}