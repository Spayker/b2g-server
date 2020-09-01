package com.spayker.account.controller;

import com.spayker.account.domain.Training;
import com.spayker.account.service.TrainingService;
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

/**
 *  A controller layer for trainings with all needed (for now) methods.
 *  Last ones are called when request handling starts happening.
 *  Requests come on correspond url that linked by RequestMapping annotation with an appropriate declared method below.
 **/
@RestController
@RequestMapping("/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    /**
     *  Returns an Training instance found by its provided id.
     *  @param id Long value to make search by id possible
     *  @return found Training entity
     **/
    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id) {
        return new ResponseEntity<>(trainingService.findTrainingById(id), HttpStatus.OK);
    }

    /**
     *  Creates training by provided training instance.
     *  @param training - data container related to new training entity to be created
     *  @return ResponseEntity with created Training (toDo: make return just id of created training)
     **/
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<Training> createTraining(@Valid @RequestBody Training training) {
        return new ResponseEntity<>(trainingService.create(training), HttpStatus.CREATED);
    }

}
