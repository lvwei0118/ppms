package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.ForumPosts;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/15 18:05
 * @project: ppms
 * @Description: 帖子管理
 */
public interface ForumPostsServiceinter {
    boolean sendFor(ForumPosts forumPosts);

    List<ForumPosts> listFInfo();

    boolean deleFInfo(Integer tid);

    ForumPosts selectFInfo(Integer tid);
}

