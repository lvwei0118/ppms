package com.ujiuye.usual.service;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.bean.FunctionJion;
import com.ujiuye.usual.bean.Comparison;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 16:00
 * @project: ppms
 * @Description: 对标信息
 */
public interface ComparisonServiceInter {
    List<Comparison> listCInfo();

    boolean saveCInfo(Comparison comparison);

    List<Comparison> compare();
}
