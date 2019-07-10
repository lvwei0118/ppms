package com.ujiuye.emp.service;


import com.ujiuye.emp.bean.Position;
import com.ujiuye.emp.mapper.PositionMapper;
import com.ujiuye.usual.bean.Msg;
import com.ujiuye.usual.mapper.MsgMapper;
import com.ujiuye.usual.service.MsgServiceInter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:58
 * @project: ppms
 * @Description: 职位管理
 */
@Service
public class PositionService implements PositionServiceInter {
    @Resource
    private PositionMapper positionMapper;
    /*查询职位*/
    @Override
    public List<Position> listInfo() {
        return positionMapper.selectByExample(null);
    }





}