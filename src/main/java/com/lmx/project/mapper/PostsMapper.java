package com.lmx.project.mapper;

import com.lmx.project.model.entity.Posts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Lenovo
* @description 针对表【posts(帖子表)】的数据库操作Mapper
* @createDate 2023-03-14 19:27:20
* @Entity com.lmx.project.model.entity.Posts
*/
public interface PostsMapper extends BaseMapper<Posts> {

    int updatePostLikeNum(@Param("id")  Long postsid,int num);
}




