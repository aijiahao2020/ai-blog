package com.aijiahao.blog.server.service;

import com.aijiahao.blog.server.entity.UserEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author aijiahao
 * @create 2023/12/25  17:35
 * @description UserService 进行测试，粗略测试，只测试正常用例下是否能执行
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    
    @Resource
    private UserService userService;

    /**
     * 测试queryByUserName ok
     */
    @Test
    public void queryByUserNameTest(){
        UserEntity user = userService.queryByUserName("aijiahao");
        System.out.println(user);
    }

    /**
     * 测试saveUser ok
     */
    @Test
    public void saveUserTest(){
        UserEntity user = new UserEntity();
        user.setUsername("aijiahao122");
        user.setNickname("haoge");
        user.setPassword("123456");
        Date currentTime = new Date();
        user.setCreateTime(currentTime);
        user.setUpdateTime(currentTime);
        // sha256加密
        String salt = RandomStringUtils.randomAlphabetic(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        user.setStatus(1);
        System.out.println(user);
        boolean b = userService.saveUser(user);
        System.out.println(b);
    }

    /**
     * 测试updateByUserId ok
     */
    @Test
    public void updateByUserIdTest(){
        UserEntity user = userService.queryByUserId(5L);
        user.setNickname("test");
        Date currentTime = new Date();
        user.setUpdateTime(currentTime);
        System.out.println(user);
        boolean b = userService.updateByUserId(user);
        System.out.println(b);

    }

    /**
     * 测试deleteByUserId ok
     */
    @Test
    public void deleteByUserIdTest(){
        boolean b = userService.deleteByUserId(8L);
        System.out.println(b);
    }

    /**
     * 测试disableByUserId ok
     */
    @Test
    public void disableByUserIdTest(){
        boolean b = userService.disableByUserId(6L);
        System.out.println(b);
    }

    /**
     * 测试enableByUserId ok
     */
    @Test
    public void enableByUserIdTest(){
        boolean b = userService.enableByUserId(6L);
        System.out.println(b);
    }
    

}
