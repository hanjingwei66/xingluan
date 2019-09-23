package com.shuojie.utils.vo;

import lombok.Data;

@Data
public class ReturnUpdateLog extends Result {

    private Integer id;

    private String updateName;

    private String updateText;

    public ReturnUpdateLog(int code, String message, String command) {
        super(code, message, command);
    }

}
