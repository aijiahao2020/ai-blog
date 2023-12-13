package com.aijiahao.blog.server.vo;

import lombok.Data;

/**
 * @author aijiahao
 * @create 2023/11/15  23:34
 */

@Data
public class LoginVo {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
