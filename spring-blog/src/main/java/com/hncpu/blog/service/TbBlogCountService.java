package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.dto.TbBlogCountByYearDTO;
import com.hncpu.blog.entity.TbBlogCountEntity;
import com.hncpu.blog.entity.TbBlogEntity;

import java.util.List;

public interface TbBlogCountService extends IService<TbBlogCountEntity> {

    List<TbBlogEntity> getBlogAccessList();
}
