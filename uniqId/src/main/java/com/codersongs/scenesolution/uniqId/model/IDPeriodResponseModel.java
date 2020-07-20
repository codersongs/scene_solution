package com.codersongs.scenesolution.uniqId.model;

import lombok.Data;

/**
 * 批量ID响应模型
 */
@Data
public class IDPeriodResponseModel {
    private Long start;
    private Long step;

    public IDPeriodResponseModel() {
    }

    public IDPeriodResponseModel(Long start, Long step) {
        this.start = start;
        this.step = step;
    }
}
