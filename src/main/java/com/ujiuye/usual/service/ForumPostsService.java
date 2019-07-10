package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.ForumPosts;
import com.ujiuye.usual.mapper.ForumPostsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/15 11:08
 * @project: ppms
 * @Description: 帖子管理
 */
@Service
public class ForumPostsService implements ForumPostsServiceinter {

    @Resource
    private ForumPostsMapper forumPostsMapper;


    @Override
    public boolean sendFor(ForumPosts forumPosts) {
       int i= forumPostsMapper.sendFor(forumPosts);
        return i>0;
    }

    @Override
    public List<ForumPosts> listFInfo() {
        List<ForumPosts> list =forumPostsMapper.listFInfo();
        return list;
    }

    @Override
    public boolean deleFInfo(Integer tid) {
        int i=forumPostsMapper.deleFInfo(tid);
        return i>0;
    }

    @Override
    public ForumPosts selectFInfo(Integer tid) {
        ForumPosts fp=forumPostsMapper.selectFInfo(tid);
        return fp;
    }
}
