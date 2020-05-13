package com.spayker.account.service;

import com.spayker.account.domain.Account;
import com.spayker.account.domain.Training;

import java.util.Date;
import java.util.List;

public interface TrainingService {

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