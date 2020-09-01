package com.spayker.consumer.service;

import com.spayker.consumer.domain.Account;
import com.spayker.consumer.domain.Training;
import com.spayker.consumer.exception.TrainingException;
import com.spayker.consumer.repository.TrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TrainingRepository trainingRepository;

    /**
     *  Looks for stored training by its id.
     *  @param id - string value for search
     *  @return found training
     **/
    @Override
    public Training findTrainingById(Long id) {
        Optional<Training> training = trainingRepository.findById(id);
        return training.orElse(null);
    }

    /**
     *  Looks for stored training by its device name.
     *  @param name - string value for search
     *  @return found list of found trainings
     **/
    @Override
    public List<Training> findTrainingsByDeviceName(String name) {
        return trainingRepository.findByDeviceName(name);
    }

    /**
     *  Looks for stored training by its device firmware.
     *  @param firmware - string value for search
     *  @return found list of found trainings
     **/
    @Override
    public List<Training> findTrainingsByDeviceFirmware(String firmware) {
        return trainingRepository.findByDeviceFirmware(firmware);
    }

    /**
     *  Looks for stored training by its createDate.
     *  @param createDate - string value for search
     *  @return found list of found trainings
     **/
    @Override
    public Training findTrainingByCreatedDate(Date createDate) {
        return trainingRepository.findByCreatedDate(createDate);
    }

    /**
     *  Looks for stored training by its time.
     *  @param time - string value for search
     *  @return found list of found trainings
     **/
    @Override
    public List<Training> findTrainingsByTime(int time) {
        return trainingRepository.findByTime(time);
    }

    /**
     *  Looks for stored training by its type.
     *  @param type - string value for search
     *  @return found list of found trainings
     **/
    @Override
    public List<Training> findTrainingsByType(String type) {
        return trainingRepository.findByType(type);
    }

    /**
     *  Looks for stored training by its calories.
     *  @param calories - string value for search
     *  @return found list of found trainings
     **/
    @Override
    public List<Training> findTrainingsByCalories(int calories) {
        return trainingRepository.findByCalories(calories);
    }

    /**
     *  Looks for stored training by its account.
     *  @param account - string value for search
     *  @return found list of found trainings
     **/
    @Override
    public List<Training> findTrainingsByAccount(Account account) {
        return trainingRepository.findByAccount(account);
    }

    /**
     *  Creates a new Training.
     *  @param training - instance of Training
     *  @return created Training
     **/
    @Override
    public Training create(Training training) {
        training.setCreatedDate(new Date());
        Training savedTraining = trainingRepository.saveAndFlush(training);
        log.info("new training has been created: " + savedTraining.getId());
        return savedTraining;
    }

    /**
     *  Updates a stored training and returns its updated variant.
     *  @param update - an updated variation of Training that must be persisted
     **/
    @Override
    public Training saveChanges(Training update) {
        if(trainingRepository.existsById(update.getId())){
            log.debug("account {} changes have been saved", update.getId());
            return trainingRepository.saveAndFlush(update);
        } else {
            throw new TrainingException("can't find training with id " + update.getId());
        }
    }

}
