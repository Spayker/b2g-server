package com.spayker.consumer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.service.ConsumerService;
import com.spayker.consumer.util.factory.ConsumerFactory;
import com.sun.security.auth.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ConsumerControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private ConsumerController accountController;

	@Mock
	private ConsumerService accountService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}

	private static Stream<Arguments> provideCommonConsumers() {
		return Stream.of(
				Arguments.of(ConsumerFactory.createConsumer("name1", "name1@gmail.com", new Date(), null)),
				Arguments.of(ConsumerFactory.createConsumer("name2", "name2@gmail.com", new Date(), null))
		);
	}

	@ParameterizedTest
	@MethodSource("provideCommonConsumers")
	@DisplayName("Looks for account by name")
	public void shouldGetConsumerByName(Consumer account) throws Exception {
		when(accountService.findConsumerByName(account.getName())).thenReturn(List.of(account));

		mockMvc.perform(get("/" + account.getName()))
				.andExpect(jsonPath("$[0].id").value(account.getId()))
				.andExpect(jsonPath("$[0].name").value(account.getName()))
				.andExpect(jsonPath("$[0].email").value(account.getEmail()))
				.andExpect(jsonPath("$[0].createdDate").value(account.getCreatedDate()))
				.andExpect(jsonPath("$[0].modifiedDate").value(account.getModifiedDate()))
				.andExpect(status().isOk());
	}


	@ParameterizedTest
	@MethodSource("provideCommonConsumers")
	@DisplayName("Creates a new account and returns created account")
	public void shouldRegisterNewConsumer(Consumer account) throws Exception {
		String json = mapper.writeValueAsString(account);

		when(accountService.create(any())).thenReturn(account);
		mockMvc.perform(post("/")
				.principal(new UserPrincipal("test"))
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(jsonPath("$.id").value(account.getId()))
				.andExpect(jsonPath("$.name").value(account.getName()))
				.andExpect(jsonPath("$.email").value(account.getEmail()))
				.andExpect(jsonPath("$.createdDate").value(account.getCreatedDate()))
				.andExpect(jsonPath("$.modifiedDate").value(account.getModifiedDate()))
				.andExpect(status().isCreated());
	}
}
