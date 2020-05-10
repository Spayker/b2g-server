package com.spayker.consumer.controller;

import com.spayker.consumer.domain.Consumer;
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

@RestController
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;

	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Consumer>> getConsumerByName(@PathVariable String name) {
		return new ResponseEntity<>(consumerService.findConsumerByName(name), HttpStatus.OK);
	}


	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<Consumer> createNewConsumer(@Valid @RequestBody Consumer account) {
		return new ResponseEntity<>(consumerService.create(account), HttpStatus.CREATED);
	}
	
}
