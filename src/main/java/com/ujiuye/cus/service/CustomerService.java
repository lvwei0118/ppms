package com.ujiuye.cus.service;

import com.mchange.v1.identicator.IdList;
import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.bean.CustomerExample;
import com.ujiuye.cus.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: TODO
 */
@Service
public class CustomerService implements CustomerServiceInter{
    @Resource
    private CustomerMapper customerMapper;

    /*添加客户信息*/
    @Override
    @Transactional
    public boolean saveInfo(Customer customer) {
        customer.setAddtime(new Date());
        int insert = customerMapper.insert(customer);
        return insert>0;
    }
    /*查询客户信息*/
    @Override
    @Transactional
    public List<Customer> listInfo() {
        CustomerExample example = new CustomerExample();
        List<Customer> list = customerMapper.selectByExample(example);
        return list;
    }
    /*查询单个客户详情*/
    @Override
    @Transactional
    public Customer findInfoById(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }
    /*修改单个客户详情*/
    @Override
    @Transactional
    public boolean updateInfo(Customer customer) {
        int i = customerMapper.updateByPrimaryKeySelective(customer);
        return i>0;
    }
    /*删除客户信息*/
    @Override
    @Transactional
    public boolean delInfo(String ids){

        String[] idsArr=ids.split(",");
        List<Integer> list = new ArrayList<Integer>(ids.length());
        for (int i=0;i<idsArr.length;i++
             ) {
            list.add(Integer.parseInt(idsArr[i]));
        }
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(list);
        int i = customerMapper.deleteByExample(example);
        return i>0;
    }

    @Override
    @Transactional
    public List<Customer> selectInfo(int chid,String infoKey,int orderWord) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria1 = example.createCriteria();
        CustomerExample.Criteria criteria2 = example.createCriteria();
        if (chid==0){
            criteria1.andComnameLike( "%"+infoKey+"%");
            criteria2.andCompanypersonLike("%"+infoKey+"%");
            example.or(criteria2);
        }else if (chid==1){
            criteria1.andComnameLike("%"+infoKey+"%");
        }else if (chid==2){
            criteria2.andCompanypersonLike("%"+infoKey+"%");
        }
        if (orderWord==1){
            example.setOrderByClause("id");
        }
        List<Customer> list = customerMapper.selectByExample(example);
        return list;
    }
}
