package com.spayker.consumer.service;

import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.repository.ConsumerRepository;
import com.spayker.consumer.util.factory.ConsumerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConsumerServiceTest {

	@InjectMocks
	private ConsumerServiceImpl consumerService;

	@Mock
	private ConsumerRepository repository;

	@BeforeEach
	public void setup() { initMocks(this); }

	@AfterEach
	public void clearDb() { repository.deleteAll(); }

	private static Stream<Arguments> provideCommonConsumers() {
		return Stream.of(
			Arguments.of(ConsumerFactory.createConsumer("name1", "name1@gmail.com", new Date(), null)),
			Arguments.of(ConsumerFactory.createConsumer("name2", "name2@gmail.com", new Date(), null))
		);
	}

	@ParameterizedTest
	@MethodSource("provideCommonConsumers")
	@DisplayName("Saves consumer by given consumer data")
	public void shouldCreateConsumer(Consumer consumer) {
		// when
		Consumer savedConsumer = consumerService.create(consumer);

		// then
		assertNotNull(savedConsumer);
		assertEquals(consumer.getName(), savedConsumer.getName());

		assertNotNull(savedConsumer.getCreatedDate());
		assertNull(savedConsumer.getModifiedDate());

		verify(repository, times(1)).saveAndFlush(consumer);
	}

	@ParameterizedTest
	@MethodSource("provideCommonConsumers")
	@DisplayName("Updates consumer by received changes")
	public void shouldSaveChangesWhenUpdatedConsumerGiven(Consumer consumer) {
		// given
		final String updatePrefix = "_updated";

		// when
		Consumer createdConsumer = consumerService.create(consumer);
		when(repository.findByEmail(consumer.getEmail())).thenReturn(createdConsumer);
		when(repository.saveAndFlush(consumer)).thenReturn(createdConsumer);

		createdConsumer.setName(createdConsumer.getName() + updatePrefix);

		Consumer updatedConsumer = consumerService.saveChanges(createdConsumer);

		// then
		assertNotNull(updatedConsumer);
		assertEquals(updatedConsumer.getName(), 	 createdConsumer.getName());

		verify(repository, times(2)).saveAndFlush(consumer);
	}

	@ParameterizedTest
	@MethodSource("provideCommonConsumers")
	@DisplayName("Looks for consumer by name")
	public void shouldFindConsumerByName(Consumer consumer) {
		// given
		final int expectedFoundConsumers = 1;

		// when
		Consumer savedConsumer = consumerService.create(consumer);
		when(repository.findByName(consumer.getName())).thenReturn(List.of(savedConsumer));
		List<Consumer> foundConsumers = consumerService.findConsumerByName(consumer.getName());

		assertEquals(expectedFoundConsumers, foundConsumers.size());
		assertEquals(foundConsumers.get(0).getId(), savedConsumer.getId());
		assertEquals(foundConsumers.get(0).getName(), savedConsumer.getName());
		assertEquals(foundConsumers.get(0).getEmail(), savedConsumer.getEmail());
		assertEquals(foundConsumers.get(0).getCreatedDate(), savedConsumer.getCreatedDate());
		assertEquals(foundConsumers.get(0).getModifiedDate(), savedConsumer.getModifiedDate());
	}

	private static Stream<Arguments> provideSameNameConsumerList() {
		final String name = "name";
		List<Consumer> consumerList = List.of(
				ConsumerFactory.createConsumer(0L, name, "name1@gmail.com", new Date(), null),
				ConsumerFactory.createConsumer(1L, name, "name2@gmail.com", new Date(), null));
		return Stream.of(Arguments.of(consumerList));
	}

	@ParameterizedTest
	@MethodSource("provideSameNameConsumerList")
	@DisplayName("Looks for consumers by name")
	public void shouldFindConsumerByName(List<Consumer> consumers) {
		// given
		final int expectedFoundConsumers = 2;

		// when
		when(repository.findByName(consumers.get(0).getName())).thenReturn(consumers);

		List<Consumer> foundConsumers = consumerService.findConsumerByName(consumers.get(0).getName());

		assertEquals(expectedFoundConsumers, foundConsumers.size());
		assertEquals(foundConsumers.get(0).getId(),   consumers.get(0).getId());
		assertEquals(foundConsumers.get(0).getName(), consumers.get(0).getName());
		assertEquals(foundConsumers.get(1).getId(), 	 consumers.get(1).getId());
		assertEquals(foundConsumers.get(1).getName(), consumers.get(1).getName());
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when looks for consumer by empty string name")
	public void shouldFailWhenFindConsumerByNameWithEmptyNameValue() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> consumerService.findConsumerByName(""));
	}

	@ParameterizedTest
	@MethodSource("provideCommonConsumers")
	@DisplayName("Looks for consumer by email")
	public void shouldFindConsumerByEmail(Consumer consumer) {
		// given
		Consumer savedConsumer = consumerService.create(consumer);

		// when
		when(repository.findByEmail(consumer.getEmail())).thenReturn(savedConsumer);
		Consumer foundConsumer = consumerService.findConsumerByEmail(consumer.getEmail());

		assertNotNull(foundConsumer);
		assertEquals(foundConsumer.getId(), savedConsumer.getId());
		assertEquals(foundConsumer.getName(), savedConsumer.getName());
		assertEquals(foundConsumer.getEmail(), savedConsumer.getEmail());
		assertEquals(foundConsumer.getCreatedDate(), savedConsumer.getCreatedDate());
		assertEquals(foundConsumer.getModifiedDate(), savedConsumer.getModifiedDate());
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when looks for consumer by empty string email")
	public void shouldFailWhenFindConsumerByEmailWithEmptyEmailValue() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> consumerService.findConsumerByEmail(""));
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when looks for consumer by blank string email")
	public void shouldFailWhenFindConsumerByEmailWithBlankEmailValue() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> consumerService.findConsumerByEmail("     "));
	}

	@ParameterizedTest
	@MethodSource("provideCommonConsumers")
	@DisplayName("Looks for consumer by created date")
	public void shouldFindConsumerByCreatedDate(Consumer consumer) {
		// given
		final int expectedFoundConsumers = 1;
		Consumer savedConsumer = consumerService.create(consumer);

		// when
		when(repository.findByCreatedDate(consumer.getCreatedDate())).thenReturn(List.of(savedConsumer));
		List<Consumer> foundConsumers = consumerService.findConsumerByCreatedDate(consumer.getCreatedDate());

		assertNotNull(foundConsumers);
		assertEquals(expectedFoundConsumers, foundConsumers.size());

		assertEquals(foundConsumers.get(0).getId(), savedConsumer.getId());
		assertEquals(foundConsumers.get(0).getName(), savedConsumer.getName());
		assertEquals(foundConsumers.get(0).getEmail(), savedConsumer.getEmail());
		assertEquals(foundConsumers.get(0).getCreatedDate(), savedConsumer.getCreatedDate());
		assertEquals(foundConsumers.get(0).getModifiedDate(), savedConsumer.getModifiedDate());
	}

	@ParameterizedTest
	@MethodSource("provideCommonConsumers")
	@DisplayName("Looks for consumer by modified date")
	public void shouldFindConsumerByModifiedDate(Consumer consumer) {
		// given
		final int expectedFoundConsumers = 1;
		Consumer savedConsumer = consumerService.create(consumer);

		// when
		when(repository.findByModifiedDate(consumer.getModifiedDate())).thenReturn(List.of(savedConsumer));
		List<Consumer> foundConsumers = consumerService.findConsumerByModifiedDate(consumer.getModifiedDate());

		assertNotNull(foundConsumers);
		assertEquals(expectedFoundConsumers, foundConsumers.size());

		assertEquals(foundConsumers.get(0).getId(), savedConsumer.getId());
		assertEquals(foundConsumers.get(0).getName(), savedConsumer.getName());
		assertEquals(foundConsumers.get(0).getEmail(), savedConsumer.getEmail());
		assertEquals(foundConsumers.get(0).getCreatedDate(), savedConsumer.getCreatedDate());
		assertEquals(foundConsumers.get(0).getModifiedDate(), savedConsumer.getModifiedDate());
	}

}
