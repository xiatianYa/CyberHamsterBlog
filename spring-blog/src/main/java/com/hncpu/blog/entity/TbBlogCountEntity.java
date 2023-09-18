package com.hncpu.blog.entity;

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
    private Integer countId;
    private Integer blogId;
    private Integer countNumber;
}
