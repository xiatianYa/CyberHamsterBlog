package com.hncpu.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hncpu.blog.common.ApiResult;
import com.hncpu.blog.entity.TbUserEntity;
import com.hncpu.blog.service.TbUserService;
import com.hncpu.blog.exception.BlogException;
import com.hncpu.blog.utils.BlogProperty;
import com.hncpu.blog.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;
    @SaCheckPermission("user.manager")
    @GetMapping("/{pageNum}/{pageSize}")
    public ApiResult<List<TbUserEntity>> queryAllByPage(@RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "30") int pageSize){
        List<TbUserEntity> tbUserEntities = tbUserService.queryAllByPage(pageNum, pageSize);
        return ApiResult.success(tbUserEntities);
    }
    @PostMapping("/login")
    public ApiResult<SaTokenInfo> login(@RequestBody TbUserEntity user){
        LambdaQueryWrapper<TbUserEntity> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbUserEntity::getLoginUserName,user.getLoginUserName());
        TbUserEntity TbUser = tbUserService.getOne(wrapper);
        if (TbUser==null){
            return ApiResult.error("用户不存在");
        }
        String password = Md5Utils.encryptPassword(user.getLoginUserName(), user.getLoginPassword(), BlogProperty.MD5_SALT);
        if (!password.equals(TbUser.getLoginPassword())){
            return ApiResult.error("密码错误");
        }
        if (TbUser.getLocked()!=0){
            return ApiResult.error("用户被锁定,无法登录,请联系管理员!");
        }
        StpUtil.login(TbUser.getAdminUserId());
        SaTokenInfo SaToken = StpUtil.getTokenInfo();
        return ApiResult.success(SaToken);
    }
    @PostMapping("/logout")
    public ApiResult<String> logout(){
        StpUtil.logout();
        return ApiResult.success("退出成功");
    }
    //info
    @GetMapping("/info")
    public ApiResult<Map<String,Object>> getInfo(){
        Map<String,Object> map=new HashMap<>();
        int loginIdByToken = StpUtil.getLoginIdAsInt();
        TbUserEntity TbUser = tbUserService.getById(loginIdByToken);
        map.put("roles","[admin]");
        map.put("name",TbUser.getLoginUserName());
        map.put("avatar",TbUser.getAvatar());
        return ApiResult.success(map);
    }
    @SaCheckPermission("user.manager")
    @GetMapping("/queryTagByUserId/{Id}")
    public ApiResult<TbUserEntity> queryTagByUserId(@PathVariable Integer Id){
        try {
            return ApiResult.success(tbUserService.queryTagByUserId(Id));
        } catch (BlogException e) {
            return ApiResult.error(e.getMessage());
        }
    }
    @SaCheckPermission("user.manager")
    @PostMapping("/updateByUserId")
    public ApiResult<String> updateByUserId(@RequestBody TbUserEntity tbUserEntity){
        try {
            int count = tbUserService.updateByUserId(tbUserEntity);
            if (count>0){
                return ApiResult.msg("修改成功");
            }
        }catch (Exception e){
            return ApiResult.error(e.getMessage());
        }
        return ApiResult.error("修改失败");
    }
    @SaCheckPermission("user.manager")
    @PostMapping("/insertUser")
    public ApiResult<String> insertUser(@RequestBody TbUserEntity tbUserEntity){
        tbUserEntity.setAvatar(BlogProperty.AVATAR);
        tbUserEntity.setLevel(0);
        try {
            int count = tbUserService.insertUser(tbUserEntity);
            if (count>0){
                return ApiResult.msg("添加成功");
            }
        }catch (Exception e){
            return ApiResult.error(e.getMessage());
        }
        return ApiResult.error("添加失败");
    }
    @SaCheckPermission("user.manager")
    @GetMapping("/deleteByUserId/{Id}")
    public ApiResult<String> deleteByUserId(@PathVariable Integer Id){
        try {
            int count = tbUserService.deleteByUserId(Id);
            if (count>0){
                return ApiResult.msg("删除成功");
            }
        }catch (Exception e){
            return ApiResult.error(e.getMessage());
        }
        return ApiResult.error("删除失败");
    }
    @SaCheckPermission("user.manager")
    @PostMapping("/updateUserPwd")
    public ApiResult<String> updateUserPwd(@RequestBody TbUserEntity tbUser){
        try {
            tbUser.setLoginPassword(Md5Utils.encryptPassword(tbUser.getLoginUserName(), tbUser.getLoginPassword(), BlogProperty.MD5_SALT));
            int count = tbUserService.UpdateUserPwd(tbUser);
            if (count>0){
                return ApiResult.msg("修改成功");
            }
        }catch (Exception e){
            return ApiResult.error(e.getMessage());
        }
        return ApiResult.error("修改失败");
    }
    @SaCheckPermission("user.manager")
    @PostMapping("/updateUserLevelAndLocked")
    public ApiResult<String> updateUserLevelAndLocked(@RequestBody TbUserEntity tbUser){
        try {
            int count = tbUserService.updateUserLevelAndLocked(tbUser);
            if (count>0){
                return ApiResult.msg("修改成功");
            }
        }catch (Exception e){
            return ApiResult.error(e.getMessage());
        }
        return ApiResult.error("修改失败");
    }
}
