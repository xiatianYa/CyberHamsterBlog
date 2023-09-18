package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.entity.TbTagsEntity;
import com.hncpu.blog.exception.BlogException;

import java.util.List;

public interface TbTagsService extends IService<TbTagsEntity> {
    List<TbTagsEntity> queryAll();
    Integer queryCount();

    TbTagsEntity queryTagByTagId(Integer id);

    int updateTagByTagId(TbTagsEntity tbTags);

    int deleteTagByTagId(Integer id) throws BlogException;

    int insertTag(TbTagsEntity tbTags);

    Page<TbTagsEntity> selectTagList(int pageNum, int pageSize);
}
