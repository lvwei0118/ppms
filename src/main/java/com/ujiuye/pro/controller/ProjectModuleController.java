package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.bean.ModuleJoin;
import com.ujiuye.pro.service.ProjectModuleServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:54
 * @project: ppms
 * @Description: 项目模块管理
 */
@Controller
@RequestMapping("/mod")
public class ProjectModuleController {
    @Autowired
    private ProjectModuleServiceInter moduleService;

    /*添加模块信息*/
    @RequestMapping(value = "/saveMInfo",method = RequestMethod.POST)
    public String saveMInfo(Module module,String projectname) {
        String[] arr =projectname.split(",");
        module.setProname(arr[0]);
        boolean info = moduleService.saveMInfo(module);
        if (info) {
            return "redirect:/project-model.jsp";
        }else {
            return "redirect:/project-model-add.jsp";
        }
    }

    /*查询模块信息(连表)*/
    @RequestMapping(value = "/listMInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<ModuleJoin> listMInfo() {
        List<ModuleJoin> list = moduleService.listMInfo();
        /*map.put("mouleList",list);
        return "redirect:/project-model.jsp";*/
        return list;
    }
    /*根据analysisFk查询模块*/
    @RequestMapping(value = "/listMInfoByaf/{aid}",method = RequestMethod.GET)
    @ResponseBody
    public List<ModuleJoin> listMInfoByaf(@PathVariable("aid")Integer aid) {
        List<ModuleJoin> list = moduleService.listMInfoByaf(aid);
        return list;
    }
    /*查询单个模块详情*/
    @RequestMapping(value = "/findMInfoById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findMInfoById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("/project-model-look");
        ModuleJoin mInfoById = moduleService.findMInfoById(id);
        mv.addObject("moduleDetails",mInfoById);
        return mv;
    }
    /*修改单个模块详情*/
    @RequestMapping(value = "/findMById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findMById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("/project-model-edit");
        ModuleJoin mInfoById = moduleService.findMInfoById(id);
        mv.addObject("moduleDetails",mInfoById);
        return mv;
    }
    @RequestMapping(value = "/updateMInfo",method = RequestMethod.POST)
    public String updateMInfo(Module module) {
        boolean updateMInfo = moduleService.updateMInfo(module);
        if (updateMInfo) {
            return "redirect:/project-model.jsp";
        }else {
            return "redirect:/project-model-edit.jsp";
        }
    }
    /*删除模块*/
    @RequestMapping(value = "/delMInfo/{ids}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delMInfo(@PathVariable("ids")String ids) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean b = moduleService.delMInfo(ids);
            map.put("statusCode",200);
            map.put("message","success");
        }catch (Exception e){
            map.put("statusCode",500);
            map.put("message",e.getMessage());
        }
        return map;
    }
    /*条件查询模块信息*/
    @RequestMapping(value = "/selectMInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<Module> selectNInfo(int nhid, String infoKey, int orderWord) {
        return moduleService.selectMInfo(nhid,infoKey,orderWord);

    }
}




