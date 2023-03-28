package com.lmx.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmx.project.common.BaseResponse;
import com.lmx.project.common.ErrorCode;
import com.lmx.project.common.ResultUtils;
import com.lmx.project.exception.BusinessException;
import com.lmx.project.model.dto.document.DocumentAddRequest;
import com.lmx.project.model.dto.document.DocumentQueryRequest;
import com.lmx.project.model.dto.document.DocumentUpdateRequest;
import com.lmx.project.model.entity.Document;
import com.lmx.project.service.DocumentService;
import com.lmx.project.until.FileUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/document")
@Slf4j
@Api("文献模块")
public class DocumentController {

    @Resource
    private DocumentService documentService;


    private String documentdir = "document/";

    @Resource
    private FileUntil fileUntil;

    /**
     * 增加文献信息
     */

    @PostMapping
    @ApiOperation("增加文献信息")
    public BaseResponse<Boolean> addDocument(DocumentAddRequest documentAddRequest) throws IOException, ParseException {
        if (documentAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (documentAddRequest.getAnimalid()==null || documentAddRequest.getAnimalid() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请关联濒危动物");
        }

        if (documentAddRequest.getAuthor() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "作者不能为空");
        }

        if (documentAddRequest.getTitle() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题不能为空");
        }




        Document document = new Document();
        BeanUtils.copyProperties(documentAddRequest, document);

        if (documentAddRequest.getPublishtime()!=null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(documentAddRequest.getPublishtime().toString());
            document.setPublishtime(date);
        }
        if (documentAddRequest.getDocumentfile() != null) {
            MultipartFile documentfile = documentAddRequest.getDocumentfile();
            String originalFilename = documentfile.getOriginalFilename();
//            后缀名
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

            String resultfilename = UUID.randomUUID().toString().replace("-", "");


            String b = fileUntil.saveFile(documentfile.getInputStream(), documentdir + resultfilename+substring);
            if (b!=null) {
                document.setUrl(b);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传错误");
            }

        }

        boolean save = documentService.save(document);

        return ResultUtils.success(save);

    }

    /**
     * 修改文献信息
     */
    @PostMapping("update")
    @ApiOperation("修改文献信息")
    public BaseResponse<Boolean> updateDocument(DocumentUpdateRequest documentUpdateRequest) throws IOException, ParseException {

        if (documentUpdateRequest == null || documentUpdateRequest.getId() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "必须有id属性");
        }
        Document document = new Document();
        BeanUtils.copyProperties(documentUpdateRequest, document);
        if (documentUpdateRequest.getDocumentfile() != null) {
            MultipartFile documentfile = documentUpdateRequest.getDocumentfile();
            String originalFilename = documentfile.getOriginalFilename();
//            后缀名
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

            String resultfilename = UUID.randomUUID().toString().replace("-", "");


            String b = fileUntil.saveFile(documentfile.getInputStream(), documentdir + resultfilename+substring);
            if (b!=null) {
                document.setUrl(b);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传错误");
            }
        }

        if (documentUpdateRequest.getPublishtime()!=null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(documentUpdateRequest.getPublishtime().toString());
            document.setPublishtime(date);
        }
        boolean b = documentService.updateById(document);

        return ResultUtils.success(b);
    }


    /**
     * 删除文献信息
     */
    @DeleteMapping
    @ApiOperation(value = "删除文献信息",notes = "参数是id，待删除数据库记录的id")
    public BaseResponse<Boolean> delelteDocument(Long id) throws IOException {

        if (id == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id不能为0或空");
        }

        boolean b = documentService.removeById(id);

        return ResultUtils.success(b);


    }

    /**
     * 根据id查询文献信息
     */

    @GetMapping
    @ApiOperation("根据id查询文献信息")
    public BaseResponse<Document> getDocument(Long id) throws IOException {

        if (id == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id不能为0或空");
        }
        Document byId = documentService.getById(id);
//        String ipaddress = fileUntil.getIpaddress();
//        byId.setUrl(ipaddress + byId.getUrl());
        return ResultUtils.success(byId);
    }

    /**
     * 分页查询文献信息
     */

    @PostMapping("list")
    @ApiOperation(value = "分页查询文献信息",notes = "current与size是必须传递的参数，其他的参数根据业务需求传递")
    public BaseResponse<Page<Document>> getDocumentlist(@RequestBody DocumentQueryRequest documentQueryRequest) {
        if (documentQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (documentQueryRequest.getPageSize() == 0 || documentQueryRequest.getCurrent() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求页数不合法");
        }
        Document target = new Document();
//        BeanUtils.copyProperties(documentQueryRequest, target);
        LambdaQueryWrapper<Document> documentLambdaQueryWrapper = new LambdaQueryWrapper<>();

// 动物类别查
        if (documentQueryRequest.getAnimalid() != null && documentQueryRequest.getAnimalid()!=0) {
         documentLambdaQueryWrapper.eq(Document::getAnimalid,documentQueryRequest.getAnimalid());
        }
//   作者查
        if (StringUtils.isNotBlank(documentQueryRequest.getAuthor())) {
            documentLambdaQueryWrapper.like(Document::getAuthor,documentQueryRequest.getAuthor());
        }
// 标题查
        if (StringUtils.isNotBlank(documentQueryRequest.getTitle())) {
            documentLambdaQueryWrapper.like(Document::getTitle,documentQueryRequest.getTitle());

        }


//        QueryWrapper<Document> queryWrapper = new QueryWrapper<>(target);
        Page<Document> page = documentService.page(new Page<>(documentQueryRequest.getCurrent(), documentQueryRequest.getPageSize()),
                documentLambdaQueryWrapper);
        List<Document> records = page.getRecords();

//        records.stream().forEach(item->{
//            try {
//                item.setUrl(fileUntil.getIpaddress()+item.getUrl());
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            }
//        });
//
//        page.setRecords(records);
        return ResultUtils.success(page);
    }


}
