package com.lmx.project.model.dto.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录请求体
 *
 * @author lmx
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;


    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
    * openid
    * */
    @ApiModelProperty("openid")
    private String openid;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;


}
