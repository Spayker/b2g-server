package com.spayker.consumer.controller;

import com.spayker.consumer.domain.Account;
import com.spayker.consumer.domain.Training;
import com.spayker.consumer.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id) {
        return new ResponseEntity<>(trainingService.findTrainingById(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Training>> getAllTrainingsByAccountId(@Valid @RequestBody Account account) {
        return new ResponseEntity<>(trainingService.findTrainingsByAccount(account), HttpStatus.OK);
    }

}
