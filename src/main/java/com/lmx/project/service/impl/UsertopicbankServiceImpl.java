package com.lmx.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.project.mapper.UsertopicbankMapper;
import com.lmx.project.model.entity.Usertopicbank;
import com.lmx.project.model.vo.TopicBankVo;
import com.lmx.project.service.UsertopicbankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Lenovo
* @description 针对表【usertopicbank(用户-题库表)】的数据库操作Service实现
* @createDate 2023-02-28 23:36:08
*/
@Service
@Slf4j
public class UsertopicbankServiceImpl extends ServiceImpl<UsertopicbankMapper, Usertopicbank>
    implements UsertopicbankService {

    @Resource
    private UsertopicbankMapper usertopicbankMapper;
    @Override
    public List<Integer> getlevelByUserid(Long userid) {
       return usertopicbankMapper.selectLevelByUserid(userid);

    }

    @Override
    public List<TopicBankVo> getTopicBankBlevel(Long userid, Long level) {

      return   usertopicbankMapper.selectTopicBankByUserIdAndLevel(userid,level);



    }
}




