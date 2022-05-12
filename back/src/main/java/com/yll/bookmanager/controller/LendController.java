package com.yll.bookmanager.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yll.bookmanager.config.Result;
import com.yll.bookmanager.entity.Book;
import com.yll.bookmanager.entity.Lend;
import com.yll.bookmanager.service.LendService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 杨龙龙
 * @since 2022-05-10
 */
@RestController
public class LendController {

    @Autowired
    LendService lendService;

    @GetMapping("/lend")
    public Result list() {
        List<Lend> lendList=lendService.list(new QueryWrapper<Lend>().orderByDesc("date_lend"));
        return Result.succ(lendList);
    }

    @RequiresAuthentication
    @PostMapping("/lend/return/{target}")
    public Result delete(@PathVariable(name = "target") String name){
        Lend lend=lendService.getOne(new QueryWrapper<Lend>().eq("name",name));
        Lend temp=lend; temp.setLended(false);
        BeanUtil.copyProperties(lend, temp);
        lendService.saveOrUpdate(temp);
        return Result.succ("已还书",null);
    }
}
