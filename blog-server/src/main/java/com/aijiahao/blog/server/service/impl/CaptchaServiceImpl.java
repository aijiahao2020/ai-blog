package com.aijiahao.blog.server.service.impl;

import com.aijiahao.blog.server.service.CaptchaService;
import com.aijiahao.blog.server.utils.captcha.ImageRead;
import com.aijiahao.blog.server.utils.captcha.ImageVerificationUtil;
import com.aijiahao.blog.server.utils.redis.RedisUtil;
import com.aijiahao.blog.server.vo.CaptchaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author aijiahao
 * @create 2023/11/22  0:42
 * @description 验证码业务实现类
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 源图路径前缀
     */
    @Value("${captcha.slide-verification-code.path.origin-image:classpath:static/targets}")
    private String verificationImagePathPrefix;

    /**
     * 模板图路径前缀
     */
    @Value("${captcha.slide-verification-code.path.template-image:classpath:static/templates}")
    private String templateImagePathPrefix;
    
    /**
     * 获取request对象
     *
     * @return 返回request对象
     */
    protected static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取response对象
     *
     * @return 返回response对象
     */
    protected static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }


    @Override
    public CaptchaVo selectCaptcha() {
        CaptchaVo captchaVo = null;

        try {
            // 随机取得目标文件夹中一张图片
            ImageRead originImageRead = readTargetImage();
            // 获取模板图片文件
            ImageRead templateImageRead = readTemplateImage(templateImagePathPrefix.concat("/template.png"));
            // 获取描边图片文件
            ImageRead borderImageRead = readTemplateImage(templateImagePathPrefix.concat("/border.png"));

            //  获取原图文件类型
            String originImageFileType = originImageRead.getFileExtension();
            //  获取模板图文件类型
            String templateImageFileType = templateImageRead.getFileExtension();
            //  获取描边图文件类型
            String borderImageFileType = borderImageRead.getFileExtension();

            //读取源图
            BufferedImage verificationImage = originImageRead.getImage();
            //  读取模板图
            BufferedImage templateImage = templateImageRead.getImage();
            //  读取描边图片
            BufferedImage borderImage = borderImageRead.getImage();

            //  获取原图裁剪区域坐标
            captchaVo = ImageVerificationUtil.generateCutoutCoordinates(verificationImage, templateImage);

            Integer y = captchaVo.getY();
            
            //  在分布式应用中，可将session改为redis存储
            // getRequest().getSession().setAttribute("captchaVo", captchaVo);
            String uuid = UUID.randomUUID().toString();
            System.out.println(captchaVo);
            System.out.println(uuid);
            // redisUtil.set(uuid, captchaVo.getX().toString());
            // redisUtil.setEx(uuid, captchaVo.getX().toString(),1, TimeUnit.MINUTES);
            redisTemplate.opsForValue().set(uuid, captchaVo,1, TimeUnit.MINUTES);
            //  根据原图生成遮罩图和切块图
            captchaVo = ImageVerificationUtil.pictureTemplateCutout(originImageRead, templateImageRead, captchaVo.getX(), captchaVo.getY());

            //   剪切图描边
            captchaVo = ImageVerificationUtil.cutoutImageEdge(captchaVo, borderImage, borderImageFileType);
            captchaVo.setY(y);

            captchaVo.setUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return captchaVo;
    }

    /**
     * 读取目标图
     *
     * @return
     */
    public ImageRead readTargetImage() {
        ImageRead imageRead = null;

        try {
            Random random = new Random(System.currentTimeMillis());
            if (verificationImagePathPrefix.indexOf("classpath") >= 0) {
                ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                Resource[] resources = resolver.getResources(verificationImagePathPrefix.concat("/*"));

                if (resources == null) {
                    throw new RuntimeException("not found target image");
                }
                int i = random.nextInt(resources.length);
                imageRead = new ImageRead();
                imageRead.setImage(ImageIO.read(resources[i].getInputStream()));
                
                String extension = resources[i].getFilename().substring(resources[i].getFilename().lastIndexOf(".") + 1);
                imageRead.setInputStream(resources[i].getInputStream());
                imageRead.setFileExtension(extension);

            } else {
                File importImage = new File(verificationImagePathPrefix);
                if (importImage == null) {
                    throw new RuntimeException("not found target image");
                }
                File[] files = importImage.listFiles();
                int i = random.nextInt(files.length);
                imageRead = new ImageRead();
                imageRead.setImage(ImageIO.read(files[i]));
                String extension = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1);
                imageRead.setFileExtension(extension);
                imageRead.setInputStream(new FileInputStream(files[i]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageRead;
    }

    /**
     * 读取模板图
     * 
     * @param path
     * @return
     */
    public ImageRead readTemplateImage(String path) {
        ImageRead templateImageFile = null;

        try {
            if (templateImageFile != null) {
                return templateImageFile;
            }
            templateImageFile = new ImageRead();
            if(verificationImagePathPrefix.indexOf("classpath") >= 0) {
                ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                Resource resource = resolver.getResource(path);
                if (resource == null) {
                    throw new RuntimeException("not found template image");
                }
                templateImageFile.setImage(ImageIO.read(resource.getInputStream()));
                String extension = resource.getFilename().substring(resource.getFilename().lastIndexOf(".") + 1);
                templateImageFile.setFileExtension(extension);
                templateImageFile.setInputStream(resource.getInputStream());
            } else {
                File file = new File(path);
                templateImageFile.setImage(ImageIO.read(file));
                String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                templateImageFile.setInputStream(new FileInputStream(file));
                templateImageFile.setFileExtension(extension);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return templateImageFile;
    }

    /**
     * 滑动验证码验证方法
     *
     * @param x x轴坐标
     * @param y y轴坐标
     * @return 滑动验证码验证状态
     */
    @Override
    public boolean checkCaptchaResult(String x, String y, String uuid) {
        int threshold = 5;

        try {
            // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // CaptchaVo captchaVo = (CaptchaVo) request.getSession().getAttribute("captchaVo");
            // String originX = redisUtil.get(uuid);
            CaptchaVo captchaVo = (CaptchaVo) redisTemplate.opsForValue().get(uuid);
            Integer originX = captchaVo.getX();
            System.out.println("redis-get:" + captchaVo.getX());
            if (originX != null) {
                // if ((Math.abs(Integer.parseInt(x) - captchaVo.getX()) <= threshold) && y.equals(String.valueOf(captchaVo.getY()))) {
                if ((Math.abs(Integer.parseInt(x) - originX) <= threshold)) {
                    System.out.println("验证成功");
                    return true;
                } else {
                    System.out.println("验证失败");
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
}
