package com.ujiuye.usual.service;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.bean.FunctionExample;
import com.ujiuye.pro.bean.FunctionJion;
import com.ujiuye.pro.mapper.Function2Mapper;
import com.ujiuye.pro.mapper.FunctionMapper;
import com.ujiuye.usual.bean.Comparison;
import com.ujiuye.usual.mapper.ComparisonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 16:00
 * @project: ppms
 * @Description: 项目功能管理
 */

@Service
public class ComparisonService implements ComparisonServiceInter {
    @Resource
    private ComparisonMapper comparisonMapper;
    @Override
    public List<Comparison> listCInfo() {
        List<Comparison> list=comparisonMapper.listCInfo();
        return list;
    }

    @Override
    public boolean saveCInfo(Comparison comparison) {
        int i = comparisonMapper.insert(comparison);
        return i>0;
    }

    @Override
    public List<Comparison> compare() {
        return comparisonMapper.compare();
    }


}
