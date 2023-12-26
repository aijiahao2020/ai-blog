package com.aijiahao.blog.server.controller;

import com.aijiahao.blog.common.utils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aijiahao
 * @create 2023/9/12  21:57
 */

@RestController
@RequestMapping("/home")
@Api(tags = "首页展示模块", value = "首页展示")
public class HomeController {
    
    
    
    @GetMapping("/getRecommendBlogList")
    public R getRecommendList() {
        return R.ok();
    }
    
    @GetMapping("/getTypeList")
    public R getTypeList() {
        return R.ok();
    }
}
