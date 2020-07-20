package com.codersongs.scenesolution.uniqId.controller;

import com.codersongs.scenesolution.common.model.ResponseModel;
import com.codersongs.scenesolution.uniqId.annotation.DataSourceAnnotaion;
import com.codersongs.scenesolution.uniqId.enums.DataSourceEnum;
import com.codersongs.scenesolution.uniqId.enums.IDGeneratorTypeEnum;
import com.codersongs.scenesolution.uniqId.model.IDPeriodRequestModel;
import com.codersongs.scenesolution.uniqId.model.SnowFlakeRequestModel;
import com.codersongs.scenesolution.uniqId.service.IDGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.TimeLimitExceededException;


/**
 * 分布式ID生成器
 */
@RestController
@RequestMapping("id")
public class IDGeneratorController {
    @Autowired
    private IDGeneratorService idGeneratorService;

    /**
     * 使用UUID
     * @return
     */
    @PostMapping("/genUUID")
    public ResponseModel genUUID() {
       return new ResponseModel(idGeneratorService.genUniqId(IDGeneratorTypeEnum.UUID, null, null));
    }

    /**
     * 使用MySQL自增ID
     * @return
     */
    @PostMapping("/genMySQLAutoInc")
    public ResponseModel genMySQLAutoInc() {
        return new ResponseModel(idGeneratorService.genUniqId(IDGeneratorTypeEnum.MYSQL_INC, null, null));
    }

    /**
     * 使用多个MySQL，通过分配初始值和步长来保证取值的唯一
     * @return
     */
    @PostMapping("/genMySQLMultiInc")
    @DataSourceAnnotaion(name = DataSourceEnum.MULTIRANDOM)
    public ResponseModel genMySQLMultiInc() {
        return new ResponseModel(idGeneratorService.genUniqId(IDGeneratorTypeEnum.MYSQL_MULTI_INC, null, null));
    }

    /**
     * 通过MySQL，一次获取一批序列号
      * @param idPeriodRequestModel
     * @return
     * @throws TimeLimitExceededException
     */
    @PostMapping("/genMySQLPeriod")
    public ResponseModel genMySQLPeriod(@RequestBody IDPeriodRequestModel idPeriodRequestModel) throws TimeLimitExceededException {
        return new ResponseModel(idGeneratorService.genPeriodId(idPeriodRequestModel.getBizType()));
    }

    /**
     * 通过Redis的自增
     * @return
     */
    @PostMapping("/genRedisInc")
    public ResponseModel genRedisInc() {
        return new ResponseModel(idGeneratorService.genUniqId(IDGeneratorTypeEnum.REDIS_INC, null, null));
    }

    /**
     * 使用雪花算法，请求的时候可以使用分布式锁获取dataCenterId和machineId，保证唯一性
     * @return
     */
    @PostMapping("/genSnowFlake")
    public ResponseModel genSnowFlake(@RequestBody SnowFlakeRequestModel snowFlakeRequestModel) {
        return new ResponseModel(idGeneratorService.genUniqId(IDGeneratorTypeEnum.SNOW_FLAKE, snowFlakeRequestModel.getDataCenterId(), snowFlakeRequestModel.getMachineId()));
    }

    /**
     * 使用百度的uid-generator
     * @return
     */
    @PostMapping("/uidGenerator")
    public ResponseModel uidGenerator() {
        return new ResponseModel(idGeneratorService.genUniqId(IDGeneratorTypeEnum.SNOW_FLAKE, null, null));
    }

    /**
     * 使用美团的leaf算法
     * @return
     */
    @PostMapping("/leaf")
    public ResponseModel leaf() {
        return new ResponseModel(idGeneratorService.genUniqId(IDGeneratorTypeEnum.SNOW_FLAKE, null, null));
    }

    /**
     * 使用滴滴的Tinyid算法
     * @return
     */
    @PostMapping("/tinyid")
    public ResponseModel tinyid() {
        return new ResponseModel(idGeneratorService.genUniqId(IDGeneratorTypeEnum.SNOW_FLAKE, null, null));
    }

    /**
     * 使用微信的seqserver
     * @return
     */
    @PostMapping("/seqserver")
    public ResponseModel seqserver() {
        return new ResponseModel(idGeneratorService.genUniqId(IDGeneratorTypeEnum.SNOW_FLAKE, null, null));
    }

    /**
     * 请求者使用自己的主机+进程号+线程号+时间戳+随机数
     * @return
     */
    @PostMapping("/reqFeature")
    public ResponseModel reqFeature() {
        return new ResponseModel(idGeneratorService.genUniqId(IDGeneratorTypeEnum.SNOW_FLAKE, null, null));
    }
}
