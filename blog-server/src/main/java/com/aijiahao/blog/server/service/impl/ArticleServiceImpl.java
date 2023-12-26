package com.aijiahao.blog.server.service.impl;

import com.aijiahao.blog.common.utils.PageUtils;
import com.aijiahao.blog.common.utils.Query;
import com.aijiahao.blog.server.dao.ArticleTagDao;
import com.aijiahao.blog.server.dao.TagDao;
import com.aijiahao.blog.server.dao.UserArticleDao;
import com.aijiahao.blog.server.entity.TagEntity;
import com.aijiahao.blog.server.vo.ArticleVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.aijiahao.blog.server.dao.ArticleDao;
import com.aijiahao.blog.server.entity.ArticleEntity;
import com.aijiahao.blog.server.service.ArticleService;

import javax.annotation.Resource;


@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    
    @Resource
    private ArticleDao articleDao;
    
    @Resource
    private UserArticleDao userArticleDao;


    /**
     * 查询所有文章
     * @return
     */
    @Override
    public List<ArticleEntity> queryAllArticle() {
        //单表查询+代码层组装
        List<ArticleEntity> articleEntities = articleDao.selectAll();

        return articleEntities;
    }

    /**
     * 根据articleId 查询文章内容
     * @param articleId
     * @return
     */
    @Override
    public ArticleEntity queryByArticleId(Long articleId) {
        return articleDao.selectByArticleId(articleId);
    }

    /**
     * 返回文章数
     * @return
     */
    @Override
    public Integer queryNumberByPublished() {
        return articleDao.selectNumberByPublished();
    }

    // @Override
    // public PageUtils queryPage(Map<String, Object> params) {
    //     IPage<ArticleEntity> page = this.page(
    //             new Query<ArticleEntity>().getPage(params),
    //             new QueryWrapper<ArticleEntity>()
    //     );
    //
    //     return new PageUtils(page);
    // }

}
