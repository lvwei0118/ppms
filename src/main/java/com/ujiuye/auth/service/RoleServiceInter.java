package com.ujiuye.auth.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.auth.bean.Role;
import com.ujiuye.auth.bean.Sources;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/16 13:02
 * @project: ppms
 * @Description: 角色管理
 */
public interface RoleServiceInter {

    int addRole(Role role);

    Role lookOneById(Integer id);

    boolean updateInfo(Role role);

    Boolean deleteNode(Integer id);

    PageInfo<Role> roleList(String pageNum);

    List<Role> listInfo();
}
