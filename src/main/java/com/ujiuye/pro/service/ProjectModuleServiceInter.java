package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.bean.ModuleJoin;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:58
 * @project: ppms
 * @Description: 项目模块管理
 */
public interface ProjectModuleServiceInter {

    boolean saveMInfo(Module module);


    List<ModuleJoin> listMInfo();


    ModuleJoin findMInfoById(Integer id);


    boolean updateMInfo(Module module);


    boolean delMInfo(String ids);


    List<Module> selectMInfo(int mhid, String infoKey, int orderWord);

    List<ModuleJoin> listMInfoByaf(Integer aid);
}
