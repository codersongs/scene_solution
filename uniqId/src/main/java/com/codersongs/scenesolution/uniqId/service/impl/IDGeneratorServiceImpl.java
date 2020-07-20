package com.codersongs.scenesolution.uniqId.service.impl;

import com.codersongs.scenesolution.uniqId.dbdo.IDGenerator;
import com.codersongs.scenesolution.uniqId.dbdo.SequenceId;
import com.codersongs.scenesolution.uniqId.enums.IDGeneratorTypeEnum;
import com.codersongs.scenesolution.uniqId.model.IDPeriodResponseModel;
import com.codersongs.scenesolution.uniqId.repository.IDGeneratorRepository;
import com.codersongs.scenesolution.uniqId.repository.SequenceIdRepository;
import com.codersongs.scenesolution.uniqId.service.IDGeneratorService;
import com.codersongs.scenesolution.uniqId.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.naming.TimeLimitExceededException;
import java.util.UUID;

/**
 * 生成分布式唯一ID
 */
@Service
@Slf4j
public class IDGeneratorServiceImpl implements IDGeneratorService {

    @Autowired
    private SequenceIdRepository sequenceIdRepository;
    @Autowired
    private IDGeneratorRepository idGeneratorRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String REDIS_INC_KEY = "redis_inc_key";
    @Autowired
    private SnowFlakeUtils snowFlakeUtils;

    /**
     * 生成唯一ID
     * @param idGeneratorTypeEnum
     * @param centerId
     * @param machineId
     * @return
     */
    @Override
    public String genUniqId(IDGeneratorTypeEnum idGeneratorTypeEnum, Long centerId, Long machineId) {
        switch (idGeneratorTypeEnum){
            case UUID:
                return genUUID();
            case MYSQL_INC:
                return genMySQLAutoInc();
            case MYSQL_MULTI_INC:
                return genMySQLMultiInc();
            case REDIS_INC:
                return genRedisInc();
            case SNOW_FLAKE:
                return genSnowFlake(centerId, machineId);
        }
        return null;
    }

    private String genSnowFlake(Long centerId, Long machineId) {
        return String.valueOf(snowFlakeUtils.nextId(centerId, machineId));
    }

    private String genRedisInc() {
        return redisTemplate.opsForValue().increment(REDIS_INC_KEY).toString();
    }

    private String genMySQLMultiInc() {
        return sequenceIdRepository.save(new SequenceId()).getId().toString();
    }

    private String genMySQLAutoInc() {
        return sequenceIdRepository.save(new SequenceId()).getId().toString();
    }

    /**
     * 使用UUID作为分布式唯一ID
     * @return
     */
    private String genUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public IDPeriodResponseModel genPeriodId(Long bizType) throws TimeLimitExceededException{
        IDGenerator res;
        long start = System.currentTimeMillis();
        for (;;){
            IDGenerator idGenerator = idGeneratorRepository.findByBizType(bizType);
            System.out.println(idGenerator);
            idGenerator.setMaxId(idGenerator.getMaxId() + idGenerator.getStep());
            try {
                res = idGeneratorRepository.save(idGenerator);
                break;
            } catch (ObjectOptimisticLockingFailureException e){
                log.info("optimistic lock:" + e.getMessage());
            }
            long end = System.currentTimeMillis();
            if (end - start > 3000){
                throw new TimeLimitExceededException("fetch id period time exceed");
            }
        }
        return new IDPeriodResponseModel(res.getMaxId(), res.getStep());
    }
}
