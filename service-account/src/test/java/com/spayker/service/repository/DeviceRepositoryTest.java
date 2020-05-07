package com.spayker.service.repository;

import com.spayker.service.domain.Device;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class DeviceRepositoryTest {

	@Autowired
	private DeviceRepository repository;

	@Test
	public void shouldFindDeviceById() {
		// given
		final Device stub = createStubDevice();

		// when
		Device saved = repository.save(stub);

		// then
		Device found = repository.findByDeviceId(saved.getDeviceId());
		assertEquals(saved.getDeviceId(), found.getDeviceId());
		assertEquals(stub.getHrData(), found.getHrData());
		assertEquals(stub.getDate(), found.getDate());
		repository.delete(stub);
	}

	private Device createStubDevice() {
		return Device.builder()
				.deviceId(Long.parseLong(RandomStringUtils.randomNumeric(10)))
				.date(new Date().toString())
				.hrData(RandomStringUtils.randomNumeric(2))
				.build();
	}
}
