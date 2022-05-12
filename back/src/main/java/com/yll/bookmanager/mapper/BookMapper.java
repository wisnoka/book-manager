package com.yll.bookmanager.mapper;

import com.yll.bookmanager.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 杨龙龙
 * @since 2022-05-10
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}
