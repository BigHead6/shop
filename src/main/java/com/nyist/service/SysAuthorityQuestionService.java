package com.nyist.service;

import com.nyist.entity.SysAuthorityQuestion;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 15:58
 * @description：${description}
 * @version: $version$
 */
public interface SysAuthorityQuestionService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAuthorityQuestion record);

    int insertSelective(SysAuthorityQuestion record);

    SysAuthorityQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAuthorityQuestion record);

    int updateByPrimaryKey(SysAuthorityQuestion record);
}
