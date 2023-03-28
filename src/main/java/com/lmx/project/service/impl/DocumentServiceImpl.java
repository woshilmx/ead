package com.lmx.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.project.mapper.DocumentMapper;
import com.lmx.project.model.entity.Document;
import com.lmx.project.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【document(文献表)】的数据库操作Service实现
* @createDate 2023-02-28 23:36:08
*/
@Service
@Slf4j
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document>
    implements DocumentService{

}




