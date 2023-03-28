package com.lmx.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.lmx.project.common.DeleteRequest;
import com.lmx.project.common.ErrorCode;
import com.lmx.project.common.ResultUtils;
import com.lmx.project.model.dto.user.*;
import com.lmx.project.model.entity.User;
import com.lmx.project.model.vo.UserVO;
import com.lmx.project.common.BaseResponse;
import com.lmx.project.exception.BusinessException;
import com.lmx.project.model.dto.*;
import com.lmx.project.model.dto.user.*;
import com.lmx.project.service.UserService;
import com.lmx.project.until.FileUntil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 用户接口
 *
 * @author lmx
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api("用户模块")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "lmx";

    private String userdir = "user/";


    @Resource
    private FileUntil fileUntil;

    /**
     * 用户注册
     */
    @PostMapping("resgiter")
    @ApiOperation("用户注册")
    public BaseResponse<Boolean> AddUser(UserAddRequest userAddRequest) throws IOException {

        if (!StringUtils.isNotBlank(userAddRequest.getEmail())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱不能为空");
        }

        if (!StringUtils.isNotBlank(userAddRequest.getPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能为空");
        }

        if (!StringUtils.isNotBlank(userAddRequest.getNickname())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "昵称不能为空");
        }

        if (userAddRequest.getAvatar()==null && userAddRequest.getAddAvatarFile() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "头像不能为空");
        }


        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,userAddRequest.getEmail());
        User one = userService.getOne(queryWrapper);
        if (one!=null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"该邮箱已存在,请勿重复注册");
        }

        boolean result = userService.addUser(userAddRequest);
        return ResultUtils.success(result);


    }


    /**
     * 用户注册
     */
    @PostMapping("wxresgiter")
    @ApiOperation("微信端用户的注册")
    public BaseResponse<Boolean> AddUserWx(UserAddRequest userAddRequest) throws IOException {

        if (!StringUtils.isNotBlank(userAddRequest.getOpenid())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "openid不能为空");
        }

//        if (!StringUtils.isNotBlank(userAddRequest.getPassword())) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能为空");
//        }

        if (!StringUtils.isNotBlank(userAddRequest.getNickname())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "昵称不能为空");
        }

        if (userAddRequest.getAvatar()==null && userAddRequest.getAddAvatarFile() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "头像不能为空");
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenid,userAddRequest.getOpenid());
        User one = userService.getOne(queryWrapper);
        if (one!=null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"该OpenId已存在，请勿重复注册");
        }

        boolean result = userService.addUser(userAddRequest);
        return ResultUtils.success(result);


    }


    /**
     * 用户更新
     */
    @PutMapping("update")
    @ApiOperation("用户信息的更新")
    public BaseResponse<Boolean> UpdateUser(UserUpdateRequest updateRequest) throws IOException {

        if (updateRequest.getId() == null || updateRequest.getId() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id属性不能为空");
        }
        log.info("update信息是{}",updateRequest.toString());
//     如果是修改密码，直接加密
        if (updateRequest.getPassword() != null) {
            updateRequest.setPassword(
                    DigestUtils.md5DigestAsHex(
                            (SALT + updateRequest.getPassword())
                                    .getBytes()));
        }
        User user = new User();

        BeanUtils.copyProperties(updateRequest,user );


        if (updateRequest.getUpdateAvatarFile() != null) {
            MultipartFile addAvatarFile = updateRequest.getUpdateAvatarFile();


            String originalFilename = addAvatarFile.getOriginalFilename();
//            后缀名
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

            String resultfilename = UUID.randomUUID().toString().replace("-", "");


            String b = fileUntil.saveFile(addAvatarFile.getInputStream(), userdir + resultfilename+substring);
            if (b!=null) {
                user.setAvatar(b);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传错误");
            }
        }
        log.info("用户信息是{}",user.toString());
        boolean b = userService.updateById(user);
        return ResultUtils.success(b);
    }

    /**
     * 用户登录
     */

    @PostMapping("login")
    @ApiOperation("用户登录")
    public BaseResponse<User> LoginUser(@RequestBody UserLoginRequest userLoginRequest) throws IOException {

        if (!StringUtils.isNotBlank(userLoginRequest.getEmail())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱不能为空");
        }

        if (!StringUtils.isNotBlank(userLoginRequest.getPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能为空");
        }

        String password = userLoginRequest.getPassword();

        String pwd = DigestUtils.md5DigestAsHex((SALT + password).getBytes());


        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, userLoginRequest.getEmail()).eq(User::getPassword, pwd);
        User one = userService.getOne(queryWrapper);
//        one.setAvatar(fileUntil.getIpaddress()+one.getAvatar());
        if (one != null) {
            return ResultUtils.success(one);
        } else {
            return ResultUtils.error(403, "密码或用户名错误");
        }

    }

    /**
     * 用户登录 小程序端登录
     */
    @PostMapping("wxlogin")
    @ApiOperation("小程序端用户的登录,openid")
    public BaseResponse<User> loginUserWX(@RequestBody UserLoginRequest userLoginRequest) throws IOException {

        if (!StringUtils.isNotBlank(userLoginRequest.getOpenid())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱不能为空");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenid, userLoginRequest.getOpenid());
        User one = userService.getOne(queryWrapper);
        if (one != null) {
//            if (!one.getAvatar().contains("http")){
//                one.setAvatar(fileUntil.getIpaddress()+one.getAvatar());
//            }

            return ResultUtils.success(one);
        } else {
            return ResultUtils.error(403, "暂无该用户信息");
        }
    }






    /**
     * 获取当前用户信息
     */
    @PostMapping()
    @ApiOperation("获取当前用户信息")
    public BaseResponse<User> getUserByid(Long id) throws IOException {
        if (id == null || id == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id属性不能为空或0");
        }

        User byId = userService.getById(id);
        if (byId!=null){
//            if (!byId.getAvatar().contains("http")){
//                byId.setAvatar(fileUntil.getIpaddress() + byId.getAvatar());
//            }

        }
        return ResultUtils.success(byId);
    }


    /**
     * 退出登录
     * */


    /**
     * 分页查询用户信息
     */
    @PostMapping("list")
    @ApiOperation("分页查询用户信息")
    public BaseResponse<Page<User>> getUserList(@RequestBody UserQueryRequest userQueryRequest) throws IOException {
        if (userQueryRequest==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<User> userPage = new Page<>(userQueryRequest.getCurrent(), userQueryRequest.getPageSize());


        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        邮箱查
        if (StringUtils.isNotBlank(userQueryRequest.getEmail())) {
            queryWrapper.eq(User::getEmail, userQueryRequest.getEmail());
        }
//        id查
        if (userQueryRequest.getId() != null && userQueryRequest.getId() != 0) {
            queryWrapper.eq(User::getId, userQueryRequest.getId());
        }
// 昵称查
        if (StringUtils.isNotBlank(userQueryRequest.getNickname())) {
            queryWrapper.eq(User::getNickname, userQueryRequest.getNickname());
        }


        Page<User> page = userService.page(userPage, queryWrapper);


//        List<User> records = page.getRecords();


//        String ipaddress = fileUntil.getIpaddress();

//        records.stream().forEach(item -> {
//            if (!item.getAvatar().contains("http")){
//                item.setAvatar(ipaddress + item.getAvatar());
//            }
//
//        });
//
//        page.setRecords(records);

        return ResultUtils.success(page);


    }


}
