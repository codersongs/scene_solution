package com.codersongs.scenesolution.uniqId.model;

import lombok.Data;

/**
 * 雪花算法请求模型
 */
@Data
public class SnowFlakeRequestModel {
    private Long dataCenterId;
    private Long machineId;
}
