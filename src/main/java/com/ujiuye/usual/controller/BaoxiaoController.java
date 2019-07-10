package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.service.NeedAnalysisServiceInter;
import com.ujiuye.tool.parseParamMap.ParseParamMapToMybatisMap;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.service.BaoxiaoServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: 报销管理
 */
@Controller
@RequestMapping("/bao")
public class BaoxiaoController {
    @Autowired
    private BaoxiaoServiceInter baoxiaoServiceInter;
    @Autowired
    private ParseParamMapToMybatisMap parseParamMap;

    /*添加报销信息*/
    @RequestMapping(value = "/saveBInfo",method = RequestMethod.POST)
    public String saveNInfo(Baoxiao baoxiao, HttpSession session,Map<String,Object> map) {
        Employee activeUser =(Employee) session.getAttribute("activeUser");
        baoxiao.setEmpFk(activeUser.getEid());
        String bxid= UUID.randomUUID().toString().replaceAll("-","");
        baoxiao.setBxid(bxid);
        boolean info = baoxiaoServiceInter.saveBInfo(baoxiao);
        if (info) {
            return "redirect:/bao/searchBInfo";
        }else {
            map.put("msg","添加失败");
            return "redirect:/mybaoxiao-add.jsp";
        }
    }

    /*查询报销信息*/
    /*@RequestMapping(value = "/listBInfo",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView listBInfo(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue="1")String pageNum) {
        PageInfo<Baoxiao> list=baoxiaoServiceInter.listBInfo(pageNum);
        ModelAndView mv = new ModelAndView("mybaoxiao-base");
        String requestUrl= request.getRequestURL().toString();
        mv.addObject("url",requestUrl);
        mv.addObject("page",list);
        return mv;
    }*/

    /*条件查询报销信息*/
    @RequestMapping(value = "/searchBInfo",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView searchBInfo(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue="1")String pageNum) {
        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "search_");
        PageInfo<Baoxiao> list=baoxiaoServiceInter.searchBInfo(pageNum,paramMap);
        ModelAndView mv = new ModelAndView("mybaoxiao-base");
        String queryString = parseParamMap.parseParamMapToString(paramMap);
        String requestUrl= request.getRequestURL().toString();
        mv.addObject("queryString",queryString);
        mv.addObject("url",requestUrl);
        mv.addObject("page",list);
        return mv;
    }

    /*修改单个报销详情*/
    @RequestMapping(value = "/findBById/{bxid}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findBById(@PathVariable("bxid")String bxid) {
        ModelAndView mv = new ModelAndView("mybaoxiao-edit");
        Baoxiao baoDetails= baoxiaoServiceInter.findBInfoById(bxid);
        mv.addObject("nnbx",baoDetails);
        return mv;
    }
    @RequestMapping(value = "/updateBInfo",method = RequestMethod.POST)
    public String updateBInfo(Baoxiao baoxiao) {
        boolean updateBInfo = baoxiaoServiceInter.updateBInfo(baoxiao);
        if (updateBInfo) {
            return "redirect:/bao/searchBInfo";
        }else {
            return "redirect:/mybaoxiao-edit.jsp";
        }

    }

}


