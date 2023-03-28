package com.lmx.project.model.dto.comment;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lmx.project.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 评论表
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class CommentQueryRequest extends PageRequest implements Serializable {



    /**
     * 用户id
     */
    private Long userId;

    /**
     * 帖子id
     */
    private Long postId;






}