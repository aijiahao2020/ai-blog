package com.aijiahao.blog.server.controller;


import com.aijiahao.blog.server.dto.CaptchaDto;
import com.aijiahao.blog.server.service.CaptchaService;
import com.aijiahao.blog.server.utils.redis.RedisUtil;
import com.aijiahao.blog.server.vo.CaptchaVo;
import com.aijiahao.blog.server.vo.LoginVo;
import com.aijiahao.blog.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author aijiahao
 * @create 2023/9/12  16:34
 */

@RestController
@Slf4j
@RequestMapping("/blog")
public class BlogLoginController extends AbstractController{
    @Autowired
    private UserService userService;
    
    @Autowired
    private CaptchaService captchaService;
    

    /**
     * 获取验证码
     * @return 返回滑动验证码
     */
    @GetMapping("/captcha/get")
    public CaptchaVo getCaptchaImage() {
        CaptchaVo captchaVo = null;
        try {
            captchaVo = captchaService.selectCaptcha();
        } catch (Exception e) {
            return null;
        }
        return captchaVo;
    }
    
    @RequestMapping("/captcha/check")
    public boolean checkCaptchaResult(String x, String y, String uuid) {
        System.out.println(x);
        System.out.println(y);
        System.out.println(uuid);
        boolean result = false;
        try {
            result = captchaService.checkCaptchaResult(x, y, uuid);
        } catch (Exception e) {
            return false;
        }
        return result;
    }
    
    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginVo loginVo) throws IOException {
        System.out.println("进入login...");

        return null;
    }
    
    @GetMapping("/redis/save")
    public String save(String key, String value) {
        System.out.println(key + "," + value);
        // stringRedisTemplate.opsForValue().set(key, value);
        return "success";
    }
}
