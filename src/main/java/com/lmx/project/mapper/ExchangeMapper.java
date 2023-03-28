package com.lmx.project.mapper;

import com.lmx.project.model.entity.Exchange;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Lenovo
* @description 针对表【exchange(兑换)】的数据库操作Mapper
* @createDate 2023-02-28 23:36:08
* @Entity com.lmx.project.model.entity.Exchange
*/
@Mapper
public interface ExchangeMapper extends BaseMapper<Exchange> {

    int reduce(Long id);
}




