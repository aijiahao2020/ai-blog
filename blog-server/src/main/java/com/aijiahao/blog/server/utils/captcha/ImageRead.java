package com.aijiahao.blog.server.utils.captcha;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * @author aijiahao
 * @create 2023/11/22  0:48
 * @description 图片读取类
 */

@Data
public class ImageRead {
    
    private BufferedImage image;

    private String fileExtension;

    private java.io.InputStream InputStream;

}
