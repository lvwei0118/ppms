package com.ujiuye.pro.controller;
import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentJion;
import com.ujiuye.pro.service.FileAttachmentServiceInter;
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
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:55
 * @project: ppms
 * @Description: 附件管理（文件上传和下载）
 */

@Controller
@RequestMapping("/att")
public class FileAttachmentController {
    @Autowired
    private FileAttachmentServiceInter attachmentService;

    /*上传附件*/
    @RequestMapping(value = "/saveAInfo",method = RequestMethod.POST)
    //获取从页面上传的文件file
    public String uploadFile(@RequestParam("file") MultipartFile file, Attachment attachment, HttpServletRequest request, RedirectAttributes ra) {
        //获取域对象
        ServletContext context = request.getSession().getServletContext();
        //获取上传文件存入的真实路径
        String realpath = context.getRealPath("/upload");
        //将文件放入存入的路径
        File  file1 = new File(realpath);
        //判断该路径是否存在
        if (!file1.exists()) {
            //不存在就创建一个
            file1.mkdirs();	}
        //使用uuid重写文件名
        String  id= UUID.randomUUID().toString().replaceAll("-", "");
        //获取上传文件file的真实文件名
        String fileName=id+"_"+file.getOriginalFilename();
        attachment.setPath(fileName);
        try {
            file.transferTo(new File(realpath+"/"+fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean info = attachmentService.saveAInfo(attachment);
        if (info) {
            return "redirect:/project-file.jsp";
        }else {
            ra.addFlashAttribute("msg","上传失败");
            return "redirect:/project-file-add.jsp";
        }

    }

    /*附件下载*/
    @RequestMapping("/download")
    public ResponseEntity<byte[]> downLoad(HttpServletRequest request) throws IOException {
        //获取作用域
        ServletContext servletContext = request.getSession().getServletContext();
        //设置要下载的文件名
        String fileName="";
        //根据作用域获取要下载文件的真实路径
        String realPath = servletContext.getRealPath("/upload/"+fileName);
        //将路径写入输入流中
        FileInputStream in = new FileInputStream(new File(realPath));
        //定义一个字节数组，将下载的文件转化为字节数组，定义大小为输入流的长度
        byte[] body=new byte[in.available()];
        //将输入流读取到字节数组中
        in.read(body);
        //创建响应头
        MultiValueMap<String,String> headers = new HttpHeaders();
        //编码解码（在前）
        fileName=new String(fileName.getBytes("gbk"),"iso8859-1");
        //设置响应头信息
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        //响应代码，下载成功后响应
        HttpStatus status=HttpStatus.OK;
        //定义ResponseEntity并在其中放入字节数组、头信息、响应代码
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body,headers,status);
        //返回ResponseEntity
        return responseEntity;
    }

    /*查询附件信息*/
    @RequestMapping(value = "/listAInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<AttachmentJion> listAInfo() {
        return attachmentService.listAInfo();
    }
    /*查询单个附件详情*/
    @RequestMapping(value = "/findAInfoById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findAInfoById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("/project-file-look");
        AttachmentJion aInfoById = attachmentService.findAInfoById(id);
        mv.addObject("attachDetails",aInfoById);
        return mv;
    }
    /*修改单个附件详情*/
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
    /*删除附件*/
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
    /*条件查询附件信息*/
    @RequestMapping(value = "/selectAInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<Attachment> selectAInfo(int ahid, String infoKey, int orderWord) {
        return attachmentService.selectAInfo(ahid,infoKey,orderWord);
    }


}

