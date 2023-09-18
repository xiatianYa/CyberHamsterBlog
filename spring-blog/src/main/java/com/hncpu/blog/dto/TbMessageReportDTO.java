package com.hncpu.blog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TbMessageReportDTO {
    private Integer reportId;
    private String reportContent;
    private Date createTime;
}
