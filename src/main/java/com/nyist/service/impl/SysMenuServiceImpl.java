package com.nyist.service.impl;

import com.nyist.dao.SysMenuMapper;
import com.nyist.entity.SysMenu;
import com.nyist.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:07
 * @description：${description}
 * @version: $version$
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return sysMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysMenu record) {
        return sysMenuMapper.insert(record);
    }

    @Override
    public int insertSelective(SysMenu record) {
        return sysMenuMapper.insertSelective(record);
    }

    @Override
    public SysMenu selectByPrimaryKey(Long id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysMenu record) {
        return sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(SysMenu record) {
        return sysMenuMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(SysMenu record) {
        return sysMenuMapper.updateByPrimaryKey(record);
    }
}
