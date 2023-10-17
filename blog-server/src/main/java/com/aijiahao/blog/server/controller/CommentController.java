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

import com.aijiahao.blog.server.entity.CommentEntity;
import com.aijiahao.blog.server.service.CommentService;



/**
 * 评论表
 *
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@RestController
@RequestMapping("server/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("server:comment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{commentId}")
    //@RequiresPermissions("server:comment:info")
    public R info(@PathVariable("commentId") Long commentId){
		CommentEntity comment = commentService.getById(commentId);

        return R.ok().put("comment", comment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("server:comment:save")
    public R save(@RequestBody CommentEntity comment){
		commentService.save(comment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("server:comment:update")
    public R update(@RequestBody CommentEntity comment){
		commentService.updateById(comment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("server:comment:delete")
    public R delete(@RequestBody Long[] commentIds){
		commentService.removeByIds(Arrays.asList(commentIds));

        return R.ok();
    }

}
