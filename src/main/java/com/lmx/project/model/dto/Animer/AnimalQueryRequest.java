package com.lmx.project.model.dto.Animer;

import com.baomidou.mybatisplus.annotation.*;
import com.lmx.project.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * 动物
 * @TableName animal
 */

@Data
public class AnimalQueryRequest extends PageRequest implements Serializable {
    /**
     * 动物编号
     */
@ApiModelProperty("id")
    private Long id;

    /**
     * 动物名称
     */
    @ApiModelProperty("动物名称")
    private String name;

    /**
     * 目编号
     */
    @ApiModelProperty("目编号")
    private Long categoryid;

    /**
     * 类编号
     */
    @ApiModelProperty("类编号")
    private Long categoryIdClass;

    /**
     * 动物简介
     */
    @ApiModelProperty("动物简介")
    private String introduction;


    /**
     * 濒危等级
     */
    @ApiModelProperty("濒危等级")
    private String endangeredlevel;

    /**
     * 形态描述
     */
    @ApiModelProperty("形态描述")
    private String morphologydescription;

    /**
     * 生活习性
     */
    @ApiModelProperty("生活习惯")
    private String habit;

    /**
     * 生活地理环境
     */
    @ApiModelProperty("生活地理环境")
    private String geographicalenvironment;

    /**
     * 分布范围
     */
    @ApiModelProperty("分布范围")
    private String distributionrange;





}