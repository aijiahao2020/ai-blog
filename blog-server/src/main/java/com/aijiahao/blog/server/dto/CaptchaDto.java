package com.aijiahao.blog.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author aijiahao
 * @create 2023/11/22  0:23
 * @description 验证码封装类 （验证码类型默认为滑动验证码）
 */
@Data
public class CaptchaDto implements Serializable {
    /**
     * 图源
     */
    private String originImage;

    /**
     * 遮罩图
     */
    private String shadeImage;

    /**
     * 裁剪图
     */
    private String cutoutImage;

    /**
     * x轴
     */
    private Integer x;

    /**
     * y轴
     */
    private Integer y; 
    
}
