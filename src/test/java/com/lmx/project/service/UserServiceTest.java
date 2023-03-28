package com.lmx.project.service;

import com.lmx.project.mapper.UsertopicbankMapper;
import com.lmx.project.model.dto.exchange.UserexchangeQueryRequest;
import com.lmx.project.model.entity.User;
import com.lmx.project.until.FileUntil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * 用户服务测试
 *
 * @author lmx
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;
    @Resource
    private UsertopicbankMapper usertopicbankMapper;

    @Resource
    private FileUntil fileUntil;

    @Resource
    private UserexchangeService userexchangeService;

    @Test
    public void asfadf() throws UnknownHostException {
//        usertopicbankMapper.selectTopicBankByUserIdAndLevel(1L, 2L);
//        System.out.println(fileUntil.getIpaddress());
    }

    @Test
    public void asfadf33() {
        UserexchangeQueryRequest userexchangeQueryRequest = new UserexchangeQueryRequest();
        userexchangeQueryRequest.setUserid(1L);
        userexchangeQueryRequest.setGoodsid(2L);
        System.out.println(userexchangeService.getAllExchange(userexchangeQueryRequest));
    }

    @Test
    void testAddUser() {
        String filename = "121212.jpg";
        String substring = filename.substring(filename.lastIndexOf("."), filename.length());

        String s = UUID.randomUUID().toString();
        String replace = s.replace("-", "");

        String resultfilename = replace + substring;
        System.out.println(resultfilename);

    }
    @Test
    void testUpdateUser() {
        User user = new User();
        boolean result = userService.updateById(user);
        Assertions.assertTrue(result);
    }

    @Test
    void testDeleteUser() {
        boolean result = userService.removeById(1L);
        Assertions.assertTrue(result);
    }

    @Test
    void testGetUser() {
        User user = userService.getById(1L);
        Assertions.assertNotNull(user);
    }

//    @Test
//    void userRegister() {
//        String userAccount = "lmx";
//        String userPassword = "";
//        String checkPassword = "123456";
//        try {
//            long result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "yu";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "lmx";
//            userPassword = "123456";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "yu pi";
//            userPassword = "12345678";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            checkPassword = "123456789";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "doglmx";
//            checkPassword = "12345678";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "lmx";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//        } catch (Exception e) {
//
//        }
//    }
}