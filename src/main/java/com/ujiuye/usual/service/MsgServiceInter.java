package com.ujiuye.usual.service;

import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.bean.ModuleJoin;
import com.ujiuye.usual.bean.Msg;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:58
 * @project: ppms
 * @Description: 消息管理
 */
public interface MsgServiceInter {

    boolean saveMsg(Msg msg);


    List<Msg> listMInfo();
}
