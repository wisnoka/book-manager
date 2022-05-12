package com.yll.bookmanager.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yll.bookmanager.config.Result;
import com.yll.bookmanager.entity.Book;
import com.yll.bookmanager.service.BookService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class MangController {

    @Autowired
    BookService bookService;

    //进入管理页面后显示所有图书信息，取消使用了数据库分页
    @GetMapping("/mang")
    public Result list() {
        List<Book> bookList=bookService.list(new QueryWrapper<Book>().orderByDesc("id"));
        return Result.succ(bookList);
    }

    //图书添加和修改，通过按下按钮后提交的数据是否有id确定
    //此操作需要登录权限
    @RequiresAuthentication
    @PostMapping("/add")
    public Result add(@Validated @RequestBody Book book) {
        System.out.println(book.toString());
        Book temp;
        if(book.getId() != null) {
//            如果有id，则是修改图书信息，先存入图书信息
            temp = bookService.getById(book.getId());
//            如何设置访问权限？
//            Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "没有权限编辑");
        } else {
            //如果是添加新书，从参数中拿信息
            temp = new Book();
            temp.setName(book.getName());
            temp.setAuthor(book.getAuthor());
            temp.setPress(book.getPress());
            temp.setIsbn(book.getIsbn());
        }
        BeanUtil.copyProperties(book, temp);
        bookService.saveOrUpdate(temp);
        return Result.succ("添加修改操作成功", null);
    }

}
