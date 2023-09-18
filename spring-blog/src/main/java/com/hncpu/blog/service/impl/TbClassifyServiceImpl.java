package com.hncpu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncpu.blog.dto.TbClassIfyByBlogCountDTO;
import com.hncpu.blog.entity.TbBlogEntity;
import com.hncpu.blog.entity.TbClassifyEntity;
import com.hncpu.blog.mapper.TbClassifyMapper;
import com.hncpu.blog.service.TbBlogService;
import com.hncpu.blog.service.TbClassifyService;
import com.hncpu.blog.exception.BlogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TbClassifyServiceImpl extends ServiceImpl<TbClassifyMapper, TbClassifyEntity> implements TbClassifyService {
    @Autowired
    TbBlogService tbBlogService;
    /** 查询全部归档 */
    @Override
    public List<TbClassifyEntity> queryAll() {
        return baseMapper.selectList(new LambdaQueryWrapper<>());
    }
    // 查询归档根据Id
    @Override
    public TbClassifyEntity queryClassIfyById(Integer Id) {
        return baseMapper.selectClassIfyById(Id);
    }
    //删除归档
    @Override
    public int deleteClassIfyById(Integer id) throws BlogException {
        LambdaQueryWrapper<TbBlogEntity> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbBlogEntity::getBlogClassifyId,id);
        long count = tbBlogService.count(wrapper);
        if (count >0){
            throw new BlogException("该归档有文章关联,无法删除!");
        }
        return baseMapper.deleteById(id);
    }
    /**
     * 修改归档根据ID
     */
    @Override
    public int updateClassIfyById(TbClassifyEntity tbClassifyEntity) throws BlogException {
        LambdaQueryWrapper<TbBlogEntity> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbBlogEntity::getBlogClassifyId,tbClassifyEntity.getClassifyId());
        long count = tbBlogService.count(wrapper);
        if (count >0){
            throw new BlogException("该归档有文章关联,无法修改!");
        }
        tbClassifyEntity.setUpdateTime(new Date());
        return baseMapper.updateClassIfyById(tbClassifyEntity);
    }
    /**
     * 新增归档
     */
    @Override
    public int insertClassIfy(TbClassifyEntity tbClassifyEntity) {
        tbClassifyEntity.setCreateTime(new Date());
        tbClassifyEntity.setUpdateTime(new Date());
        return baseMapper.insert(tbClassifyEntity);
    }


    /**
     * 查询归档 后台
     */
    @Override
    public List<TbClassifyEntity> queryClassIfyAll(int pageNum, int pageSize) {
        return baseMapper.selectClassIfyList(pageNum,pageSize);
    }

    @Override
    public List<TbClassIfyByBlogCountDTO> getClassIfyAndBlogCount() {
        List<TbClassIfyByBlogCountDTO> tbClassIfyByBlogCountDTOS= new ArrayList<>();
        List<TbClassifyEntity> tbClassifyEntities = baseMapper.selectList(new LambdaQueryWrapper<>());
        for (TbClassifyEntity item:tbClassifyEntities){
            Integer classifyId = item.getClassifyId();
            LambdaQueryWrapper<TbBlogEntity> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(TbBlogEntity::getBlogClassifyId,classifyId);
            long count = tbBlogService.count(wrapper);
            tbClassIfyByBlogCountDTOS.add(new TbClassIfyByBlogCountDTO(item.getClassifyName(),count));
        }
        return tbClassIfyByBlogCountDTOS;
    }

}
