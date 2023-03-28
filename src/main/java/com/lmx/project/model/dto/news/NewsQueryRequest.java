package com.lmx.project.model.dto.news;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lmx.project.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName news
 */

@Data
public class NewsQueryRequest extends PageRequest implements Serializable {


    /**
     * 新闻名称
     */
    @ApiModelProperty("新闻名称")
    private String name;



    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private Date releasetime;



    /**
     * 所属单位
     */
    @ApiModelProperty("所属单位")
    private String newscontent;


}