package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.AnalysisExample;
import com.ujiuye.pro.mapper.AnalysisMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:57
 * @project: ppms
 * @Description: 项目需求分析管理
 */

@Service
public class NeedAnalysisServiceImpl implements NeedAnalysisServiceInter {
    @Resource
    private AnalysisMapper analysisMapper;

    @Override
    @Transactional
    /*添加需求*/
    public boolean saveNInfo(Analysis analysis) {
        int i = analysisMapper.insertSelective(analysis);
        return i>0;
    }


    /*查询需求信息*/
    @Override
    @Transactional
    public List<Analysis> listNInfo() {
        AnalysisExample example = new AnalysisExample();
        List<Analysis> list = analysisMapper.selectByExample(example);
        return list;
    }
    /*查询单个需求详情*/
    @Override
    @Transactional
    public Analysis findNInfoById(Integer id) {
        Analysis analysis = analysisMapper.selectByPrimaryKey(id);
        return analysis;
    }
    /*修改单个需求详情*/
    @Override
    @Transactional
    public boolean updateNInfo(Analysis analysis) {
        int i = analysisMapper.updateByPrimaryKeySelective(analysis);
        return i>0;
    }
    /*删除需求信息*/
    @Override
    @Transactional
    public boolean delNInfo(String ids) {
        String[] idsArr=ids.split(",");
        List<Integer> list = new ArrayList<Integer>(ids.length());
        for (int i=0;i<idsArr.length;i++
        ) {
            list.add(Integer.parseInt(idsArr[i]));
        }
        AnalysisExample example = new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(list);
        int i = analysisMapper.deleteByExample(example);
        return i>0;
    }

    /*条件查询需求*/
    @Override
    @Transactional
    public List<Analysis> selectNInfo(int nhid,String infoKey,int orderWord) {
        AnalysisExample example = new AnalysisExample();
        AnalysisExample.Criteria criteria1 = example.createCriteria();
        AnalysisExample.Criteria criteria2 = example.createCriteria();
        if (nhid==0){
            criteria1.andPronameEqualTo("infoKey");
            criteria2.andTitleEqualTo("infoKey");
            example.or(criteria2);
        }else if (nhid==1){
            criteria1.andPronameEqualTo("infoKey");
        }else{
            criteria2.andTitleEqualTo("infoKey");
        }
        if (orderWord==1){
            example.setOrderByClause("pid");
        }
        return analysisMapper.selectByExample(example);
    }


}
