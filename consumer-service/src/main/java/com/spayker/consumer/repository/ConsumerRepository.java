package com.spayker.consumer.repository;

import com.spayker.consumer.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    List<Consumer> findByName(String name);

    Consumer findByEmail(String email);

    List<Consumer> findByCreatedDate(Date createDate);

    List<Consumer> findByModifiedDate(Date modifiedDate);


}
