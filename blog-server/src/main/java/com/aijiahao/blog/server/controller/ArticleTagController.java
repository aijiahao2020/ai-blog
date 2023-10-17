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

import com.aijiahao.blog.server.entity.ArticleTagEntity;
import com.aijiahao.blog.server.service.ArticleTagService;




/**
 * 博文标签对应表
 *
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@RestController
@RequestMapping("server/articletag")
public class ArticleTagController {
    @Autowired
    private ArticleTagService articleTagService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("server:articletag:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articleTagService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("server:articletag:info")
    public R info(@PathVariable("id") Long id){
		ArticleTagEntity articleTag = articleTagService.getById(id);

        return R.ok().put("articleTag", articleTag);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("server:articletag:save")
    public R save(@RequestBody ArticleTagEntity articleTag){
		articleTagService.save(articleTag);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("server:articletag:update")
    public R update(@RequestBody ArticleTagEntity articleTag){
		articleTagService.updateById(articleTag);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("server:articletag:delete")
    public R delete(@RequestBody Long[] ids){
		articleTagService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
