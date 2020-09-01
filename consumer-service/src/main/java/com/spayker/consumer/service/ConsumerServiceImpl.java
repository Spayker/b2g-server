package com.spayker.consumer.service;

import com.spayker.consumer.client.AuthServiceClient;
import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.domain.User;
import com.spayker.consumer.exception.ConsumerException;
import com.spayker.consumer.repository.ConsumerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *  Service layer implementation to work with Consumer entities.
 **/
@Service
public class ConsumerServiceImpl implements ConsumerService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ConsumerRepository repository;

	@Autowired
	private AuthServiceClient authClient;

	/**
	 *  Looks for stored consumer by its id.
	 *  @param consumerId - string value for search
	 *  @return found consumer
	 **/
	@Override
	public Consumer findByConsumerId(Long consumerId) {
		return repository.findById(consumerId).orElse(null);
	}

	/**
	 *  Looks for stored consumer by its name.
	 *  @param name - string value for search
	 *  @return list of found consumerss
	 **/
	@Override
	public List<Consumer> findConsumerByName(String name) {
		if(name.isEmpty() || name.isBlank()){
			throw new IllegalArgumentException("provided name is empty or blank");
		}
		return repository.findByName(name);
	}

	/**
	 *  Looks for stored consumer by its email
	 *  @param email - string value for search
	 *  @return found Consumer
	 **/
	@Override
	public Consumer findConsumerByEmail(String email) {
		if(email.isEmpty() || email.isBlank()){
			throw new IllegalArgumentException("provided email is empty or blank");
		}
		return repository.findByEmail(email);
	}

	/**
	 *  Looks for stored consumer by its created date
	 *  @param createdDate - string value for search
	 *  @return list of found consumers
	 **/
	@Override
	public List<Consumer> findConsumerByCreatedDate(Date createdDate) {
		return repository.findByCreatedDate(createdDate);
	}

	/**
	 *  Looks for stored consumer by its modified date
	 *  @param modifiedDate - string value for search
	 *  @return list of found consumers
	 **/
	@Override
	public List<Consumer> findConsumerByModifiedDate(Date modifiedDate) {
		return repository.findByModifiedDate(modifiedDate);
	}

	/**
	 *  Creates new Consumer and returns it by provided User instance.
	 *  @param user - instance of User with email and password
	 *  @return created Consumer
	 **/
	@Override
	public Consumer create(Consumer consumer, User user) {
		Consumer existing = repository.findByEmail(consumer.getEmail());
		if(existing == null){
			authClient.createUser(user);
			Consumer savedConsumer = repository.saveAndFlush(consumer);
			log.info("new consumer has been created: " + savedConsumer.getEmail());
			return savedConsumer;
		} else {
			throw new ConsumerException("consumer already exists: " + consumer.getEmail());
		}
	}

	/**
	 *  Updates a stored consumer and returns its updated variant.
	 *  @param update - an updated variation of Consumer that must be persisted
	 **/
	@Override
	public Consumer saveChanges(Consumer update) {
		Consumer consumer = repository.findByEmail(update.getEmail());
		if(consumer == null){
			throw new ConsumerException("can't find consumer with email " + update.getEmail());
		} else {
			update.setModifiedDate(new Date());
			log.debug("consumer {} changes have been saved", update.getEmail());
			return repository.saveAndFlush(update);
		}
	}
}
