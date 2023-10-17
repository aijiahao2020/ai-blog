package com.aijiahao.blog.admin.modules.sys.controller;

import com.aijiahao.blog.admin.modules.common.utils.R;
import com.aijiahao.blog.admin.modules.sys.entity.SysUserEntity;
import com.aijiahao.blog.admin.modules.sys.form.SysLoginForm;
import com.aijiahao.blog.admin.modules.sys.service.SysCaptchaService;
import com.aijiahao.blog.admin.modules.sys.service.SysUserService;
import com.aijiahao.blog.admin.modules.sys.service.SysUserTokenService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author aijiahao
 * @create 2023/8/23  12:05
 */
@RestController
public class SysLoginController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SysCaptchaService sysCaptchaService;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        System.out.println(uuid);
        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @PostMapping("/sys/login")
    public Map<String, Object> login(@RequestBody SysLoginForm form) throws IOException {
        System.out.println("进入login...");
        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
        if (!captcha) {
            return R.error("验证码不正确");
        }

        // 用户信息
        SysUserEntity userEntity = sysUserService.queryByUserName(form.getUsername());

        //账号不存在、密码错误
        if (userEntity == null || !userEntity.getPassword().equals(new Sha256Hash(form.getPassword(), userEntity.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }

        //账号锁定
        if (userEntity.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }

        // 生成token，并保存到服务器
        R r = sysUserTokenService.createToken(userEntity.getUserId());
        return r;
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/sys/logout")
    public R logout() {
        sysUserTokenService.logout(getUserId());
        return R.ok();
    }
}
