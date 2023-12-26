package com.aijiahao.blog.server.service;

import com.aijiahao.blog.common.utils.PageUtils;
import com.aijiahao.blog.server.vo.ArticleVo;
import com.baomidou.mybatisplus.extension.service.IService;

import com.aijiahao.blog.server.entity.ArticleEntity;

import java.util.List;
import java.util.Map;

/**
 * 博文表
 *
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
public interface ArticleService {

    /**
     * 查询所有article
     * @return
     */
    List<ArticleEntity> queryAllArticle();
    // PageUtils queryPage(Map<String, Object> params);


    /**
     * 根据articleId 查询article信息
     */
    ArticleEntity queryByArticleId(Long articleId);

    /**
     * 查询所有已发表的文章
     * @return
     */
    Integer queryNumberByPublished();
}

