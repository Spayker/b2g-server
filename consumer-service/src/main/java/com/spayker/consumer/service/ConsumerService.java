package com.spayker.consumer.service;

import com.spayker.consumer.domain.Consumer;

import java.util.Date;
import java.util.List;

public interface ConsumerService {

	Consumer findByConsumerId(Long consumerId);

	List<Consumer> findConsumerByName(String consumerName);

	Consumer findConsumerByEmail(String email);

	List<Consumer> findConsumerByCreatedDate(Date createdDate);

	List<Consumer> findConsumerByModifiedDate(Date modifiedDate);

	Consumer create(Consumer consumer);

	Consumer saveChanges(Consumer update);
}
