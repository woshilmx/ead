package com.lmx.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.project.model.entity.Comment;
import com.lmx.project.service.CommentService;
import com.lmx.project.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2023-03-14 19:27:20
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




