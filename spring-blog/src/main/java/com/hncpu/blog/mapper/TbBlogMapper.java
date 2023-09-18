package com.hncpu.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hncpu.blog.entity.TbBlogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

@Mapper
public interface TbBlogMapper extends BaseMapper<TbBlogEntity> {
    @Select("SELECT blog_id,blog_title,blog_content,blog_classify_id,blog_status,is_deleted,create_time,update_time FROM tb_blog")
    Page<TbBlogEntity> queryAllEssayList(Page<TbBlogEntity> blogPage,LambdaQueryWrapper<TbBlogEntity> wrapper);
    @Select("SELECT blog_id,blog_title,blog_content,blog_classify_id,blog_status,is_deleted,create_time,update_time FROM tb_blog WHERE blog_id=#{Id}")
    TbBlogEntity queryEssayByBlogId(Integer Id);
    @Update("UPDATE tb_blog SET blog_title=#{blogTitle}, blog_content=#{blogContent}, blog_classify_id=#{blogClassifyId}, blog_status=#{blogStatus}, is_deleted=#{isDeleted} ,update_time=#{updateTime} WHERE blog_id=#{blogId}")
    int updateEssayById(Integer blogId, String blogTitle, String blogContent, Integer blogClassifyId, Integer blogStatus, Integer isDeleted, Date updateTime);

}
