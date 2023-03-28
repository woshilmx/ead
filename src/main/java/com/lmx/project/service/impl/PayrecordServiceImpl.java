package com.lmx.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.project.mapper.PayrecordMapper;
import com.lmx.project.model.entity.Payrecord;
import com.lmx.project.service.PayrecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【payrecord(支付记录)】的数据库操作Service实现
* @createDate 2023-02-28 23:36:08
*/
@Service
@Slf4j
public class PayrecordServiceImpl extends ServiceImpl<PayrecordMapper, Payrecord>
    implements PayrecordService{

}




