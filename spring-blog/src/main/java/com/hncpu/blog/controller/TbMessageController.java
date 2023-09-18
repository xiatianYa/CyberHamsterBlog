package com.hncpu.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.dto.TbMessageQueryParam;
import com.hncpu.blog.entity.TbMessageEntity;
import com.hncpu.blog.service.TbMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/message")
public class TbMessageController {
    @Autowired
    private TbMessageService tbMessageService;
    @PostMapping
    public ApiResult<Page> getMessageList(@RequestBody TbMessageQueryParam param){
        return ApiResult.success(tbMessageService.getMessageList(param));
    }
    @PostMapping("/insertMessage")
    public ApiResult<String> insertMessage(@RequestBody TbMessageEntity tbMessageEntity){
        int count =tbMessageService.insertMessage(tbMessageEntity);
        if (count>0){
            return ApiResult.success("添加成功");
        }else{
            return ApiResult.success("添加失败");
        }
    }
    @GetMapping("/addLike/{Id}")
    public ApiResult<String> addLike(@PathVariable Integer Id){
        tbMessageService.addLike(Id);
        return ApiResult.success("");
    }
    @GetMapping("/getMessageCount")
    public ApiResult<Long> getMessageCount(){
        return ApiResult.success(tbMessageService.count());
    }
}
