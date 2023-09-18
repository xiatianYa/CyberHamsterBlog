package com.hncpu.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hncpu.blog.entity.TbBlogTagsRelationEntity;
import com.hncpu.blog.entity.TbTagsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TbBlogTagsRelationMapper extends BaseMapper<TbBlogTagsRelationEntity> {
    @Select("select * from tb_tags as tag where tag.tags_id in (select tags_id from tb_blog_tags_relation where blog_id =#{blogId})")
    List<TbTagsEntity> getTagsByBlogId(Integer blogId);
}
