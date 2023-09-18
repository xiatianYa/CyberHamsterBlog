package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.entity.TbBlogTagsRelationEntity;
import com.hncpu.blog.entity.TbTagsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TbBlogTagsRelationService extends IService<TbBlogTagsRelationEntity> {
    List<TbTagsEntity> getTagsByBlogId(Integer BlogId);
}
