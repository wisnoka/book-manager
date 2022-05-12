package com.yll.bookmanager.config;

import lombok.Data;

import java.io.Serializable;

//用于传递结果信息
@Data
public class Result implements Serializable {
    //结果代码
    private String code;
    //结果信息
    private String msg;
    private Object data;

    //是否连带返回数据的两种方法
    public static Result succ(Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg("操作成功");
        return m;
    }

    public static Result succ(String mess, Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(String mess) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(null);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(String mess, Object data) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(String.valueOf(code));
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
