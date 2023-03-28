package com.lmx.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmx.project.common.BaseResponse;
import com.lmx.project.common.ErrorCode;
import com.lmx.project.common.ResultUtils;
import com.lmx.project.config.FileConfig;
import com.lmx.project.exception.BusinessException;
import com.lmx.project.model.dto.Animer.AnimalAddRequest;
import com.lmx.project.model.dto.Animer.AnimalQueryRequest;
import com.lmx.project.model.dto.Animer.AnimalUpdateRequest;
import com.lmx.project.model.entity.Animal;
import com.lmx.project.model.entity.Classify;
import com.lmx.project.model.vo.ClassifyVO;
import com.lmx.project.service.AnimalService;
import com.lmx.project.service.ClassifyService;
import com.lmx.project.until.FileUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animer")
@Slf4j
@Api("动物模块")
public class AnimerController {
    @Resource
    private AnimalService animalService;
    @Resource
    private ClassifyService classifyService;


    private String animalfile = "animal/";
    //    @Resource
//    private FileConfig fileConfig;
    @Resource
    private FileUntil fileUntil;

    /**
     * 增加濒危动物信息
     */
    @PostMapping()
    @ApiOperation("增加濒危动物信息")
    public BaseResponse<Boolean> addAnimer(AnimalAddRequest animalAddRequest) throws IOException {
        if (animalAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (animalAddRequest.getName() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "动物名称不能为空");
        }
        if (animalAddRequest.getHabit() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "动物习惯不能为空");
        }
        if (animalAddRequest.getCategoryid() != null && animalAddRequest.getCategoryid() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "分类不能为空");

        }
        if (animalAddRequest.getIntroduction() == null) {
            throw new
                    BusinessException(ErrorCode.PARAMS_ERROR, "简介不能为空");

        }
        if (animalAddRequest.getDistributionrange() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "分布范围不能为空");

        }
        if (animalAddRequest.getMorphologydescription() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "形态描述不能为空");

        }
        if (animalAddRequest.getEndangeredlevel() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "濒危等级不能为空");

        }
        if (animalAddRequest.getGeographicalenvironment() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "生活地理环境");

        }
        Animal animal = new Animal();
        BeanUtils.copyProperties(animalAddRequest, animal);
//         处理图片信息
        if (animalAddRequest.getImage() != null) {
            MultipartFile image = animalAddRequest.getImage();
            String filename = image.getOriginalFilename();
            log.info("文件名称是{}", filename);
//             提取文件后缀,".jpg"
            String substring = filename.substring(filename.lastIndexOf("."), filename.length());

            String s = UUID.randomUUID().toString();
            String replace = s.replace("-", "");

            String resultfilename = replace + substring;
            InputStream inputStream = image.getInputStream();
            String filepath = animalfile + resultfilename;
            String b = fileUntil.saveFile(inputStream, filepath);
            if (b!=null) {
                animal.setPicture(b);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传失败");
            }

        }


        boolean save = animalService.save(animal);

        return ResultUtils.success(save);
    }
//

    /**
     * 更新濒危动物信息
     */
    @ApiOperation("更新濒危动物信息")
    @PostMapping("update")
    public BaseResponse<Boolean> updateAnimer(AnimalUpdateRequest animalUpdateRequest) throws IOException {
        if (animalUpdateRequest == null || animalUpdateRequest.getId() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "必须有id属性");
        }
        Animal animal = new Animal();


//        如果该文件不为空，则是修改图片
        if (animalUpdateRequest.getImage() != null) {
            MultipartFile image = animalUpdateRequest.getImage();
            String filename = image.getOriginalFilename();
            log.info("文件名称是{}", filename);
//             提取文件后缀,".jpg"
            String substring = filename.substring(filename.lastIndexOf("."), filename.length());

            String s = UUID.randomUUID().toString();
            String replace = s.replace("-", "");

            String resultfilename = replace + substring;
            InputStream inputStream = image.getInputStream();
            String filepath = animalfile + resultfilename;
            String b = fileUntil.saveFile(inputStream, filepath);
            if (b!=null) {
                animal.setPicture(b);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片修改失败");
            }
        }

        BeanUtils.copyProperties(animalUpdateRequest, animal);

        boolean b = animalService.updateById(animal);

        return ResultUtils.success(b);

    }


    /**
     * 根据id查询动物信息
     */
    @GetMapping()
    @ApiOperation("根据id查询动物信息")
    public BaseResponse<Animal> getOneById(@RequestParam Long id) throws UnknownHostException {

        if (id == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Animal byId = animalService.getById(id);

//        if (byId!=null){
//            byId.setPicture(fileUntil.getIpaddress()+byId.getPicture());;
//        }
        return ResultUtils.success(byId);
    }


    /**
     * 分页查询动物信息
     */
    @PostMapping("list")
    @ApiOperation("分页查询动物信息，current与size是必传参数，其他的参数根据业务需求自行确定是否传参")
    public BaseResponse<IPage<Animal>> getAnimelList(@RequestBody AnimalQueryRequest animalQueryRequest) {

        if (animalQueryRequest.getCurrent() == 0 || animalQueryRequest.getPageSize() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "分页信息错误");
        }

//        Animal animal = new Animal();
//        BeanUtils.copyProperties(animalQueryRequest, animal);

        LambdaQueryWrapper<Animal> animalLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(animalQueryRequest.getName())) {
            animalLambdaQueryWrapper.like(Animal::getName, animalQueryRequest.getName());
        }
        if (StringUtils.isNotBlank(animalQueryRequest.getHabit())) {
            animalLambdaQueryWrapper.like(Animal::getHabit, animalQueryRequest.getHabit());
        }
        if (animalQueryRequest.getCategoryid() != null && animalQueryRequest.getCategoryid() != 0) {
            animalLambdaQueryWrapper.eq(Animal::getCategoryid, animalQueryRequest.getCategoryid());

        }

        if (animalQueryRequest.getCategoryIdClass() != null && animalQueryRequest.getCategoryIdClass() != 0) {
            animalLambdaQueryWrapper.eq(Animal::getCategoryIdClass, animalQueryRequest.getCategoryIdClass());

        }

        if (StringUtils.isNotBlank(animalQueryRequest.getIntroduction())) {
            animalLambdaQueryWrapper.like(Animal::getIntroduction, animalQueryRequest.getIntroduction());

        }
        if (StringUtils.isNotBlank(animalQueryRequest.getDistributionrange())) {
            animalLambdaQueryWrapper.like(Animal::getDistributionrange, animalQueryRequest.getDistributionrange());


        }
        if (StringUtils.isNotBlank(animalQueryRequest.getMorphologydescription())) {
            animalLambdaQueryWrapper.like(Animal::getMorphologydescription, animalQueryRequest.getMorphologydescription());


        }
        if (StringUtils.isNotBlank(animalQueryRequest.getEndangeredlevel())) {
            animalLambdaQueryWrapper.like(Animal::getEndangeredlevel, animalQueryRequest.getEndangeredlevel());


        }
        if (StringUtils.isNotBlank(animalQueryRequest.getGeographicalenvironment())) {
            animalLambdaQueryWrapper.like(Animal::getGeographicalenvironment, animalQueryRequest.getGeographicalenvironment());


        }
//        QueryWrapper<Animal> animalQueryWrapper = new QueryWrapper<>(animal);

        IPage<Animal> page = animalService.page(new Page<>(animalQueryRequest.getCurrent(), animalQueryRequest.getPageSize()), animalLambdaQueryWrapper);


//        List<Animal> records = page.getRecords();
//
//
//        records.stream().forEach(item -> {
//            try {
////                if (item!=null){
////                    item.setPicture(fileUntil.getIpaddress() + item.getPicture());
////                }
//
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            }
//        });
        return ResultUtils.success(page);
    }


    /**
     * 获取分类的信息
     */
    @GetMapping("classify")
    @ApiOperation("获取动物的分类信息")
    public BaseResponse<List<ClassifyVO>> getClassify() {
        List<Classify> list = classifyService.list();

        List<ClassifyVO> classifyVOS = new ArrayList<>();

        List<Classify> classify1 = new ArrayList<>();
        List<Classify> classify2 = new ArrayList<>();


        ClassifyVO classifyVO1 = new ClassifyVO();
        classifyVO1.setStasu(1);
        ClassifyVO classifyVO2 = new ClassifyVO();
        classifyVO2.setStasu(2);
        list.stream().forEach(item->{
            if (item.getStatus()!=null){
                if (item.getStatus()==1){
                    classify1.add(item);
                }
                if (item.getStatus()==2){
                    classify2.add(item);
                }
            }
        });
        classifyVO1.setClassifyList(classify1);
        classifyVO2.setClassifyList(classify2);

        classifyVOS.add(classifyVO1);
        classifyVOS.add(classifyVO2);

        return ResultUtils.success(classifyVOS);
    }
}
