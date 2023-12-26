package com.aijiahao.blog.server.utils.captcha;

import com.aijiahao.blog.server.vo.CaptchaVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Random;

/**
 * @author aijiahao
 * @create 2023/11/28  21:13
 * @description 图片验证工具类
 */
public class ImageVerificationUtil {
    // private static final Logger log = LoggerFactory.getLogger(ImageVerificationUtil.class);

    /**
     * 默认图片宽度
     */
    private static final int DEFAULT_IMAGE_WIDTH = 280;


    /**
     * 默认图片高度
     */
    private static final int DEFAULT_IMAGE_HEIGHT = 171;


    /**
     * 生成裁剪区域坐标
     * @param verificationImage 源图
     * @param templateImage 模板图
     * @return 裁剪坐标
     */
    public static CaptchaVo generateCutoutCoordinates(BufferedImage verificationImage, BufferedImage templateImage) {
        int x, y;
        CaptchaVo captchaVo = null;

        //  抠图模板宽度
        int templateImageWidth = templateImage.getWidth();
        //  抠图模板高度
        int templateImageHeight = templateImage.getHeight();

        Random random = new Random(System.currentTimeMillis());

        //  取范围内坐标数据，坐标抠图一定要落在源图中，否则会导致程序错误
        x = random.nextInt(DEFAULT_IMAGE_WIDTH - templateImageWidth) % (DEFAULT_IMAGE_WIDTH - templateImageWidth - templateImageWidth + 1) + templateImageWidth;
        y = random.nextInt(DEFAULT_IMAGE_HEIGHT - templateImageHeight) % (DEFAULT_IMAGE_HEIGHT - templateImageHeight - templateImageHeight + 1) + templateImageHeight;
        if (templateImageHeight - DEFAULT_IMAGE_HEIGHT >= 0) {
            y = random.nextInt(10);
        }
        captchaVo = new CaptchaVo();
        captchaVo.setX(x);
        captchaVo.setY(y);
        return captchaVo;
    }
    
    public static CaptchaVo pictureTemplateCutout(ImageRead originImageRead, ImageRead templateImageRead, int x, int y) throws Exception{
        CaptchaVo captchaVo = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        BufferedImage originImage = originImageRead.getImage();
        BufferedImage templateImage = templateImageRead.getImage();
        InputStream inputStream = originImageRead.getInputStream();
        String originImageFileType = originImageRead.getFileExtension();
        String templateImageFileType = templateImageRead.getFileExtension();

        try {
            int templateImageWidth = templateImage.getWidth();
            int templateImageHeight = templateImage.getHeight();
            //  切块图   根据模板图尺寸创建一张透明图片
            BufferedImage cutoutImage = new BufferedImage(templateImageWidth, templateImageHeight, templateImage.getType());

            //  根据坐标获取裁剪区域
            BufferedImage cutArea = getCutArea(x, y, templateImageWidth, templateImageHeight, inputStream, originImageFileType);
            //  根据模板图片切图
            cutoutImage = cutoutImageByTemplateImage(cutArea, templateImage, cutoutImage);

            //  图片绘图
            int bold = 5;
            Graphics2D graphics = cutoutImage.createGraphics();
            graphics.setBackground(Color.white);
            //  设置抗锯齿属性
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setStroke(new BasicStroke(bold, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
            graphics.drawImage(cutoutImage, 0, 0, null);
            graphics.dispose();

            //  原图生成遮罩
            BufferedImage shadeImage = generateShadeByTemplateImage(originImage, templateImage, x, y);

            captchaVo = new CaptchaVo();
            byteArrayOutputStream = new ByteArrayOutputStream();
            //  图片转为二进制字符串
            ImageIO.write(originImage, originImageFileType, byteArrayOutputStream);
            byte[] originImageBytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.reset();
            //  图片加密成base64字符串
            String originImageString = Base64Utils.encodeToString(originImageBytes);
            // captchaVo.setOriginImage(originImageString);

            ImageIO.write(shadeImage, templateImageFileType, byteArrayOutputStream);
            //  图片转为二进制字符串
            byte[] shadeImageBytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.reset();
            //  图片加密成base64字符串
            String shadeImageString = Base64Utils.encodeToString(shadeImageBytes);
            captchaVo.setShadeImage(shadeImageString);

            ImageIO.write(cutoutImage, templateImageFileType, byteArrayOutputStream);
            //  图片转为二进制字符串
            byte[] cutoutImageBytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.reset();
            //  图片加密成base64字符串
            String cutoutImageString = Base64Utils.encodeToString(cutoutImageBytes);
            captchaVo.setCutoutImage(cutoutImageString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return captchaVo;

    }

    /**
     * 根据模板图生成遮罩图
     * @param originImage 源图
     * @param templateImage 模板图
     * @param x 裁剪区域X轴
     * @param y 裁剪区域Y轴
     * @return 遮罩图
     * @throws IOException 数据转换异常
     */
    private static BufferedImage generateShadeByTemplateImage(BufferedImage originImage, BufferedImage templateImage, int x, int y) throws IOException {
        //  根据原图，创建支持alpha通道的rgb图片
        BufferedImage shadeImage = new BufferedImage(originImage.getWidth(), originImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //  原图片矩阵
        int[][] originImageMatrix = getMatrix(originImage);
        //  模板图片矩阵
        int[][] templateImageMatrix = getMatrix(templateImage);

        //  将原图的像素拷贝到遮罩图
        for (int i = 0; i < originImageMatrix.length; i++) {
            for (int j = 0; j < originImageMatrix[0].length; j++) {
                int rgb = originImage.getRGB(i, j);
                //  获取rgb色度
                int r = (0xff & rgb);
                int g = (0xff & (rgb >> 8));
                int b = (0xff & (rgb >> 16));
                //  无透明处理
                rgb = r + (g << 8) + (b << 16) + (255 << 24);
                shadeImage.setRGB(i, j, rgb);
            }
        }

        //  对遮罩图根据模板像素进行处理
        for (int i = 0; i < templateImageMatrix.length; i++) {
            for (int j = 0; j < templateImageMatrix[0].length; j++) {
                int rgb = templateImage.getRGB(i, j);

                //对源文件备份图像(x+i,y+j)坐标点进行透明处理
                if (rgb != 16777215 && rgb < 0) {
                    int originRGB = shadeImage.getRGB(x + i, y + j);
                    int r = (0xff & originRGB);
                    int g = (0xff & (originRGB >> 8));
                    int b = (0xff & (originRGB >> 16));


                    originRGB = r + (g << 8) + (b << 16) + (140 << 24);

                    //  对遮罩透明处理
                    shadeImage.setRGB(x + i, y + j, originRGB);
                    //  设置遮罩颜色
//                    shadeImage.setRGB(x + i, y + j, originRGB);

                }

            }
        }

        return shadeImage;
    }


    /**
     * 根据模板图抠图
     * @param cutArea  裁剪区域图
     * @param templateImage  模板图
     * @param cutoutImage 裁剪图
     * @return 裁剪图
     */
    private static BufferedImage cutoutImageByTemplateImage(BufferedImage cutArea, BufferedImage templateImage, BufferedImage cutoutImage) {
        //  获取裁剪区域图片矩阵
        int[][] cutAreaMatrix = getMatrix(cutArea);
        //  获取模板图片矩阵
        int[][] templateImageMatrix = getMatrix(templateImage);

        //  将模板图非透明像素设置到剪切图中
        for (int i = 0; i < templateImageMatrix.length; i++) {
            for (int j = 0; j < templateImageMatrix[0].length; j++) {
                int rgb = templateImageMatrix[i][j];
                if (rgb != 16777215 && rgb < 0) {
                    cutoutImage.setRGB(i, j, cutArea.getRGB(i, j));
                }
            }
        }

        return cutoutImage;
    }

    /**
     * 图片生成图像矩阵
     * @param bufferedImage  图片源
     * @return 图片矩阵
     */
    private static int[][] getMatrix(BufferedImage bufferedImage) {
        int[][] matrix = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                matrix[i][j] = bufferedImage.getRGB(i, j);
            }
        }
        return matrix;
    }

    /**
     * 获取裁剪区域
     * @param x 裁剪区域X轴
     * @param y 裁剪区域Y轴
     * @param templateImageWidth  模板图宽度
     * @param templateImageHeight 模板图高度
     * @param originImageType 源图扩展名
     * @return
     */
    private static BufferedImage getCutArea(int x, int y, int templateImageWidth, int templateImageHeight, InputStream inputStream, String originImageType) throws Exception {
        ImageInputStream imageInputStream = null;

        try {

            // Iterator<ImageReader> imageReaderIterator = ImageIO.getImageReadersByFormatName(originImageType);
            Iterator<ImageReader> imageReaderIterator = ImageIO.getImageReadersBySuffix(originImageType);;
            ImageReader imageReader = imageReaderIterator.next();

            //  获取图片流
            imageInputStream = ImageIO.createImageInputStream(inputStream);
            //  图片输入流顺序读写
            imageReader.setInput(imageInputStream, true);

            ImageReadParam imageReadParam = imageReader.getDefaultReadParam();

            //  根据坐标生成矩形
            Rectangle rectangle = new Rectangle(x, y, templateImageWidth, templateImageHeight);
            imageReadParam.setSourceRegion(rectangle);
            BufferedImage cutAreaImage = imageReader.read(0, imageReadParam);
            return cutAreaImage;
        } catch (IOException e) {
            // log.error(e.getMessage(), e);
            e.printStackTrace();
            throw new Exception();
        } finally {
            try {
                imageInputStream.close();
            } catch (IOException e) {
                // log.error(e.getMessage(), e);
                e.printStackTrace();
                throw new Exception();
            }
        }
    }

    /**
     * 切块图描边
     * @param captchaVo 图片容器
     * @param borderImage 描边图
     * @param borderImageFileType 描边图类型
     * @return 图片容器
     * @throws Exception 图片描边异常
     */
    public static CaptchaVo cutoutImageEdge(CaptchaVo captchaVo, BufferedImage borderImage, String borderImageFileType) throws Exception{

        ByteArrayInputStream byteArrayInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            String cutoutImageString = captchaVo.getCutoutImage();
            //  图片解密成二进制字符创
            byte[] bytes = Base64Utils.decodeFromString(cutoutImageString);
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            //  读取图片
            BufferedImage cutoutImage = ImageIO.read(byteArrayInputStream);
            //  获取模板边框矩阵， 并进行颜色处理
            int[][] borderImageMatrix = getMatrix(borderImage);
            for (int i = 0; i < borderImageMatrix.length; i++) {
                for (int j = 0; j < borderImageMatrix[0].length; j++) {
                    int rgb = borderImage.getRGB(i, j);
                    if (rgb < 0) {
                        cutoutImage.setRGB(i, j , -7237488);
                    }
                }
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(cutoutImage, borderImageFileType, byteArrayOutputStream);
            //  新模板图描边处理后转成二进制字符串
            byte[] cutoutImageBytes = byteArrayOutputStream.toByteArray();
            //  二进制字符串加密成base64字符串
            String cutoutImageStr = Base64Utils.encodeToString(cutoutImageBytes);
            captchaVo.setCutoutImage(cutoutImageStr);
        } catch (IOException e) {
            // log.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            try {
                byteArrayInputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return captchaVo;
    }

}
