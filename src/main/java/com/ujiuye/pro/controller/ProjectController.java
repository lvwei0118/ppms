package com.ujiuye.pro.controller;



import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.Project2;
import com.ujiuye.pro.service.ProjectServiceInter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: 项目基本信息管理
 */

@Controller
@RequestMapping("/pro")
public class ProjectController {
    @Resource
    private ProjectServiceInter projectServiceInter;

    /*添加项目信息*/
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Project project ,String componyname) {
        String[] arr = componyname.split(",");
        project.setComname(Integer.parseInt(arr[1]));

        boolean result =projectServiceInter.saveInfo(project);
        if (result) {
            return "redirect:/project-base.jsp";
        }else {
            return "redirect:/project-base-add.jsp";
        }
    }

    /*查询项目信息*/
    @RequestMapping(value = "/listPInfo",method = RequestMethod.GET)
    @ResponseBody
    public  /*ModelAndView*/List<Project2> listPInfo() {
        /*ModelAndView mv = new ModelAndView("project-base");
        List<Project> listPInfo = projectServiceInter.listPInfo();
        mv.addObject("project",listPInfo);
        return mv;*/
        /*List<Project> list = projectServiceInter.listPInfo();*/
        List<Project2> list = projectServiceInter.listPInfo();
        return list;
    }
    /*查询项目（无需求分析）*/
    @RequestMapping(value = "/listPInfoWithoutNeed",method = RequestMethod.GET)
    @ResponseBody
    public  List<Project> listPInfoWithoutNeed() {
        List<Project> list = projectServiceInter.listPInfoWithoutNeed();
        return list;
    }
    /*查询单个项目详情*/
    @RequestMapping(value = "/findPInfoById/{pid}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findPInfoById(@PathVariable("pid")Integer pid) {
        ModelAndView mv = new ModelAndView("/project-base-look");
        Project2 pInfoById = projectServiceInter.findPInfoById(pid);
        mv.addObject("proDetails",pInfoById);
        return mv;
    }
    /*修改单个项目详情*/
    @RequestMapping(value = "/findPById/{pid}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findPById(@PathVariable("pid")Integer pid) {
        ModelAndView mv = new ModelAndView("/project-base-edit");
        Project2 pInfoById = projectServiceInter.findPInfoById(pid);
        mv.addObject("proDetails",pInfoById);
        return mv;
    }
    @RequestMapping(value = "/updatePInfo",method = RequestMethod.POST)
    public String updatePInfo(Project project,String componyname) {
        String[] arr = componyname.split(",");
        project.setComname(Integer.parseInt(arr[1]));
        boolean updatePInfo = projectServiceInter.updatePInfo(project);
        if (updatePInfo) {
            return "redirect:/project-base.jsp";
        }else {
            return "redirect:/project-base-edit.jsp";
        }
    }
    /*删除项目*/
    @RequestMapping(value = "/delPInfo/{ids}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delPInfo(@PathVariable("ids")String ids) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean b = projectServiceInter.delPInfo(ids);
            map.put("statusCode",200);
            map.put("message","success");
        }catch (Exception e){
            map.put("statusCode",500);
            map.put("message",e.getMessage());
        }
        return map;
    }
    /*条件查询项目信息*/
    @RequestMapping(value = "/selectPInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<Project2> selectPInfo(int phid, String infoKey, int orderWord) {
        return projectServiceInter.selectPInfo(phid,infoKey,orderWord);

    }


}
