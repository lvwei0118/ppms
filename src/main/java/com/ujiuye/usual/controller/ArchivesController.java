package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.usual.bean.Archives;
import com.ujiuye.usual.service.ArchivesServiceInter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: 档案管理
 */
@Controller
@RequestMapping("/arch")
public class ArchivesController {
    @Autowired
    private ArchivesServiceInter archivesServiceInter;

    /*添加档案信息（Excel上传）*/
    @RequestMapping(value = "/uploadAInfo",method = RequestMethod.POST)
    public String uploadAInfo(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file == null) {
            return "获取文件失败";
        }
         /*文件名*/
        String fileName = file.getOriginalFilename();
        /*路径*/
        String path = request.getSession().getServletContext().getRealPath("upload");
        /*长度*/
        long size = file.getSize();
        if (fileName == null || ("").equals(fileName) && size == 0) {
            return "空文件或者文件名字为空";
        }
        if (fileName.indexOf(".xls") > 0) {
            // 创建存放上传文件的文件夹
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
           /*保存*/
            File targetFile = new File(path, fileName);
            try {
                file.transferTo(targetFile);
                InputStream in = new FileInputStream(targetFile);
                BufferedInputStream sb=new BufferedInputStream(in);
                /*创建对Excel工作薄*/
                HSSFWorkbook workbook = new HSSFWorkbook(sb);
                HSSFSheet sheet = workbook.getSheetAt(0);
                // 调用Service，将数据插入Excel
                int i = archivesServiceInter.uploadAInfo(sheet);
                if (i > 0) {
                    return "redirect:/archives.jsp";
                } else {
                    return "redirect:/archives-add.jsp";
                }
            } catch (IOException e) {
                return "存在异常"+e.getMessage();
            }
        }
        return "空文件或者文件不是execl";

    }

    /*查询档案信息*/
    @RequestMapping(value = "/listAInfo",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView listAInfo(HttpServletRequest request,@RequestParam(value = "pageNum",defaultValue="1")String pageNum) {
        ModelAndView mv = new ModelAndView("archives");
        PageInfo<Archives> listAInfo = archivesServiceInter.listAInfo(pageNum);
        StringBuffer requestURL = request.getRequestURL();
        mv.addObject("url",requestURL);
        mv.addObject("page",listAInfo);
        return mv;
    }


    /*查询个人档案详情*/
    @RequestMapping(value = "/findAInfoById",method = RequestMethod.GET)
    public String findAInfoById(HttpSession session,Map<String,Object> map) {
        Employee user = (Employee)session.getAttribute("activeUser");
        Integer eid = user.getEid();
        Archives archives= archivesServiceInter.findAInfoById(eid);
        map.put("aList",archives);
        return "myarchives";
    }
    /*查看个人信息*/
    @RequestMapping(value = "/findUserInfo",method = RequestMethod.GET)
    public String findUserInfo(HttpSession session,Map<String,Object> map) {
        Employee user = (Employee)session.getAttribute("activeUser");
        Integer eid = user.getEid();
        Archives archives= archivesServiceInter.findAInfoById(eid);
        map.put("aList",archives);
        return "info";
    }
    /*修改个人档案详情*/
    @RequestMapping(value = "/updateAInfo",method = RequestMethod.POST)
    public String updateAInfo(Archives archives) {

        if (1>0) {
            return "redirect:/arch/listAInfo";
        }else {
            return "redirect:/myarchives.jsp";

        }
    }

}
