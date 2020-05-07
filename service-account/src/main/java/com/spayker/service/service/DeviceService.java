package com.spayker.service.service;

import com.spayker.service.domain.Device;

public interface DeviceService {

	Device findByDeviceId(Long deviceId);

	Device create(Device device);

	void saveChanges(Device update);
}
