package com.ujiuye.usual.service;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.usual.bean.Email;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:57
 * @project: ppms
 * @Description: 邮件管理
 */
public interface EmailServiceInter {
    boolean send(Email email);

    List<Email> listEInfo();

}
