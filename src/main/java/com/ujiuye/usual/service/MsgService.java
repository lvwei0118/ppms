package com.ujiuye.usual.service;


import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.bean.ModuleExample;
import com.ujiuye.pro.bean.ModuleJoin;
import com.ujiuye.pro.mapper.Module2Mapper;
import com.ujiuye.pro.mapper.ModuleMapper;
import com.ujiuye.usual.bean.Msg;
import com.ujiuye.usual.bean.MsgExample;
import com.ujiuye.usual.mapper.MsgMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:58
 * @project: ppms
 * @Description: 消息管理
 */
@Service
public class MsgService implements MsgServiceInter {
    @Resource
    private MsgMapper msgMapper;

    /*添加信息*/
    @Override
    public boolean saveMsg(Msg msg) {
        int i = msgMapper.insert(msg);
        return i>0;
    }

    /*展示信息*/
    @Override
    public List<Msg> listMInfo() {
        return msgMapper.listMInfo();
    }

}