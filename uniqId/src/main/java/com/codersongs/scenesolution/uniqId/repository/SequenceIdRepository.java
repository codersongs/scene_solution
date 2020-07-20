package com.codersongs.scenesolution.uniqId.repository;

import com.codersongs.scenesolution.uniqId.dbdo.SequenceId;
import org.springframework.data.repository.CrudRepository;

public interface SequenceIdRepository extends CrudRepository<SequenceId, Long> {
}
