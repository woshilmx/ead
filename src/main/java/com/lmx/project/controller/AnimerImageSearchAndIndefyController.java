package com.lmx.project.controller;

import com.lmx.project.common.BaseResponse;
import com.lmx.project.common.ErrorCode;
import com.lmx.project.common.ResultUtils;
import com.lmx.project.config.RabbitMQConfigure;
import com.lmx.project.exception.BusinessException;
import com.lmx.project.model.enums.ImageMode;
import com.lmx.project.until.AnimalIdentUntil;
import com.lmx.project.until.ImageChangeUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("aisc")
@Api("动物识别模块")
public class AnimerImageSearchAndIndefyController {
    @Resource
    private AnimalIdentUntil animalIdentUntil;

    @Resource
    private ImageChangeUntil imageChangeUntil;

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 实现根据图片检测动物信息
     */
    @PostMapping("indefy")
    @ApiOperation(value = "传递动物照片信息", notes = "传递的文件参数名称为animerimage")
    public BaseResponse<String> IdenfyAnimerByImage(@RequestParam MultipartFile animerimage) throws IOException {
        if (animerimage.getSize() >= 4 * 1024 * 1024) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件大小需要小于4MB");
        }

        InputStream inputStream = animerimage.getInputStream();
        JSONObject animerByImage = animalIdentUntil.getAnimerByImage(inputStream);
        return ResultUtils.success(animerByImage.toString());

    }

    /**
     * 实现图像风格转化
     */
    @PostMapping("change")
    @ApiOperation(value = "实现图像的风格转化",notes = "传递的参数是：animerimage 文件,mode为变换的类型，详情可见获取类型接口")
    public BaseResponse<String> ChangeAnimerByImage(@RequestParam MultipartFile animerimage, @RequestParam String mode) throws IOException {
        if (animerimage.getSize() >= 4 * 1024 * 1024) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件大小需要小于4MB");
        }

        InputStream inputStream = animerimage.getInputStream();

        ImageMode item = ImageMode.getItem(mode);
        String s = imageChangeUntil.imageChange(inputStream, item);

        if (s != null) {

            return ResultUtils.success(s);
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 获取所有的能转化的风格样式
     */
    @GetMapping("mode")
    @ApiOperation("获取能转换的风格样式")
    public BaseResponse<Map<String, String>> getImageMode() {
        Map<String, String> values = ImageMode.getValues();
        return ResultUtils.success(values);
    }
}
