package com.lmx.project.model.dto.exchange;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * 兑换
 *
 * @TableName exchange
 */

@Data
public class ExchangeAddRequest implements Serializable {


    /**
     * 名称
     */
    @ApiModelProperty("商品名称")
    private String name;

    /**
     * 所需积分
     */
    @ApiModelProperty("所需积分")
    private Integer requiredscore;

//    /**
//     * 图片
//     */
//    private String picture;

    /**
     * 商品图片的文件
     */
    @ApiModelProperty("商品图片（文件）")
    private MultipartFile exchangefile;

    /**数量*/
    @ApiModelProperty("商品数量")
    private int number;
//    /**
//     * 上架状态 0-上架 1-下架
//     * */
//    private int statu;

}