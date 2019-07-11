package com.nyist.service;

import com.nyist.entity.SysMenu;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:59
 * @description：${description}
 * @version: $version$
 */
public interface SysMenuService {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKeyWithBLOBs(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}
