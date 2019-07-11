package com.nyist.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyist.dao.CustomerMapper;
import com.nyist.entity.Customer;
import com.nyist.entity.Warehouse;
import com.nyist.service.CustomerService;
import com.nyist.vo.ResultVo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/23 16:04
 * @description：${description}
 * @version: $version$
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public int deleteByPrimaryKey(Integer custId) {
        return customerMapper.deleteByPrimaryKey(custId);
    }

    @Override
    public int insert(Customer record) {
        return customerMapper.insert(record);
    }

    @Override
    public int insertSelective(Customer record) {
        return customerMapper.insertSelective(record);
    }

    @Override
    public Customer selectByPrimaryKey(Integer custId) {
        return customerMapper.selectByPrimaryKey(custId);
    }

    @Override
    public int updateByPrimaryKeySelective(Customer record) {
        return customerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Customer record) {
        return customerMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据用户名查找用户
     * @param custName
     * @return
     */
    @Override
    public Customer selectByName(String custName) {
        return customerMapper.selectByName(custName);
    }

    /**
     * 注册功能
     */
    @Override
    public int registerData(Customer customer) {
        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(customer.getCustName());
        /*
        * MD5加密：
        * 使用SimpleHash类对原始密码进行加密。
        * 第一个参数代表使用MD5方式加密
        * 第二个参数为原始密码
        * 第三个参数为盐值，即用户名
        * 第四个参数为加密次数
        * 最后用toHex()方法将加密后的密码转成String
        * */
        String newPs = new SimpleHash("MD5", customer.getCustCode(), salt, 1024).toHex();
        customer.setCustCode(newPs);
        // 看数据库中是否存在该账户
        Customer customer1 = customerMapper.selectByName(customer.getCustName());
        if(customer1 == null) {
            customerMapper.insertSelective(customer);
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public List<Customer> findAll() {
        return customerMapper.findAll();
    }

    @Override
    public PageInfo<Customer> pageList(int currentPage, int pageSize) {
        List<Customer> list = new ArrayList<>();
        PageHelper.startPage(currentPage, pageSize);
        list = customerMapper.selectAll();
        PageInfo<Customer> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
