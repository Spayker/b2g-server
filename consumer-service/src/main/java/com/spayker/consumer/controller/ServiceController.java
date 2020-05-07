package com.spayker.consumer.controller;

import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
public class ServiceController {

	@Autowired
	private ConsumerService ConsumerService;

	@RequestMapping(path = "/{deviceId}", method = RequestMethod.GET)
	public Consumer getDeviceById(@PathVariable String deviceId) {
		return ConsumerService.findByConsumerId(Long.valueOf(deviceId));
	}

	@RequestMapping(path = "/", method = RequestMethod.PUT)
	public void updateDeviceData(@Valid @RequestBody Consumer consumer) {
		this.ConsumerService.saveChanges(consumer);
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public Consumer createNewDevice(@Valid @RequestBody Consumer consumer) {
		return this.ConsumerService.create(consumer);
	}
}
