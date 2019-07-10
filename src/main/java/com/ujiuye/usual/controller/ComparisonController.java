package com.ujiuye.usual.controller;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.service.NeedAnalysisServiceInter;
import com.ujiuye.usual.bean.Comparison;
import com.ujiuye.usual.service.ComparisonServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:53
 * @project: ppms
 * @Description: 对标管理
 */
@Controller
@RequestMapping("/com")
public class ComparisonController {

    @Autowired
    private ComparisonServiceInter comparisonServiceInter;

    /*添加对标信息*/
    @RequestMapping(value = "/saveCInfo",method = RequestMethod.POST)
    public String saveCInfo(Comparison comparison, HttpSession session) {
        Employee user =(Employee) session.getAttribute("activeUser");
        Integer eid = user.getEid();
        comparison.setEmpFK(eid);
        boolean info = comparisonServiceInter.saveCInfo(comparison);
        if (info) {
            return "redirect:/indexvalue-base.jsp";
        }else {
            return "redirect:/indexvalue-add.jsp";
       }
    }

    /*查询对标信息*/
    @RequestMapping(value = "/listCInfo",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView listCInfo() {
        ModelAndView mv = new ModelAndView("indexvalue-base");
        List<Comparison> list = comparisonServiceInter.listCInfo();
        mv.addObject("comp",list);
        return mv;
    }
    /*查询数据*/
    @RequestMapping(value = "/compare",method = RequestMethod.GET)
    @ResponseBody
    public List<Comparison> compare() {
        List<Comparison> list = comparisonServiceInter.compare();
        return list;
    }

}
