package com.aijiahao.blog.server.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author aijiahao
 * @create 2023/11/15  23:34
 */

@Data
public class LoginVo {
    // 登录账号
    @NotNull
    private String username;
    
    // 密码
    @NotNull
    private String password;

    // 登录位置 省
    private String loginProvince;
    
    // 登录位置 市
    private String loginCity;
    
    // 登录位置 经度
    private String loginLat;
    
    // 登录位置 维度
    private String loginLng;
    
    // private String captcha;
    // private String uuid;
}
