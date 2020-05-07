package com.spayker.consumer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.service.ConsumerService;
import com.sun.security.auth.UserPrincipal;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private ServiceController serviceController;

	@Mock
	private ConsumerService ConsumerService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(serviceController).build();
	}

	@Test
	public void shouldGetConsumerById() throws Exception {

		final Consumer consumer = Consumer.builder()
				.id(Long.parseLong(RandomStringUtils.randomNumeric(10)))
				.date(new Date().toString())
				.build();

		when(this.ConsumerService.findByConsumerId(consumer.getId())).thenReturn(consumer);

		mockMvc.perform(get("/" + consumer.getId()))
				.andExpect(jsonPath("$.id").value(consumer.getId()))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldRegisterNewConsumer() throws Exception {

		final Consumer consumer = Consumer.builder()
				.name("spayker")
				.id(Long.parseLong(RandomStringUtils.randomNumeric(10)))
				.date(new Date().toString())
				.build();

		String json = mapper.writeValueAsString(consumer);
		System.out.println(json);
		mockMvc.perform(post("/")
				.principal(new UserPrincipal("spayker"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldSaveCurrentConsumer() throws Exception {

		final Consumer consumer = Consumer.builder()
				.id(Long.parseLong(RandomStringUtils.randomNumeric(10)))
				.name(RandomStringUtils.randomNumeric(10))
				.date(new Date().toString())
				.build();

		String json = mapper.writeValueAsString(consumer);

		mockMvc.perform(put("/").principal(new UserPrincipal(consumer.getName())).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldFailOnValidationTryingToRegisterNewConsumer() throws Exception {
		final Consumer consumer = null;
		String json = mapper.writeValueAsString(consumer);

		mockMvc.perform(post("/")
				.principal(new UserPrincipal("test"))
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());
	}
}
