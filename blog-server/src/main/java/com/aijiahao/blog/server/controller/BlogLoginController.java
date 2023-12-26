package com.aijiahao.blog.server.controller;


import com.aijiahao.blog.common.utils.R;
import com.aijiahao.blog.server.dto.CaptchaDto;
import com.aijiahao.blog.server.entity.UserEntity;
import com.aijiahao.blog.server.service.CaptchaService;
import com.aijiahao.blog.server.service.UserTokenService;
import com.aijiahao.blog.server.utils.redis.RedisUtil;
import com.aijiahao.blog.server.vo.CaptchaVo;
import com.aijiahao.blog.server.vo.LoginVo;
import com.aijiahao.blog.server.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
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
    
    @Autowired
    private UserTokenService userTokenService;
    

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
        // System.out.println("get:" + captchaVo);
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

    /**
     * 博客登录接口
     * @param loginVo
     * @return
     * @throws IOException
     */
    @PostMapping("/login")
    public Map<String, Object> login(@Valid @RequestBody LoginVo loginVo,  BindingResult result) throws IOException {
        System.out.println("进入login...");
        System.out.println(loginVo);
        // 后端校验
        if(result.hasErrors()) {
            HashMap<String, String> map = new HashMap<>();
            // 获取校验的错误结果
            result.getFieldErrors().forEach((fieldError -> {
                String message = fieldError.getDefaultMessage();
                String field = fieldError.getField();
                System.out.println(field + ":" + message);
                map.put(field, message);
            }));
            return R.error("数据校验错误");
        }
        UserEntity userEntity = userService.queryByUserName(loginVo.getUsername());
        if(userEntity == null || !userEntity.getPassword().equals(new Sha256Hash(loginVo.getPassword(), userEntity.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }

        //账号锁定
        if (userEntity.getStatus() == null || userEntity.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }
        // 生成token，并保存到服务器
        R r = userTokenService.createToken(userEntity.getUserId());
        userEntity.setPassword(loginVo.getPassword());
        return r.put("code", 200).put("msg", "登录成功").put("user", userEntity);
    }

    /**
     * 博客注册接口
     * @param registerVo
     * @return
     */
    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody LoginVo registerVo, BindingResult result) {
        System.out.println(registerVo);
        System.out.println("进入register...");
        // 后端校验
        if(result.hasErrors()) {
            HashMap<String, String> map = new HashMap<>();
            // 获取校验的错误结果
            result.getFieldErrors().forEach((fieldError -> {
                String message = fieldError.getDefaultMessage();
                String field = fieldError.getField();
                System.out.println(field + ":" + message);
                map.put(field, message);
            }));
            return R.error("数据校验错误");
        }
        UserEntity userEntity = userService.queryByUserName(registerVo.getUsername());
        if(userEntity != null) {
            return R.error("用户已注册，请登录");
        }
        UserEntity user = new UserEntity();
        user.setUsername(registerVo.getUsername());
        user.setPassword(registerVo.getPassword());
        if(userService.saveUser(user)) {
            return R.ok("注册成功，请登录").put("code", 200);
        }else{
            return R.error("注册失败，请稍后再试");
        }
        
    }
    
}
