package com.lmx.project.mapper;

import com.lmx.project.model.entity.Usertopicbank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmx.project.model.vo.TopicBankVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lenovo
 * @description 针对表【usertopicbank(用户-题库表)】的数据库操作Mapper
 * @createDate 2023-02-28 23:36:08
 * @Entity com.lmx.project.model.entity.Usertopicbank
 */
public interface UsertopicbankMapper extends BaseMapper<Usertopicbank> {

    List<Integer> selectLevelByUserid(@Param("userid") Long userid);

    List<TopicBankVo>  selectTopicBankByUserIdAndLevel(@Param("userid") Long userid, @Param("belongLevel") Long level);
}




