package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.dto.TbBlogDTO;
import com.hncpu.blog.dto.TbClassIfyByBlogCountDTO;
import com.hncpu.blog.entity.TbBlogEntity;
import com.hncpu.blog.exception.BlogException;

import java.util.List;

public interface TbBlogService extends IService<TbBlogEntity> {
    List<TbBlogDTO> selectUserInfoByGtFraction(Integer pageNum, Integer pageSize);
    List<TbBlogEntity> queryAllByClassIfyId(Integer Id);
    TbBlogDTO queryBlogByBlogId(Integer Id);
    List<TbBlogEntity> queryBlogByTagsId(Integer TagsId);

    int updateBlogById(TbBlogDTO tbBlogEntity) throws Exception;

    int deleteBlogById(Integer id) throws BlogException;

    Page<TbBlogEntity> selectEssayList(int pageNum, int pageSize);

    TbBlogEntity queryEssayByBlogId(Integer Id);

    int insertEssay(TbBlogDTO tbBlogEntity) throws BlogException;

    int deleteEssayByIds(List<Integer> ids);

}
