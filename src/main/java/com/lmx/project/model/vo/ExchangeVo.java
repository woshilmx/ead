package com.lmx.project.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ExchangeVo {
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;


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

    /**
     * 邮寄状态 0——未邮寄 1——邮寄
     */
    @ApiModelProperty("邮寄状态 0——未邮寄 1——邮寄")
    private Integer status;

}
