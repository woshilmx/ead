package com.lmx.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.project.common.ErrorCode;
import com.lmx.project.model.dto.user.UserAddRequest;
import com.lmx.project.model.entity.User;
import com.lmx.project.exception.BusinessException;
import com.lmx.project.mapper.UserMapper;
import com.lmx.project.service.UserService;
import com.lmx.project.until.FileUntil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.UUID;

import static com.lmx.project.constant.UserConstant.ADMIN_ROLE;
import static com.lmx.project.constant.UserConstant.USER_LOGIN_STATE;


/**
 * 用户服务实现类
 *
 * @author lmx
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private FileUntil fileUntil;

    private String userdir = "user/";


    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "lmx";

    /**
     * 用户注册
     */
    @Override
    public boolean addUser(UserAddRequest userAddRequest) throws IOException {
        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);

        if (userAddRequest.getAddAvatarFile() != null) {
            MultipartFile addAvatarFile = userAddRequest.getAddAvatarFile();


            String originalFilename = addAvatarFile.getOriginalFilename();
//            后缀名
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

            String resultfilename = UUID.randomUUID().toString().replace("-", "");


            String b = fileUntil.saveFile(addAvatarFile.getInputStream(), userdir + resultfilename + substring);
            if (b!=null) {
                user.setAvatar(b);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传错误");
            }
        }

//        设置积分为零

        String password = user.getPassword();
        String s = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        user.setPassword(s);

        user.setIntegral(0);

        this.save(user);

        return true;


    }
}




