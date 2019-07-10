package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.bean.ModuleJoin;
import com.ujiuye.pro.service.ProjectModuleServiceInter;
import com.ujiuye.tool.parseParamMap.ParseParamMapToMybatisMap;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.service.NoticeServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:54
 * @project: ppms
 * @Description: 通知管理
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeServiceInter noticeServiceInter;
    @Autowired
    private ParseParamMapToMybatisMap parseParamMap;

    /*添加公告*/
    @RequestMapping(value = "/saveNInfo",method = RequestMethod.POST)
    public String saveNInfo(Notice notice) {
        notice.setNdate(new Date());
        boolean info = noticeServiceInter.saveNInfo(notice);
        if (info) {
            return "redirect:/notice/searchNInfo";
        }else {
            return "redirect:/notice-add.jsp";
        }
    }
    /*显示公告信息*/
    @RequestMapping(value = "/searchNInfo",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView searchNInfo(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue="1")String pageNum) {
        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "search_");
        PageInfo<Notice> list=noticeServiceInter.searchNInfo(pageNum,paramMap);
        ModelAndView mv = new ModelAndView("notice");
        String queryString = parseParamMap.parseParamMapToString(paramMap);
        String requestUrl= request.getRequestURL().toString();
        mv.addObject("queryString",queryString);
        mv.addObject("url",requestUrl);
        mv.addObject("page",list);
        return mv;
    }
    /*主页显示三条信息*/
    @RequestMapping(value = "/listNInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<Notice> listNInfo() {
        List<Notice> list = noticeServiceInter.listNInfo();
        return list;
    }
    /*显示单个通知内容*/
    @RequestMapping(value = "/showOneInfo",method = RequestMethod.GET)
    @ResponseBody
    public Notice showOneInfo(@RequestParam("nid")Integer nid) {
        Notice notice = noticeServiceInter.showOneInfo(nid);
        return notice;
    }
}




