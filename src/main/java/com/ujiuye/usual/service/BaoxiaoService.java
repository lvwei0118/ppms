package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentExample;
import com.ujiuye.pro.bean.AttachmentJion;
import com.ujiuye.pro.mapper.Attachment2Mapper;
import com.ujiuye.pro.mapper.AttachmentMapper;
import com.ujiuye.tool.PageUtils.PageUtils;
import com.ujiuye.tool.parseParamMap.ParseParamMapToMybatisMap;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.mapper.BaoxiaoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/5 15:57
 * @project: ppms
 * @Description: 报销管理
 */
@Service
public class BaoxiaoService implements BaoxiaoServiceInter {
    @Resource
    private BaoxiaoMapper baoxiaoMapper;
    @Resource
    private ParseParamMapToMybatisMap toMybatisMap;

    /*添加报销*/
    @Override
    public boolean saveBInfo(Baoxiao baoxiao) {
        int i = baoxiaoMapper.insert(baoxiao);
        return i>0;
    }
    /*分页查询报销信息*/
    @Override
    public PageInfo<Baoxiao> listBInfo(String pageNum) {
        Integer pageNo=1;
        try{
            pageNo=Integer.parseInt(pageNum);
        }catch (Exception e){

        }
        PageHelper.startPage(pageNo, PageUtils.PAGE_SIZE);
        List<Baoxiao> list = baoxiaoMapper.selectByExample(null);
        PageInfo<Baoxiao> pageBList = new PageInfo<>(list, 5);
        return pageBList;
    }
    /*分页条件查询*/
    @Override
    public PageInfo<Baoxiao> searchBInfo(String pageNum, Map<String, Object> paramMap) {
        Map<String, Object> resultMap = toMybatisMap.parseParamMapToMybatisMap(paramMap);
        Integer pageNo=1;
        try{
            pageNo=Integer.parseInt(pageNum);
        }catch (Exception e){

        }
        PageHelper.startPage(pageNo, PageUtils.PAGE_SIZE);
        List<Baoxiao> list = baoxiaoMapper.searchBInfo(resultMap);
        PageInfo<Baoxiao> pageBList = new PageInfo<>(list, 5);
        return pageBList;
    }

    @Override
    public Baoxiao findBInfoById(String bxid) {
        Baoxiao baoxiao = baoxiaoMapper.selectByPrimaryKey(bxid);
        return baoxiao;
    }

    @Override
    public boolean updateBInfo(Baoxiao baoxiao) {
        int i = baoxiaoMapper.updateByPrimaryKey(baoxiao);
        return i>0;
    }


}
