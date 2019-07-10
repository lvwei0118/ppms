package com.ujiuye.auth.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.auth.bean.Role;
import com.ujiuye.auth.bean.Sources;
import com.ujiuye.auth.service.RoleServiceInter;
import com.ujiuye.auth.service.RoleSourcesServiceInter;
import com.ujiuye.auth.service.SourcesServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/15 11:07
 * @project: ppms
 * @Description: 角色管理
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleServiceInter serviceinter;
    /*角色权限关联*/
    @Autowired
    private RoleSourcesServiceInter roleSourcesServiceInter;

    /*添加角色*/
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public String addRole(Role role,String sourcesId) {
        //1、保存角色信息
        int roleId =serviceinter.addRole(role);
        //2、保存角色的权限信息到中间表（role-source）
        roleSourcesServiceInter.saveRoleSource(roleId,sourcesId);
        return "redirect:/role/roleList";
    }
    /*角色列表*/
    @RequestMapping(value = "/roleList",method=RequestMethod.GET)
    public ModelAndView roleList(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1")String pageNum){
        StringBuffer url = request.getRequestURL();
        ModelAndView mv = new ModelAndView("role");
        PageInfo<Role> roleList = serviceinter.roleList(pageNum);
        mv.addObject("url",url);
        mv.addObject("rl",roleList);
        return mv;
    }
    @RequestMapping(value = "/listInfo",method=RequestMethod.GET)
    @ResponseBody
    public List<Role> listInfo(){
        List<Role> roleList = serviceinter.listInfo();
        return roleList;
    }
    /*查看角色*/
    @RequestMapping(value = "/lookOneById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView lookOneById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("role-look");
        Role rl =serviceinter.lookOneById(id);
        mv.addObject("ROLE",rl);
        return mv;
    }
    /*更新角色*/
    @RequestMapping(value = "/getOneById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getOneById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("role-edit");
        Role rl =serviceinter.lookOneById(id);
        mv.addObject("role",rl);
        return mv;
    }
    @RequestMapping(value = "updateInfo",method = RequestMethod.POST)
    public String updateInfo(Role role) {
        boolean updateInfo = serviceinter.updateInfo(role);
        if (updateInfo) {
            return "redirect:/role.jsp";
        }else {
            return "redirect:/role-edit.jsp";
        }
    }

    /*删除节点*/
    @RequestMapping(value = "/deleteNode",method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteNode(@RequestParam("id")Integer id) {
        return serviceinter.deleteNode(id);
    }
}
