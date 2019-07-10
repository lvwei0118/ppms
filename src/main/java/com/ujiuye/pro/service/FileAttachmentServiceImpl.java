package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.*;
import com.ujiuye.pro.mapper.Attachment2Mapper;
import com.ujiuye.pro.mapper.AttachmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:57
 * @project: ppms
 * @Description: 附件管理
 */
@Service
public class FileAttachmentServiceImpl implements FileAttachmentServiceInter {

    @Resource
    private AttachmentMapper attachmentMapper;
    @Resource
    private Attachment2Mapper attachment2Mapper;

    @Override
    @Transactional
    /*添加附件*/
    public boolean saveAInfo(Attachment attachment) {
        int i = attachmentMapper.insertSelective(attachment);
        return i>0;
    }

    /*查询附件信息*/
    @Override
    @Transactional
    public List<AttachmentJion> listAInfo() {
        List<AttachmentJion> list = attachment2Mapper.listAInfo();
        return list;
    }
    /*查询单个附件详情*/
    @Override
    @Transactional
    public AttachmentJion findAInfoById(Integer id) {
        return attachment2Mapper.findAInfoById(id);
    }
    /*修改单个需求详情*/
    @Override
    @Transactional
    public boolean updateAInfo(Attachment attachment) {
        int i = attachmentMapper.updateByPrimaryKeySelective(attachment);
        return i>0;
    }
    /*删除需求信息*/
    @Override
    @Transactional
    public boolean delAInfo(String ids) {
        String[] idsArr=ids.split(",");
        List<Integer> list = new ArrayList<Integer>(ids.length());
        for (int i=0;i<idsArr.length;i++
        ) {
            list.add(Integer.parseInt(idsArr[i]));
        }
        AttachmentExample example = new AttachmentExample();
        AttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(list);
        int i = attachmentMapper.deleteByExample(example);
        return i>0;
    }

    /*条件查询需求*/
    @Override
    @Transactional
    public List<Attachment> selectAInfo(int ahid, String infoKey, int orderWord) {
        AttachmentExample example = new AttachmentExample();
        AttachmentExample.Criteria criteria1 = example.createCriteria();
        AttachmentExample.Criteria criteria2 = example.createCriteria();
        if (ahid==0){
            criteria1.andAttnameLike("%"+infoKey+"%");
            criteria2.andProFkEqualTo(1);
            example.or(criteria2);
        }else if (ahid==1){
            criteria1.andAttnameLike("%"+infoKey+"%");
        }else{
            criteria2.andProFkEqualTo(1);
        }
        if (orderWord==1){
            example.setOrderByClause("id");
        }
        return attachmentMapper.selectByExample(example);
    }

}
