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

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ConsumerRepositoryTest {

	@Autowired
	private ConsumerRepository repository;

	// success creation
	// failed creation
	// success search
	// failed search
	// list search to prove it can return more than 1 element

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
				.createdDate(new Date())
				.build();
	}
}
