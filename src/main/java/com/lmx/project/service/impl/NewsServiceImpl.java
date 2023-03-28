package com.lmx.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.project.mapper.NewsMapper;
import com.lmx.project.model.entity.News;
import com.lmx.project.service.NewsService;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【news】的数据库操作Service实现
* @createDate 2023-02-28 23:36:08
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

}




