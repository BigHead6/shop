package com.nyist.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyist.dao.CompanyMapper;
import com.nyist.entity.Company;
import com.nyist.entity.Customer;
import com.nyist.entity.Warehouse;
import com.nyist.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:04
 * @description：${description}
 * @version: $version$
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Override
    public int deleteByPrimaryKey(Integer supNo) {
        return companyMapper.deleteByPrimaryKey(supNo);
    }

    @Override
    public int insert(Company record) {
        return companyMapper.insert(record);
    }

    @Override
    public int insertSelective(Company record) {
        return companyMapper.insertSelective(record);
    }

    @Override
    public Company selectByPrimaryKey(Integer supNo) {
        return companyMapper.selectByPrimaryKey(supNo);
    }

    @Override
    public int updateByPrimaryKeySelective(Company record) {
        return companyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Company record) {
        return companyMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Company> pageList(int currentPage, int pageSize) {
        List<Company> list = new ArrayList<>();
        PageHelper.startPage(currentPage, pageSize);
        list = companyMapper.selectAll();
        PageInfo<Company> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<String> selectAll() {
        List<Company> companies= companyMapper.selectAll();
        List<String> list=new ArrayList<>();
        for(int i=0;i<companies.size();i++){
            list.add(companies.get(i).getSupName());
        }
        return list;
    }
}
