package com.hncpu.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.entity.TbTagsEntity;
import com.hncpu.blog.service.TbTagsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/tags")
public class TbTagsController {
    @Autowired
    private TbTagsService tagsService;
    /** 获取标签列表 */
    @GetMapping("/list")
    @Cacheable(cacheNames = "TagsList")
    public ApiResult<List<TbTagsEntity>> queryAll(){
        List<TbTagsEntity> tbTagsEntities = tagsService.queryAll();
        return ApiResult.success(tbTagsEntities);
    }
    /** 查询博客标签数量 */
    @GetMapping("/getTagsCount")
    @Cacheable(cacheNames = "TagsCount")
    public ApiResult<Long> getTagsCount(){
        return ApiResult.success(tagsService.count());
    }
    /** 后台查询标签列表 */
    @SaCheckPermission("user.get")
    @GetMapping("/tag/{pageNum}/{pageSize}")
    public ApiResult<Page<TbTagsEntity>> ManagequeryAllByPage(@PathVariable(required = false,value = "pageNum") int pageNum,
                                                              @PathVariable(required = false,value = "pageSize") int pageSize){
        return ApiResult.success(tagsService.selectTagList(pageNum, pageSize));
    }
    /** 根据标签ID 获取标签信息 */
    @SaCheckPermission("user.get")
    @GetMapping("/queryTagByTagId/{Id}")
    public ApiResult<TbTagsEntity> queryTagByTagId(@PathVariable Integer Id){
        return ApiResult.success(tagsService.queryTagByTagId(Id));
    }
    /** 根据标签信息 修改标签 */
    @SaCheckPermission("user.update")
    @PostMapping("/updateTagByTagId")
    @CacheEvict(cacheNames = {"TagsList"},allEntries = true)
    public ApiResult<String> updateTagByTagId(@RequestBody TbTagsEntity tbTagsEntity){
        int count=tagsService.updateTagByTagId(tbTagsEntity);
        if (count>0){
            return ApiResult.msg("修改成功");
        }
        return ApiResult.error("修改失败");
    }
    /** 根据标签ID 删除标签 */
    @SaCheckPermission("user.delete")
    @GetMapping("/deleteTagByTagId/{Id}")
    @CacheEvict(cacheNames = {"TagsList","TagsCount"},allEntries = true)
    public ApiResult<String> deleteTagByTagId(@PathVariable Integer Id){
        try {
            int count=tagsService.deleteTagByTagId(Id);
            if (count>0){
                return ApiResult.msg("删除成功");
            }
        }catch (Exception e){
            return ApiResult.error(e.getMessage());
        }
        return ApiResult.error("删除失败");
    }
    /** 根据标签信息 插入标签 */
    @SaCheckPermission("user.add")
    @PostMapping("/insertTag")
    @CacheEvict(cacheNames = {"TagsList","TagsCount"},allEntries = true)
    public ApiResult<String> insertTag(@RequestBody TbTagsEntity tbTags){
        int count=tagsService.insertTag(tbTags);
        if (count>0){
            return ApiResult.msg("添加成功");
        }
        return ApiResult.error("添加失败");
    }
}
