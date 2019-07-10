package com.ujiuye.auth.service;

import com.ujiuye.auth.bean.EmpRole;
import com.ujiuye.auth.bean.Role;
import com.ujiuye.auth.mapper.EmpRoleMapper;
import com.ujiuye.auth.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: lvwei
 * @Date: 2019/4/16 21:46
 * @project: ppms
 * @Description: 用户-角色
 */
@Service
public class EmpRoleService implements EmpRoleServiceInter {

    @Resource
    private EmpRoleMapper empRoleMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public void saverole(int eid, String roleid) {
        int rolid = Integer.parseInt(roleid);
        Role trole = roleMapper.selectByPrimaryKey(rolid);
        String rolename = trole.getRolename();
        EmpRole empRole = new EmpRole();
        empRole.setEmpFk(eid);
        empRole.setRoleFk(rolid);
        empRole.setErdis(rolename);
        empRoleMapper.insert(empRole);
    }

}
