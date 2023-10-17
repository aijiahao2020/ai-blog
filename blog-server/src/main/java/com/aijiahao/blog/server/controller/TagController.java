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

import com.aijiahao.blog.server.entity.TagEntity;
import com.aijiahao.blog.server.service.TagService;



/**
 * 博客标签表
 *
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@RestController
@RequestMapping("server/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("server:tag:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tagService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{tagId}")
    //@RequiresPermissions("server:tag:info")
    public R info(@PathVariable("tagId") Integer tagId){
		TagEntity tag = tagService.getById(tagId);

        return R.ok().put("tag", tag);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("server:tag:save")
    public R save(@RequestBody TagEntity tag){
		tagService.save(tag);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("server:tag:update")
    public R update(@RequestBody TagEntity tag){
		tagService.updateById(tag);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("server:tag:delete")
    public R delete(@RequestBody Integer[] tagIds){
		tagService.removeByIds(Arrays.asList(tagIds));

        return R.ok();
    }

}
