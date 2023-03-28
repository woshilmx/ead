package com.lmx.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.project.mapper.UserexchangeMapper;
import com.lmx.project.model.dto.exchange.UserexchangeQueryRequest;
import com.lmx.project.model.entity.Userexchange;
import com.lmx.project.model.vo.ExchangeVo;
import com.lmx.project.service.UserexchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Lenovo
* @description 针对表【userexchange(用户兑换表)】的数据库操作Service实现
* @createDate 2023-02-28 23:36:08
*/
@Service
@Slf4j
public class UserexchangeServiceImpl extends ServiceImpl<UserexchangeMapper, Userexchange>
    implements UserexchangeService{


    @Resource
    private UserexchangeMapper userexchangeMapper;


    @Override
    public List<ExchangeVo> getAllExchange(UserexchangeQueryRequest userexchangeQueryRequest) {




       return userexchangeMapper.selectExchangeVo(userexchangeQueryRequest);






    }
}




