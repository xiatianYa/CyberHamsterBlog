package com.hncpu.blog.dto;

import lombok.Data;

@Data
public class TbMessageQueryParam {
    private Integer pageNum;
    private Integer pageSize;
    private Integer type;
}
