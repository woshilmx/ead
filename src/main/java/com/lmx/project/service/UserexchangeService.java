package com.lmx.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmx.project.model.dto.exchange.UserexchangeQueryRequest;
import com.lmx.project.model.entity.Userexchange;
import com.lmx.project.model.vo.ExchangeVo;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【userexchange(用户兑换表)】的数据库操作Service
* @createDate 2023-02-28 23:36:08
*/
public interface UserexchangeService extends IService<Userexchange> {

    List<ExchangeVo> getAllExchange(UserexchangeQueryRequest userexchangeQueryRequest);

}
