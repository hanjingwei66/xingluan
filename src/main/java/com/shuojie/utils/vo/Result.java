package com.shuojie.utils.vo;

import lombok.Data;

@Data
public  class Result {
    private int code;
    private String message;
    private String command;

    public Result(int code, String message, String command) {
        this.code = code;
        this.message = message;
        this.command = command;
    }
}
