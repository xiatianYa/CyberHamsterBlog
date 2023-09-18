package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.entity.TbReplyEntity;

public interface TbReplyService extends IService<TbReplyEntity> {
    int insertReply(TbReplyEntity replyEntity);
}
