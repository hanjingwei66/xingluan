package com.shuojie.utils.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public  class Result <T>{
    private int code;
    private String message;
    private String command;
    private T data;
    private List list;

    public Result(int code, String message, String command, T data) {
        this.code = code;
        this.message = message;
        this.command = command;
        this.data = data;
    }
    public Result(int code, String message, String command) {
        this.code = code;
        this.message = message;
        this.command = command;
    }
    public Result(int code, String message, String command,List<T> list) {
        this.code = code;
        this.message = message;
        this.command = command;
        this.list=list;
    }

}