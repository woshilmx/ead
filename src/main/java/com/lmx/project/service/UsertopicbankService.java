package com.lmx.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmx.project.model.entity.Usertopicbank;
import com.lmx.project.model.vo.TopicBankVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【usertopicbank(用户-题库表)】的数据库操作Service
* @createDate 2023-02-28 23:36:08
*/
public interface UsertopicbankService extends IService<Usertopicbank> {

    List<Integer> getlevelByUserid(@Param("") Long userid);

    List<TopicBankVo> getTopicBankBlevel(Long userid, Long level);

}
