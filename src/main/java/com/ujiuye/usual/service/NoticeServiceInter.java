package com.ujiuye.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.Project2;
import com.ujiuye.usual.bean.Notice;

import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 13:08
 * @project: ppms
 * @Description: 通知管理
 */

public interface NoticeServiceInter {
    /*添加通知*/
    boolean saveNInfo(Notice notice);

    PageInfo<Notice> searchNInfo(String pageNum, Map<String, Object> paramMap);

    List<Notice> listNInfo();

    Notice showOneInfo(Integer nid);
}
