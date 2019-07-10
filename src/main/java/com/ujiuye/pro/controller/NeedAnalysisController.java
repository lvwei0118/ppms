package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.service.NeedAnalysisServiceInter;
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
 * @Date: 2019/4/5 15:53
 * @project: ppms
 * @Description: 项目需求分析管理
 */
@Controller
@RequestMapping("/anal")
public class NeedAnalysisController {

    @Autowired
    private NeedAnalysisServiceInter analysisService;


    /*添加需求信息*/
    @RequestMapping(value = "/saveNInfo",method = RequestMethod.POST)
    public String saveNInfo(Analysis analysis,String pronameid) {
        String[] arr = pronameid.split(",");
        analysis.setProname(arr[0]);
        analysis.setId(Integer.parseInt(arr[1]));
        boolean info = analysisService.saveNInfo(analysis);
        if (info) {
            return "redirect:/project-need.jsp";
        }else {
            return "redirect:/project-need-add.jsp";
        }
    }

    /*查询需求信息*/
    @RequestMapping(value = "/listNInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<Analysis> listNInfo() {
        List<Analysis> list = analysisService.listNInfo();
        return list;
    }
    /*根据项目查询对应需求*/

    /*查询单个需求详情*/
    @RequestMapping(value = "/findNInfoById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findNInfoById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("/project-need-look");
        Analysis nInfoById = analysisService.findNInfoById(id);
        mv.addObject("analDetails",nInfoById);
        return mv;
    }
    /*修改单个需求详情*/
    @RequestMapping(value = "/findNById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findNById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("/project-need-edit");
        Analysis nInfoById = analysisService.findNInfoById(id);
        mv.addObject("analDetails",nInfoById);
        return mv;
    }
    @RequestMapping(value = "/updateNInfo",method = RequestMethod.POST)
    public String updateNInfo(Analysis analysis) {
        boolean updatePInfo = analysisService.updateNInfo(analysis);
        if (updatePInfo) {
            return "redirect:/project-need.jsp";
        }else {
            return "redirect:/project-need-edit.jsp";
        }
    }
    /*删除需求*/
    @RequestMapping(value = "/delNInfo/{ids}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delNInfo(@PathVariable("ids")String ids) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean b = analysisService.delNInfo(ids);
            map.put("statusCode",200);
            map.put("message","success");
        }catch (Exception e){
            map.put("statusCode",500);
            map.put("message",e.getMessage());
        }
        return map;
    }
    /*条件查询需求信息*/
    @RequestMapping(value = "/selectNInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<Analysis> selectNInfo(int nhid, String infoKey, int orderWord) {
        return analysisService.selectNInfo(nhid,infoKey,orderWord);

    }
}
