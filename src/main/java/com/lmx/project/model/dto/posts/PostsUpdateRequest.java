package com.lmx.project.model.dto.posts;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子表
 *
 * @TableName posts
 */

@Data
public class PostsUpdateRequest implements Serializable {
    /**
     * 帖子ID
     */
    @ApiModelProperty("帖子ID")
    private Long id;

    /**
     * 帖子标题
     */
    @ApiModelProperty("帖子标题")
    private String title;


    /**
     * 帖子内容
     */
    @ApiModelProperty("帖子内容")
    private String content;


    /**
     * 状态：1-已发布，2-草稿
     */
    @ApiModelProperty("状态：1-已发布，2-草稿")
    private Integer status;


}