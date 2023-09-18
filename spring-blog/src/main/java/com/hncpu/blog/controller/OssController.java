package com.hncpu.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;
    //上传头像的方法
    @PostMapping("/uploadOssFile")
    public ApiResult<String> uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return ApiResult.success(url);
    }
}
