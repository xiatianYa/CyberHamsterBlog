package com.hncpu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncpu.blog.dto.TbBlogCountByYearDTO;
import com.hncpu.blog.entity.TbBlogCountEntity;
import com.hncpu.blog.entity.TbBlogEntity;
import com.hncpu.blog.entity.TbClassifyEntity;
import com.hncpu.blog.mapper.TbBlogCountMapper;
import com.hncpu.blog.service.TbBlogCountService;
import com.hncpu.blog.service.TbBlogService;
import com.hncpu.blog.service.TbClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TbBlogCountServiceImpl extends ServiceImpl<TbBlogCountMapper, TbBlogCountEntity> implements TbBlogCountService {
    @Autowired
    private TbBlogService tbBlogService;
    @Override
    public List<TbBlogEntity> getBlogAccessList() {
        Page<TbBlogCountEntity> page=new Page<>(0,10);
        LambdaQueryWrapper<TbBlogCountEntity> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByDesc(TbBlogCountEntity::getCountNumber);
        baseMapper.selectPage(page, wrapper);
        //根据排行前10的blogId 查询Blog列表
        List<TbBlogEntity> blogList = page.getRecords().stream().map(item -> {
            Integer blogId = item.getBlogId();
            LambdaQueryWrapper<TbBlogEntity> blogWrapper = new LambdaQueryWrapper<>();
            blogWrapper.eq(TbBlogEntity::getBlogId, blogId);
            blogWrapper.eq(TbBlogEntity::getBlogStatus,0);
            TbBlogEntity blog = tbBlogService.getOne(blogWrapper);
            return blog;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return blogList;
    }
}
