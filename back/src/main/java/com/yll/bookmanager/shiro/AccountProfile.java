package com.yll.bookmanager.shiro;

import lombok.Data;

import java.io.Serializable;

//返回可以显示的数据，比如id和用户名
@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
}
