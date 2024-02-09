package com.hncpu.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.dto.TbMessageQueryParam;
import com.hncpu.blog.entity.TbMessageEntity;
import com.hncpu.blog.service.TbMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/message")
public class TbMessageController {
    @Autowired
    private TbMessageService tbMessageService;
    @PostMapping
    @Cacheable(value = "MessageList",key = "#param.type")
    public ApiResult<Page> getMessageList(@RequestBody TbMessageQueryParam param){
        return ApiResult.success(tbMessageService.getMessageList(param));
    }
    @PostMapping("/insertMessage")
    @CacheEvict(cacheNames = {"MessageList","MessageCount"},allEntries = true)
    public ApiResult<String> insertMessage(@RequestBody TbMessageEntity tbMessageEntity){
        int count =tbMessageService.insertMessage(tbMessageEntity);
        if (count>0){
            return ApiResult.success("添加留言成功");
        }else{
            return ApiResult.success("添加留言失败");
        }
    }
    @GetMapping("/addLike/{Id}")
    @CacheEvict(cacheNames = {"MessageList"},allEntries = true)
    public ApiResult<String> addLike(@PathVariable Integer Id){
        tbMessageService.addLike(Id);
        return ApiResult.success("");
    }
    @GetMapping("/getMessageCount")
    @Cacheable(value = "MessageCount")
    public ApiResult<Long> getMessageCount(){
        return ApiResult.success(tbMessageService.count());
    }
}
