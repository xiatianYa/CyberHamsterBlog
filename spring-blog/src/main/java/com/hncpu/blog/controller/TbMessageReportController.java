package com.hncpu.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.dto.TbMessageReportDTO;
import com.hncpu.blog.service.TbMessageReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/message/report")
public class TbMessageReportController {
    @Autowired
    private TbMessageReportService tbMessageReportService;
    /** 添加举报 */
    @PostMapping("/{Id}")
    @CacheEvict(cacheNames = {"MessageReportListByPage"},allEntries = true)
    public ApiResult<String> addMessageReport(@PathVariable Integer Id){
        int count=tbMessageReportService.addMessageReport(Id);
        if (count>0){
            return ApiResult.success("举报成功");
        }else{
            return ApiResult.error("举报失败! | 已举报!");
        }
    }
    /** 删除举报 */
    @SaCheckPermission("user.delete")
    @PostMapping("/deleteMessageReport")
    @CacheEvict(cacheNames = {"MessageReportListByPage"},allEntries = true)
    public ApiResult<String> deleteEssayByIds(@RequestBody List<Integer> Ids){
        tbMessageReportService.deleteEssayByIds(Ids);
        return ApiResult.success("删除成功");
    }
    @GetMapping("/{pageNum}/{pageSize}")
    @Cacheable(cacheNames = "MessageReportListByPage",key = "#pageNum")
    public ApiResult<Page> getMessageReportList(@PathVariable Integer pageNum, @PathVariable Integer pageSize){
        return ApiResult.success(tbMessageReportService.getMessageReportList(pageNum, pageSize));
    }
    /** 举报审核通过 */
    @SaCheckPermission("user.delete")
    @PostMapping("/yesMessageReport")
    @CacheEvict(cacheNames = {"MessageReportListByPage"},allEntries = true)
    public ApiResult<String> yesMessageReport(@RequestBody List<Integer> Ids){
        tbMessageReportService.yesMessageReport(Ids);
        return ApiResult.success("审核成功");
    }

}
