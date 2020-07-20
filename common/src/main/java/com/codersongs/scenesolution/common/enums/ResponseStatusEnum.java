package com.codersongs.scenesolution.common.enums;

/**
 * 接口响应状态
 */

public enum ResponseStatusEnum {
    SUCCESS(0, "SUCCESS");
    private Integer code;
    private String msg;
    ResponseStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
