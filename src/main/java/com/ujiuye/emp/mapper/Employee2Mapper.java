package com.ujiuye.emp.mapper;

import com.ujiuye.emp.bean.EmployeeJion;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/9 16:41
 * @project: ppms
 * @Description: 联表查询
 */
public interface Employee2Mapper {
    List<EmployeeJion> listEInfo();
}
