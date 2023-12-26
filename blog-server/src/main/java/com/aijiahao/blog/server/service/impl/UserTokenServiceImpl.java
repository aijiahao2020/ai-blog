package com.aijiahao.blog.server.service.impl;

import com.aijiahao.blog.common.utils.R;
import com.aijiahao.blog.server.dao.UserTokenDao;
import com.aijiahao.blog.server.entity.UserTokenEntity;
import com.aijiahao.blog.server.oauth2.TokenGenerator;
import com.aijiahao.blog.server.service.UserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author aijiahao
 * @create 2023/12/13  22:15
 * @description
 */
@Service("userTokenService")
public class UserTokenServiceImpl implements UserTokenService {

    @Resource
    private UserTokenDao userTokenDao;
    
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;
    
    @Override
    public R createToken(Long userId) {
        // 生成一个tokenTokenGenerator
        String token = TokenGenerator.generateValue();
        System.out.println("token:" + token);
        // 当前时间
        Date now = new Date();
        
        // 过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        UserTokenEntity tokenEntity = userTokenDao.selectByUserId(userId);
        if(tokenEntity == null) {
            tokenEntity = new UserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            // 保存token
            userTokenDao.insertToken(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            userTokenDao.updateByUserId(tokenEntity);
        }
        R r = R.ok().put("token", token).put("expire", EXPIRE);

        return r;
    }

    @Override
    public void logout(Long userId) {
        userTokenDao.deleteByUserId(userId);
    }
}
