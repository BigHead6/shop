package com.nyist.dao;

import com.nyist.entity.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKeyWithBLOBs(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}