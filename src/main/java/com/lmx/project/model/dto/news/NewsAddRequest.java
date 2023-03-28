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
public class NewsAddRequest implements Serializable {


    /**
     * 新闻名称
     */
    @ApiModelProperty("新闻名称")
    private String name;

    /**
     * 新闻url地址
     */
    @ApiModelProperty("新闻url地址")
    private String content ;

    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private String releasetime;

//    /**
//     * 封面图片
//     */
//    private String coverimg;

    /**
     * 出版单位
     */
    @ApiModelProperty("出版单位")
    private String newscontent;

    /**
    * 封面图片
    * */
    @ApiModelProperty("封面图片文件")
    private MultipartFile coverFile;
}