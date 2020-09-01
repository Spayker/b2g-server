package com.spayker.consumer.service;

import com.spayker.consumer.domain.Account;
import com.spayker.consumer.domain.Training;

import java.util.Date;
import java.util.List;

/**
 *  Service layer interface to provided API for work with Training entity.
 **/
public interface TrainingService {

    /**
     *  Looks for stored training by its id.
     *  @param id - string value for search
     *  @return found training
     **/
    Training findTrainingById(Long id);

    /**
     *  Looks for stored training by its device name.
     *  @param name - string value for search
     *  @return found list of found trainings
     **/
    List<Training> findTrainingsByDeviceName(String name);

    /**
     *  Looks for stored training by its device firmware.
     *  @param firmware - string value for search
     *  @return found list of found trainings
     **/
    List<Training> findTrainingsByDeviceFirmware(String firmware);

    /**
     *  Looks for stored training by its createDate.
     *  @param createDate - string value for search
     *  @return found list of found trainings
     **/
    Training findTrainingByCreatedDate(Date createDate);

    /**
     *  Looks for stored training by its time.
     *  @param time - string value for search
     *  @return found list of found trainings
     **/
    List<Training> findTrainingsByTime(int time);

    /**
     *  Looks for stored training by its type.
     *  @param type - string value for search
     *  @return found list of found trainings
     **/
    List<Training> findTrainingsByType(String type);

    /**
     *  Looks for stored training by its calories.
     *  @param calories - string value for search
     *  @return found list of found trainings
     **/
    List<Training> findTrainingsByCalories(int calories);

    /**
     *  Looks for stored training by its account.
     *  @param account - string value for search
     *  @return found list of found trainings
     **/
    List<Training> findTrainingsByAccount(Account account);

    /**
     *  Creates a new Training.
     *  @param training - instance of Training
     *  @return created Training
     **/
    Training create(Training training);

    /**
     *  Updates a stored training and returns its updated variant.
     *  @param update - an updated variation of Training that must be persisted
     **/
    Training saveChanges(Training update);


}
