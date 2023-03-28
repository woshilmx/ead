package com.lmx.project.model.vo;

import com.lmx.project.model.entity.Classify;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ClassifyVO {
    @ApiModelProperty("1——纲 2——目")
    private Integer stasu;
    private List<Classify> classifyList;
}
