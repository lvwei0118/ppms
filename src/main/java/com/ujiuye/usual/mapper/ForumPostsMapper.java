package com.ujiuye.usual.mapper;

import com.ujiuye.usual.bean.ForumPosts;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/15 11:10
 * @project: ppms
 * @Description: 帖子管理
 */
public interface ForumPostsMapper {

    int sendFor(ForumPosts forumPosts);

    List<ForumPosts> listFInfo();

    int deleFInfo(Integer tid);

    ForumPosts selectFInfo(Integer tid);
}
