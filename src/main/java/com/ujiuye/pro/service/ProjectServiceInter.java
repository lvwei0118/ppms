package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.Project2;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 13:08
 * @project: ppms
 * @Description: 项目基本信息管理
 */

public interface ProjectServiceInter {
    /*添加项目*/
    boolean saveInfo(Project project);

    /*查询项目信息*/
    List<Project2> listPInfo();
    /*List<Project> selectByExample(ProjectExample example);*/

    /*查询单个项目详情*/

    Project2 findPInfoById(Integer pid);

    /*修改单个项目详情*/

    boolean updatePInfo(Project project);

    /*删除项目信息*/

    boolean delPInfo(String ids);

    /*条件查询项目*/

    List<Project2> selectPInfo(int phid, String infoKey, int orderWord);

    /*查询项目（无需求分析）*/
    List<Project> listPInfoWithoutNeed();
}
