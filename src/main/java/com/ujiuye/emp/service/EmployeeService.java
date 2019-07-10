package com.ujiuye.emp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.bean.EmployeeExample;
import com.ujiuye.emp.bean.EmployeeJion;
import com.ujiuye.emp.mapper.Employee2Mapper;
import com.ujiuye.emp.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: TODO
 */
@Service
public class EmployeeService implements EmployeeServiceInter{
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private Employee2Mapper employee2Mapper;
    /*查询员工位项目经理p_fk=4*/
    @Transactional(readOnly = true)
    @Override
    public List<Employee> getEmpWithManList() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
        List<Employee> employees = employeeMapper.selectByExample(example);
        return employees;
    }

    /*查询单个员工*/
    @Override
    public Employee getEmpByEid(Integer eid) {

        return employeeMapper.selectByPrimaryKey(eid);
    }

    /*员工登录*/
    @Override
    public Employee login(Employee employee) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(example);
        if (employees!=null && employees.size()>0){
            /*返回employees的eid*/
            return employees.get(0);
        }else{
            return new Employee();
        }

    }
    /*查询所有员工信息带职位*/
    @Override
    public List<EmployeeJion> listEInfo() {

        return employee2Mapper.listEInfo();
    }

    /*查询可以发邮件的员工*/
    @Override
    public List<Employee> getEmpexcludeSelf(Integer eid) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEidNotEqualTo(eid);
        return employeeMapper.selectByExample(example);
    }

    @Override
    public int updatePassword(Employee employee) {
        return employeeMapper.updateByPrimaryKey(employee) ;
    }
    /*查看用户*/
    @Override
    public PageInfo<EmployeeJion> userList(String pageNum) {
        Integer paegNo = 1;
        try {
            paegNo = Integer.parseInt(pageNum);
        } catch (Exception e) {
        }
        PageHelper.startPage(paegNo,3);
        List<EmployeeJion> employeeJions = employee2Mapper.listEInfo();
        PageInfo<EmployeeJion> pageInfo = new PageInfo<EmployeeJion>(employeeJions);
        return pageInfo;
    }

    @Override
    public int  saveInfo(Employee employee) {
        employeeMapper.insert(employee);
        Integer eid = employee.getEid();
        return eid;
    }
}
