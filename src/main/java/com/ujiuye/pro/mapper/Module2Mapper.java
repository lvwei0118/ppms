package com.ujiuye.pro.mapper;

import com.ujiuye.pro.bean.ModuleJoin;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/7 19:21
 * @project: ppms
 * @Description: 联表查询（连需求表）
 */
public interface Module2Mapper {

    List<ModuleJoin> selectMList();

    ModuleJoin findMInfoById(Integer id);

    List<ModuleJoin> listMInfoByaf(Integer aid);
}
