package com.lmx.project.common;

import com.lmx.project.constant.CommonConstant;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * 分页请求
 *
 * @author lmx
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    @ApiModelProperty("当前的页号")
    private long current = 1;

    /**
     * 页面大小
     */
    @ApiModelProperty("页面的大小")
    private long pageSize = 10;

//    /**
//     * 排序字段
//     */git
//    private String sortField;
//
//    /**
//     * 排序顺序（默认升序）
//     */
//    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
