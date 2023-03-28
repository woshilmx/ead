package com.lmx.project.model.dto.usertopicbank;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户-题库表
 * @TableName usertopicbank
 */
@TableName(value ="usertopicbank")
@Data
public class UserTopicBankAddRequest implements Serializable {


    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userid;

    /**
     * 题目ID
     */
    @ApiModelProperty("题目ID")
    private Long questionid;

    /**
     * 状态：0-错误 1-正确
     */
    @ApiModelProperty("状态：0-错误 1-正确")
    private Integer status;



}