package com.aijiahao.blog.admin.TestDao;

import com.aijiahao.blog.admin.modules.sys.entity.SysUserEntity;
import com.aijiahao.blog.admin.modules.sys.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author aijiahao
 * @create 2023/9/10  16:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSysUserDao {
    @Autowired
    private SysUserService sysUserService;
    @Test
    public void testQueryByUserName(){
        SysUserEntity userEntity = sysUserService.queryByUserName("admin");
        System.out.println(userEntity);
    }
    

}
