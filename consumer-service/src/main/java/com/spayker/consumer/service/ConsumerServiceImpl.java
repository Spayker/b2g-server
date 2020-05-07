package com.spayker.consumer.service;

import com.spayker.consumer.client.AccountServiceClient;
import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.exception.ConsumerException;
import com.spayker.consumer.repository.ConsumerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountServiceClient accountClient;

	@Autowired
	private ConsumerRepository repository;

	@Override
	public Consumer findByConsumerId(Long consumerId) {
		return repository.findById(consumerId).orElse(null);
	}

	@Override
	public Consumer create(Consumer consumer) {
		Consumer existing = repository.findById(consumer.getId()).orElse(null);
		if (existing == null){

			String username = consumer.getName();
			if (username == null){
				throw new ConsumerException("username is null, can not attach consumer: " + consumer.getId());
			}

			if (username.isEmpty() || username.isBlank()){
				throw new ConsumerException("username is empty, can not attach consumer: " + consumer.getId());
			}

			String account = accountClient.getAccountByName(username);
			if(account != null){
				repository.save(consumer);
				log.info("new consumer has been created: " + consumer.getId());
				return consumer;
			} else {
				throw new ConsumerException("Account with name: " + username + " has not been registered yet");
			}
		} else {
			throw new ConsumerException("consumer with id: " + consumer.getId() + " already exists");
		}
	}

	@Override
	public void saveChanges(Consumer consumer) {

		Long consumerId = consumer.getId();

		Consumer storedConsumer = repository.findById(consumer.getId())
				.orElseThrow(() -> new IllegalArgumentException("Consumer with id " + consumerId + " does not exist"));

		storedConsumer.setName(consumer.getName());
		storedConsumer.setDate(consumer.getDate());
		repository.save(storedConsumer);

		log.debug("consumer {} changes has been saved", consumerId);
	}
}
