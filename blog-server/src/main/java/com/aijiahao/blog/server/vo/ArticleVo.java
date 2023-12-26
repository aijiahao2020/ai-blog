package com.aijiahao.blog.server.vo;

import com.aijiahao.blog.server.entity.ArticleEntity;
import com.aijiahao.blog.server.entity.TagEntity;
import com.aijiahao.blog.server.entity.UserEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author aijiahao
 * @create 2023/12/26  9:48
 * @description 文章信息类，包含
 */
@Data
public class ArticleVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * article 信息
     */
    private ArticleEntity articleEntity;

    /**
     * article 对应的用户信息
     */
    private UserEntity userEntity;

    /**
     * article 对用的标签信息
     */
    private TagEntity tagEntity;

}
