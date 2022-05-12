package com.yll.bookmanager.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杨龙龙
 * @since 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Lend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "name", type = IdType.AUTO)
    private String name;

    private Boolean lended;

    private Integer userId;

    private String dateLend;

    private String dateReturn;


}
