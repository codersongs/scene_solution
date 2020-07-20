package com.codersongs.scenesolution.uniqId.service;

import com.codersongs.scenesolution.uniqId.enums.IDGeneratorTypeEnum;
import com.codersongs.scenesolution.uniqId.model.IDPeriodResponseModel;

import javax.naming.TimeLimitExceededException;

/**
 * 生成分布式唯一消息ID
 */
public interface IDGeneratorService {
    String genUniqId(IDGeneratorTypeEnum idGeneratorTypeEnum, Long centerId, Long machineId);
    IDPeriodResponseModel genPeriodId(Long bizType) throws TimeLimitExceededException;
}
