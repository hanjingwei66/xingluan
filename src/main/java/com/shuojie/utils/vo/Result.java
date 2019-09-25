package com.shuojie.utils.vo;

import lombok.Data;

@Data
public  class Result <T>{
    private int code;
    private String message;
    private String command;
    private T data;

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
}
