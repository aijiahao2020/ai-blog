package com.aijiahao.blog.server.dao;

import com.aijiahao.blog.server.entity.CommentEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author aijiahao
 * @create 2023/12/26  18:53
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentDaoTest {
    
    @Resource
    private CommentDao commentDao;
    
    @Test
    public void insertCommentTest() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserId(5L);
        commentEntity.setArticleId(1L);
        commentEntity.setThumbs(214);
        commentEntity.setContent("确实");
        Date date = new Date();
        commentEntity.setCreateTime(date);
        commentEntity.setParentCommentId(0L);
        commentDao.insertComment(commentEntity);
        System.out.println("success");
    }
    
    @Test
    public void selectAllByArticleId() {
        List<CommentEntity> commentEntities = commentDao.selectAllByArticleId(1L);
        for(CommentEntity commentEntity : commentEntities) {
            System.out.println(commentEntity);
        }
    }
}
