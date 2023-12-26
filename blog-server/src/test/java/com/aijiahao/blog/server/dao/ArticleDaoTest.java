package com.aijiahao.blog.server.dao;

import com.aijiahao.blog.server.entity.ArticleEntity;
import com.aijiahao.blog.server.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author aijiahao
 * @create 2023/12/26  10:47
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleDaoTest {

    @Resource
    private ArticleDao articleDao;

    /**
     * 测试查询所有文章 ok
     */
    @Test
    public void selectAllTest() {
        List<ArticleEntity> articleEntities = articleDao.selectAll();
        for(ArticleEntity articleEntity : articleEntities) {
            System.out.println(articleEntity);
        }
    }

    /**
     * 测试发表文章总数 ok
     */
    @Test
    public void selectNumberByPublishedTest() {
        Integer number = articleDao.selectNumberByPublished();
        System.out.println(number);
    }

    /**
     * 测试插入文章 ok
     */
    @Test
    public void insertArticleTest() {
        Date currentTime = new Date();
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle("文章" + currentTime);
        articleEntity.setDescription("本文是对边缘环境下的任务卸载与服务缓存策略进行研究，主要采用多智能体强化学习和分层强化学习");
        articleEntity.setPublished(0);
        String content = "# 绪论 \n ## 1. 研究背景与意义 \n ## 2. 研究内容 \n # 相关技术 \n # 基于多智能体强化学习 \n # 分层强化学习 \n";
        articleEntity.setContent(content);
        articleEntity.setViews(54888);
        articleEntity.setThumbs(23888);
        articleEntity.setComments(8388);
        
        articleEntity.setCreateTime(currentTime);
        articleEntity.setUpdateTime(currentTime);
        articleDao.insertArticle(articleEntity);
        System.out.println("success");
        
    }
}
