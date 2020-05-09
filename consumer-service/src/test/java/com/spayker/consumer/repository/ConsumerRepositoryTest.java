package com.spayker.consumer.repository;

import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.util.factory.ConsumerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ConsumerRepositoryTest {

	@Autowired
	private ConsumerRepository repository;

	@AfterEach
	public void clearRecordsInDb(){
		repository.deleteAll();
	}

	private static Stream<Arguments> provideCommonConsumers() {
		return Stream.of(
				Arguments.of(ConsumerFactory.createConsumer("name1", "name1@gmail.com", new Date(), null)),
				Arguments.of(ConsumerFactory.createConsumer("name2", "name2@gmail.com", new Date(), null))
		);
	}

	@ParameterizedTest
	@MethodSource("provideCommonConsumers")
	@DisplayName("Saves consumers by repository API")
	public void shouldSaveConsumer(Consumer consumer){
		// when
		Consumer savedConsumer = repository.save(consumer);

		// then
		assertNotNull(savedConsumer);
		assertEquals(consumer.getCreatedDate(), savedConsumer.getCreatedDate());
		assertEquals(consumer.getModifiedDate(), savedConsumer.getModifiedDate());
		assertEquals(consumer.getName(), savedConsumer.getName());
		assertEquals(consumer.getEmail(), savedConsumer.getEmail());
	}

	@ParameterizedTest
	@MethodSource("provideCommonConsumers")
	@DisplayName("Returns saved consumers by their ids")
	public void shouldFindConsumerById(Consumer consumer) {
		// when
		Consumer savedConsumer = repository.save(consumer);
		Optional<Consumer> foundEntity = repository.findById(consumer.getId());

		// then
		assertNotNull(savedConsumer);
		assertTrue(foundEntity.isPresent());
		Consumer foundConsumer = foundEntity.get();
		assertEquals(consumer.getCreatedDate(), foundConsumer.getCreatedDate());
		assertEquals(consumer.getModifiedDate(), foundConsumer.getModifiedDate());
		assertEquals(consumer.getName(), foundConsumer.getName());
		assertEquals(consumer.getEmail(), foundConsumer.getEmail());
	}

	private static Stream<Arguments> provideSameNameConsumerList() {
		List<Consumer> consumerList = List.of(
			ConsumerFactory.createConsumer("name", "name1@gmail.com", new Date(), null),
			ConsumerFactory.createConsumer("name", "name2@gmail.com", new Date(), null));
		return Stream.of(Arguments.of(consumerList));
	}


	@ParameterizedTest
	@MethodSource("provideSameNameConsumerList")
	@DisplayName("Returns saved consumers by their names")
	public void shouldFindConsumersByName(List<Consumer> consumers) {
		// given
		final String name = "name";

		// when
		consumers.forEach(repository::save);
		List<Consumer> foundConsumers = repository.findByName(name);

		// then
		assertNotNull(foundConsumers);
		assertEquals(consumers.size(), foundConsumers.size());

		for(int i = 0; i != consumers.size(); i++){
			assertEquals(consumers.get(i).getName(), 		foundConsumers.get(i).getName());
			assertEquals(consumers.get(i).getEmail(), 		foundConsumers.get(i).getEmail());
			assertEquals(consumers.get(i).getCreatedDate(),  foundConsumers.get(i).getCreatedDate());
			assertEquals(consumers.get(i).getModifiedDate(), foundConsumers.get(i).getModifiedDate());
		}
	}

	@ParameterizedTest
	@MethodSource("provideCommonConsumers")
	@DisplayName("Returns saved consumers by their email")
	public void shouldFindConsumerByEmail(Consumer consumer) {
		// given
		// when
		repository.save(consumer);
		Consumer foundConsumer = repository.findByEmail(consumer.getEmail());

		// then
		assertEquals(consumer.getCreatedDate(), 	foundConsumer.getCreatedDate());
		assertEquals(consumer.getModifiedDate(), foundConsumer.getModifiedDate());
		assertEquals(consumer.getName(), 		foundConsumer.getName());
		assertEquals(consumer.getEmail(), 		foundConsumer.getEmail());
	}

	private static Stream<Arguments> provideSameCreateDateConsumerList() {
		final Date createdDate = new Date();
		List<Consumer> consumerList = List.of(
			ConsumerFactory.createConsumer("name1", "name@gmail.com", createdDate, null),
			ConsumerFactory.createConsumer("name2", "name@gmail.com", createdDate, null));
		return Stream.of(Arguments.of(consumerList));
	}

	@ParameterizedTest
	@MethodSource("provideSameCreateDateConsumerList")
	@DisplayName("Returns saved consumers by their created date")
	public void shouldFindConsumersByCreatedDate(List<Consumer> consumers) {
		// given
		final Date expectedCreatedDate = consumers.get(0).getCreatedDate();

		// when
		consumers.forEach(repository::save);
		List<Consumer> foundConsumers = repository.findByCreatedDate(expectedCreatedDate);

		// then
		assertNotNull(foundConsumers);
		assertEquals(consumers.size(), foundConsumers.size());

		for(int i = 0; i != consumers.size(); i++){
			assertEquals(consumers.get(i).getName(), 		foundConsumers.get(i).getName());
			assertEquals(consumers.get(i).getEmail(), 		foundConsumers.get(i).getEmail());
			assertEquals(consumers.get(i).getCreatedDate(),  foundConsumers.get(i).getCreatedDate());
			assertEquals(consumers.get(i).getModifiedDate(), foundConsumers.get(i).getModifiedDate());
		}
	}

	private static Stream<Arguments> provideSameModifiedDateConsumerList() {
		final Date modifiedDate = new Date();
		List<Consumer> consumerList = List.of(
			ConsumerFactory.createConsumer("name1", "name@gmail.com", null, modifiedDate),
			ConsumerFactory.createConsumer("name2", "name@gmail.com", null, modifiedDate));
		return Stream.of(Arguments.of(consumerList));
	}

	@ParameterizedTest
	@MethodSource("provideSameModifiedDateConsumerList")
	@DisplayName("Returns saved consumers by their modified date")
	public void shouldFindConsumerByModifiedDate(List<Consumer> consumers) {
		// given
		final Date expectedModifiedDate = consumers.get(0).getModifiedDate();

		// when
		consumers.forEach(repository::save);
		List<Consumer> foundConsumers = repository.findByModifiedDate(expectedModifiedDate);

		// then
		assertNotNull(foundConsumers);
		assertEquals(consumers.size(), foundConsumers.size());

		for(int i = 0; i != consumers.size(); i++){
			assertEquals(consumers.get(i).getName(), 		foundConsumers.get(i).getName());
			assertEquals(consumers.get(i).getEmail(), 		foundConsumers.get(i).getEmail());
			assertEquals(consumers.get(i).getCreatedDate(),  foundConsumers.get(i).getCreatedDate());
			assertEquals(consumers.get(i).getModifiedDate(), foundConsumers.get(i).getModifiedDate());
		}
	}
}
