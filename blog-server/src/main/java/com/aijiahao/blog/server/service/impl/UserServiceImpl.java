package com.aijiahao.blog.server.service.impl;

import com.aijiahao.blog.common.utils.PageUtils;
import com.aijiahao.blog.common.utils.Query;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.aijiahao.blog.server.dao.UserDao;
import com.aijiahao.blog.server.entity.UserEntity;
import com.aijiahao.blog.server.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    /**
     * 分页查询
     */
    // @Override
    // public PageUtils queryPage(Map<String, Object> params) {
    //     IPage<UserEntity> page = this.page(
    //             new Query<UserEntity>().getPage(params),
    //             new QueryWrapper<UserEntity>()
    //     );
    //
    //     return new PageUtils(page);
    // }

    /**
     * 根据用户名，查询系统用户
     */
    @Override
    public UserEntity queryByUserName(String username) {
        return userDao.selectByUserName(username);
    }

    /**
     * 根据用户id，查询系统用户
     */
    @Override
    public UserEntity queryByUserId(Long userId) {
        return userDao.selectByUserId(userId);
    }


    /**
     * 保存用户
     */
    @Override
    @Transactional
    public boolean saveUser(UserEntity user) {
        Date currentTime = new Date();
        user.setCreateTime(currentTime);
        user.setUpdateTime(currentTime);
        // sha256加密
        String salt = RandomStringUtils.randomAlphabetic(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        user.setStatus(1);
        return userDao.insertUser(user);
    }

    /**
     * 更新用户
     */
    @Override
    public boolean updateByUserId(UserEntity user) {
        return userDao.updateByUserId(user);
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        return userDao.deleteByUserId(userId);
    }

    @Override
    public boolean disableByUserId(Long userId) {
        Date currentTime = new Date();
        return userDao.disableByUserId(userId, currentTime);
    }

    @Override
    public boolean enableByUserId(Long userId) {
        Date currentTime = new Date();
        return userDao.enableByUserId(userId, currentTime);
    }

}
