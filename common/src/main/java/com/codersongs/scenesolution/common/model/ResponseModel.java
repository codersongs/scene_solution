package com.codersongs.scenesolution.common.model;

import com.codersongs.scenesolution.common.enums.ResponseStatusEnum;
import lombok.Data;

/**
 * 接口响应模型
 */
@Data
public class ResponseModel {
    private Integer code;
    private String msg;
    private Object data;

    public ResponseModel() {
        this.code = ResponseStatusEnum.SUCCESS.getCode();
        this.msg = ResponseStatusEnum.SUCCESS.getMsg();
    }

    public ResponseModel(Object data) {
        this.code = ResponseStatusEnum.SUCCESS.getCode();
        this.msg = ResponseStatusEnum.SUCCESS.getMsg();
        this.data = data;
    }
}
