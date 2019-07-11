package com.nyist.service.impl;

import com.nyist.dao.SysAuthorityQuestionMapper;
import com.nyist.entity.SysAuthorityQuestion;
import com.nyist.service.SysAuthorityQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:07
 * @description：${description}
 * @version: $version$
 */
@Service
public class SysAuthorityQuestionServiceImpl implements SysAuthorityQuestionService {
    @Resource
    private SysAuthorityQuestionMapper sysAuthorityQuestionMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysAuthorityQuestionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysAuthorityQuestion record) {
        return sysAuthorityQuestionMapper.insert(record);
    }

    @Override
    public int insertSelective(SysAuthorityQuestion record) {
        return sysAuthorityQuestionMapper.insertSelective(record);
    }

    @Override
    public SysAuthorityQuestion selectByPrimaryKey(Integer id) {
        return sysAuthorityQuestionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysAuthorityQuestion record) {
        return sysAuthorityQuestionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysAuthorityQuestion record) {
        return sysAuthorityQuestionMapper.updateByPrimaryKey(record);
    }
}
