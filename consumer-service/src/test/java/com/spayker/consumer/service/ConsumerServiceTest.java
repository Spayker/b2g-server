package com.spayker.consumer.service;

import com.spayker.consumer.client.AccountServiceClient;
import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.repository.ConsumerRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConsumerServiceTest {

	@InjectMocks
	private ConsumerServiceImpl consumerService;

	@Mock
	private ConsumerRepository repository;

	@Mock
	private AccountServiceClient accountServiceClient;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	public void shouldFindConsumerById() {
		/*final Consumer consumer = Consumer.builder()
				.id(Long.parseLong(RandomStringUtils.randomNumeric(10)))
				.date(new Date().toString())
				.build();

		when(consumerService.findByConsumerId(consumer.getId())).thenReturn(consumer);
		Consumer found = consumerService.findByConsumerId(consumer.getId());
		assertEquals(consumer, found);*/
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailWhenNameIsEmpty() {
		consumerService.findByConsumerId(Long.parseLong(""));
	}

	@Test
	public void shouldCreateConsumerWithGivenUser() {
		final int expectedCallTimes = 1;
		Consumer consumer = Consumer.builder()
				.name(RandomStringUtils.randomAlphabetic(6))
				.id(Long.parseLong(RandomStringUtils.randomNumeric(10)))
				.createdDate(new Date())
				.build();

		when(accountServiceClient.getAccountByName(anyString())).thenReturn("account");
		Consumer storedConsumer = consumerService.create(consumer);

		assertEquals(storedConsumer.getId(), consumer.getId());
		assertNotNull(storedConsumer.getCreatedDate());
		verify(repository, times(expectedCallTimes)).save(consumer);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailWhenNoConsumersExistedWithGivenName() {
		final Consumer update = getStubConsumer();

		when(consumerService.findByConsumerId(12345L)).thenReturn(null);
		consumerService.saveChanges(update);
	}

	private Consumer getStubConsumer() {
		return Consumer.builder()
				.id(Long.parseLong(RandomStringUtils.randomNumeric(10)))
				.createdDate(new Date())
				.build();
	}
}
