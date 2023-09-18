package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("tb_blog_tags_relation")
@AllArgsConstructor
@NoArgsConstructor
public class TbBlogTagsRelationEntity {
    @TableId(type = IdType.AUTO)
    private Integer classifyTagsRelationId;
    /** 文章表ID */
    private Integer blogId;
    /** 标签表 */
    private Integer tagsId;
}
