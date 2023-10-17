package com.aijiahao.blog.admin.modules.sys.service;

import com.aijiahao.blog.admin.modules.sys.entity.SysCaptchaEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.awt.image.BufferedImage;

/**
 * @author aijiahao
 * @create 2023/8/23  13:42
 */
public interface SysCaptchaService extends IService<SysCaptchaEntity> {
    /**
     * 获取图片验证码
     *
     * @param uuid
     * @return
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码校验
     *
     * @param uuid
     * @param code
     * @return true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}
