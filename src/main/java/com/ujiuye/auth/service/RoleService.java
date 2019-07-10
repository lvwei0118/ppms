package com.ujiuye.auth.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.auth.bean.Role;
import com.ujiuye.auth.bean.Sources;
import com.ujiuye.auth.bean.SourcesExample;
import com.ujiuye.auth.mapper.RoleMapper;
import com.ujiuye.auth.mapper.SourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/16 13:03
 * @project: ppms
 * @Description: 角色管理
 */
@Service
public class RoleService implements RoleServiceInter {
    @Resource
    private RoleMapper roleMapper;

    /*添加角色*/
    @Override
    public int addRole(Role role) {
        roleMapper.insert(role);
        Integer roleid = role.getRoleid();
        return roleid;
    }
    /*列表*/
    @Override
    public PageInfo<Role> roleList(String pageNum) {
       Integer pageNo=1;
      try{
          pageNo= Integer.parseInt(pageNum);
       }catch (Exception e){
      }
      PageHelper.startPage(pageNo, 5);
      List<Role> list = roleMapper.selectByExample(null);
      PageInfo<Role> rolePageInfo = new PageInfo<>(list, 5);
      return rolePageInfo;
    }
    @Override
    public List<Role> listInfo() {
        List<Role> list = roleMapper.selectByExample(null);
        return list;
    }

    /*查看*/
    @Override
    public Role lookOneById(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }
    /*修改*/
    @Override
    public boolean updateInfo(Role role) {
        int i = roleMapper.updateByPrimaryKey(role);
        return i>0;
    }
    /*删除*/
    @Override
    public Boolean deleteNode(Integer id) {
        int i = roleMapper.deleteByPrimaryKey(id);
        return i>0;
    }

}
