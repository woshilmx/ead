package com.lmx.project.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import nonapi.io.github.classgraph.json.Id;

/**
 * 动物
 * @TableName animal
 */
@TableName(value ="animal")
@Data
@ToString
@ApiModel("动物")
public class Animal implements Serializable {
    /**
     * 动物编号
     */
    @TableId(type = IdType.AUTO)
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
    @ApiModelProperty("分类中目的编号")
    private Long categoryid;


    /**
    * 类编号
    * */
    @ApiModelProperty("分类中 类 的编号")
    private Long categoryIdClass;
    /**
     * 动物简介
     */
    @ApiModelProperty("动物简介")
    private String introduction;

    /**
     * 图片
     */
    @ApiModelProperty("动物图片地址")
    private String picture;

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
    @ApiModelProperty("生活习性")
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

//    /**
//     * 各界报道
//     */
//    private String report;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}