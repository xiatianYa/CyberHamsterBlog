package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.dto.TbClassIfyByBlogCountDTO;
import com.hncpu.blog.entity.TbClassifyEntity;
import com.hncpu.blog.exception.BlogException;

import java.util.List;

public interface TbClassifyService extends IService<TbClassifyEntity> {


    TbClassifyEntity queryClassIfyById(Integer Id);

    int deleteClassIfyById(Integer id) throws BlogException;

    int updateClassIfyById(TbClassifyEntity tbClassifyEntity) throws BlogException;

    int insertClassIfy(TbClassifyEntity tbClassifyEntity);


    List<TbClassifyEntity> queryClassIfyAll();

    List<TbClassIfyByBlogCountDTO> getClassIfyAndBlogCount();
}
