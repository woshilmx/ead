package com.lmx.project.service;

import com.lmx.project.model.entity.Posts;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * @author Lenovo
 * @description 针对表【posts(帖子表)】的数据库操作Service
 * @createDate 2023-03-14 19:27:20
 */
public interface PostsService extends IService<Posts> {

    int changelikeNum(Long userid,Long postsid,int num);
}
