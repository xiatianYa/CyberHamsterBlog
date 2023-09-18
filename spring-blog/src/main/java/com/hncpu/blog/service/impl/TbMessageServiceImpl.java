package com.hncpu.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hncpu.blog.dto.TbMessageQueryDTO;
import com.hncpu.blog.dto.TbMessageQueryParam;
import com.hncpu.blog.entity.TbMessageEntity;
import com.hncpu.blog.entity.TbReplyEntity;
import com.hncpu.blog.mapper.TbMessageMapper;
import com.hncpu.blog.service.TbMessageService;
import com.hncpu.blog.service.TbReplyService;
import com.hncpu.blog.utils.BlogProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TbMessageServiceImpl extends ServiceImpl<TbMessageMapper, TbMessageEntity> implements TbMessageService {
    @Autowired
    private TbReplyService tbReplyService;
    @Override
    public Page getMessageList(TbMessageQueryParam param) {
        LambdaQueryWrapper<TbMessageEntity> MessageWrapper=new LambdaQueryWrapper<>();
        if (param.getType()!=null && param.getType()!=-1){
            MessageWrapper.eq(TbMessageEntity::getMessageType,param.getType());
        }
        Page page=new Page<>(param.getPageNum(), param.getPageSize());
        baseMapper.selectPage(page,MessageWrapper);
        List<TbMessageEntity> records = page.getRecords();
        List<TbMessageQueryDTO> messageDto = records.stream().map(item -> {
            TbMessageQueryDTO tbMessageQueryDTO = new TbMessageQueryDTO();
            BeanUtils.copyProperties(item, tbMessageQueryDTO);
            LambdaQueryWrapper<TbReplyEntity> ReplyWrapper = new LambdaQueryWrapper<>();
            ReplyWrapper.eq(TbReplyEntity::getMessageId, item.getMessageId());
            List<TbReplyEntity> ReplyList = tbReplyService.list(ReplyWrapper);
            tbMessageQueryDTO.setReplyList(ReplyList);
            return tbMessageQueryDTO;
        }).collect(Collectors.toList());
        page.setRecords(messageDto);
        return page;
    }

    @Override
    public int insertMessage(TbMessageEntity tbMessageEntity) {
        tbMessageEntity.setMessageAvatar(BlogProperty.AVATAR);
        tbMessageEntity.setMessageLike(0);
        tbMessageEntity.setCreateTime(new Date());
        return baseMapper.insert(tbMessageEntity);
    }

    @Override
    public void addLike(Integer id) {
        LambdaQueryWrapper<TbMessageEntity> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbMessageEntity::getMessageId,id);
        TbMessageEntity tbMessageEntity = baseMapper.selectOne(wrapper);
        tbMessageEntity.setMessageLike(tbMessageEntity.getMessageLike()+1);
        baseMapper.updateById(tbMessageEntity);
    }
}
