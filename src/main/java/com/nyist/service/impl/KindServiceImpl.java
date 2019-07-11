package com.nyist.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyist.dao.KindMapper;
import com.nyist.entity.Kind;
import com.nyist.entity.Sales;
import com.nyist.service.KindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:05
 * @description：${description}
 * @version: $version$
 */
@Service
public class KindServiceImpl implements KindService  {
    @Resource
    private  KindMapper kindMapper;
    @Override
    public int deleteByPrimaryKey(Integer kindNo) {
        return kindMapper.deleteByPrimaryKey(kindNo);
    }

    @Override
    public int insert(Kind record) {
        return kindMapper.insert(record);
    }

    @Override
    public int insertSelective(Kind record) {
        return kindMapper.insertSelective(record);
    }

    @Override
    public Kind selectByPrimaryKey(Integer kindNo) {
        return kindMapper.selectByPrimaryKey(kindNo);
    }

    @Override
    public int updateByPrimaryKeySelective(Kind record) {
        return kindMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Kind record) {
        return kindMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<String> selectAll() {
        List<Kind> kinds= kindMapper.selectAll();
        List<String> list=new ArrayList<>();
        for(int i=0;i<kinds.size();i++){
            list.add(kinds.get(i).getKindName());
        }
        return list;
    }

    @Override
    public PageInfo<Kind> pageList(int currentPage, int pageSize) {

        List<Kind> list = new ArrayList<>();
        PageHelper.startPage(currentPage, pageSize);
        list = kindMapper.selectAll();
        PageInfo<Kind> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
