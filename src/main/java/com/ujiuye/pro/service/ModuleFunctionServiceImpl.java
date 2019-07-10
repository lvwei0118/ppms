package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.bean.FunctionExample;
import com.ujiuye.pro.bean.FunctionJion;
import com.ujiuye.pro.mapper.Function2Mapper;
import com.ujiuye.pro.mapper.FunctionMapper;
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
public class ModuleFunctionServiceImpl implements ModuleFunctionServiceInter {

    @Resource
    private FunctionMapper functionMapper;
    @Resource
    private Function2Mapper function2Mapper;

    /*添加功能*/
    @Override
    @Transactional
    public boolean saveFInfo(Function function) {
        int i = functionMapper.insert(function);
        return i > 0;
    }


    /*查询功能信息*/
    @Override
    @Transactional
    public List<FunctionJion> listFInfo() {
        List<FunctionJion> list = function2Mapper.listFInfo();
        return list;
    }

    /*查询单个功能详情*/
    @Override
    @Transactional
    public FunctionJion findFInfoById(Integer id) {
        FunctionJion function = function2Mapper.findFInfoById(id);
        return function;
    }

    /*修改单个功能详情*/
    @Override
    @Transactional
    public boolean updateFInfo(Function function) {
        int i = functionMapper.updateByPrimaryKeySelective(function);
        return i > 0;
    }

    /*删除功能信息*/
    @Override
    @Transactional
    public boolean delFInfo(String ids) {
        String[] idsArr = ids.split(",");
        List<Integer> list = new ArrayList<Integer>(ids.length());
        for (int i = 0; i < idsArr.length; i++
        ) {
            list.add(Integer.parseInt(idsArr[i]));
        }
        FunctionExample example = new FunctionExample();
        FunctionExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(list);
        int i = functionMapper.deleteByExample(example);
        return i > 0;
    }

    /*条件查询功能*/
    @Override
    @Transactional
    public List<Function> selectFInfo(int fhid, String infoKey, int orderWord) {
        FunctionExample example = new FunctionExample();
        FunctionExample.Criteria criteria1 = example.createCriteria();
        FunctionExample.Criteria criteria2 = example.createCriteria();
        FunctionExample.Criteria criteria3 = example.createCriteria();
        FunctionExample.Criteria criteria4 = example.createCriteria();
        if (fhid == 0) {
            criteria1.andPronameLike("%" + infoKey + "%");
            criteria2.andAnalysisnameLike("%" + infoKey + "%");
            criteria3.andFunctionnameLike("%" + infoKey + "%");
            criteria4.andModeleFkEqualTo(1);
            example.or(criteria2);
        } else if (fhid == 1) {
            criteria1.andPronameEqualTo("infoKey");
        } else if(fhid == 2){
            criteria2.andAnalysisnameLike("%" + infoKey + "%");
        }else if(fhid == 3){
            criteria3.andFunctionnameLike("%" + infoKey + "%");
        }else {
            criteria4.andModeleFkEqualTo(1);
        }

        if (orderWord == 1) {
            example.setOrderByClause("id");
        }
        return functionMapper.selectByExample(example);

    }

    @Override
    public List<Function> selectFInfoNoTask(Integer id) {
        List<Function> list = functionMapper.selectFInfoNoTask(id);
        return list;
    }


}
