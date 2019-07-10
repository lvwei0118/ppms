package com.ujiuye.usual.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.usual.bean.ForumPosts;
import com.ujiuye.usual.bean.Msg;
import com.ujiuye.usual.service.ForumPostsServiceinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/15 11:07
 * @project: ppms
 * @Description: 帖子管理
 */
@Controller
@RequestMapping("/for")
public class ForumPostsController {
    @Autowired
    private ForumPostsServiceinter serviceinter;
    /*发帖*/
    @RequestMapping(value = "/sendFor",method = RequestMethod.POST)
    public String sendFor(ForumPosts forumPosts) {
        Date date = new Date();
        forumPosts.setPostDate(date);
        boolean result =serviceinter.sendFor(forumPosts);
        if (result) {
            return "redirect:/forum.jsp";
        }else {
            return "redirect:/forum-add.jsp";
        }
    }

    /*看帖*/
    @RequestMapping(value = "/listFInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<ForumPosts> listFInfo() {
        List<ForumPosts> listFInfo =serviceinter .listFInfo();
        return listFInfo;
    }
    /*查看帖子详情*/
    @RequestMapping(value = "/selectFInfo/{tid}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView selectFInfo(@PathVariable("tid")Integer tid) {
        ModelAndView mv = new ModelAndView("forumlook");
        ForumPosts fp =serviceinter .selectFInfo(tid);
        mv.addObject("forum",fp);
        return mv;
    }
    /*删帖*/
    public String deleFInfo(Integer tid) {
        serviceinter.deleFInfo(tid);
        return "redirect:/for/listFInfo";
    }
}
