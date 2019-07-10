package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.Project2;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.Project2Mapper;
import com.ujiuye.pro.mapper.ProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: 项目基本信息管理
 */
@Service
public class ProjectService implements ProjectServiceInter {
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private Project2Mapper project2Mapper;
    /*添加项目*/

    @Override
    @Transactional
    public boolean saveInfo(Project project) {
        int i = projectMapper.insertSelective(project);
        return i>0;
    }


    /*查询项目信息*/
    @Override
    @Transactional
    public List<Project2> listPInfo() {
        List<Project2> list = project2Mapper.listPInfo();
        return list;
        /*ProjectExample example = new ProjectExample();
        List<Project> list = projectMapper.selectByExample(example);
        return list;*/
    }
    /*查询单个项目详情*/
    @Override
    @Transactional
    public Project2 findPInfoById(Integer pid) {
        Project2 project2 = project2Mapper.findPInfoById(pid);
        return project2;
    }
    /*修改单个项目详情*/
    @Override
    @Transactional
    public boolean updatePInfo(Project project) {
        int i = projectMapper.updateByPrimaryKeySelective(project);
        return i>0;
    }
    /*删除项目信息*/
    @Override
    @Transactional
    public boolean delPInfo(String ids) {
        String[] idsArr=ids.split(",");
        List<Integer> list = new ArrayList<Integer>(ids.length());
        for (int i=0;i<idsArr.length;i++
        ) {
            list.add(Integer.parseInt(idsArr[i]));
        }
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(list);
        int i = projectMapper.deleteByExample(example);
        return i>0;
    }

    /*条件查询项目*/
    @Override
    @Transactional
    public List<Project2> selectPInfo(int phid, String infoKey, int orderWord) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria1 = example.createCriteria();
        ProjectExample.Criteria criteria2 = example.createCriteria();
        if (phid==0){
            criteria1.andPnameLike("%"+infoKey+"%");

            example.or(criteria2);
        }else if (phid==1){
            criteria1.andPnameLike("%"+infoKey+"%");
        }else{

        }
        if (orderWord==1){
            example.setOrderByClause("pid");
        }
        return project2Mapper.listPInfo();


    }
    /*查询项目（无需求分析）*/
    @Override
    public List<Project> listPInfoWithoutNeed() {
        return  projectMapper.listPInfoWithoutNeed();
    }
}
