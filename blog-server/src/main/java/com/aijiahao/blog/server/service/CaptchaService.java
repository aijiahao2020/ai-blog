package com.aijiahao.blog.server.service;

import com.aijiahao.blog.server.dto.CaptchaDto;
import com.aijiahao.blog.server.vo.CaptchaVo;

/**
 * @description 验证码业务处理类
 * @author aijiahao
 * @create 2023/11/22  0:16
 */
public interface CaptchaService {

    /**
     * 获取滑动验证码
     * @param
     * @return  图片验证码
     */
    CaptchaVo selectCaptcha();

    /**
     * 校验验证码结果
     * @param x x轴坐标
     * @param y y轴坐标
     * @return 校验结果
     */
    boolean checkCaptchaResult(String x, String y, String uuid);
}
