package com.nyist.service.impl;

import com.nyist.dao.SysAuthorityMapper;
import com.nyist.entity.SysAuthority;
import com.nyist.service.SysAuthorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:07
 * @description：${description}
 * @version: $version$
 */
@Service
public class SysAuthorityServiceImpl implements SysAuthorityService {
    @Resource
    private SysAuthorityMapper sysAuthorityMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return sysAuthorityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysAuthority record) {
        return sysAuthorityMapper.insert(record);
    }

    @Override
    public int insertSelective(SysAuthority record) {
        return sysAuthorityMapper.insertSelective(record);
    }

    @Override
    public SysAuthority selectByPrimaryKey(Long id) {
        return sysAuthorityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysAuthority record) {
        return sysAuthorityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysAuthority record) {
        return sysAuthorityMapper.updateByPrimaryKey(record);
    }

    @Override
    public SysAuthority selectByName(String username) {
        return sysAuthorityMapper.selectByUsername(username);
    }
}
