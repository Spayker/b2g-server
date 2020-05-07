package com.spayker.consumer.service;

import com.spayker.consumer.domain.Consumer;

public interface ConsumerService {

	Consumer findByConsumerId(Long consumerId);

	Consumer create(Consumer consumer);

	void saveChanges(Consumer update);
}
