package com.ujiuye.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentJion;
import com.ujiuye.usual.bean.Baoxiao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:56
 * @project: ppms
 * @Description: 报销管理
 */
public interface BaoxiaoServiceInter {
   /*添加报销信息*/
    boolean saveBInfo(Baoxiao baoxiao);


    PageInfo<Baoxiao> listBInfo(String pageNum);

    PageInfo<Baoxiao> searchBInfo(String pageNum, Map<String, Object> paramMap);


    Baoxiao findBInfoById(String bxid);

 boolean updateBInfo(Baoxiao baoxiao);
}
