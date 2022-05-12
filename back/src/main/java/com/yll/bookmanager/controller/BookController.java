package com.yll.bookmanager.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yll.bookmanager.config.Result;
import com.yll.bookmanager.entity.Book;
import com.yll.bookmanager.service.BookService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 杨龙龙
 * @since 2022-05-10
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    //    图书查询
    @GetMapping("/{target}")
    public Result searchBook(@PathVariable(name = "target") String target) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", target).or().like("name", target)
                .or().like("press", target).or().like("author", target)
                .or().eq("ISBN", target);
        List<Book> books = bookService.list(queryWrapper);
        //如果未查询到结果，返回查询结果为空
        Assert.notNull(books,"查询结果为空。");
        //否则返回查询结果
        return Result.succ("查询成功",books);
    }

    //图书删除操作，在图书列表中通过点按删除，传回图书信息，并进行删除，必定成功，此操作需要权限判断
    @RequiresAuthentication
    @PostMapping("/del/{target}")
    public Result delete(@PathVariable(name = "target") String id){
        bookService.removeById(id);
        return Result.succ("删除操作成功",null);
    }
}
