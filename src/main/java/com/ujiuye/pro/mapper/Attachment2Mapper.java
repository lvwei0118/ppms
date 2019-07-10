package com.ujiuye.pro.mapper;

import com.ujiuye.pro.bean.AttachmentJion;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/8 21:21
 * @project: ppms
 * @Description: 联表查询mapper
 */
public interface Attachment2Mapper {

    List<AttachmentJion> listAInfo();

    AttachmentJion findAInfoById(Integer id);

}
