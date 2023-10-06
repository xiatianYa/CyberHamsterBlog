package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("tb_blog_count")
@AllArgsConstructor
@NoArgsConstructor
public class TbBlogCountEntity {
    @TableId(type = IdType.AUTO)
    private Integer countId;
    private Integer blogId;
    private Integer countNumber;
}
