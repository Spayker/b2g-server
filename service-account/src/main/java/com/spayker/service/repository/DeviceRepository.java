package com.spayker.service.repository;

import com.spayker.service.domain.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

	Device findByDeviceId(Long deviceId);

}
