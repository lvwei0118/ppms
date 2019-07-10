package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.Project2;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.Project2Mapper;
import com.ujiuye.pro.mapper.ProjectMapper;
import com.ujiuye.tool.parseParamMap.ParseParamMapToMybatisMap;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.bean.NoticeExample;
import com.ujiuye.usual.mapper.NoticeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: 通知管理
 */
@Service
public class NoticeService implements NoticeServiceInter {

    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private ParseParamMapToMybatisMap parseParamMap;
    /*添加通知*/
    @Override
    public boolean saveNInfo(Notice notice) {
        int i = noticeMapper.insert(notice);
        return i>0;
    }
    /*分页显示通知信息（带条件查询）*/
    @Override
    public PageInfo<Notice> searchNInfo(String pageNum, Map<String, Object> paramMap) {
        Map<String, Object> objectMap = parseParamMap.parseParamMapToMybatisMap(paramMap);
        Integer pageNo=1;
        try{
            pageNo=Integer.parseInt(pageNum);
        }catch (Exception ex){
        }
        PageHelper.startPage(pageNo,5);
        List<Notice> list= noticeMapper.searchNInfo(objectMap);
        PageInfo<Notice> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
    }
    /*主页显示三条信息*/
    @Override
    public List<Notice> listNInfo() {
        NoticeExample example = new NoticeExample();
        return noticeMapper.selectByExample(example);
    }
    /*显示单个通知内容*/
    @Override
    public Notice showOneInfo(Integer nid) {
        Notice notice = noticeMapper.selectByPrimaryKey(nid);
        return notice;
    }

}