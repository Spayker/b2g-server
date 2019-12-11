package com.spayker.device.service;

import com.spayker.device.domain.UnitConfig;
import com.spayker.device.domain.User;

public interface ArmaService {

	UnitConfig findByName(String accountName);

	UnitConfig create(User user);

	void saveChanges(String name, UnitConfig update);
}
