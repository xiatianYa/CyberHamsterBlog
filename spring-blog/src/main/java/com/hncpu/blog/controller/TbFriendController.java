package com.hncpu.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.entity.TbFriendEntity;
import com.hncpu.blog.service.TbFriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/friend")
public class TbFriendController {
    @Autowired
    private TbFriendService tbFriendService;
    @GetMapping("/list")
    public ApiResult<List<TbFriendEntity>> queryAll(){
        List<TbFriendEntity> tbFriendEntities = tbFriendService.queryAll();
        return ApiResult.success(tbFriendEntities);
    }
    /** 查询友链列表 后台 */
    @SaCheckPermission("user.get")
    @GetMapping("/{pageNum}/{pageSize}")
    public ApiResult<List<TbFriendEntity>> ManagequeryAllByPage(@PathVariable(value = "pageNum") int pageNum,
                                                                @PathVariable(value = "pageSize") int pageSize){
        return ApiResult.success(tbFriendService.selectFrienList(pageNum,pageSize));
    }
    /** 修改友链 后台 */
    @SaCheckPermission("user.update")
    @PostMapping("/updateByFriendId")
    public ApiResult<String> updateByFriendById(@RequestBody TbFriendEntity tbFriendEntity){
        int count= tbFriendService.updateByFriendById(tbFriendEntity);
        if (count>0){
            return ApiResult.msg("修改成功");
        }
        return ApiResult.error("修改失败");
    }
    /** 删除友链 后台 */
    @SaCheckPermission("user.delete")
    @GetMapping("/deleteByFriendId/{Id}")
    public ApiResult<String> deleteByFriendId(@PathVariable Integer Id){
        int count=tbFriendService.deleteByFriendId(Id);
        if (count>0){
            return ApiResult.msg("删除成功");
        }
        return ApiResult.error("删除失败");
    }
    /** 新增友链 后台 */
    @SaCheckPermission("user.add")
    @PostMapping("/insertFriend")
    public ApiResult<String> insertFriend(@RequestBody TbFriendEntity tbFriendEntity){
        int count=tbFriendService.insertFriend(tbFriendEntity);
        if (count>0){
            return ApiResult.msg("添加成功");
        }
        return ApiResult.error("添加失败");
    }
    /** 根据ID查询友链 后台 */
    @SaCheckPermission("user.get")
    @GetMapping("/queryTagByFriendId/{Id}")
    public ApiResult<TbFriendEntity> queryTagByFriendId(@PathVariable Integer Id){
        return ApiResult.success(tbFriendService.queryTagByFriendId(Id));
    }
}
