package com.ujiuye.auth.service;

import com.ujiuye.auth.bean.Sources;
import com.ujiuye.auth.bean.SourcesExample;
import com.ujiuye.auth.mapper.SourcesMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/16 13:03
 * @project: ppms
 * @Description: 权限管理
 */
@Service
public class SourcesService implements SourcesServiceInter {
    @Resource
    private SourcesMapper sourcesMapper;

    /*显示权限树*/
    @Override
    public List<Sources> getSourcesByPid(int i) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        //获取pid(父元素)为0的元素列表
        criteria.andPidEqualTo(i);
        List<Sources> psources = sourcesMapper.selectByExample(example);
        return psources;
    }
    /*更新节点*/
    @Override
    public Sources getOneById(Integer id) {
        return sourcesMapper.selectByPrimaryKey(id) ;
    }

    @Override
    public boolean updateInfo(Sources sources) {
        int i = sourcesMapper.updateByPrimaryKey(sources);
        return i>0;
    }
    /*添加节点*/
    @Override
    public boolean addNode(Sources sources) {
        int i = sourcesMapper.insert(sources);
        return i>0;
    }
    /*删除节点*/
    @Override
    public boolean deleteNode(Integer id) {
        int i = sourcesMapper.deleteByPrimaryKey(id);
        return  i>0;
    }
    /*根据用户查询权限*/
    @Override
    public List<Sources> getSourcesByEid(Integer eid) {
    /*查询出二级权限信息*/
        List<Sources> secondSourcesByEid = sourcesMapper.getSecondSourcesByEid(eid);
        /*遍历二级权限列表*/
        for (Sources ss:secondSourcesByEid) {
            /*查询二级权限的子权限*/
            Integer pid= ss.getId();
            List<Sources> children = sourcesMapper.selectChildren(pid,eid);
            ss.setChildren(children);
        }
        return secondSourcesByEid;
    }
}
