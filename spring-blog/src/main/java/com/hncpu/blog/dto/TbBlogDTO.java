package com.hncpu.blog.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hncpu.blog.entity.TbBlogEntity;
import com.hncpu.blog.entity.TbTagsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbBlogDTO extends TbBlogEntity {
    private Integer blogId;
    /** 文章标题 */
    private String blogTitle;
    /** 文章内容 */
    private String blogContent;
    /** 文章归档id */
    private Integer blogClassifyId;
    /** 文章状态id 0完成 1编辑 */
    private Integer blogStatus;
    /** 文章是否删除 0 未删除 1 删除 */
    private Integer isDeleted;
    private List<TbTagsEntity> tbTagsEntityList;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
