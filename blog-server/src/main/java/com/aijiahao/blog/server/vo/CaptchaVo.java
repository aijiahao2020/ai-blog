package com.aijiahao.blog.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author aijiahao
 * @create 2023/11/22  0:33
 * @description 验证码
 */
@Data
public class CaptchaVo implements Serializable {
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

    /**
     * uuid
     */
    private String uuid;
}
