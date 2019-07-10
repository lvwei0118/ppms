package com.ujiuye.usual.controller;

import com.sun.javafx.collections.MappingChange;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentJion;
import com.ujiuye.pro.service.FileAttachmentServiceInter;
import com.ujiuye.usual.bean.Task;
import com.ujiuye.usual.service.TaskServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:55
 * @project: ppms
 * @Description: 任务管理
 */

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskServiceInter taskServiceInter;

    /*添加任务*/
    @RequestMapping(value = "/saveTInfo",method = RequestMethod.POST)
    public String saveTInfo(Task task, RedirectAttributes ra, HttpSession session) {
        /*Employee user = (Employee)session.getAttribute("activeUser");*/
        boolean info = taskServiceInter.saveTInfo(task);
        if (info) {
            return "redirect:/task.jsp";
        }else {
            ra.addFlashAttribute("msg","上传失败");
            return "redirect:/task-add.jsp";
        }
    }
    /*显示任务*/
    @RequestMapping(value = "/listTInfo",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView listAInfo(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("task");
        List<Task> list=taskServiceInter.listAInfo();
        mv.addObject("tasks",list);
        return mv;
    }

    /*显示登陆者任务*/
    @RequestMapping(value = "/LoginAInfo",method = RequestMethod.GET)
    public String LoginAInfo(HttpSession session,Map<String,Object> map) {
        Employee user = (Employee)session.getAttribute("activeUser");
        Integer eid = user.getEid();
        List<Task> lists=taskServiceInter.LoginAInfo(eid);
        map.put("tasks",lists);
        return "task-my";
    }
    /*查询附件信息*//*
    @RequestMapping(value = "/listAInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<AttachmentJion> listAInfo() {

        return attachmentService.listAInfo();
    }
    *//*查询单个附件详情*//*
    @RequestMapping(value = "/findAInfoById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findAInfoById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("/project-file-look");
        AttachmentJion aInfoById = attachmentService.findAInfoById(id);
        mv.addObject("attachDetails",aInfoById);
        return mv;
    }
    *//*修改单个附件详情*//*
    @RequestMapping(value = "/findAById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findAById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("/project-file-edit");
        AttachmentJion aInfoById = attachmentService.findAInfoById(id);
        mv.addObject("attachDetails",aInfoById);
        return mv;
    }
    @RequestMapping(value = "/updateAInfo",method = RequestMethod.POST)
    public String updateAInfo(Attachment attachment) {
        boolean updatePInfo = attachmentService.updateAInfo(attachment);
        if (updatePInfo) {
            return "redirect:/project-file.jsp";
        }else {
            return "redirect:/project-file-edit.jsp";
        }
    }
    *//*删除附件*//*
    @RequestMapping(value = "/delAInfo/{ids}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delAInfo(@PathVariable("ids")String ids) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean b = attachmentService.delAInfo(ids);
            map.put("statusCode",200);
            map.put("message","success");
        }catch (Exception e){
            map.put("statusCode",500);
            map.put("message",e.getMessage());
        }
        return map;
    }
    *//*条件查询附件信息*//*
    @RequestMapping(value = "/selectAInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<Attachment> selectAInfo(int ahid, String infoKey, int orderWord) {
        return attachmentService.selectAInfo(ahid,infoKey,orderWord);
    }*/


}

