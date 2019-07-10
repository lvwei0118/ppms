package com.ujiuye.pro.mapper;

import com.ujiuye.pro.bean.Project2;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/7 11:58
 * @project: ppms
 * @Description: 项目联表查询2
 */
public interface Project2Mapper {
    List<Project2> listPInfo();

    Project2 findPInfoById(Integer pid);
}
