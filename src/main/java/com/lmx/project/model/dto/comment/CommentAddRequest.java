package com.lmx.project.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName comment
 */

@Data
public class CommentAddRequest implements Serializable {


    /**
     * 用户id
     */
    private Long userId;

    /**
     * 帖子id
     */
    private Long postId;

    /**
     * 评论内容
     */
    private String content;



}