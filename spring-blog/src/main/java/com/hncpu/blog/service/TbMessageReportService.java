package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.dto.TbMessageReportDTO;
import com.hncpu.blog.entity.TbMessageReportEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TbMessageReportService extends IService<TbMessageReportEntity> {
    int addMessageReport(Integer id);

    void deleteEssayByIds(List<Integer> ids);

    Page getMessageReportList(Integer pageNum, Integer pageSize);

    void yesMessageReport(List<Integer> ids);
}
