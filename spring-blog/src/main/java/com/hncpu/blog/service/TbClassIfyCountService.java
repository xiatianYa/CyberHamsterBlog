package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.dto.TbBlogCountByYearDTO;
import com.hncpu.blog.entity.TbClassIfyCountEntity;

public interface TbClassIfyCountService extends IService<TbClassIfyCountEntity> {
    TbBlogCountByYearDTO getBlogCountByYear();
}
