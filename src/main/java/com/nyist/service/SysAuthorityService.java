package com.nyist.service;

import com.nyist.entity.SysAuthority;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:57
 * @description：${description}
 * @version: $version$
 */
public interface SysAuthorityService {
    int deleteByPrimaryKey(Long id);

    int insert(SysAuthority record);

    int insertSelective(SysAuthority record);

    SysAuthority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAuthority record);

    int updateByPrimaryKey(SysAuthority record);


    SysAuthority selectByName(String username);
}
