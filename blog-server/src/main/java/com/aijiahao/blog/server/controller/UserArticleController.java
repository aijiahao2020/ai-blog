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

import com.aijiahao.blog.server.entity.UserArticleEntity;
import com.aijiahao.blog.server.service.UserArticleService;



/**
 * 用户与文章对应表
 *
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@RestController
@RequestMapping("server/userarticle")
public class UserArticleController {
    @Autowired
    private UserArticleService userArticleService;

    /**
     * 列表
     */
    // @RequestMapping("/list")
    // //@RequiresPermissions("server:userarticle:list")
    // public R list(@RequestParam Map<String, Object> params){
    //     PageUtils page = userArticleService.queryPage(params);
    //
    //     return R.ok().put("page", page);
    // }


    /**
     * 信息
     */
    // @RequestMapping("/info/{id}")
    // //@RequiresPermissions("server:userarticle:info")
    // public R info(@PathVariable("id") Long id){
	// 	UserArticleEntity userArticle = userArticleService.getById(id);
    //
    //     return R.ok().put("userArticle", userArticle);
    // }

    /**
     * 保存
     */
    // @RequestMapping("/save")
    // //@RequiresPermissions("server:userarticle:save")
    // public R save(@RequestBody UserArticleEntity userArticle){
	// 	userArticleService.save(userArticle);
    //
    //     return R.ok();
    // }

    /**
     * 修改
     */
    // @RequestMapping("/update")
    // //@RequiresPermissions("server:userarticle:update")
    // public R update(@RequestBody UserArticleEntity userArticle){
	// 	userArticleService.updateById(userArticle);
    //
    //     return R.ok();
    // }

    /**
     * 删除
     */
    // @RequestMapping("/delete")
    // //@RequiresPermissions("server:userarticle:delete")
    // public R delete(@RequestBody Long[] ids){
	// 	userArticleService.removeByIds(Arrays.asList(ids));
    //
    //     return R.ok();
    // }

}
