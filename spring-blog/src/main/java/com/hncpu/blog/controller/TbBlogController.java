package com.hncpu.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.dto.TbBlogDTO;
import com.hncpu.blog.dto.TbClassIfyByBlogCountDTO;
import com.hncpu.blog.entity.TbBlogEntity;
import com.hncpu.blog.service.TbBlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/blog")
public class TbBlogController {
    @Autowired
    private TbBlogService tbBlogService;
    /** 分页查询博客文章 */
    @GetMapping("/list/{pageNum}/{pageSize}")
    public ApiResult<List<TbBlogDTO>> queryAllByPage(@PathVariable(value = "pageNum") int pageNum,
                                    @PathVariable(value = "pageSize") int pageSize){
        List<TbBlogDTO> tbBlogDTOS = tbBlogService.selectUserInfoByGtFraction(pageNum, pageSize);
        return ApiResult.success(tbBlogDTOS);
    }
    /** 分页查询博客文章数量 */
    @GetMapping("/getBlogCount")
    public ApiResult<Long> getBlogCount(){
        return ApiResult.success(tbBlogService.count());
    }
    // 根据归档ID 查询文章
    @GetMapping("/byClassId/{Id}")
    public ApiResult<List<TbBlogEntity>> queryAllByClassIfyId(@PathVariable Integer Id){
        List<TbBlogEntity> tbBlogEntities = tbBlogService.queryAllByClassIfyId(Id);
        return ApiResult.success(tbBlogEntities);
    }
    // 根据文章Id 查询文章
    @GetMapping("/queryByBlogId/{Id}")
    public ApiResult<TbBlogDTO> queryBlogByBlogId(@PathVariable Integer Id){
        TbBlogDTO tbBlogDTO = tbBlogService.queryBlogByBlogId(Id);
        return ApiResult.success(tbBlogDTO);
    }
    // 根据标签Id 查询文章列表
    @GetMapping("/byTagsId/{Id}")
    public ApiResult<List<TbBlogEntity>> queryBlogByTagsId(@PathVariable Integer Id){
        List<TbBlogEntity> tbBlogEntities = tbBlogService.queryBlogByTagsId(Id);
        return ApiResult.success(tbBlogEntities);
    }
    //根据文章 修改文章ID
    @SaCheckPermission("user.update")
    @PostMapping("/updateBlogById")
    public ApiResult<String> updateBlogById(@RequestBody TbBlogDTO TbBlogDTO){
        try {
            int count = tbBlogService.updateBlogById(TbBlogDTO);
            if (count>0){
                return ApiResult.msg("修改成功");
            }
        }catch (Exception e){
            return ApiResult.error(e.getMessage());
        }
        return ApiResult.error("修改失败");
    }
    //根据文章ID 删除文章
    @SaCheckPermission("user.delete")
    @GetMapping("/deleteBlogById/{Id}")
    public ApiResult<String> deleteBlogById(@PathVariable Integer Id){
        try {
            int count = tbBlogService.deleteBlogById(Id);
            if (count>0){
                return ApiResult.msg("删除成功");
            }
        }catch (Exception e){
            return ApiResult.error(e.getMessage());
        }
            return ApiResult.success("删除失败");
    }
    /** 分页查询博客文章 (后台) */
    @SaCheckPermission("user.get")
    @GetMapping("/essay/{pageNum}/{pageSize}")
    public ApiResult<Page<TbBlogEntity>> ManagequeryAllByPage(@PathVariable(value = "pageNum") int pageNum,
                                                              @PathVariable(value = "pageSize") int pageSize){
        return ApiResult.success(tbBlogService.selectEssayList(pageNum, pageSize));
    }
    /** 查询文章 */
    @SaCheckPermission("user.get")
    @GetMapping("/queryEssayById/{Id}")
    public ApiResult<TbBlogEntity> queryEssayByBlogId(@PathVariable Integer Id){
        TbBlogEntity tbBlogEntity = tbBlogService.queryEssayByBlogId(Id);
        return ApiResult.success(tbBlogEntity);
    }
    /** 新增文章 */
    @SaCheckPermission("user.add")
    @PostMapping("/insertEssay")
    public ApiResult<String> insertEssay(@RequestBody TbBlogDTO tbBlogDTO){
        try {
            int count = tbBlogService.insertEssay(tbBlogDTO);
            if (count>0){
                return ApiResult.msg("添加成功");
            }
        }catch (Exception e){
            return ApiResult.error(e.getMessage());
        }
        return ApiResult.error("添加失败");
    }
    /** 删除文章 */
    @SaCheckPermission("user.delete")
    @PostMapping("/deleteEssayByIds")
    public ApiResult<String> deleteEssayByIds(@RequestBody List<Integer> Ids){
        int count = tbBlogService.deleteEssayByIds(Ids);
        if (count>0){
            return ApiResult.msg("删除成功");
        }
        return ApiResult.error("删除失败");
    }
}
