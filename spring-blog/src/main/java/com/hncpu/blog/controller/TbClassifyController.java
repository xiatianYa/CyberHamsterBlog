package com.hncpu.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.dto.TbClassIfyByBlogCountDTO;
import com.hncpu.blog.entity.TbClassifyEntity;
import com.hncpu.blog.service.TbClassifyService;
import com.hncpu.blog.exception.BlogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/classify")
public class TbClassifyController {
    @Autowired
    private TbClassifyService tbClassifyService;
    /** 查询博客归档数量 */
    @GetMapping("/getClassifyCount")
    @Cacheable(cacheNames = "ClassIfyCount")
    public ApiResult<Long> getClassIfyCount(){
        return ApiResult.success(tbClassifyService.count());
    }
    /** 获取所有标签 */
    @GetMapping
    @Cacheable(cacheNames = "ClassIfyList")
    public ApiResult<List<TbClassifyEntity>> queryClassIfyList(){
        List<TbClassifyEntity> tbClassifyEntities = tbClassifyService.queryClassIfyAll();
        return ApiResult.success(tbClassifyEntities);
    }
    /** 根据Id查询归档 */
    @GetMapping("/queryClassIfyById/{Id}")
    @Cacheable(cacheNames = "ClassIfyById",key = "#Id")
    public ApiResult<TbClassifyEntity> queryClassIfyById(@PathVariable Integer Id){
        return ApiResult.success(tbClassifyService.queryClassIfyById(Id));
    }
    /** 删除归档 */
    @SaCheckPermission("user.delete")
    @GetMapping("/deleteClassIfyById/{Id}")
    @CacheEvict(cacheNames = {"ClassIfyCount","ClassIfyList","ClassIfyById","ClassIfyAndBlogCount"},allEntries = true)
    public ApiResult<String> deleteClassIfyById(@PathVariable Integer Id){
        try {
            int count = tbClassifyService.deleteClassIfyById(Id);
            if (count>0){
                return ApiResult.msg("删除成功");
            }
        } catch (BlogException e) {
            return ApiResult.error(e.getMessage());
        }
        return ApiResult.error("删除失败");
    }
    /** 修改归档 */
    @SaCheckPermission("user.update")
    @PostMapping("/updateClassIfyById")
    @CacheEvict(cacheNames = {"ClassIfyCount","ClassIfyList","ClassIfyById"},allEntries = true)
    public ApiResult<String> updateClassIfyById(@RequestBody TbClassifyEntity tbClassifyEntity){
        try {
            int count = tbClassifyService.updateClassIfyById(tbClassifyEntity);
            if (count>0){
                return ApiResult.msg("修改成功");
            }
        } catch (BlogException e) {
            return ApiResult.error(e.getMessage());
        }
        return ApiResult.error("修改失败");
    }
    /** 新增归档 */
    @SaCheckPermission("user.add")
    @PostMapping("/insertClassIfy")
    @CacheEvict(cacheNames = {"ClassIfyCount","ClassIfyList","ClassIfyById","ClassIfyAndBlogCount"},allEntries = true)
    public ApiResult<String> insertClassIfy(@RequestBody TbClassifyEntity tbClassifyEntity){
        int count = tbClassifyService.insertClassIfy(tbClassifyEntity);
        if (count>0){
            return ApiResult.msg("添加成功");
        }
        return ApiResult.error("添加失败");
    }
    /** 获取各归档文章数量 */
    @SaCheckPermission("user.get")
    @GetMapping("/getClassIfyAndBlogCount")
    @Cacheable(cacheNames = "ClassIfyAndBlogCount")
    public ApiResult<List<TbClassIfyByBlogCountDTO>> getClassIfyAndBlogCount(){
        List<TbClassIfyByBlogCountDTO> BlogCountDto=tbClassifyService.getClassIfyAndBlogCount();
        return ApiResult.success(BlogCountDto);
    }
}
