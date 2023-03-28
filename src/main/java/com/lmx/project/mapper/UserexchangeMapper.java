package com.lmx.project.mapper;

import com.lmx.project.model.dto.exchange.UserexchangeQueryRequest;
import com.lmx.project.model.entity.Userexchange;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmx.project.model.vo.ExchangeVo;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【userexchange(用户兑换表)】的数据库操作Mapper
* @createDate 2023-02-28 23:36:08
* @Entity com.lmx.project.model.entity.Userexchange
*/
public interface UserexchangeMapper extends BaseMapper<Userexchange> {


    List<ExchangeVo> selectExchangeVo(UserexchangeQueryRequest userexchangeQueryRequest);
}




