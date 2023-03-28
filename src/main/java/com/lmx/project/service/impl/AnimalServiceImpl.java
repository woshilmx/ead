package com.lmx.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.project.mapper.AnimalMapper;
import com.lmx.project.model.entity.Animal;
import com.lmx.project.service.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【animal(动物)】的数据库操作Service实现
* @createDate 2023-02-28 23:36:08
*/
@Service
@Slf4j
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal>
    implements AnimalService{

}




