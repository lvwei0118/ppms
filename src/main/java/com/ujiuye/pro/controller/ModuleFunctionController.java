package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.bean.FunctionJion;
import com.ujiuye.pro.service.ModuleFunctionServiceInter;
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
 * @Date: 2019/4/5 15:59
 * @project: ppms
 * @Description: 模块功能管理
 */
@Controller
@RequestMapping("/func")
public class ModuleFunctionController {

    @Autowired
    private ModuleFunctionServiceInter functionService;

    /*添加功能信息*/
    @RequestMapping(value = "/saveFInfo",method = RequestMethod.POST)
    public String saveFInfo(Function function,String projectname) {
        String[] arr = projectname.split(",");
        function.setProname(arr[0]);
        boolean info = functionService.saveFInfo(function);
        if (info) {
            return "redirect:/project-function.jsp";
        }else {
            return "redirect:/project-function-add.jsp";
        }
    }

    /*查询功能信息*/
    @RequestMapping(value = "/listFInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<FunctionJion> listFInfo(){
        return functionService.listFInfo();
    }
    /*查询单个功能详情*/
    @RequestMapping(value = "/findFInfoById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findFInfoById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("/project-function-look");
        FunctionJion fInfoById = functionService.findFInfoById(id);
        mv.addObject("funcDetails",fInfoById);
        return mv;
    }
    /*修改单个功能详情*/
    @RequestMapping(value = "/findFById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findMById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("/project-function-edit");
        FunctionJion fInfoById = functionService.findFInfoById(id);
        mv.addObject("funcDetails",fInfoById);
        return mv;
    }
    @RequestMapping(value = "/updateFInfo",method = RequestMethod.POST)
    public String updateFInfo(Function function) {
        boolean updateFInfo = functionService.updateFInfo(function);
        if (updateFInfo) {
            return "redirect:/project-function.jsp";
        }else {
            return "redirect:/project-function-edit.jsp";
        }
    }
    /*删除功能*/
    @RequestMapping(value = "/delFInfo/{ids}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delFInfo(@PathVariable("ids")String ids) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean b = functionService.delFInfo(ids);
            map.put("statusCode",200);
            map.put("message","success");
        }catch (Exception e){
            map.put("statusCode",500);
            map.put("message",e.getMessage());
        }
        return map;
    }
    /*条件查询功能信息*/
    @RequestMapping(value = "/selectFInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<Function> selectNInfo(int fhid, String infoKey, int orderWord) {
        return functionService.selectFInfo(fhid,infoKey,orderWord);
    }
    /*查询功能（不带有任务分配且与对应模块匹配）*/
    @RequestMapping(value = "/selectFInfoNoTask/{id}",method = RequestMethod.GET)
    @ResponseBody
    public List<Function> selectFInfoNoTask(@PathVariable("id")Integer id) {

        return functionService.selectFInfoNoTask(id);
    }
}
