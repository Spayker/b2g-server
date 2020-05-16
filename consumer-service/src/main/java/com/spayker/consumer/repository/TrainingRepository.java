package com.spayker.consumer.repository;

import com.spayker.consumer.domain.Account;
import com.spayker.consumer.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByDeviceName(String name);

    List<Training> findByDeviceFirmware(String firmware);

    Training findByCreatedDate(Date createDate);

    List<Training> findByTime(int time);

    List<Training> findByType(String type);

    List<Training> findByCalories(int calories);

    List<Training> findByAccount(Account account);

}
