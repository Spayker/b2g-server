package com.spayker.device.repository;

import com.spayker.device.domain.UnitConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmaRepository extends CrudRepository<UnitConfig, String> {

	UnitConfig findByName(String name);

}
