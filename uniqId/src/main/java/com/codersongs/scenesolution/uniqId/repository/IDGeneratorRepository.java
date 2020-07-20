package com.codersongs.scenesolution.uniqId.repository;

import com.codersongs.scenesolution.uniqId.dbdo.IDGenerator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * 号码段生成
 */
public interface IDGeneratorRepository extends CrudRepository<IDGenerator, Long> {
    IDGenerator findByBizType(Long bizType);

    @Modifying
    @Transactional
    @Query("update IDGenerator ig set ig.maxId = :maxId + :step, ig.version = :version + 1 where ig.version = :version and ig.bizType = :bizType")
    int updateByBizTypeAndVersion(@Param("maxId") Long maxId, @Param("step") Long step, @Param("version") Long version, @Param("bizType") Long bizType);
}
