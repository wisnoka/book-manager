package com.yll.bookmanager.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.yll.bookmanager.config.Result;
import com.yll.bookmanager.dto.LoginDto;
import com.yll.bookmanager.entity.User;
import com.yll.bookmanager.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.yll.bookmanager.util.JwtUtils;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

//    实现登录和登录校验功能
    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        //先按邮箱获得用户信息
        User user = userService.getOne(new QueryWrapper<User>().eq("email", loginDto.getEmail()));
        Assert.notNull(user, "用户不存在");
        //开始判断密码是否正确，使用简单的md5加密，此处选择不用
//        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword())))
        if(!user.getPassword().equals(loginDto.getPassword())) {
            return Result.fail("密码错误！");
        }
        //按用户id生成jwt，并放在header里
        String jwt = jwtUtils.generateToken(Long.parseLong(user.getId()));
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        // 可以直接返回空，但为了其他访问方便，也可以都返回去给前端用
        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("name", user.getName())
                .put("email", user.getEmail())
                .map()
        );
    }

    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
