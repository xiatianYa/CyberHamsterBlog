package com.hncpu.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbBlogCountYearParam {
    private String name;
    private String type;
    private List<Integer> data;
}
