package com.lmx.project.model.dto.posts;

import com.baomidou.mybatisplus.annotation.*;
import com.lmx.project.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子表
 * @TableName posts
 */

@Data
public class PostsQueryRequest extends PageRequest implements Serializable {


    /**
     * 帖子标题
     */
    @ApiModelProperty("帖子标题")
    private String title;

    /**
     * 发布者ID
     */
    @ApiModelProperty("发布者ID")
    private Integer userid;

    /**
     * 帖子内容
     */
    @ApiModelProperty("帖子内容")
    private String content;



    /**
     * 状态：1-已发布，2-草稿
     */
    private Integer status;



}