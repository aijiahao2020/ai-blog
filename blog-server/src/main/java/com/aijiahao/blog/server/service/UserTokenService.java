package com.aijiahao.blog.server.service;

import com.aijiahao.blog.common.utils.R;
import com.aijiahao.blog.server.entity.UserTokenEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author aijiahao
 * @create 2023/12/13  22:13
 * @description
 */

public interface UserTokenService {
    /**
     * 生成token
     *
     * @param userId
     * @return
     */
    R createToken(Long userId);

    /**
     * 退出，修改token
     *
     * @param userId
     */
    void logout(Long userId);
}
