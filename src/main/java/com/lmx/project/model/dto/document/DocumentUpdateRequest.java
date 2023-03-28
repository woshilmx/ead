package com.lmx.project.model.dto.document;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * 文献表
 *
 * @TableName document
 */

@Data
public class DocumentUpdateRequest implements Serializable {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;
    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 动物id
     */
    @ApiModelProperty("动物id")
    private Long animalid;


    /**
     * 发布时间 2023-03-10
     */
    @ApiModelProperty("发布时间")
    private String publishtime;

    /**
     * 作者
     */
    @ApiModelProperty("作者")
    private String author;

    /**
     * 期刊
     */
    @ApiModelProperty("期刊")
    private String periodical;
    /**
     * 文档信息
     */
    @ApiModelProperty("文献文件")
    private MultipartFile documentfile;



}