package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@TableName("tb_tags")
@AllArgsConstructor
@NoArgsConstructor
public class TbTagsEntity {
    /** 标签ID */
    @TableId(type = IdType.AUTO)
    private Integer tagsId;
    /** 标签名称 */
    private String tagsName;
    /** 标签使用数 */
    private Integer tagsRank;
    /** 标签是否删除 0 未删除 1 删除 */
    @TableLogic
    private Integer isDeleted;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
}
