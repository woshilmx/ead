package com.lmx.project.model.dto.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户更新请求
 *
 * @author lmx
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * 用户ID
     */
@ApiModelProperty("")
    private Long id;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * openid
     * */
    @ApiModelProperty("openid")
    private String openid;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
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
     * 积分
     */
    @ApiModelProperty("积分")
    private Integer integral;

    /**
    * 图片更新的文件
    * */
    @ApiModelProperty("图片更新的文件")
    private MultipartFile updateAvatarFile;
}