package com.ujiuye.usual.service;

import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.bean.CustomerExample;
import com.ujiuye.cus.mapper.CustomerMapper;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.mapper.EmployeeMapper;
import com.ujiuye.usual.bean.Task;
import com.ujiuye.usual.mapper.TaskMapper;
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
 * @Description:任务管理
 */
@Service
public class TaskService implements TaskServiceInter {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private EmployeeMapper employeeMapper;

    /*添加任务*/
    @Override
    public boolean saveTInfo(Task task) {
        int i = taskMapper.insert(task);
        return i > 0;
    }

    /*显示任务*/
    @Override
    public List<Task> listAInfo() {
        List<Task> list = taskMapper.selectByExample(null);
        Date date = new Date();
        long now = date.getTime();
        for (Task task : list) {
            Date starttiem = task.getStarttime();
            long start = starttiem.getTime();
            Date endtime = task.getEndtime();
            long end = endtime.getTime();

            if (start - now > 0) {
                task.setStatus("未开始");
            }
            if (end - now > 0 && start - now < 0) {
                task.setStatus("在进行...");
            }
            if (end - now < 0) {
                task.setStatus("已完成");
            }
            Employee employee = employeeMapper.selectByPrimaryKey(task.getEmpFk());
            task.setEmp(employee);
        }
        return list;
    }

    @Override
    public List<Task> LoginAInfo(Integer eid) {
        List<Task> lists = taskMapper.LoginAInfo(eid);
        Date date = new Date();
        long now = date.getTime();
        for (Task tasks : lists) {
            Date starttiem = tasks.getStarttime();
            long start = starttiem.getTime();
            Date endtime = tasks.getEndtime();
            long end = endtime.getTime();

            if (start - now > 0) {
                tasks.setStatus("未开始");
            }
            if (end - now > 0 && start - now < 0) {
                tasks.setStatus("在进行...");
            }
            if (end - now < 0) {
                tasks.setStatus("已完成");
            }

            Employee employee2 = employeeMapper.selectByPrimaryKey(tasks.getEmpFk());
            tasks.setEmp(employee2);
        }
        return lists;
    }

   /* @Resource
    private CustomerMapper customerMapper;

    *//*添加客户信息*//*
    @Override
    @Transactional
    public boolean saveInfo(Customer customer) {
        customer.setAddtime(new Date());
        int insert = customerMapper.insert(customer);
        return insert>0;
    }
    *//*查询客户信息*//*
    @Override
    @Transactional
    public List<Customer> listInfo() {
        CustomerExample example = new CustomerExample();
        List<Customer> list = customerMapper.selectByExample(example);
        return list;
    }
    *//*查询单个客户详情*//*
    @Override
    @Transactional
    public Customer findInfoById(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }
    *//*修改单个客户详情*//*
    @Override
    @Transactional
    public boolean updateInfo(Customer customer) {
        int i = customerMapper.updateByPrimaryKeySelective(customer);
        return i>0;
    }
    *//*删除客户信息*//*
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
    }*/
}
