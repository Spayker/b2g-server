package com.spayker.consumer.controller;

import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.domain.User;
import com.spayker.consumer.dto.ConsumerDto;
import com.spayker.consumer.dto.mapper.ConsumerMapper;
import com.spayker.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 *  A controller layer with all needed (for now) methods.
 *  Last ones are called when request handling starts happening.
 *  Requests come on correspond url that linked by RequestMapping annotation with an appropriate declared method below.
 **/
@RestController
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private ConsumerMapper consumerMapper;

	/**
	 *  Returns an Consumer instance found by name.
	 *  @param name Strign value to make search by name possible
	 *  @return found Consumer entity
	 **/
	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Consumer>> getConsumerByName(@PathVariable String name) {
		return new ResponseEntity<>(consumerService.findConsumerByName(name), HttpStatus.OK);
	}

	/**
	 *  Creates consumer by provided User instance.
	 *  @param consumerDto - data container related to new consumer entity to be created
	 *  @return ResponseEntity with created Consumer (toDo: make return just id of created consumer)
	 **/
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<Consumer> createNewConsumer(@Valid @RequestBody ConsumerDto consumerDto) {
		Consumer consumer = consumerMapper.consumerDtoToConsumer(consumerDto);
		User user = consumerMapper.consumerDtoToUser(consumerDto);
		return new ResponseEntity<>(consumerService.create(consumer, user), HttpStatus.CREATED);
	}
	
}
