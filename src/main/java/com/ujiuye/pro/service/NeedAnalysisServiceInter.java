package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Analysis;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:57
 * @project: ppms
 * @Description: 需求
 */
public interface NeedAnalysisServiceInter {
    @Transactional
    boolean saveNInfo(Analysis analysis);

    /*查询需求信息*/
    @Transactional
    List<Analysis> listNInfo();

    /*查询单个需求详情*/
    @Transactional
    Analysis findNInfoById(Integer id);

    /*修改单个需求详情*/
    @Transactional
    boolean updateNInfo(Analysis analysis);

    /*删除需求信息*/
    @Transactional
    boolean delNInfo(String ids);

    /*条件查询需求*/
    @Transactional
    List<Analysis> selectNInfo(int nhid, String infoKey, int orderWord);
}
