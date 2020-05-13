package com.spayker.account.service;

import com.spayker.account.client.AuthServiceClient;
import com.spayker.account.domain.Account;
import com.spayker.account.domain.Training;
import com.spayker.account.exception.AccountException;
import com.spayker.account.exception.TrainingException;
import com.spayker.account.repository.AccountRepository;
import com.spayker.account.repository.TrainingRepository;
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
    private AccountRepository accountRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public Training findTrainingById(Long id) {
        Optional<Training> training = trainingRepository.findById(id);
        return training.orElse(null);
    }

    @Override
    public List<Training> findTrainingsByDeviceName(String name) {
        return null;
    }

    @Override
    public List<Training> findTrainingsByDeviceFirmware(String firmware) {
        return null;
    }

    @Override
    public Training findTrainingByCreatedDate(Date createDate) {
        return null;
    }

    @Override
    public List<Training> findTrainingsByTime(int time) {
        return null;
    }

    @Override
    public List<Training> findTrainingsByType(String type) {
        return null;
    }

    @Override
    public List<Training> findTrainingsByCalories(int calories) {
        return null;
    }

    @Override
    public List<Training> findTrainingsByAccount(Account account) {
        return null;
    }

    @Override
    public Training create(Training training) {
        if(training.getCreatedDate() == null){
            training.setCreatedDate(new Date());
            Training savedTraining = trainingRepository.saveAndFlush(training);
            log.info("new training has been created: " + savedTraining.getId());
            return savedTraining;
        } else {
            throw new TrainingException("training already exists for date: " + training.getId());
        }
    }

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
