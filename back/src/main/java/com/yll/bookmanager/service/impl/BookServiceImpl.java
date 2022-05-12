package com.yll.bookmanager.service.impl;

import com.yll.bookmanager.entity.Book;
import com.yll.bookmanager.mapper.BookMapper;
import com.yll.bookmanager.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 杨龙龙
 * @since 2022-05-10
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
