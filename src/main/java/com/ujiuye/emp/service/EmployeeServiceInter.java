package com.ujiuye.emp.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.bean.EmployeeJion;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/4 21:37
 * @project: ppms
 * @Description: 员工模块
 */
public interface EmployeeServiceInter {
    /*查询员工位项目经理p_fk=4*/
    List<Employee> getEmpWithManList();

    /*查询单个员工信息*/
    Employee getEmpByEid(Integer eid);
    /*员工登录*/
    Employee login(Employee employee);

    List<EmployeeJion> listEInfo();

    List<Employee> getEmpexcludeSelf(Integer eid);

    int updatePassword(Employee employee);

    PageInfo<EmployeeJion> userList(String pageNum);

    int  saveInfo(Employee employee);
}
