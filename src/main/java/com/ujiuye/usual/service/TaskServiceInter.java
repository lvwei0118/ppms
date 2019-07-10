package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Task;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/8 23:08
 * @project: ppms
 * @Description: 任务管理
 */
public interface TaskServiceInter {
    boolean saveTInfo(Task task);

    List<Task> listAInfo();

    List<Task> LoginAInfo(Integer eid);
}
