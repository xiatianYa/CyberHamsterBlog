package com.hncpu.blog.controller;

import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.entity.TbReplyEntity;
import com.hncpu.blog.service.TbReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/reply")
public class TbReplyController {
    @Autowired
    private TbReplyService tbReplyService;
    @PostMapping("/insertReply")
    public ApiResult<String> insertReply(@RequestBody TbReplyEntity replyEntity){
        int count = tbReplyService.insertReply(replyEntity);
        if (count>0){
            return ApiResult.success("添加成功");
        }else{
            return ApiResult.error("添加失败");
        }
    }
}
