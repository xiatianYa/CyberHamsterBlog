package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("tb_classify_count")
@AllArgsConstructor
@NoArgsConstructor
public class TbClassIfyCountEntity {
    private Integer countId;
    private Integer classifyId;
    private Date createTime;
}
