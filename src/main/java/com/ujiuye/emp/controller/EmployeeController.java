package com.ujiuye.emp.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ujiuye.auth.bean.Sources;
import com.ujiuye.auth.service.EmpRoleServiceInter;
import com.ujiuye.auth.service.SourcesServiceInter;
import com.ujiuye.cus.bean.Customer;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.bean.EmployeeExample;
import com.ujiuye.emp.bean.EmployeeJion;
import com.ujiuye.emp.service.EmployeeService;
import com.ujiuye.emp.service.EmployeeServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: 员工模块
 */

@Controller
@RequestMapping(value = "/emp")
public class EmployeeController {
    @Autowired
    private EmployeeServiceInter employeeServiceInter;
    @Autowired
    private EmpRoleServiceInter empRoleServiceInter;
    @Autowired
    private SourcesServiceInter sourcesServiceInter;
    @Autowired
    private JedisPool jedisPool;

    /*查询所有员工*/
    @RequestMapping(value = "/listEInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<EmployeeJion> listEInfo() {

        return employeeServiceInter.listEInfo();
    }

    /*查询员工位项目经理p_fk=4*/
    @RequestMapping(value = "/getEmpWithMan",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getEmpWithManList() {

        return employeeServiceInter.getEmpWithManList();
    }
    /*查询单个员工信息*/
    @RequestMapping(value = "/getEmByEid",method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmpByEid(Integer eid) {

        return employeeServiceInter.getEmpByEid(eid);
    }
    /*员工登录*/
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(Employee employee, String userCode, HttpSession session, RedirectAttributes ra) {
        String code =(String)session.getAttribute("code");
        session.removeAttribute("code");
        if (code.equalsIgnoreCase(userCode)){
            Employee emp=employeeServiceInter.login(employee);
            if (emp!=null){
                //返回员工的eid查出对应的角色-权限
                Integer eid = emp.getEid();
                //使用redis缓存获取jedis对象
                Jedis jedis = jedisPool.getResource();
                //根据eid的键从redis缓存中取值
                String s = jedis.get(eid + "");
                //将字符串类型转换成list集合
                List<Sources> sourcesList = JSON.parseArray(s, Sources.class);
                //如果缓存中没有
                if(sourcesList==null){
                    //从数据库中获取并放入缓存
                    sourcesList= sourcesServiceInter.getSourcesByEid(eid);
                    jedis.set(eid + "",JSON.toJSONString(sourcesList));
                }
                //将权限放入session域中
                session.setAttribute("sourcesList",sourcesList);
                //将登录用户放入域中
                session.setAttribute("activeUser",emp);
                return "redirect:/index.jsp";
            }else{
                ra.addFlashAttribute("errormsg","密码用户名有误");
                return "redirect:/login.jsp";
            }
        }else{
            ra.addFlashAttribute("errormsg","验证码有误");
            return "redirect:/login.jsp";
        }

    }

    /*员工退出*/
    @RequestMapping(value = "Logout",method = RequestMethod.GET)
    public String Logout(HttpSession session) throws Exception {
       /*将user从session中移除*/
        session.removeAttribute("activeUser");
       /*回到登录页面*/
        return "redirect:/login.jsp";
    }
    /*查询可以发邮件的员工*/
    @RequestMapping(value = "/getEmpexcludeSelf",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getEmpexcludeSelf(HttpSession session) {
        Employee employee =(Employee) session.getAttribute("activeUser");
        Integer eid = employee.getEid();
        return employeeServiceInter.getEmpexcludeSelf(eid);
    }
    /*修改密码*/
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public String updatePassword(String oldPassWord, String newPassWord, String rnewPassWord, HttpSession session) {
        int i=0;
        Employee employee =(Employee) session.getAttribute("activeUser");
        String password = employee.getPassword();
        if (oldPassWord.equals(password)){
            session.removeAttribute("errormsg1");
            if(newPassWord.equals(rnewPassWord)){
                session.removeAttribute("errormsg2");
                employee.setPassword(newPassWord);
               i= employeeServiceInter.updatePassword(employee);
            }else{
             session.setAttribute("errormsg2","两次输入密码不一致");
               return "redirect:/modpassword.jsp";
            }

        }else{
            session.setAttribute("errormsg1","输入旧密码不正确");
            return "redirect:/modpassword.jsp";
        }
        if (i>0){
            session.removeAttribute("errormsg3");
            return "redirect:/login.jsp";
        }else{
            session.setAttribute("errormsg3","密码修改失败");
            return "redirect:/modpassword.jsp";
        }
    }

    /*添加用户*/
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Employee employee,String roleid) {
       int eid = employeeServiceInter.saveInfo(employee);
        empRoleServiceInter.saverole(eid,roleid);
       return "redirect:/emp/userList";
    }
    /*查看用户*/
    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView userList(HttpServletRequest request,@RequestParam(value = "pageNum",defaultValue = "1")String pageNum) {
        ModelAndView mv = new ModelAndView("user");
        PageInfo<EmployeeJion> pageInfo = employeeServiceInter.userList(pageNum);
        StringBuffer requestURL = request.getRequestURL();
        mv.addObject("url",requestURL);
        mv.addObject("page",pageInfo);
        return mv;
    }
}
