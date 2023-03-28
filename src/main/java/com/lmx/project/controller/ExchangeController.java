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
import com.lmx.project.model.dto.exchange.ExchangeAddRequest;
import com.lmx.project.model.dto.exchange.ExchangeUpdateRequest;
import com.lmx.project.model.dto.exchange.UserexchangeAddRequest;
import com.lmx.project.model.dto.exchange.UserexchangeQueryRequest;
import com.lmx.project.model.entity.Document;
import com.lmx.project.model.entity.Exchange;
import com.lmx.project.model.entity.Userexchange;
import com.lmx.project.model.vo.ExchangeVo;
import com.lmx.project.service.ExchangeService;
import com.lmx.project.service.UserexchangeService;
import com.lmx.project.until.FileUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 兑换商品
 */
@RestController
@RequestMapping("/exchange")
@Slf4j
@Api("商品模块")
public class ExchangeController {


    @Resource
    private ExchangeService exchangeService;
    @Resource
    private FileUntil fileUntil;

    private String exchangedir = "exchange/";

    /**
     * 用户兑换记录
     */
    @Resource
    private UserexchangeService userexchangeService;


    /**
     * 增加商品信息
     */

    @PostMapping
    @ApiOperation("增加商品信息")
    public BaseResponse<Boolean> addExchange(ExchangeAddRequest exchangeAddRequest) throws IOException {
        if (exchangeAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (exchangeAddRequest.getName() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "商品名称不能为空");
        }

        if (exchangeAddRequest.getRequiredscore() == null || exchangeAddRequest.getRequiredscore() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "积分不能为空或0");
        }

        if (exchangeAddRequest.getNumber() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "数量不能为0");
        }

        Exchange exchange = new Exchange();
        BeanUtils.copyProperties(exchangeAddRequest, exchange);
        if (exchangeAddRequest.getExchangefile() != null) {
            MultipartFile documentfile = exchangeAddRequest.getExchangefile();
            String originalFilename = documentfile.getOriginalFilename();
//            后缀名
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

            String resultfilename = UUID.randomUUID().toString().replace("-", "");


            String b = fileUntil.saveFile(documentfile.getInputStream(), exchangedir + resultfilename + substring);
            if (b != null) {
                exchange.setPicture(b);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传错误");
            }

        }

        boolean save = exchangeService.save(exchange);

        return ResultUtils.success(save);

    }

    /**
     * 修改商品信息
     */
    @PostMapping("update")
    @ApiOperation(value = "修改商品信息", notes = "id是必传的参数")
    public BaseResponse<Boolean> UpdateExchange(ExchangeUpdateRequest exchangeUpdateRequest) throws IOException {
        if (exchangeUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Exchange exchange = new Exchange();
        BeanUtils.copyProperties(exchangeUpdateRequest, exchange);
        if (exchangeUpdateRequest.getExchangefile() != null) {
            MultipartFile documentfile = exchangeUpdateRequest.getExchangefile();
            String originalFilename = documentfile.getOriginalFilename();
//            后缀名
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

            String resultfilename = UUID.randomUUID().toString().replace("-", "");


            String b = fileUntil.saveFile(documentfile.getInputStream(), exchangedir + resultfilename + substring);
            if (b != null) {
                exchange.setPicture(b);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传错误");
            }

        }

        boolean save = exchangeService.updateById(exchange);

        return ResultUtils.success(save);

    }


    /**
     * 删除商品信息
     */
    @DeleteMapping
    @ApiOperation("删除商品信息")
    public BaseResponse<Boolean> delelteExchange(Long id) throws IOException {

        if (id == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id不能为0或空");
        }

        boolean b = exchangeService.removeById(id);

        return ResultUtils.success(b);


    }

    /**
     * 根据id查询商品信息
     */

    @GetMapping
    @ApiOperation("根据id查询商品信息")
    public BaseResponse<Exchange> getExchange(Long id) throws IOException {

        if (id == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id不能为0或空");
        }
        Exchange byId = exchangeService.getById(id);
//        String ipaddress = fileUntil.getIpaddress();
//        byId.setPicture(ipaddress + byId.getPicture());
        return ResultUtils.success(byId);
    }

    /**
     * 分页查询商品信息,商品数量不多
     */
    @PostMapping("list")
    @ApiOperation("查询所有商品信息")
    public BaseResponse<List<Exchange>> getExchage() {
        LambdaQueryWrapper<Exchange> queryWrapper = new LambdaQueryWrapper<>();
//        0是上架状态
        queryWrapper.eq(Exchange::getStatu, 0);
        List<Exchange> list = exchangeService.list(queryWrapper);

//        list.stream().forEach(item -> {
//            try {
//                item.setPicture(fileUntil.getIpaddress() + item.getPicture());
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            }
//        });
        return ResultUtils.success(list);
    }

    /**
     * 增加商品的兑换记录
     */
    @ApiOperation("增加商品兑换记录")
    @PostMapping("userexchange")
    public BaseResponse<Boolean> addUserExchange(@RequestBody UserexchangeAddRequest userexchangeAddRequest) {
        if (userexchangeAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (userexchangeAddRequest.getUserid() == 0 || userexchangeAddRequest.getUserid() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "缺少userid");
        }
        if (userexchangeAddRequest.getGoodsid() == 0 || userexchangeAddRequest.getGoodsid() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "缺少Goodsid");
        }

        if (userexchangeAddRequest.getPhone() == null || userexchangeAddRequest.getPhone().equals("")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请填写联系方式");
        }

        if (userexchangeAddRequest.getAddress() == null || userexchangeAddRequest.getAddress().equals("")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请填写地址");
        }

        Userexchange userexchange = new Userexchange();
        BeanUtils.copyProperties(userexchangeAddRequest, userexchange);
        boolean save = userexchangeService.save(userexchange);
        exchangeService.reduceNumber(userexchange.getId());


        return ResultUtils.success(save);
    }

    /**
     * 修改商品的兑换记录
     */
    @GetMapping("userexchange")
    @ApiOperation(value = "修改商品的兑换记录",notes = "id必须传递的属性")
    public BaseResponse<Boolean> UpdateUserExchange(Long id, int statu) {
        if (id == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (statu != 0 || statu != 1) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Userexchange userexchange = new Userexchange();
        userexchange.setId(id);
        userexchange.setStatus(statu);
        boolean b = userexchangeService.updateById(userexchange);
        return ResultUtils.success(b);
    }

    /**
     * 查询兑换记录
     */
    @PostMapping("userexchange/list")
    @ApiOperation(value = "查询兑换记录",notes = "current与size为必须传递的属性")
    public BaseResponse<List<ExchangeVo>> GetUserExchange(@RequestBody UserexchangeQueryRequest userexchangeQueryRequest) {
        List<ExchangeVo> exchangeVos = userexchangeService.getAllExchange(userexchangeQueryRequest);
        return ResultUtils.success(exchangeVos);
    }
}
