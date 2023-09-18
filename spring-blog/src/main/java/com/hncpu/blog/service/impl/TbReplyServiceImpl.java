package com.hncpu.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncpu.blog.entity.TbReplyEntity;
import com.hncpu.blog.mapper.TbReplyMapper;
import com.hncpu.blog.service.TbReplyService;
import com.hncpu.blog.utils.BlogProperty;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TbReplyServiceImpl extends ServiceImpl<TbReplyMapper, TbReplyEntity> implements TbReplyService {
    @Override
    public int insertReply(TbReplyEntity replyEntity) {
        replyEntity.setReplyAvatar(BlogProperty.AVATAR);
        replyEntity.setCreateTime(new Date());
        return baseMapper.insert(replyEntity);
    }
}
