package com.hncpu.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.dto.TbBlogCountByYearDTO;
import com.hncpu.blog.entity.TbBlogEntity;
import com.hncpu.blog.service.TbBlogCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/blogCount")
public class TbBlogCountController {
    @Autowired
    private TbBlogCountService tbBlogCountService;
    @SaCheckPermission("user.get")
    @GetMapping
    public ApiResult<List<TbBlogEntity>> getBlogAccessList(){
        return ApiResult.success(tbBlogCountService.getBlogAccessList());
    }
}
