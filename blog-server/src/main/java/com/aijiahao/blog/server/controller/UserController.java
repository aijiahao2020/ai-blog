package com.aijiahao.blog.server.controller;

import java.util.Arrays;
import java.util.Map;

import com.aijiahao.blog.common.utils.PageUtils;
import com.aijiahao.blog.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aijiahao.blog.server.entity.UserEntity;
import com.aijiahao.blog.server.service.UserService;



/**
 * 用户表
 *
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    // @RequestMapping("/list")
    //@RequiresPermissions("server:user:list")
    // public R list(@RequestParam Map<String, Object> params){
    //     PageUtils page = userService.queryPage(params);
    //
    //     return R.ok().put("page", page);
    // }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("server:user:info")
    public R info(@PathVariable("userId") Long userId){
		UserEntity user = userService.queryByUserId(userId);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("server:user:save")
    public R save(@RequestBody UserEntity user){
		userService.saveUser(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("server:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateByUserId(user);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("server:user:delete")
    public R delete(@RequestBody Long userId){
		userService.deleteByUserId(userId);

        return R.ok();
    }

}
