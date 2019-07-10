package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentJion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:56
 * @project: ppms
 * @Description: 附件管理
 */
public interface FileAttachmentServiceInter {
    @Transactional
    /*添加附件*/ boolean saveAInfo(Attachment attachment);

    /*查询附件信息*/
    @Transactional
    List<AttachmentJion> listAInfo();

    /*查询单个附件详情*/
    @Transactional
    AttachmentJion findAInfoById(Integer id);

    /*修改单个需求详情*/
    @Transactional
    boolean updateAInfo(Attachment attachment);

    /*删除需求信息*/
    @Transactional
    boolean delAInfo(String ids);

    /*条件查询需求*/
    @Transactional
    List<Attachment> selectAInfo(int ahid, String infoKey, int orderWord);
}
