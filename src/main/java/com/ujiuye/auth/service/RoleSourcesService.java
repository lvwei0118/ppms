package com.ujiuye.auth.service;

import com.ujiuye.auth.bean.Role;
import com.ujiuye.auth.bean.RoleSources;
import com.ujiuye.auth.mapper.RoleMapper;
import com.ujiuye.auth.mapper.RoleSourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @Auther: lvwei
 * @Date: 2019/4/16 18:37
 * @project: ppms
 * @Description: 角色权限关联表管理
 */
@Service
public class RoleSourcesService implements RoleSourcesServiceInter {
    @Resource
    private RoleSourcesMapper roleSourcesMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public void saveRoleSource(int roleId, String sourcesId) {
        String[] ids = sourcesId.split(",");
        ArrayList<RoleSources> roleSources1ist = new ArrayList<RoleSources>();
        for (String id :ids) {
            RoleSources roleSources = new RoleSources();
            roleSources.setRoleid(roleId);
            Role role1 = roleMapper.selectByPrimaryKey(roleId);
            String roledis = role1.getRoledis();
            roleSources.setRsdis(roledis);
            roleSources.setSid(Integer.parseInt(id));
            roleSources1ist.add(roleSources);
        }
        roleSourcesMapper.batchInsert(roleSources1ist);
    }
}
