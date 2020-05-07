package com.spayker.consumer.repository;

import com.spayker.consumer.domain.Consumer;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ConsumerRepositoryTest {

	@Autowired
	private ConsumerRepository repository;

	@Test
	public void shouldFindConsumerById() {
		/*// given
		final Consumer stub = createStubConsumer();

		// when
		Consumer saved = repository.save(stub);

		// then
		Optional<Consumer> found = repository.findById(saved.getId());
		if(found.isPresent()){
			assertEquals(saved.getId(), found.get().getId());
			assertEquals(stub.getDate(), found.get().getDate());
			repository.delete(stub);
		}*/
	}

	private Consumer createStubConsumer() {
		return Consumer.builder()
				.id(Long.parseLong(RandomStringUtils.randomNumeric(10)))
				.date(new Date().toString())
				.build();
	}
}
