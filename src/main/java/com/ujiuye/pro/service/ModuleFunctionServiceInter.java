package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.bean.FunctionJion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 16:00
 * @project: ppms
 * @Description: 功能
 */
public interface ModuleFunctionServiceInter {
    /*添加功能*/
    @Transactional
    boolean saveFInfo(Function function);

    /*查询功能信息*/
    @Transactional
    List<FunctionJion> listFInfo();

    /*查询单个功能详情*/
    @Transactional
    FunctionJion findFInfoById(Integer id);

    /*修改单个功能详情*/
    @Transactional
    boolean updateFInfo(Function function);

    /*删除功能信息*/
    @Transactional
    boolean delFInfo(String ids);

    /*条件查询功能*/
    @Transactional
    List<Function> selectFInfo(int fhid, String infoKey, int orderWord);


    List<Function> selectFInfoNoTask(Integer id);
}
