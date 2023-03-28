package com.lmx.project.model.dto.comment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 评论表
 * @TableName comment
 */

@Data
public class CommentDeleteRequest implements Serializable {

    /**
     * 评论id
     * */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;




}