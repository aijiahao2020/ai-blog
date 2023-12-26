package com.aijiahao.blog.server.dao;

import com.aijiahao.blog.server.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author aijiahao
 * @create 2023/12/26  10:47
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    /**
     * 测试查询所有用户 ok
     */
    @Test
    public void selectAllTest() {
        List<UserEntity> userEntities = userDao.selectAll();
        for(UserEntity userEntity : userEntities) {
            System.out.println(userEntity);
        }
    }
}
