package com.ujiuye.auth.service;

import com.ujiuye.auth.bean.Sources;
import com.ujiuye.usual.bean.ForumPosts;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/16 13:02
 * @project: ppms
 * @Description: 权限管理
 */
public interface SourcesServiceInter {
    //获取pid(父元素)为0的元素列表
    List<Sources> getSourcesByPid(int i);

    Sources getOneById(Integer id);

    boolean updateInfo(Sources sources);

    boolean addNode(Sources sources);

    boolean deleteNode(Integer id);

    List<Sources> getSourcesByEid(Integer eid);
}
