package com.lmx.project.model.dto.exchange;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户兑换表
 * @TableName userexchange
 */

@Data
public class UserexchangeAddRequest implements Serializable {


    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userid;

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long goodsid;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String phone;




}