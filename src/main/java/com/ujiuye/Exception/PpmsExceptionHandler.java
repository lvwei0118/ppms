package com.ujiuye.Exception;

import com.ujiuye.tool.EmailUtils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * @Auther: lvwei
 * @Date: 2019/4/17 20:03
 * @project: ppms
 * @Description: 全局异常处理
 */
@ControllerAdvice
public class PpmsExceptionHandler {

    @Autowired
    private JavaMailSender javaMailSender;

    @ExceptionHandler(value ={Exception.class} )
    public void handleSQLException(Exception ex){
        ExceptionBean exceptionBean = new ExceptionBean();
        String e = String.valueOf(ex);
        exceptionBean.setCause(e);
        Date date = new Date();
        exceptionBean.setTime(date);
        /*将异常信息发送*/
        EmailUtils emailUtils = new EmailUtils();
        emailUtils.sendMail(e,"SQL异常","jiayoulvwei@163.com",javaMailSender ,false);
    }
}
