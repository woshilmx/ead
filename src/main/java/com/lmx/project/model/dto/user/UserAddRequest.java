package com.lmx.project.model.dto.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户创建请求
 *
 * @author lmx
 */
@Data
public class UserAddRequest implements Serializable {



    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty("头像url")
    private String avatar;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * openid
     * */
    @ApiModelProperty("openid")
    private String openid;

    /**
     * 头像文件
     * */
    @ApiModelProperty("头像文件")
    private MultipartFile addAvatarFile;




}