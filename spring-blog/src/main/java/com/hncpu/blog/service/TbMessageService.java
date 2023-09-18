package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.dto.TbMessageQueryDTO;
import com.hncpu.blog.dto.TbMessageQueryParam;
import com.hncpu.blog.entity.TbMessageEntity;

import java.util.List;

public interface TbMessageService extends IService<TbMessageEntity> {
    Page getMessageList(TbMessageQueryParam param);

    int insertMessage(TbMessageEntity tbMessageEntity);

    void addLike(Integer id);
}
