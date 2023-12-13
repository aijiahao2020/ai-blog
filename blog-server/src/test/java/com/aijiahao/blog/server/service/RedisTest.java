package com.aijiahao.blog.server.service;

import com.aijiahao.blog.server.entity.UserEntity;
import com.aijiahao.blog.server.utils.redis.RedisUtil;
import com.alibaba.fastjson.parser.ParserConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author aijiahao
 * @create 2023/12/13  0:47
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Test
    public void testObjectSave(){
        
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(12345L);
        userEntity.setUsername("aijaiahoa");
        redisTemplate.opsForValue().set("user", userEntity);

        UserEntity user = (UserEntity) redisTemplate.opsForValue().get("user");
        System.out.println(user);
    }
}
