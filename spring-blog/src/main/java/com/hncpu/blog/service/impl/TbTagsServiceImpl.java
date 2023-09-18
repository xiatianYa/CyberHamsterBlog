package com.hncpu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncpu.blog.entity.TbBlogTagsRelationEntity;
import com.hncpu.blog.entity.TbTagsEntity;
import com.hncpu.blog.mapper.TbTagsMapper;
import com.hncpu.blog.service.TbBlogTagsRelationService;
import com.hncpu.blog.service.TbTagsService;
import com.hncpu.blog.exception.BlogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbTagsServiceImpl extends ServiceImpl<TbTagsMapper, TbTagsEntity> implements TbTagsService {
    @Autowired
    private TbBlogTagsRelationService tbBlogTagsRelationService;
    /** 查询所有标签 */
    @Override
    public List<TbTagsEntity> queryAll() {
        return baseMapper.selectList(new LambdaQueryWrapper<>());
    }
    /** 查询标签数量 */
    @Override
    public Integer queryCount() {
        return Math.toIntExact(baseMapper.selectCount(new LambdaQueryWrapper<>()));
    }
    /** 根据Id 查询标签信息 */
    @Override
    public TbTagsEntity queryTagByTagId(Integer id) {
        return baseMapper.selectTagById(id);
    }
    /** 根据Id 修改标签信息 */
    @Override
    public int updateTagByTagId(TbTagsEntity tags) {
        return baseMapper.updateTagById(tags);
    }
    /** 根据Id 删除标签信息 */
    @Override
    public int deleteTagByTagId(Integer id) throws BlogException {
        LambdaQueryWrapper<TbBlogTagsRelationEntity> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbBlogTagsRelationEntity::getTagsId,id);
        long count = tbBlogTagsRelationService.count(wrapper);
        if (count>0){
            throw new BlogException("当前有文章使用此标签,不可删除!");
        }
        return baseMapper.deleteById(id);
    }
    /** 添加标签 */
    @Override
    public int insertTag(TbTagsEntity tbTags) {
        tbTags.setCreateTime(new Date());
        return baseMapper.insert(tbTags);
    }
    /**
     * 查询标签列表 后台
     */
    @Override
    public Page<TbTagsEntity> selectTagList(int pageNum, int pageSize) {
        Page<TbTagsEntity> page=new Page(pageNum,pageSize);
        return baseMapper.selectTagList(page);
    }

}
