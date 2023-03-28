package com.lmx.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmx.project.model.entity.Exchange;

/**
* @author Lenovo
* @description 针对表【exchange(兑换)】的数据库操作Service
* @createDate 2023-02-28 23:36:08
*/
public interface ExchangeService extends IService<Exchange> {

    void reduceNumber(Long id);
}
