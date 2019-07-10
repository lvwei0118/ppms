package com.ujiuye.pro.mapper;

import com.ujiuye.pro.bean.FunctionJion;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/7 22:07
 * @project: ppms
 * @Description: TODO
 */
public interface Function2Mapper {
    List<FunctionJion> listFInfo();

    FunctionJion findFInfoById(Integer id);
}
