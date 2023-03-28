package com.lmx.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.project.mapper.ExchangeMapper;
import com.lmx.project.model.entity.Exchange;
import com.lmx.project.service.ExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Lenovo
* @description 针对表【exchange(兑换)】的数据库操作Service实现
* @createDate 2023-02-28 23:36:08
*/
@Service
@Slf4j
public class ExchangeServiceImpl extends ServiceImpl<ExchangeMapper, Exchange>
    implements ExchangeService{


    @Resource
    public ExchangeMapper exchangeMapper;
    @Override
    public void reduceNumber(Long id) {
        int i=exchangeMapper.reduce(id);
    }
}




