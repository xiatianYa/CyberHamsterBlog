package com.hncpu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncpu.blog.dto.TbMessageReportDTO;
import com.hncpu.blog.entity.TbMessageEntity;
import com.hncpu.blog.entity.TbMessageReportEntity;
import com.hncpu.blog.entity.TbReplyEntity;
import com.hncpu.blog.mapper.TbMessageReportMapper;
import com.hncpu.blog.service.TbMessageReportService;
import com.hncpu.blog.service.TbMessageService;
import com.hncpu.blog.service.TbReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TbMessageReportServiceImpl extends ServiceImpl<TbMessageReportMapper, TbMessageReportEntity> implements TbMessageReportService {
    @Autowired
    private TbMessageService tbMessageService;
    @Autowired
    private TbReplyService tbReplyService;
    @Override
    public int addMessageReport(Integer id) {
        LambdaQueryWrapper<TbMessageReportEntity> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbMessageReportEntity::getMessageId,id);
        Long count = baseMapper.selectCount(wrapper);
        if (count>0){
            return 0;
        }
        TbMessageEntity message = tbMessageService.getById(id);
        if(message==null){
            return 0;
        }
        TbMessageReportEntity tbMessageReportEntity=new TbMessageReportEntity();
        tbMessageReportEntity.setCreateTime(new Date());
        tbMessageReportEntity.setMessageId(id);
        return baseMapper.insert(tbMessageReportEntity);
    }

    @Override
    public void deleteEssayByIds(List<Integer> ids) {
        for (Integer id:ids){
            TbMessageReportEntity MessageReport = baseMapper.selectById(id);
            if (MessageReport!=null){
                LambdaQueryWrapper<TbReplyEntity> wrapper=new LambdaQueryWrapper<>();
                Integer messageId=MessageReport.getMessageId();
                Integer reportId=MessageReport.getReportId();
                baseMapper.deleteById(reportId);
                wrapper.eq(TbReplyEntity::getMessageId,messageId);
                List<TbReplyEntity> ReplyList = tbReplyService.list(wrapper);
                for (TbReplyEntity reply:ReplyList){
                    tbReplyService.removeById(reply.getReplyId());
                }
                tbMessageService.removeById(messageId);
            }
        }
    }

    @Override
    public Page getMessageReportList(Integer pageNum,Integer pageSize) {
        Page page=new Page<>(pageNum,pageSize);
        baseMapper.selectPage(page,new LambdaQueryWrapper<>());
        List<TbMessageReportEntity> reports = page.getRecords();
        List<TbMessageReportDTO> messageReportDto = reports.stream().map(item -> {
            Integer messageId = item.getMessageId();
            TbMessageEntity message = tbMessageService.getById(messageId);
            TbMessageReportDTO tbMessageReportDTO = new TbMessageReportDTO();
            tbMessageReportDTO.setReportId(item.getReportId());
            tbMessageReportDTO.setReportContent(message.getMessageContent());
            tbMessageReportDTO.setCreateTime(item.getCreateTime());
            return tbMessageReportDTO;
        }).collect(Collectors.toList());
        page.setRecords(messageReportDto);
        return page;
    }

    @Override
    public void yesMessageReport(List<Integer> ids) {
        for (Integer id:ids){
            TbMessageReportEntity MessageReport = baseMapper.selectById(id);
            if (MessageReport!=null){
                baseMapper.deleteById(id);
            }
        }
    }
}
