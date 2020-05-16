package com.spayker.consumer.service;

import com.spayker.consumer.domain.Account;
import com.spayker.consumer.domain.Training;

import java.util.Date;
import java.util.List;

public interface TrainingService {

    Training findTrainingById(Long id);

    List<Training> findTrainingsByDeviceName(String name);

    List<Training> findTrainingsByDeviceFirmware(String firmware);

    Training findTrainingByCreatedDate(Date createDate);

    List<Training> findTrainingsByTime(int time);

    List<Training> findTrainingsByType(String type);

    List<Training> findTrainingsByCalories(int calories);

    List<Training> findTrainingsByAccount(Account account);

    Training create(Training training);

    Training saveChanges(Training update);


}
