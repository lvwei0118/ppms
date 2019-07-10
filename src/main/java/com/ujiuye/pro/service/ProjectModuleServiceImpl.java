package com.ujiuye.pro.service;


import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.bean.ModuleExample;
import com.ujiuye.pro.bean.ModuleJoin;
import com.ujiuye.pro.mapper.Module2Mapper;
import com.ujiuye.pro.mapper.ModuleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:58
 * @project: ppms
 * @Description: 项目模块管理
 */
@Service
public class ProjectModuleServiceImpl implements ProjectModuleServiceInter {
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private Module2Mapper module2Mapper;

    /*添加模块*/
    @Override
    @Transactional
    public boolean saveMInfo(Module module) {
        int i = moduleMapper.insertSelective(module);
        return i > 0;
    }


    /*查询模块信息（连需求表查询）*/
    @Override
    @Transactional
    public List<ModuleJoin> listMInfo() {
        List<ModuleJoin> list = module2Mapper.selectMList();
        return list;
    }

    /*查询单个模块详情*/
    @Override
    @Transactional
    public ModuleJoin findMInfoById(Integer id) {
        ModuleJoin moduleJoin = module2Mapper.findMInfoById(id);
        return moduleJoin;
    }

    /*修改单个模块详情*/
    @Override
    @Transactional
    public boolean updateMInfo(Module module) {
        int i = moduleMapper.updateByPrimaryKeySelective(module);
        return i > 0;
    }

    /*删除模块信息*/
    @Override
    @Transactional
    public boolean delMInfo(String ids) {
        String[] idsArr = ids.split(",");
        List<Integer> list = new ArrayList<Integer>(ids.length());
        for (int i = 0; i < idsArr.length; i++
        ) {
            list.add(Integer.parseInt(idsArr[i]));
        }
        ModuleExample example = new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(list);
        int i = moduleMapper.deleteByExample(example);
        return i > 0;
    }

    /*条件查询模块*/
    @Override
    @Transactional
    public List<Module> selectMInfo(int mhid, String infoKey, int orderWord) {
        ModuleExample example = new ModuleExample();
        ModuleExample.Criteria criteria1 = example.createCriteria();
        ModuleExample.Criteria criteria2 = example.createCriteria();
        ModuleExample.Criteria criteria3 = example.createCriteria();

        if (mhid == 0) {
            criteria1.andPronameEqualTo("infoKey");
            criteria3.andModnameLike("%" + infoKey + "%");
            example.or(criteria2);
        } else if (mhid == 1) {
            criteria1.andPronameEqualTo("infoKey");
        } else {
            criteria3.andModnameLike("%" + infoKey + "%");
        }
        if (orderWord == 1) {
            example.setOrderByClause("id");
        }
        return moduleMapper.selectByExample(example);

    }
    /*根据analysisFk查询模块*/
    @Override
    public List<ModuleJoin> listMInfoByaf(Integer aid) {
        return module2Mapper.listMInfoByaf(aid);
    }
}