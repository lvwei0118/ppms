package com.ujiuye.auth.service;

import com.ujiuye.auth.bean.Role;

/**
 * @Auther: lvwei
 * @Date: 2019/4/16 18:38
 * @project: ppms
 * @Description: 角色权限关联表管理
 */
public interface RoleSourcesServiceInter {
    void saveRoleSource(int roleId, String sourcesId);

}
