package com.lmx.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lmx.project.model.dto.user.UserAddRequest;
import com.lmx.project.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用户服务
 *
 * @author lmx
 */
public interface UserService extends IService<User> {


    boolean addUser(UserAddRequest userAddRequest) throws IOException;

}
