package com.aijiahao.blog.admin.modules.sys.service;

import com.aijiahao.blog.admin.modules.common.utils.R;
import com.aijiahao.blog.admin.modules.sys.entity.SysUserTokenEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author aijiahao
 * @create 2023/8/23  12:37
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

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
