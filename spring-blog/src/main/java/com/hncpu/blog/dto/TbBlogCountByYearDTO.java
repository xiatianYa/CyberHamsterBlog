package com.hncpu.blog.dto;

import com.hncpu.blog.entity.TbClassifyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbBlogCountByYearDTO {
    /** 归档下的访问数量 */
    private List<String> yAxisData;
    /** 数据 年份 类型 数据[] */
    private TbBlogCountYearParam series;
}
