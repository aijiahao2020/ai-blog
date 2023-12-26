package com.aijiahao.blog.server.dao;

import com.aijiahao.blog.server.entity.ArticleEntity;
import com.aijiahao.blog.server.entity.UserArticleEntity;
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
public class UserArticleDaoTest {

    @Resource
    private UserArticleDao userArticleDao;

    /**
     * 测试插入一条user-article关联数据
     */
    @Test
    public void insertOneTest() {
        UserArticleEntity userArticleEntity = new UserArticleEntity();
        userArticleEntity.setUserId(5L);
        userArticleEntity.setArticleId(1L);
        userArticleDao.insertOne(userArticleEntity);
        System.out.println("success");
    }
}
