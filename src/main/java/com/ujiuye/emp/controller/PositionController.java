package com.ujiuye.emp.controller;



import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.bean.Position;
import com.ujiuye.emp.service.PositionServiceInter;
import com.ujiuye.usual.bean.Msg;
import com.ujiuye.usual.service.MsgServiceInter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: 职位管理
 */

@Controller
@RequestMapping("/pos")
public class PositionController {
    @Resource
    private PositionServiceInter serviceInter;


    /*查询职位*/
    @RequestMapping(value = "/listInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<Position>  listInfo() {
        List<Position> listInfo = serviceInter.listInfo();
        return listInfo;
    }



}
