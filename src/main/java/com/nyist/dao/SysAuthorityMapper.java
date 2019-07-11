package com.nyist.dao;

import com.nyist.entity.SysAuthority;

public interface SysAuthorityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysAuthority record);

    int insertSelective(SysAuthority record);

    SysAuthority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAuthority record);

    int updateByPrimaryKey(SysAuthority record);

    SysAuthority selectByUsername(String username);
}