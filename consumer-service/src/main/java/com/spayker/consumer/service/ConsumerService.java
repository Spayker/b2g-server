package com.spayker.consumer.service;

import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.domain.User;

import java.util.Date;
import java.util.List;

/**
 *  Service layer interface to provided API for work with Consumer entity.
 **/
public interface ConsumerService {

	/**
	 *  Looks for stored consumer by its id.
	 *  @param consumerId - string value for search
	 *  @return found consumer
	 **/
	Consumer findByConsumerId(Long consumerId);

	/**
	 *  Looks for stored consumer by its name.
	 *  @param consumerName - string value for search
	 *  @return list of found consumerss
	 **/
	List<Consumer> findConsumerByName(String consumerName);

	/**
	 *  Looks for stored consumer by its email
	 *  @param email - string value for search
	 *  @return found Consumer
	 **/
	Consumer findConsumerByEmail(String email);

	/**
	 *  Looks for stored consumer by its created date
	 *  @param createdDate - string value for search
	 *  @return list of found consumers
	 **/
	List<Consumer> findConsumerByCreatedDate(Date createdDate);

	/**
	 *  Looks for stored consumer by its modified date
	 *  @param modifiedDate - string value for search
	 *  @return list of found consumers
	 **/
	List<Consumer> findConsumerByModifiedDate(Date modifiedDate);

	/**
	 *  Creates new Consumer and returns it by provided User instance.
	 *  @param user - instance of User with email and password
	 *  @return created Consumer
	 **/
	Consumer create(Consumer consumer, User user);

	/**
	 *  Updates a stored consumer and returns its updated variant.
	 *  @param update - an updated variation of Consumer that must be persisted
	 **/
	Consumer saveChanges(Consumer update);
}
