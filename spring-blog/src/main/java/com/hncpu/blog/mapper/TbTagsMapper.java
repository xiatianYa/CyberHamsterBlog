package com.hncpu.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hncpu.blog.entity.TbTagsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TbTagsMapper extends BaseMapper<TbTagsEntity> {
    @Select("SELECT tags_id,tags_name,tags_rank,is_deleted,create_time FROM tb_tags")
    Page<TbTagsEntity> selectTagList(Page<TbTagsEntity> page);
    @Select("SELECT tags_id,tags_name,tags_rank,is_deleted,create_time FROM tb_tags WHERE tags_id = #{id}")
    TbTagsEntity selectTagById(Integer id);
    @Update("UPDATE tb_tags SET tags_name=#{tags.tagsName}, tags_rank=#{tags.tagsRank},is_deleted=#{tags.isDeleted} WHERE tags_id= #{tags.tagsId}")
    int updateTagById(@Param("tags") TbTagsEntity tags);
}
