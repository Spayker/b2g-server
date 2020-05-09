package com.spayker.consumer.service;

import com.spayker.consumer.domain.Consumer;

import java.util.List;

public interface ConsumerService {

	Consumer findByConsumerId(Long consumerId);

	List<Consumer> findConsumerByName(String consumerName);

	Consumer findConsumerByEmail(String email);

	Consumer create(Consumer consumer);

	void saveChanges(Consumer update);
}
