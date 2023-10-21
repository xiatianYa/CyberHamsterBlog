package com.hncpu.blog.controller;

import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.dto.TbBlogCountByYearDTO;
import com.hncpu.blog.service.TbClassIfyCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/classIfyCount")
public class TbClassIfyCountController {
    @Autowired
    private TbClassIfyCountService tbClassIfyCountService;
    /**
     * 获取年统计访问量
     */
    @GetMapping
    @Cacheable(cacheNames = "BlogCountByYear")
    public ApiResult<TbBlogCountByYearDTO> getBlogCountByYear(){
        return ApiResult.success(tbClassIfyCountService.getBlogCountByYear());
    }
}
