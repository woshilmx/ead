package com.lmx.project.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class TopicBankVo {
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
     * 解析
     */
    @ApiModelProperty("解析")
    private String analysis;

    /**
    * 问题
    * */
    @ApiModelProperty("问题")
    private String question;

    /**
     * 所属关卡
     */
    @ApiModelProperty("所属关卡")
    private Integer belonglevel;


    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userid;



    /**
     * 状态：0-错误 1-正确
     */
    @ApiModelProperty("状态：0-错误 1-正确")
    private Integer status;

}
