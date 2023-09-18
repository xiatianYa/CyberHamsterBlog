package com.hncpu.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncpu.blog.entity.TbBlogTagsRelationEntity;
import com.hncpu.blog.entity.TbTagsEntity;
import com.hncpu.blog.mapper.TbBlogTagsRelationMapper;
import com.hncpu.blog.service.TbBlogTagsRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbBlogTagsRelationServiceImpl extends ServiceImpl<TbBlogTagsRelationMapper, TbBlogTagsRelationEntity> implements TbBlogTagsRelationService {
    @Autowired
    private TbBlogTagsRelationMapper tbBlogTagsRelationMapper;
    /** 根据文章ID 查询标签列表 */
    @Override
    public List<TbTagsEntity> getTagsByBlogId(Integer BlogId) {
        return tbBlogTagsRelationMapper.getTagsByBlogId(BlogId);
    }
}
