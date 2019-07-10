package com.ujiuye.usual.controller;



import com.ujiuye.emp.bean.Employee;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.Project2;
import com.ujiuye.pro.service.ProjectServiceInter;
import com.ujiuye.usual.bean.Msg;
import com.ujiuye.usual.service.MsgServiceInter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: 信息管理
 */

@Controller
@RequestMapping("/msg")
public class MsgController {
    @Resource
    private MsgServiceInter msgServiceInter;

    /*添加信息*/
    @RequestMapping(value = "/saveMsg",method = RequestMethod.POST)
    public String saveInfo(Msg msg, HttpSession session) {
        msg.setMark(0);
        Employee user =(Employee) session.getAttribute("activeUser");
        msg.setSendp(user.getEid());
        boolean result =msgServiceInter.saveMsg(msg);
        if (result) {
            return "redirect:/msg/listMInfo";
        }else {
            return "redirect:/message-seed.jsp";
        }
    }

    /*展示信息*/
    @RequestMapping(value = "/listMInfo",method = RequestMethod.GET)
    @ResponseBody
    public  ModelAndView listMInfo() {
        ModelAndView mv = new ModelAndView("message-give");
        List<Msg> listMInfo = msgServiceInter.listMInfo();
        mv.addObject("msg",listMInfo);
        return mv;
    }



}
