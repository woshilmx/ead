package com.lmx.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.project.common.ErrorCode;
import com.lmx.project.exception.BusinessException;
import com.lmx.project.model.entity.Liketable;
import com.lmx.project.model.entity.Posts;
import com.lmx.project.service.LiketableService;
import com.lmx.project.service.PostsService;
import com.lmx.project.mapper.PostsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lenovo
 * @description 针对表【posts(帖子表)】的数据库操作Service实现
 * @createDate 2023-03-14 19:27:20
 */
@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts>
        implements PostsService {

    @Resource
    private PostsMapper postsMapper;
    @Resource
    private LiketableService liketableService;

    @Override
    public int changelikeNum(Long userid, Long postsid, int num) {
        if (num == -1) {
//             先判断是否是点过赞
            LambdaQueryWrapper<Liketable> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Liketable::getUserid, userid).eq(Liketable::getPostid, postsid);
            Liketable one = liketableService.getOne(queryWrapper);
            if (one == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "不能撤销点赞");
            }
//            判断点赞数是否是0
            Posts byId = getById(postsid);
            if (byId.getLikenum() > 0) {
                int i = postsMapper.updatePostLikeNum(postsid, num);
                if (i != 0) {
                    liketableService.remove(queryWrapper);
                }
                return i;
            }
        } else {
            int i = postsMapper.updatePostLikeNum(postsid, num);
            if (i != 0) {
                Liketable liketable = new Liketable();
                liketable.setPostid(postsid);
                liketable.setUserid(userid);
                liketableService.save(liketable);
            }
            return i;
        }

        return 0;
    }
}




