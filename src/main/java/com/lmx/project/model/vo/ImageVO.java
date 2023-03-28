package com.lmx.project.model.vo;

import lombok.Data;

import java.util.Map;
@Data
public class ImageVO {
    private Integer errno;

    private Map<String,String> data;
}
