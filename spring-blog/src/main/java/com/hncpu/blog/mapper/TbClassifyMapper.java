package com.hncpu.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hncpu.blog.entity.TbClassifyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TbClassifyMapper extends BaseMapper<TbClassifyEntity> {
    @Update("UPDATE tb_classify SET classify_name=#{classify.classifyName},update_time=#{classify.updateTime},is_deleted=#{classify.isDeleted} WHERE classify_id=#{classify.classifyId}")
    int updateClassIfyById(@Param("classify") TbClassifyEntity tbClassifyEntity);
    @Select("SELECT classify_id,classify_name,is_deleted,create_time,update_time FROM tb_classify LIMIT #{pageNum},#{pageSize}")
    List<TbClassifyEntity> selectClassIfyList(int pageNum, int pageSize);
    @Select("SELECT classify_id,classify_name,is_deleted,create_time,update_time FROM tb_classify WHERE classify_id=#{id}")
    TbClassifyEntity selectClassIfyById(Integer id);
}
