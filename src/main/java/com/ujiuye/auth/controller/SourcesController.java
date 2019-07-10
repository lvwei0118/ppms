package com.ujiuye.auth.controller;

import com.ujiuye.auth.bean.Sources;
import com.ujiuye.auth.service.SourcesServiceInter;
import com.ujiuye.cus.bean.Customer;
import com.ujiuye.usual.bean.ForumPosts;
import com.ujiuye.usual.service.ForumPostsServiceinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/15 11:07
 * @project: ppms
 * @Description: 权限管理
 */
@Controller
@RequestMapping("/auth")
public class SourcesController {
    @Autowired
    private SourcesServiceInter serviceinter;

    /*显示权限树*/
    @RequestMapping(value = "/showAuth")
    @ResponseBody
    public List<Sources> getSourceList() {
        //获取pid(父元素)为0的元素列表-总父元素(一级元素)
        List<Sources> pSource =serviceinter.getSourcesByPid(0);
        //创建方法获取父元素所有的子元素
        queryChildren(pSource.get(0));
        //返回父元素列表
        return pSource;
    }
    //方法--获取父元素所有的子元素--传入父元素参数
    private void queryChildren(Sources pSource) {
        //获取父元素的id
        Integer id = pSource.getId();
        //获取pid(父元素)为父元素id的元素列表--二级元素
        List<Sources> cSource =serviceinter.getSourcesByPid(id);
        //对二级元素进行遍历获得其子集元素
        for (Sources sour:cSource) {
            //递归
            queryChildren(sour);
        }
        pSource.setChildren(cSource);

    }

    /*更新节点*/
    @RequestMapping(value = "/getOneById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getOneById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("pm-edit");
        Sources ss =serviceinter.getOneById(id);
        mv.addObject("sour",ss);
        return mv;
    }
    @RequestMapping(value = "updateInfo",method = RequestMethod.POST)
    public String updateInfo(Sources sources) {
        boolean updateInfo = serviceinter.updateInfo(sources);
        if (updateInfo) {
            return "redirect:/pm.jsp";
        }else {
            return "redirect:/pm-edit.jsp";
        }
    }
    /*添加节点*/
    @RequestMapping(value = "/addNode",method = RequestMethod.POST)
    public String addNode(Sources sources) {
        boolean result =serviceinter.addNode(sources);
        if (result) {
            return "redirect:/pm.jsp";
        }else {
            return "redirect:/pm-add.jsp";
        }
    }
    /*删除节点*/
    @RequestMapping(value = "/deleteNode",method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteNode(@RequestParam("id")Integer id) {
        return serviceinter.deleteNode(id);
    }
}
