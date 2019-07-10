package com.ujiuye.usual.service;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.AnalysisExample;
import com.ujiuye.pro.mapper.AnalysisMapper;
import com.ujiuye.usual.bean.Email;
import com.ujiuye.usual.bean.EmailExample;
import com.ujiuye.usual.mapper.EmailMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:57
 * @project: ppms
 * @Description: 邮件管理
 */

@Service
public class EmailService implements EmailServiceInter {

    @Resource
    private EmailMapper emailMapper;

    /*发送邮件*/
    @Override
    public boolean send(Email email) {
        int i = emailMapper.insert(email);
        return i>0;
    }

    /*展示已发送邮件*/
    @Override
    public List<Email> listEInfo() {
        EmailExample example = new EmailExample();
        List<Email> emails = emailMapper.selectByExample(example);
        return emails;
    }
}
