package com.spayker.device.service;

import com.spayker.device.domain.Device;

public interface DeviceService {

	Device findByDeviceId(Long deviceId);

	Device create(Device device);

	void saveChanges(Device update);
}
