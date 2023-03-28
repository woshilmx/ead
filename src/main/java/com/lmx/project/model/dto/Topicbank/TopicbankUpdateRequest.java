package com.lmx.project.model.dto.Topicbank;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TopicbankUpdateRequest {
    /**
     * id
     */
@ApiModelProperty("id")
    private Long id;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private Integer type;

    /**
     * 答案
     */
    @ApiModelProperty("答案")
    private String answer;


    /**
     * 题目
     * */
    @ApiModelProperty("题目")
    private String question;
    /**
     * 解析
     */
    @ApiModelProperty("解析")
    private String analysis;

    /**
     * 所属关卡
     */
    @ApiModelProperty("所属关卡")
    private Integer belonglevel;


}
