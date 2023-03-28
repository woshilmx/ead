package com.lmx.project.model.dto.news;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName news
 */

@Data
public class NewsUpdateRequest implements Serializable {
    /**
     * 新闻编号
     */
    @ApiModelProperty("新闻编号")
    private Long id;

    /**
     * 新闻名称
     */
    @ApiModelProperty("新闻编号")
    private String name;

    /**
     * 新闻url地址
     */
    @ApiModelProperty("新闻编号")
    private String content;

    /**
     * 发布时间
     */
    @ApiModelProperty("新闻编号")
    private String releasetime;

    /**
     * 封面图片
     */
    @ApiModelProperty("新闻编号")
    private String coverimg;

    /**
     * 出版单位
     */
    @ApiModelProperty("新闻编号")
    private String newscontent;

    /**
    * 图片文件
    * */
    @ApiModelProperty("新闻封面图片文件")
    private MultipartFile  coverFile;



}