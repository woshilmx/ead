package com.lmx.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmx.project.common.BaseResponse;
import com.lmx.project.common.ErrorCode;
import com.lmx.project.common.ResultUtils;
import com.lmx.project.exception.BusinessException;
import com.lmx.project.model.dto.news.NewsAddRequest;
import com.lmx.project.model.dto.news.NewsQueryRequest;
import com.lmx.project.model.dto.news.NewsUpdateRequest;
import com.lmx.project.model.entity.News;
import com.lmx.project.service.NewsService;
import com.lmx.project.until.FileUntil;
import com.lmx.project.until.PaChongUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/news")
@Api("新闻模块")
public class NewsController {

    @Resource
    private NewsService newsService;

    @Resource
    private FileUntil fileUntil;

    @Resource
    private PaChongUntil paChongUntil;


    private String newsDir = "news/";


    /**
     * 增加
     */
    @PostMapping
    @ApiOperation("增加新闻信息")
    public BaseResponse<Boolean> addNews(NewsAddRequest newsAddRequest) throws IOException, ParseException {


        News target = new News();


        if (newsAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (!StringUtils.isNotBlank(newsAddRequest.getNewscontent())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "所属单位不能为空");
        }
        if (!StringUtils.isNotBlank(newsAddRequest.getContent())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "新闻url不能为空");
        }

        if (!StringUtils.isNotBlank(newsAddRequest.getName())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "新闻标题不能为空");
        }

        if (newsAddRequest.getReleasetime() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "发布时间不能为空");
        }

        if (newsAddRequest.getCoverFile() != null) {
            MultipartFile newsAddRequestCoverFile = newsAddRequest.getCoverFile();
            String originalFilename = newsAddRequestCoverFile.getOriginalFilename();
//            后缀名
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

            String resultfilename = UUID.randomUUID().toString().replace("-", "");


            String b = fileUntil.saveFile(newsAddRequestCoverFile.getInputStream(), newsDir + resultfilename + substring);
            if (b!=null) {
                target.setCoverimg(b);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传错误");
            }

        }

        if (newsAddRequest.getReleasetime()!=null && newsAddRequest.getReleasetime().equals("")){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = simpleDateFormat.parse(newsAddRequest.getReleasetime());
            target.setReleasetime(parse);
        }


        BeanUtils.copyProperties(newsAddRequest, target);

        boolean save = newsService.save(target);
        return ResultUtils.success(save);
    }

    /**
     * 删除
     */

    @DeleteMapping()
    @ApiOperation(value = "删除新闻信息",notes = "id是新闻记录的id")
    public BaseResponse<Boolean> deleteNews(Long id) {
        if (id == null || id == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean save = newsService.removeById(id);
        return ResultUtils.success(save);
    }

    /**
     * 修改
     */
    @PostMapping("update")
    @ApiOperation("修改新闻信息")
    public BaseResponse<Boolean> UpdateNews( NewsUpdateRequest newsUpdateRequest) throws ParseException, IOException {
        if (newsUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (newsUpdateRequest.getId() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id属性为必须值");
        }

        News target = new News();

        if (newsUpdateRequest.getCoverFile() != null) {
            MultipartFile newsAddRequestCoverFile = newsUpdateRequest.getCoverFile();
            String originalFilename = newsAddRequestCoverFile.getOriginalFilename();
//            后缀名
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

            String resultfilename = UUID.randomUUID().toString().replace("-", "");


            String b = fileUntil.saveFile(newsAddRequestCoverFile.getInputStream(), newsDir + resultfilename + substring);
            if (b!=null) {
                target.setCoverimg(b);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传错误");
            }

        }

        if (newsUpdateRequest.getReleasetime()!=null && !newsUpdateRequest.getReleasetime().equals("")){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = simpleDateFormat.parse(newsUpdateRequest.getReleasetime());
            target.setReleasetime(parse);
        }

        BeanUtils.copyProperties(newsUpdateRequest, target);

        boolean save = newsService.updateById(target);
        return ResultUtils.success(save);
    }

    /**
     * 根据id查询
     */
    @GetMapping()
    @ApiOperation("根据id查询")
    public BaseResponse<News> getNewByid(Long id) throws UnknownHostException {
        if (id == null || id == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        News save = newsService.getById(id);
//        save.setCoverimg(fileUntil.getIpaddress()+save.getCoverimg());
        return ResultUtils.success(save);
    }

    /**
     * 分页条件查询
     */

    @PostMapping("list")
    @ApiOperation("分页查询新闻信息")
    public BaseResponse<IPage<News>> getNews(@RequestBody NewsQueryRequest newsQueryRequest) {
        if (newsQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (newsQueryRequest.getPageSize() == 0 || newsQueryRequest.getCurrent() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求页数不合法");
        }


        LambdaQueryWrapper<News> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(newsQueryRequest.getNewscontent())) {
            lambdaQueryWrapper.like(News::getNewscontent, newsQueryRequest.getNewscontent());
        }


        if (StringUtils.isNotBlank(newsQueryRequest.getName())) {
            lambdaQueryWrapper.like(News::getName, newsQueryRequest.getName());
        }

//        if (newsQueryRequest.getReleasetime() == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "发布时间不能为空");
//        }


        IPage<News> newsPage = new Page<>(newsQueryRequest.getCurrent(), newsQueryRequest.getPageSize());
        IPage<News> page = newsService.page(newsPage, lambdaQueryWrapper);
//        List<News> records = page.getRecords();
//        records.stream().forEach(item->{
//            try {
//                item.setCoverimg(fileUntil.getIpaddress()+item.getCoverimg());
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            }
//        });
//
//        page.setRecords(records);

        return ResultUtils.success(page);
    }

    /**
    * 从央视网获取新闻
    * */
    @GetMapping("network")
    @ApiOperation(value = "从央视网获取新闻",notes = "searchtext 查询的新闻关键字 page 显示的页数")
    public BaseResponse<List<News>> getNewsByYagnShi(String searchtext,int page) throws ParseException, IOException {

        if (searchtext==null){
           searchtext="濒危";

        }
        if (page==0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"页数不能为0");
        }

        List<News> news = paChongUntil.getnewsByNetwork(searchtext, page);
        return ResultUtils.success(news);
    }

}
