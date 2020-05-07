package com.spayker.service.controller;

import com.spayker.service.domain.Device;
import com.spayker.service.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@RequestMapping(path = "/{deviceId}", method = RequestMethod.GET)
	public Device getDeviceById(@PathVariable String deviceId) {
		return deviceService.findByDeviceId(Long.valueOf(deviceId));
	}

	@RequestMapping(path = "/", method = RequestMethod.PUT)
	public void updateDeviceData(@Valid @RequestBody Device device) {
		deviceService.saveChanges(device);
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public Device createNewDevice(@Valid @RequestBody Device device) {
		return deviceService.create(device);
	}
}
