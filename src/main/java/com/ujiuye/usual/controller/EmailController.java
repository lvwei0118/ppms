package com.ujiuye.usual.controller;
import com.ujiuye.tool.EmailUtils.EmailUtils;
import com.ujiuye.usual.bean.Email;
import com.ujiuye.usual.service.EmailServiceInter;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:59
 * @project: ppms
 * @Description: 邮件管理
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailServiceInter emailServiceInter;
    @Autowired
    private JavaMailSender javaMailSender;

    /*发送邮件*/
    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public String sendMail(Email email,String emp){
        String[] strings = emp.split(",");
        email.setEmpFk(Integer.parseInt(strings[0]));
        email.setEname(strings[1]);
        boolean result =emailServiceInter.send(email);
        /*调用发送邮件工具类*/
        EmailUtils emailUtils = new EmailUtils();
        emailUtils.sendMail(email.getContent(), email.getTitle(), email.getPath(),
                javaMailSender ,false);
        if (result) {
            return "redirect:/email/listEInfo";
        }else {
            return "redirect:/email-seed.jsp";
        }

    }
    /*定时发送邮件*/
    @RequestMapping(value = "/sendByTime",method = RequestMethod.POST)
    public String sendByTime(Email email,String emp)throws Exception{
        // 创建任务管理器Scheduler对象
        Scheduler sched= StdSchedulerFactory.getDefaultScheduler();
        //创建JobDetail对象，指定对象的任务名称、组名
        JobDetail job = JobBuilder.newJob(EmailUtils.class).withIdentity("job1", "group1").build();
        //创建CronTrigger对象，指定对象名称、组名  设置任务重复执行间隔时间、启动时间
        //0/10 * * * * ? 每相隔10秒执行一次
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                .build();
        //把指定任务和CronTrigger添加到任务管理器
        Date ft = sched.scheduleJob(job, trigger);
        System.out.println(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
                + trigger.getCronExpression());
        System.out.println("启动定时任务");
        //启动定时任务管理器
        sched.start();
        //主线程睡眠1分钟
        try {
            // wait five minutes to show jobs
            Thread.sleep(60L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }
        System.out.println("关闭定时任务");
        //关闭定时任务管理器
        sched.shutdown(true);
        String[] strings = emp.split(",");
        email.setEmpFk(Integer.parseInt(strings[0]));
        email.setEname(strings[1]);
        boolean result =emailServiceInter.send(email);
        /*调用发送邮件工具类*/
        EmailUtils emailUtils = new EmailUtils();
        /*emailUtils.sendMail(email.getContent(), email.getTitle(), email.getPath(),
                javaMailSender ,false);*/
        if (result) {
            return "redirect:/email/listEInfo";
        }else {
            return "redirect:/email-seed.jsp";
        }

    }
    /*展示已发送邮件*/
    @RequestMapping(value = "/listEInfo",method = RequestMethod.GET)
    @ResponseBody
    public  ModelAndView listEInfo() {
        ModelAndView mv = new ModelAndView("message");
        List<Email> listEInfo = emailServiceInter.listEInfo();
        mv.addObject("email",listEInfo);
        return mv;
    }
}






