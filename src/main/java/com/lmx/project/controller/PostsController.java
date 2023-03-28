package com.lmx.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lmx.project.common.BaseResponse;
import com.lmx.project.common.ErrorCode;
import com.lmx.project.common.ResultUtils;
import com.lmx.project.exception.BusinessException;
import com.lmx.project.model.dto.comment.CommentDeleteRequest;
import com.lmx.project.model.dto.comment.CommentQueryRequest;
import com.lmx.project.model.dto.posts.PostsAddRequest;
import com.lmx.project.model.dto.posts.PostsQueryRequest;
import com.lmx.project.model.dto.posts.PostsUpdateRequest;
import com.lmx.project.model.entity.Comment;
import com.lmx.project.model.entity.CommentAddRequest;
import com.lmx.project.model.entity.Posts;
import com.lmx.project.model.entity.User;
import com.lmx.project.model.vo.ImageVO;
import com.lmx.project.service.CommentService;
import com.lmx.project.service.PostsService;
import com.lmx.project.service.UserService;
import com.lmx.project.until.FileUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("posts")
@Api("论坛帖子模块")
@Slf4j
public class PostsController {
    @Resource
    private PostsService postsService;

    @Resource
    private UserService userService;

    @Resource
    private CommentService commentService;

    private String postimagespath = "postimage/";

    @Resource
    private FileUntil fileUntil;

    /**
     * 增加帖子
     */
    @PostMapping
    @ApiOperation("增加帖子")
    public BaseResponse<Long> addPost(@RequestBody PostsAddRequest postsAddRequest) {
        if (!StringUtils.isNotBlank(postsAddRequest.getContent())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容不能为空");
        }

        if (!StringUtils.isNotBlank(postsAddRequest.getTitle())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题不能为空");
        }

        if (postsAddRequest.getUserid() == null || postsAddRequest.getUserid() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不能为空");
        }

        User byId = userService.getById(postsAddRequest.getUserid());
        if (byId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "暂无该用户");
        }
        Posts posts = new Posts();
        BeanUtils.copyProperties(postsAddRequest, posts);

        posts.setLikenum(0);
        if (posts.getStatus() == null || posts.getStatus() == 0) {
            posts.setStatus(1);
        }

        boolean save = postsService.save(posts);

        if (save) {
            return ResultUtils.success(posts.getId());
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }


    }


    /**
     * 删除帖子
     */

    @DeleteMapping
    @ApiOperation(value = "删除帖子",notes = "参数是帖子id")
    public BaseResponse<Boolean> deletePost(Long id) {
        if (id == null || id == 0L) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id错误");
        }
        boolean b = postsService.removeById(id);
        return ResultUtils.success(b);
    }


    /**
     * 修改帖子
     */
    @PutMapping
    @ApiOperation(value = "修改帖子",notes = "id是必须的属性")
    public BaseResponse<Long> UpdatePost(@RequestBody PostsUpdateRequest postsUpdateRequest) {

        if (postsUpdateRequest.getId() == null || postsUpdateRequest.getId() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id错误");
        }

        Posts posts = new Posts();
        BeanUtils.copyProperties(postsUpdateRequest, posts);


        boolean save = postsService.updateById(posts);


        return ResultUtils.success(posts.getId());

    }


    /**
     * 根据id查询
     */
    @GetMapping
    @ApiOperation("根据id查询帖子信息")
    public BaseResponse<Posts> GetPostbyid(Long id) {
        if (id == null || id == 0L) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id错误");
        }
        Posts byId = postsService.getById(id);
        return ResultUtils.success(byId);
    }


    /**
     * 多条件查询
     */
    @PostMapping("query")
    @ApiOperation("分页查询帖子信息")
    public BaseResponse<Page<Posts>> GetPostsBy(@RequestBody PostsQueryRequest postsQueryRequest) {


        if (postsQueryRequest.getCurrent() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "页数错误");
        }

        if (postsQueryRequest.getPageSize() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "每页条数错误");
        }

        LambdaQueryWrapper<Posts> queryWrapper = new LambdaQueryWrapper<>();

        if (postsQueryRequest.getUserid() != null && postsQueryRequest.getUserid() != 0) {
            queryWrapper.eq(Posts::getUserid, postsQueryRequest.getUserid());
        }

        if (StringUtils.isNotBlank(postsQueryRequest.getTitle())) {
            queryWrapper.like(Posts::getTitle, postsQueryRequest.getTitle());
        }

        if (StringUtils.isNotBlank(postsQueryRequest.getContent())) {
            queryWrapper.or();
            queryWrapper.like(Posts::getContent, postsQueryRequest.getContent());
        }

        queryWrapper.orderBy(true, false, Posts::getUpdateTime);

        Page<Posts> page = postsService.page(new Page<Posts>(postsQueryRequest.getCurrent(), postsQueryRequest.getPageSize()), queryWrapper);


        return ResultUtils.success(page);
    }


    /**
     * 增加点赞数
     */

    @GetMapping("addlike")
    @ApiOperation("增加点赞数")
    public BaseResponse<Boolean> uodateAddBylikeNum(Long userid, Long postsid) {

        if (userid == null || userid == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不能为空");
        }
        if (postsid == null || postsid == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "帖子不能为空");
        }

        int result = postsService.changelikeNum(userid, postsid, 1);

        return ResultUtils.success(true);
    }


    /**
     * 撤销点赞
     */
    @GetMapping("reducelike")
    @ApiOperation("撤销点赞")
    public BaseResponse<Boolean> uodateReduceBylikeNum(Long userid, Long postsid) {

        if (userid == null || userid == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不能为空");
        }
        if (postsid == null || postsid == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "帖子不能为空");
        }

        int result = postsService.changelikeNum(userid, postsid, -1);

        return ResultUtils.success(true);
    }

    /**
     * 增加评论
     */
    @PostMapping("comment")
    @ApiOperation("增加评论")
    public BaseResponse<Long> AddComent(@RequestBody CommentAddRequest commentAddRequest) {
        if (commentAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (commentAddRequest.getPostId() == null || commentAddRequest.getUserId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (commentAddRequest.getContent() == null || commentAddRequest.getContent().equals("")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "评论内容不能为空");
        }

        Posts byId = postsService.getById(commentAddRequest.getPostId());
        if (byId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文章不存在");
        }

        User user = userService.getById(commentAddRequest.getUserId());

        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在");
        }

        Comment target = new Comment();
        BeanUtils.copyProperties(commentAddRequest, target);
        commentService.save(target);

        return ResultUtils.success(target.getId());


    }

    /**
     * 删除评论
     */

    @DeleteMapping("comment")
    @ApiOperation("删除评论")
    public BaseResponse<Boolean> DeleteComent(@RequestBody CommentDeleteRequest commentDeleteRequest) {
        if (commentDeleteRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (commentDeleteRequest.getId() == null || commentDeleteRequest.getUserId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Comment byId = commentService.getById(commentDeleteRequest.getId());
        if (byId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "帖子id错误");
        }

        Posts post = postsService.getById(byId.getPostId());

//        必须是评论的发布者或者文章的发布者才能删除
        if (byId.getUserId().equals(commentDeleteRequest.getUserId()) || byId.getUserId().equals(post.getUserid())) {
            commentService.removeById(commentDeleteRequest.getId());
            return ResultUtils.success(true);
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }


    }

    /**
     * 查询评论
     */
    @GetMapping("comment")
    @ApiOperation("查询评论")
    public BaseResponse<Page<Comment>> getComments(CommentQueryRequest commentQueryRequest) {

        if (commentQueryRequest.getCurrent() == 0 || commentQueryRequest.getPageSize() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();


        if (commentQueryRequest.getPostId() != null) {
            queryWrapper.eq(Comment::getPostId, commentQueryRequest.getPostId());
        }
        if (commentQueryRequest.getUserId() != null) {
            queryWrapper.eq(Comment::getUserId, commentQueryRequest.getUserId());
        }

        Page<Comment> page = commentService.page(new Page<>(commentQueryRequest.getCurrent(), commentQueryRequest.getPageSize()), queryWrapper);

        return ResultUtils.success(page);

    }

    /**
     * 富文本编辑器中图片/视频的上传
     */

    @PostMapping("images")
    /**
     * {
     *     "errno": 0, // 注意：值是数字，不能是字符串
     *     "data": {
     *         "url": "xxx", // 图片 src ，必须
     *         "alt": "yyy", // 图片描述文字，非必须
     *         "href": "zzz" // 图片的链接，非必须
     *     }
     * }
    * */
    @ApiOperation("富文本编辑器中图片/视频的上传")
    public String addImage(MultipartFile imagefile) throws IOException {
        log.info("images接口被触发");
        if (imagefile == null) {
            return null;
        }

        String originalFilename = imagefile.getOriginalFilename();

        String substring = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

        String s = UUID.randomUUID().toString();
        String replace = s.replace("-", "");

        String resultfilename = replace + substring;

        String result = postimagespath + resultfilename;

        String imageurl = fileUntil.saveFile(imagefile.getInputStream(), result);


        ImageVO imageVO = new ImageVO();
        imageVO.setErrno(0);
        HashMap<String, String> data = new HashMap<>();
        data.put("url",imageurl);
        imageVO.setData(data);

        Gson gson = new Gson();
        String s1 = gson.toJson(imageVO);
        log.info(s1);

        return s1;
    }


}
