package com.hncpu.blog.controller;

import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.entity.TbUserInfoEntity;
import com.hncpu.blog.service.TbUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/userInfo")
public class TbUserInfoController {
    @Autowired
    private TbUserInfoService tbUserInfoService;
    @GetMapping
    public ApiResult<TbUserInfoEntity> getUserInfo(){
        return ApiResult.success(tbUserInfoService.getUserInfo());
    }
}
