package com.lmx.project.model.dto.Topicbank;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.lmx.project.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class TopicBankQueryRequest extends PageRequest implements Serializable {


    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private Integer type;


    /**
     * 所属关卡
     */
    @ApiModelProperty("所属关卡")
    private Integer belonglevel;
}
