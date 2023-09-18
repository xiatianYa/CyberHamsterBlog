package com.hncpu.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbBlogCountDTO {
    private Integer blogCount;
    private Integer classifyCount;
    private Integer tagsCount;

}
